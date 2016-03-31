package stateChartToGraph;

public class Expression {
	String lhs;
	int rhs;
	String op;
	public Expression(){
}
	public Expression(String lhs,int rhs,String op){
		this.lhs=lhs;
		this.rhs=rhs;
		this.op=op;
	}
	
	public String toString(){
		return "This is in expression class :"+this.lhs+" "+this.rhs+" "+this.op;
	}


}
