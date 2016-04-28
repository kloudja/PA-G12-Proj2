package ist.meic.pa.GenericFunctions;

import java.lang.reflect.*;


public class GenericFunction {
	
	String name;

	public GenericFunction(String name){
		
		
		this.name = name;
		
	}
	
	public String getName(){
		return name;
	}
	
	public void addMethod (GFMethod gfm){
		
		gfm.getClass().getMethods();
		
		
		
	}
	
	public void addBeforeMethod(GFMethod gfm){
		
		
	}
	
	
	public void addAfterMethod(GFMethod gfm){
		
		
	}
	
	public void makeCall(){
		
		
		
	}
	
	
	
}
