package ist.meic.pa.GenericFunctions;

import java.util.ArrayList;
import java.util.Comparator;

public class GFMethodComparator implements Comparator<GFMethod> {

	@Override
	public int compare(GFMethod gfm1, GFMethod gfm2){

		try {
			ArrayList<Class> callparameters1 = GenericFunction.getCallParameters(gfm1);
			ArrayList<Class> callparameters2 = GenericFunction.getCallParameters(gfm2);
			
			if(GenericFunction.moreSpecificClassList(callparameters1, callparameters2).equals(callparameters1))
				return -1;
			else return 1;
			
			
			
			
		} catch (ClassNotFoundException e) {
			System.err.println("Error on GFMethodComparator - Class not found for parameters of " +gfm1 +" or " +gfm2);
			e.printStackTrace();
		}
		System.out.println("This should never happen...");
		return 0;
	}

}
