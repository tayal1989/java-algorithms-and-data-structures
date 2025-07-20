package com.learning.collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>HashMap Questions - Modern Java 8+ Implementation</h1>
 * This class demonstrates various employee data analysis operations using Java 8+ features
 * including Streams, Lambda expressions, Method references, and Collectors.
 * 
 * @author Vishal Agarwal
 * @version 2.0 - Modernized with Java 8+ features
 * @date Updated for modern Java practices
 */
public class HashMapQuestions {
    
    private static final String SEPARATOR = "=====================================";
    
    public static void main(String[] args) {
        List<EmployeeHM> employeeList = createEmployeeList();
        
        // Demonstrate all operations with modern Java 8+ approach
        printResults("Male and Female Employee Count", () -> getCountOfMaleAndFemaleEmployees(employeeList));
        printResults("Department Names", () -> printDepartmentNames(employeeList));
        printResults("Average Age by Gender", () -> getAvgAgeOfMaleAndFemaleEmp(employeeList));
        printResults("Employees Joining After 2015", () -> employeeJoiningAfter(employeeList, 2015));
        printResults("Oldest Employee", () -> findOldestEmployee(employeeList));
        printResults("Average and Total Salary", () -> averageAndTotalSalary(employeeList));
        printResults("Highest Experienced Employee", () -> findHighestExperiencedEmployee(employeeList));
        printResults("Employee Count by Department", () -> getNumberOfEmployeeFromEachDepartment(employeeList));
        printResults("Total Salary by Department", () -> getTotalSalaryForEachDepartment(employeeList));
    }
    
    /**
     * Helper method to create employee list - extracted for better readability
     */
    private static List<EmployeeHM> createEmployeeList() {
        return Arrays.asList(
            new EmployeeHM(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0),
            new EmployeeHM(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0),
            new EmployeeHM(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0),
            new EmployeeHM(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0),
            new EmployeeHM(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0),
            new EmployeeHM(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0),
            new EmployeeHM(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0),
            new EmployeeHM(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0),
            new EmployeeHM(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0),
            new EmployeeHM(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5),
            new EmployeeHM(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0),
            new EmployeeHM(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0),
            new EmployeeHM(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0),
            new EmployeeHM(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5),
            new EmployeeHM(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0),
            new EmployeeHM(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0),
            new EmployeeHM(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0)
        );
    }
    
    /**
     * Helper method to format output consistently
     */
    private static void printResults(String title, Runnable operation) {
        System.out.println(SEPARATOR);
        System.out.println(title);
        System.out.println(SEPARATOR);
        operation.run();
        System.out.println(SEPARATOR + "\n");
    }
    
    /**
     * Count male and female employees using Streams and Collectors
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void getCountOfMaleAndFemaleEmployees(List<EmployeeHM> employees) {
        Map<String, Long> genderCount = employees.stream()
            .collect(Collectors.groupingBy(
                EmployeeHM::getGender, 
                Collectors.counting()
            ));
        
        System.out.printf("Male Employees: %d, Female Employees: %d%n", 
            genderCount.getOrDefault("Male", 0L), 
            genderCount.getOrDefault("Female", 0L));
    }
    
    /**
     * Get unique department names using Stream operations
     * Time Complexity: O(n), Space Complexity: O(k) where k is number of unique departments
     */
    public static void printDepartmentNames(List<EmployeeHM> employees) {
        Set<String> departments = employees.stream()
            .map(EmployeeHM::getDepartment)
            .collect(Collectors.toSet());
        
        System.out.println("Departments: " + departments);
    }
    
    /**
     * Calculate average age by gender using Streams and Collectors
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void getAvgAgeOfMaleAndFemaleEmp(List<EmployeeHM> employees) {
        Map<String, Double> avgAgeByGender = employees.stream()
            .collect(Collectors.groupingBy(
                EmployeeHM::getGender,
                Collectors.averagingInt(EmployeeHM::getAge)
            ));
        
        avgAgeByGender.forEach((gender, avgAge) -> 
            System.out.printf("Average age of %s employees: %.2f%n", gender, avgAge));
    }
    
    /**
     * Find employees joining after specified year using Stream filter
     * Time Complexity: O(n), Space Complexity: O(k) where k is number of matching employees
     */
    public static void employeeJoiningAfter(List<EmployeeHM> employees, int year) {
        List<String> employeeNames = employees.stream()
            .filter(emp -> emp.getYearOfJoining() > year)
            .map(EmployeeHM::getName)
            .collect(Collectors.toList());
        
        System.out.println("Employees joining after " + year + ":");
        employeeNames.forEach(System.out::println);
    }
    
    /**
     * Find oldest employee using Stream max operation with Optional handling
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void findOldestEmployee(List<EmployeeHM> employees) {
        Optional<EmployeeHM> oldestEmployee = employees.stream()
            .max(Comparator.comparingInt(EmployeeHM::getAge));
        
        oldestEmployee.ifPresentOrElse(
            emp -> System.out.printf("Oldest Employee: %s, Age: %d, Department: %s%n", 
                emp.getName(), emp.getAge(), emp.getDepartment()),
            () -> System.out.println("No employees found")
        );
    }
    
    /**
     * Calculate average and total salary using Stream statistics
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void averageAndTotalSalary(List<EmployeeHM> employees) {
        DoubleSummaryStatistics salaryStats = employees.stream()
            .mapToDouble(EmployeeHM::getSalary)
            .summaryStatistics();
        
        System.out.printf("Total Salary: %.2f%n", salaryStats.getSum());
        System.out.printf("Average Salary: %.2f%n", salaryStats.getAverage());
        System.out.printf("Min Salary: %.2f%n", salaryStats.getMin());
        System.out.printf("Max Salary: %.2f%n", salaryStats.getMax());
    }
    
    /**
     * Find employee with highest experience (earliest joining year)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void findHighestExperiencedEmployee(List<EmployeeHM> employees) {
        Optional<EmployeeHM> mostExperienced = employees.stream()
            .min(Comparator.comparingInt(EmployeeHM::getYearOfJoining));
        
        mostExperienced.ifPresentOrElse(
            emp -> {
                int experience = 2024 - emp.getYearOfJoining(); // Current year - joining year
                System.out.printf("Most Experienced Employee: %s, Experience: %d years, Department: %s%n", 
                    emp.getName(), experience, emp.getDepartment());
            },
            () -> System.out.println("No employees found")
        );
    }
    
    /**
     * Count employees in each department using grouping collector
     * Time Complexity: O(n), Space Complexity: O(k) where k is number of departments
     */
    public static void getNumberOfEmployeeFromEachDepartment(List<EmployeeHM> employees) {
        Map<String, Long> employeeCountByDept = employees.stream()
            .collect(Collectors.groupingBy(
                EmployeeHM::getDepartment,
                Collectors.counting()
            ));
        
        System.out.println("Employee count by department:");
        employeeCountByDept.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> System.out.printf("%s: %d employees%n", 
                entry.getKey(), entry.getValue()));
    }
    
    /**
     * Calculate total salary for each department
     * Time Complexity: O(n), Space Complexity: O(k) where k is number of departments
     */
    public static void getTotalSalaryForEachDepartment(List<EmployeeHM> employees) {
        Map<String, Double> totalSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                EmployeeHM::getDepartment,
                Collectors.summingDouble(EmployeeHM::getSalary)
            ));
        
        System.out.println("Total salary by department:");
        totalSalaryByDept.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(entry -> System.out.printf("%s: $%.2f%n", 
                entry.getKey(), entry.getValue()));
    }
}
