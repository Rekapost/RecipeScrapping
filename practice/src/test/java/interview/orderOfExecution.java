package interview;

public class orderOfExecution {
	

	public class Container {
		public Container() {
			System.out.println("in container");
		}

		public class Nested {
			public Nested() {
				System.out.println("in nested");
			}

			public void methodCall() {
				System.out.println("in method");
			}
		}

	}
	
	
	
	
	public static void main(String[] args) {
	//	orderOfExecution order= new orderOfExecution();		
	}
}
