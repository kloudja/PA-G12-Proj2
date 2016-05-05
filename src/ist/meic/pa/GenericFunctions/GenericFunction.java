package ist.meic.pa.GenericFunctions;

import java.lang.reflect.*;
import java.util.ArrayList;

public class GenericFunction {

	GFMethod gfm = new GFMethod();
	ArrayList<GFMethod> beforegfmlist = new ArrayList<GFMethod>();
	ArrayList<GFMethod> aftergfmlist = new ArrayList<GFMethod>();
	ArrayList<Class> callparameters = new ArrayList<Class>();

	public GenericFunction(String name) {

		this.name = name;

	}

	public ArrayList<Class> getCallParameters() {
		return callparameters;

	}

	public GFMethod getGfm() {
		return gfm;
	}

	public ArrayList<GFMethod> getBeforegfmlist() {
		return beforegfmlist;
	}

	public ArrayList<GFMethod> getAftergfmlist() {
		return aftergfmlist;
	}

	String name;

	public String getName() {
		return name;
	}

	public void addMethod(GFMethod gfm) throws ClassNotFoundException {

		ArrayList<Class> callparameters = getCallParameters();
		// this.gfm = mostSpecific(gfm, this.gfm);

	}

	public void addBeforeMethod(GFMethod gfm) {

	}

	public void addAfterMethod(GFMethod gfm) {

	}

	public int call(Object... o) {

		return 0;
	}

	private void getCallParameters(GFMethod gfm) throws ClassNotFoundException {

		ArrayList<String> paramclass = new ArrayList<String>();
		ArrayList<Class> classesinparametersincall = new ArrayList<Class>();

		Method[] m = gfm.getClass().getDeclaredMethods();
		Parameter[] p = null;

		for (int i = 0; i < m.length; i++) {

			p = m[i].getParameters();

			String s = m[i].getName();

			for (int j = 0; j < p.length; j++)

				paramclass.add(p[j].getType().getName() + " " + s);

		}

		for (String s : paramclass) {
			String[] s1 = s.split(" ");
			System.out.println(Class.forName(s1[0])); // class
														// [[Ljava.lang.object
														// /n interface
														// java.util.List

			classesinparametersincall.add(Class.forName(s1[0]));

		}

		// if(isMoreSpecific(classesinparametersincall, callparameters)) TODO
		this.callparameters = classesinparametersincall;
		// return classesinparametersincall;
	}

	public boolean isMoreSpecific(ArrayList<Class> tocompare,
			ArrayList<Class> base) {
		// TODO check if arguments are more specific, true if tocompare is more
		// specific than base, false otherwise

		return false;
	}

	public Class moreSpecific(Class c1, Class c2) {

		int barsc1 = countBards(c1);
		int barsc2 = countBards(c2);

		boolean foundsuper = false;
		Class superc1 = c1.getSuperclass();

		if (c1 != c2) // TODO add case for [L, where each [ is the arity of an
						// array ex: [[L is a matrix, [L is an array
			while (superc1 != null) {
				if (superc1 == c2)
					foundsuper = true;
				superc1 = superc1.getSuperclass();
			}

		if (foundsuper || (barsc1 < barsc2 && getSimpleClassName(c1.toString()).equals(getSimpleClassName(c2.toString())))){
			System.out.println("found " + c2 + " as a superclass of " + c1
					+ " , so " + c1 + " is more specific");
			return c1;
		}
		else
			System.out.println("could not find " + c2 + " as superclass of "
					+ c1 + ", " + c1 + " is not more specific than " + c2);
		return c2;

	}

	public static int countBards(Class c) {

		int count = 0;

		for (int i = 0; i < c.toString().length(); i++)
			if (c.toString().charAt(i) == '[')
				count++;

		return count;

	}

	public static String getSimpleClassName(String s) {

		String k = s.replace("Lj", "j");
		String k2 = k.replace("[", "");
		String k3 = k2.replace("class ", "");
		String[] k4 = k3.split(";");
		return k4[0];

	}

}
