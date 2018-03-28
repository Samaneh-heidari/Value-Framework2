package common;

import java.util.ArrayList;

import valueFramework.Action;
import valueFramework.Node;

public class Variables {
	public static ArrayList<Action> allPossibleActions;
	public static ArrayList<Node> allConcreteValues;
	public static final int numOfAbstractValues = 4;
	public static int valueNumber = 0;
	public static int humanId = 0;
	//TODO: when we want to use the tree as an actual argumentation, the name should be meaningful. So, childnumber won't be necessary any more.
	
	public Variables() {
		allPossibleActions = new ArrayList<Action>();	
		allConcreteValues = new ArrayList<Node>();
	}
	
	public static void createAction(int numOfActions){
		for (int i = 0; i < numOfActions; i++) {
			Action act = new Action();
			allPossibleActions.add(act);			
		}
	}
	
	public static void addConcreteValue(Node nd){
		System.out.println("node : " + nd);
		System.out.println("list of concretevalues " + allConcreteValues);
		if(allConcreteValues != null && !allConcreteValues.contains(nd))
			allConcreteValues.add(nd);
	}
}
