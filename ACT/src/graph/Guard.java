package graph;

import java.util.ArrayList;

public class Guard {
	private Transition transition;
	private ArrayList<Expression> expGuardList;
	
	public Transition getTransition() {
		return transition;
	}
	public void setTransition(Transition t) {
		this.transition = t;
	}
	public ArrayList<Expression> getExpGuardList() {
		return expGuardList;
	}
	public void setExpGuardList(ArrayList<Expression> exp) {
		this.expGuardList = exp;
	}
	public void addExpression(Expression e){
		expGuardList.add(e);
	}
	public Guard(Transition t) throws Exception{
		this.transition = t;
	}
}
