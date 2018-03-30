import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;

import javax.lang.model.element.VariableElement;

import common.FrameworkBuilder;
import mas.HumanAgent;
import valueFramework.Action;
import valueFramework.RandomTree;
import valueFramework.WaterTank;


public class Test /*extends TimerTask*/{
	
	public static void main(String [] args) throws IOException{
		FrameworkBuilder v = new FrameworkBuilder();
//		v.createRandomAction(20);
		
/*		HumanAgent h1 = new HumanAgent();
		//agents can do all the actions
		ArrayList<HumanAgent> agents = new ArrayList<HumanAgent>();
		agents.add(h1);
		
		//after creating human and therefore creating value trees, concrete values are determined
		assginConcreteValuesToActions();
		
		ArrayList<WaterTank> waterTanks = new ArrayList<WaterTank>();
		//Weird! after calling it.remove the entry removed from the hashmap!
		for(int ha = 0 ; ha < agents.size(); ha++){		
			for (RandomTree value : agents.get(ha).getValueTrees().values()) {				
		        waterTanks.add(value.getWaterTank());
			}
		}
		
		h1.assignPossibleActions(v.allPossibleActions);
*/
		//first create value files
		v.readValueTreeFile();
		ArrayList<WaterTank> waterTanks = new ArrayList<WaterTank>();
		//then read actions from file
		v.readActionsFromFile("inputFiles\\actionList3.txt");
		
//	v.conncetActionsAndConcreteValues();
		v.assginRelatedActionsToConcreteValues();
		
		HumanAgent h1 = new HumanAgent(1);
		FrameworkBuilder.humnaAgentList.add(h1);
		
		for(HumanAgent ha : FrameworkBuilder.humnaAgentList){	
			ha.assignPossibleActions(FrameworkBuilder.allPossibleActions);
		}
		
		h1.createValueTrees();
		
		for(HumanAgent ha : FrameworkBuilder.humnaAgentList){		
			for (RandomTree value : ha.getValueTrees().values()) {				        
				waterTanks.add(value.getWaterTank());
			}
		}
		
		theoryTest(100, FrameworkBuilder.humnaAgentList, waterTanks);
		System.out.println("end :)");
	}
	
	public static void assginConcreteValuesToActions() {
		for(Action act: FrameworkBuilder.allPossibleActions){
			act.assignRandomConcreteValues();
		}
	}

	public static void theoryTest(int steps, ArrayList<HumanAgent> agents, ArrayList<WaterTank> waterTanks) {
		
		int initialSteps = steps;
		
		while(steps != 0){
			System.out.println("--------step " + steps + "----------------");
			for (HumanAgent ha : agents) {
				ha.step();
			}
			for(WaterTank wt : waterTanks){
				wt.step();
			}
			steps--;
		}
//		System.out.println("Result: #uni: " + numOfUniversalism + ", #power: " + numOfPower);
	}

	/*@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}*/
}
