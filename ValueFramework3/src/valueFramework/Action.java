package valueFramework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import common.Constants;
import common.Log;
import common.Variables;

public class Action {
	private ArrayList<Object[]> relatedConcreteValues;//arraylist of list[2] : [node as a concrete value, +/- contribution]
	private String title;
	public Action() {
		relatedConcreteValues = new ArrayList<Object[]>();	
		title = Integer.toString(Constants.ID_ACTION);
		Constants.ID_ACTION++;
	}
	
	public void addRelatedConcreteValue(Node concreteValue, Boolean contribution){
		Object[] input = {concreteValue, contribution};
		relatedConcreteValues.add(input);
//		System.out.println(input[0]);
	}
	
	public void assignRandomConcreteValues(){
		ArrayList<Node> cncrValues = Variables.allConcreteValues;
		Collections.shuffle(cncrValues);
    	int randomNum = ThreadLocalRandom.current().nextInt(0, cncrValues.size());
    	for(int i = 0 ; i < randomNum; i++){
    		boolean randomCont = ThreadLocalRandom.current().nextBoolean();
    		Object[] vl = new Object[2];
    		vl[0] = cncrValues.get(i);
    		vl[1] = randomCont;
    		relatedConcreteValues.add(vl);
    	}
	
    	System.out.println("in assignRandomConcreteValues action  " + this.title + " : ");
    	Log.printConcreteValues(this.title, relatedConcreteValues);
	}
	
	public ArrayList<Object[]> getRelatedConcreteValues(){
		return relatedConcreteValues;
	}
	public int checkRalatedValueInValueTree(Node root, boolean contributionType) {
		//look into the value tree until you get the concrete values and check if the concrete values are in the list of relatedValues of the actionC
		return sweepTree(root, contributionType);
		 
	}

	private int sweepTree(Node root, boolean contributionType) {
		int numOfRelatedConcreteValues = 0;
		if(root.getChildren().size() ==0)//it's a leaf
		{
			Object[] inList = {root, contributionType};//TODO: check the if condition
			if(relatedConcreteValues.contains(inList))
				numOfRelatedConcreteValues++;
			
		}
		else{
			List<Node> children = root.getChildren();
			for (int i = 0; i < children.size(); i++) {
				numOfRelatedConcreteValues = numOfRelatedConcreteValues + sweepTree(children.get(i), contributionType); 
			}			
		}
		return numOfRelatedConcreteValues;
	}

	@Override
    public String toString(){
        return "action "+ title +", done" ;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
