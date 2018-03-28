import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;

import common.Variables;
import mas.HumanAgent;
import valueFramework.Action;
import valueFramework.RandomTree;
import valueFramework.WaterTank;


public class Test /*extends TimerTask*/{

	public static void main(String [] args){
		Variables v = new Variables();
		v.createAction(20);
		
		HumanAgent h1 = new HumanAgent();
//		HumanAgent h2 = new HumanAgent();
		//agents can do all the actions
		ArrayList<HumanAgent> agents = new ArrayList<HumanAgent>();
		agents.add(h1);
		
		//after creating human and therefore creating value trees, concrete values are determined
		assginConcreteValuesToActions();
		
		ArrayList<WaterTank> waterTanks = new ArrayList<WaterTank>();
		/*for(int ha = 0 ; ha < agents.size(); ha++){			
			Iterator it = agents.get(ha).getValueTrees().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        waterTanks.add(((RandomTree)pair.getValue()).getWaterTank());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		}*/
		//Weird! after calling it.remove the entry removed from the hashmap!
		for(int ha = 0 ; ha < agents.size(); ha++){		
			for (RandomTree value : agents.get(ha).getValueTrees().values()) {				
		        waterTanks.add(value.getWaterTank());
			}
		}
		
		h1.assignPossibleActions(v.allPossibleActions);
//		h2.assignPossibleActions(v.allPossibleActions);
		
		theoryTest(100, agents, waterTanks);
		
	}
	
	public static void assginConcreteValuesToActions() {
		for(Action act: Variables.allPossibleActions){
			act.assignRandomConcreteValues();
		}
	}

	public static void theoryTest(int steps, ArrayList<HumanAgent> agents, ArrayList<WaterTank> waterTanks) {
		
		int initialSteps = steps;
		
		while(steps != 0){
			
			for (HumanAgent ha : agents) {
				ha.step();
			}
			for(WaterTank wt : waterTanks){
				wt.step();
			}
			
			
			/*if (debug) { System.out.print("After draining, uni lv: " + t1.getFilledLevel() + ", pwr lv: " + t2.getFilledLevel() + "), "); }
			Random rand = new Random();
			if (rand.nextDouble() >= noise){
				if(t1.getPriorityPercentage() < t2.getPriorityPercentage()) {
					if (debug) { System.out.println("t1/Universalism gets chosen"); }
					t1.increasingLevel();
					numOfUniversalism++;
				}
				else if (t1.getPriorityPercentage() > t2.getPriorityPercentage()) {
					if (debug) { System.out.println("t2/Power gets chosen"); }
					t2.increasingLevel();
					numOfPower++;
				}
				else{
					if (rand.nextDouble() > 0.5){
						if (debug) { System.out.println("t1/Universalism gets chosen"); }
						t1.increasingLevel();
						numOfUniversalism++;
					}
					else{
						if (debug) { System.out.println("t2/Power gets chosen"); }
						t2.increasingLevel();
						numOfPower++;
					}
				}				
			}
			if (debug) { System.out.println("after increase uni level : " + t1.getFilledLevel() + ", pwr level : " + t2.getFilledLevel()); }
			steps --;
			if (debug) { System.out.println("------------"); }*/
		}
//		System.out.println("Result: #uni: " + numOfUniversalism + ", #power: " + numOfPower);
	}

	/*@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}*/
}
