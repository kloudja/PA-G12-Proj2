package ist.meic.pa.GenericFunctions;

import java.lang.reflect.*;
import java.util.ArrayList;


public class GenericFunction {
	
	ArrayList<GFMethod> gfmlist = new ArrayList<GFMethod>();
	ArrayList<GFMethod> beforegfmlist = new ArrayList<GFMethod>();
	ArrayList<GFMethod> aftergfmlist = new ArrayList<GFMethod>();
	String name;

	public GenericFunction(String name){
		
		
		this.name = name;
		
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<GFMethod> getGfmList(){
		return gfmlist;
	}
	
	public void addMethod (GFMethod gfm){
		
		gfmlist.add(gfm);
		
		
		
	}
	
	public void addBeforeMethod(GFMethod gfm){
		
		beforegfmlist.add(gfm);
		
	}
	
	
	public void addAfterMethod(GFMethod gfm){
		
		aftergfmlist.add(gfm);
		
	}
	
	public void makeCall(){
		
		
		
	}
	
	
	
}
