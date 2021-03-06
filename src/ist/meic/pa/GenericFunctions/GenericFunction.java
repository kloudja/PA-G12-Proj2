package ist.meic.pa.GenericFunctions;

import java.lang.reflect.*;
import java.util.ArrayList;

//ordering is done left to right, so (Integer, Number) is more specific than (Number, Integer)
//in case of tie, the next class pair is compared ex: (Integer, Number), (Integer, Integer). First the two Integers tie
//then, Integer in the second wins over Number in the first
//in case of a perfect tie ex:(Integer, Integer), (Integer, Integer), the newest one replaces the previous and is considered more specific

public class GenericFunction  {

	GFMethod gfm = new GFMethod();
	ArrayList<GFMethod> beforegfmlist = new ArrayList<GFMethod>();
	ArrayList<GFMethod> aftergfmlist = new ArrayList<GFMethod>();
	ArrayList<Class> callparameters = new ArrayList<Class>();
	String name;



	public GenericFunction(String name) {

		this.name = name;

	}

	public ArrayList<Class> getCallParameters() {
		return callparameters;

	}

	public GFMethod getGfm() {
		return gfm;
	}

	public void setGfm(GFMethod gfm){
		this.gfm = gfm;
	}

	public ArrayList<GFMethod> getBeforegfmlist() {
		return beforegfmlist;
	}

	public ArrayList<GFMethod> getAftergfmlist() {
		return aftergfmlist;
	}


	public String getName() {
		return name;
	}

	public void addMethod(GFMethod gfmnew) throws ClassNotFoundException {


		ArrayList<Class> callparameters = getCallParameters(gfmnew);
		ArrayList<Class> gfmcallparameters = getCallParameters(this.gfm);

		if(gfmcallparameters.size() == 0)
			setGfm(gfmnew);

		else{

			if(moreSpecificClassList(callparameters, gfmcallparameters) == callparameters) setGfm(gfmnew);

		}


	}


	public void addBeforeMethod(GFMethod gfm) throws ClassNotFoundException {

		addOrReplaceOnList(beforegfmlist, gfm);

	}



	public void addAfterMethod(GFMethod gfm) throws ClassNotFoundException {

		addOrReplaceOnList(aftergfmlist, gfm);

	}

	public int call(Object... o) {

		return 0;
	}

	protected static ArrayList<Class> getCallParameters(GFMethod gfm) throws ClassNotFoundException {

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

		// if(moreSpecificClassList(classesinparametersincall, callparameters)) TODO

		return classesinparametersincall;
	}

	public static ArrayList<Class> moreSpecificClassList(ArrayList<Class> tocompare,	ArrayList<Class> base) {//Note: tocompare is assumed to be more recent
		//check which list arguments are more specific from left to right, returns the more specific list, or tocompare if all elements are equally specific
		//TODO this is untested
		System.out.println(tocompare.size());
		System.out.println(base.size());
		if(tocompare.size() != base.size()) 
			throw new IllegalArgumentException("Error: number of parameters does not match");


		for(int i = 0; i < tocompare.size(); i++){

			int j = moreSpecificClass(tocompare.get(i), base.get(i));

			if(j == -1){ System.out.println("tie at slot " +i); continue; } //in case of ties goes to the next loop iteration
			if(j == 0) return tocompare;
			if(j == 1) return base;			

		}
		System.out.println("All slots tied");
		base = tocompare;
		return tocompare;
	}




	public static int moreSpecificClass(Class c1, Class c2) { //returns -1 if c1 and c2 are the same class, 0 if c1 is more specific, and 1 if c2 is more specific

		if(c1 == c2) return -1;

		int barsc1 = countBards(c1);
		int barsc2 = countBards(c2);

		boolean foundsuper = false;
		Class superc1 = c1.getSuperclass();

		if (c1 != c2) 
			while (superc1 != null) { 
				if (getSimpleClassName(superc1.toString()).equals(getSimpleClassName(c2.toString())))
					foundsuper = true;
				superc1 = superc1.getSuperclass();
			}

		if (foundsuper || (barsc1 < barsc2 && getSimpleClassName(c1.toString()).equals(getSimpleClassName(c2.toString())))){
			System.out.println("found " + c2 + " as a superclass of " + c1
					+ " , so " + c1 + " is more specific");
			return 0;
		}
		else
			System.out.println("could not find " + c2 + " as superclass of "
					+ c1 + ", " + c1 + " is not more specific than " + c2);
		return 1;

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

	public static void addOrReplaceOnList(ArrayList<GFMethod> gfmlist, GFMethod gfm) throws ClassNotFoundException{

		if(gfmlist.size() == 0){
			gfmlist.add(gfm);
			return;
		}

		ArrayList<Class> callparameters = getCallParameters(gfm);

		for(int i = 0; i< gfmlist.size(); i++){


			if(getCallParameters(gfmlist.get(i)).equals(callparameters)){

				gfmlist.set(i, gfm);
				return;
			}	
			gfmlist.add(gfm);
			
		}


	}





}
