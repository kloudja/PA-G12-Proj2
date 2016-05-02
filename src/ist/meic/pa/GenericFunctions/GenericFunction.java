package ist.meic.pa.GenericFunctions;

import java.lang.reflect.*;
import java.util.ArrayList;


public class GenericFunction { 

	GFMethod gfm = new GFMethod ();
	ArrayList<GFMethod> beforegfmlist = new ArrayList<GFMethod>();
	ArrayList<GFMethod> aftergfmlist = new ArrayList<GFMethod>();

	
	public GenericFunction(String name){

		this.name = name;

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


	
	public String getName(){
		return name;
	}

	public void addMethod (GFMethod gfm) throws ClassNotFoundException {

		ArrayList<Class> callparameters = getCallParameters(gfm);


	}




	public void addBeforeMethod(GFMethod gfm){


	}


	public void addAfterMethod(GFMethod gfm){


	}

	public int call(Object... o){


		return 0;
	}
	
	
	
	

	private ArrayList<Class> getCallParameters(GFMethod gfm) throws ClassNotFoundException {

		ArrayList<String> paramclass = new ArrayList<String>();
		ArrayList<Class> classesinparametersincall = new ArrayList<Class>();

		Method[] m =	gfm.getClass().getDeclaredMethods();
		Parameter[] p = null;	

		for(int i = 0 ; i < m.length; i++){

			p =	m[i].getParameters();

			String s = m[i].getName();

			for (int j = 0; j < p.length; j++)

				paramclass.add(p[j].getType().getName() +" " + s);

		}

		for(String s: paramclass){
			String[] s1 = s.split(" ");

			classesinparametersincall.add(Class.forName(s1[0]));


		}

		return classesinparametersincall;
	}

}
