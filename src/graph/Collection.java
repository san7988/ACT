package graph;

import java.util.ArrayList;
import java.util.List;

public class Collection {
	private static ArrayList<State> stateList = new ArrayList<State>();
	private static ArrayList<Transition> transitionList = new ArrayList<Transition>();
	private static ArrayList<Operation> operationList = new ArrayList<Operation>();
	private static ArrayList<Guard> guardList = new ArrayList<Guard>();
	
	public static List<State> getStateList() {
		return stateList;
	}
	public static void addState(State state){
		stateList.add(state);
	}
	public static List<Transition> getTransitionList() {
		return transitionList;
	}
	public static void addTransition(Transition transition){
		transitionList.add(transition);		
	}
	public static List<Operation> getOperationList(){
		return operationList;
	}
	public static void addOperation(Operation expression){
		operationList.add(expression);
	}
	public static List<Guard> getGuardList(){
		return guardList;
	}
	public static void addGuard(Guard guard){
		guardList.add(guard);
	}
	
	public static boolean hasState(INode state){
		if(stateList.contains(state))
			return true;
		return false;
	}
	public static INode getStateById(int id)
	{
		int i;
		for(i=0;i<stateList.size();i++)
			if(Collection.stateList.get(i).getId()==id)
				break;
		return Collection.stateList.get(i);
	}
	public static void printState() {
		for(int i = 0; i<stateList.size();i++){
			System.out.println("State:\t"+Collection.stateList.get(i).getName()+" "+Collection.stateList.get(i).getDesc()+" "+Collection.stateList.get(i).getInput().toString()+" "+Collection.stateList.get(i).getClickable().toString());
		}		
	}
	public static State getStateByName(String name) {
		int i;
		for(i = 0;i<stateList.size();i++){
			//System.out.println("Check: " +Collection.stateList.get(i).getName()+ " vs "+name);
			//System.out.println("Check: " +Collection.stateList.size()+ " vs "+name);
			if(Collection.stateList.get(i).getName().equals(name))	
				break;
		}
		if(stateList.size()!=i)
			return Collection.stateList.get(i);
		return null;
	}
}
