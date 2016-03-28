package stateChartToGraph;

public class Action {
	
	Expression expr;
	
	public Action(Expression expr){
		this.expr=expr;
	}
public String toString(){
if(expr.lhs==null) return "This is in action class : nulll";
else return "This is in action class : "+expr.lhs;
}
	
}
