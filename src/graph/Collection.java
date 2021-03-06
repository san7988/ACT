package graph;


import java.util.ArrayList;
import java.util.List;

public class Collection {
	private static ArrayList<State> stateList = new ArrayList<State>();
	private static ArrayList<String> idList = new ArrayList<String>();
	private static ArrayList<String> inputList = new ArrayList<String>();
	private static ArrayList<String> clickableList = new ArrayList<String>();	
	private static ArrayList<Transition> transitionList = new ArrayList<Transition>();
	private static ArrayList<Operation> operationList = new ArrayList<Operation>();
	private static ArrayList<Guard> guardList = new ArrayList<Guard>();
	
	
	public static List<State> getStateList() {
		System.out.println("Get State List");
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
		//System.out.println("Hi Dude");
		guardList.add(guard);
	}
	
	public static boolean hasState(INode state){
		System.out.println("Hello World");
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
	public static void addId(String str){
		idList.add(str);
		System.out.println(str+" addded to Collection");
	}
	public static ArrayList<String> getIdList(){
		return idList;
	}
	public static void addInputListFromIdList(){
		for(int i=0;i<idList.size();i++)
			inputList.add(idList.get(i));		
		idList.clear();
		System.out.println("InputList: "+inputList.toString());
	}
	public static void addClickableListFromIdList(){
		for(int i=0;i<idList.size();i++)
			clickableList.add(idList.get(i));		
		idList.clear();
		System.out.println("ClickableList: "+clickableList.toString());
	}	
	public static ArrayList<String> getInputList(){
		return inputList;
	}
	public static ArrayList<String> getClickableList(){
		return clickableList;
	}
}
