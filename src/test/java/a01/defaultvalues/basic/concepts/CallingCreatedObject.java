package a01.defaultvalues.basic.concepts;

class CreatingObject {
	int i ;
}

class CallingCreatedObject {

	public static void main(String[] args) {
		CreatingObject a = new CreatingObject() ;
		CreatingObject b = a ;

		System.out.println(a.i + " " + b.i);

		a.i = 10 ;
		System.out.println(a.i + " " + b.i);

		b.i = 20 ;
		System.out.println(a.i + " " + b.i);
	}

}
