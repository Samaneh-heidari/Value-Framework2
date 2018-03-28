package valueFramework;


import java.util.List;
import java.util.Random;

import common.Variables;


public class RandomTree  implements Comparable<RandomTree>{
	private WaterTank waterTank;
	private Node root;
	
	
	public void createChildren(int depth, int numOfChildren, Node parent){		
		if (depth == 0){
			parent.randomlyAssignActions(Variables.allPossibleActions);
			Variables.addConcreteValue(parent);
			System.out.println(parent.getValueName() + " as a concrete value adds possible actions " + parent.relatedActions.size());
			return;
		}
		Random rand = new Random();
		for (int i = 0; i < numOfChildren; i++){
			int numofGrandChildren= rand.nextInt(3) + 1;
			//TODO: limitation is 10 children. It can be more or less, just by changing the input arg of nextInt function
			Node newChild = new Node(Integer.toString(Variables.valueNumber), parent);
			parent.addChild(newChild);
			System.out.println("My name " + Variables.valueNumber + "\tparent : " + parent.getValueName() + "\tdepth : " + depth + "\tnumOfChildren : " + numofGrandChildren);
			Variables.valueNumber++;
			createChildren(depth-1, numofGrandChildren, newChild);
		}				
	}
	
	public Node randomTreeBuilder(int maxDepth, int maxNumOfChildren, String title){
		setWaterTank(new WaterTank());
		root = new Node(title, null);
		Random rand = new Random();
	    int depth = rand.nextInt(maxDepth) + 1;
	    int numofChildren = rand.nextInt(maxNumOfChildren) + 1;
	    createChildren(depth, numofChildren, root);
	    return root;
	}

	public WaterTank getWaterTank() {
		return waterTank;
	}

	public void setWaterTank(WaterTank waterTank) {
		this.waterTank = waterTank;
	}
			
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	@Override
	public int compareTo(RandomTree other) {
		return Double.compare(waterTank.getPriorityPercentage(),other.waterTank.getPriorityPercentage());
	}
	
	
	
	
}
