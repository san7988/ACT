package stateChartToGraph;

//import stateChartToGraph.*;
import java.util.ArrayList;

public class StateChart {
	ArrayList<State> statesList;
	ArrayList<Transition> transitionList;
	public StateChart() {
		this.statesList=new ArrayList<State>();
		this.transitionList=new ArrayList<Transition>();
	}
	public void addState(State state){
		this.statesList.add(state);
	}
	
	public void adTransition(Transition transition){
		this.transitionList.add(transition);
	}
	public ArrayList<State> getStatesList() {
		return statesList;
	}
	public ArrayList<Transition> getTransitionList() {
		return transitionList;
	}
	
	
}
