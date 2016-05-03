package ist.meic.pa.GenericFunctions;

import java.*;
import java.util.Arrays;
import java.util.List;

public class ex1 {
	
	
	public static void main (String[] args) throws ClassNotFoundException{
		
		final GenericFunction add = new GenericFunction("add");
		
	/*	explain.addMethod(new GFMethod() {
			Object call(Integer entity) {
				System.out.printf("%s is a integer", entity);
				return "";
		}
			}
		);
		explain.addMethod(new GFMethod() {
		Object call(Number entity) {
		System.out.printf("%s is a number", entity);
		return "";
		}});
		explain.addMethod(new GFMethod() {
		Object call(String entity) {
		System.out.printf("%s is a string", entity);
		return "";
		}});
		explain.addAfterMethod(new GFMethod() {
		void call(Integer entity) {
		System.out.printf(" (in hexadecimal, is %x)", entity);
		}});
		explain.addBeforeMethod(new GFMethod() {
		void call(Number entity) {
		System.out.printf("The number ", entity);
		}});
		println(explain.call(123));
		println(explain.call("Hi"));
		println(explain.call(3.14159));
		*/
		String x[] = {"1"};
		System.out.println(x.getClass());
		System.out.println(x.getClass().getSuperclass());
		System.out.println(x.getClass().getSuperclass().getSuperclass());
		//System.out.println(x.getClass().getSuperclass().getSuperclass().getSuperclass());
		
		add.addMethod(new GFMethod() {
			Object call(Object[][] a, List b) {            			//[[Ljava.lang.Object; call
                    System.out.println(b.size());                                        	//	java.util.List call
			return add.call(a, b.toArray());
			}});
		
		
		System.out.println(add.call(new Object[] { 1, 2, 3 }, Arrays.asList(4, 5, 6)));
		
		
		add.moreSpecific(Class.forName("java.lang.Float") , Class.forName("java.lang.Number"));
	}
	
	

}
