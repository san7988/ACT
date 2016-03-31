package graph;

import java.util.ArrayList;
import java.util.List;

public class Collection {
	private static ArrayList<State> stateList = new ArrayList<State>();
	private static ArrayList<Transition> transitionList = new ArrayList<Transition>();
	private static ArrayList<Expression> expressionList = new ArrayList<Expression>();
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
	public static List<Expression> getExpressionList(){
		return expressionList;
	}
	public static void addExpression(Expression expression){
		expressionList.add(expression);
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
}