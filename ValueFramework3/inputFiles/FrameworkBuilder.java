package common;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mas.HumanAgent;
import valueFramework.Action;
import valueFramework.Node;

public class FrameworkBuilder {
	public static ArrayList<Action> allPossibleActions = new ArrayList<Action>();
	public static ArrayList<Node> allConcreteValues = new ArrayList<Node>();
	public static ArrayList<String> concreteValuesName = new ArrayList<String>();
	public static ArrayList<HumanAgent> humanAgentList;
	
	
	public static final int numOfAbstractValues = 4;
	public static int valueNumber = 0;
	public static int humanId = 0;
	//TODO: when we want to use the tree as an actual argumentation, the name should be meaningful. So, childnumber won't be necessary any more.
	/*
	public static void createRandomAction(int numOfActions){
		for (int i = 0; i < numOfActions; i++) {
			Action act = new Action();
			allPossibleActions.add(act);			
		}
	}*/
	
	public static void readActionsFromFile(String filePath){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null) {
				if(!line.startsWith("%")){//means that it is not comment
					List<String> items = Arrays.asList(line.split("\\s*,\\s*"));//ignore while space after comma
					
					if (items.size() >= 1) {
						addActionFromString(items);
						addConcreteValuesFromString(items.subList(1, items.size()));
					}
					else {
						System.err.println("No elements in items or doesn't contain a concrete value:" + items);
					}
				}	
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void addActionFromString(List<String> actionAndConcreteValues) {
		
		String actionName = actionAndConcreteValues.get(0);
		ArrayList<String> concreteValues = new ArrayList<String>();
		for (int i = 1; i < actionAndConcreteValues.size(); i ++) {
			concreteValues.add(actionAndConcreteValues.get(i));
		}
		System.out.println("Add action: " + actionName + ", cvs: " + concreteValues);
		allPossibleActions.add(new Action(actionName, concreteValues));
	}
	
	public static void addConcreteValuesFromString(List<String> concreteValues) {
		
		for(String concreteValue : concreteValues){
			if(!concreteValuesName.contains(concreteValue))
				System.out.println("Add concrete value: " + concreteValue);
				concreteValuesName.add(concreteValue);
		}
	}
	
	public static void addConcreteValue(Node nd){
		System.out.println("node : " + nd);
		System.out.println("list of concretevalues " + allConcreteValues);
		if(allConcreteValues != null && !allConcreteValues.contains(nd))
			allConcreteValues.add(nd);
	}
	
	public static void readActionsFromFile(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"/inputFiles/actionList.txt"));
			String line = reader.readLine();
			while (line != null) {
				if(!line.startsWith("%")){//means that it is not comment
					List<String> items = Arrays.asList(line.split("\\s*,\\s*"));//ignore while space after comma
					for(int i = 1; i < items.size(); i++){
						if(!concreteValuesName.contains(items.get(i)))
							concreteValuesName.add(items.get(i));
					}
				}	
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/*
	public void readValueTreeFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"/inputFiles/valueTreeList.txt"));
			String line = reader.readLine();
			HumanAgent hmn;
			while (line != null) {
				if(!line.startsWith("%")){//means that it is not comment
					if (line.contains("humanid")){
						//this line contains info about a human
						int tmpid = Integer.valueOf(Arrays.asList(line.split(" = ")).get(1));
						hmn = new HumanAgent();
						line = reader.readLine();
						if (line.contains("value tree")){
						//it's value trees from now on
							line = reader.readLine();
							List<String> treeInfo = new ArrayList<String>;
							while (!line.contains("value tree") && line != null){
								treeInfo.add(line);
								line = reader.readLine();
							}
							/*
							List<String> items = Arrays.asList(line.split("\\s*,\\s*"));//ignore while space after comma
							//TODO: check the split sql
							String tmpChildren =  items.get(2);
							tmpChildren = tmpChildren.substring(tmpChildren.indexOf("["), tmpChildren.indexOf("]"));
							List<String> childrenList =  Arrays.asList(tmpChildren.split("\\s*,\\s*"));
							int numOfChild = childrenList.size();
							hmn.createValueTrees(treeInfo);
							for (RandomTree value : agents.get(ha).getValueTrees().values()) {				
						        waterTanks.add(value.getWaterTank());
							}
					
						humnaAgentList.add(hmn);
						
					}
				}	
				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
