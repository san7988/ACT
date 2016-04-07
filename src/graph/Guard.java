package graph;

import java.util.ArrayList;
import java.util.List;

public class Guard {
	private Transition transition;
	private ArrayList<Operation> expGuardList = new ArrayList<Operation>();
	
	public Transition getTransition() {
		return transition;
	}
	public void setTransition(Transition t) {
		this.transition = t;
	}
	public ArrayList<Operation> getExpGuardList() {
		return expGuardList;
	}
	public void setExpGuardList(ArrayList<Operation> exp) {
		this.expGuardList = exp;
	}
	public void addExpression(Operation e){
		expGuardList.add(e);
	}
	public Guard(List<Operation> list) throws Exception{
		this.expGuardList = (ArrayList<Operation>) list;
	}
	public String dispExp(){
		return "In Guard class :"+this.expGuardList.get(0).toString();
		}
}
