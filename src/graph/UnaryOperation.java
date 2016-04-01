package graph;

public class UnaryOperation extends Operation{
	String var;
	String op;
	public UnaryOperation(String transitionGuardUnaryOperationVar, String transitionGuardUnaryOperationOp) {
		this.var = transitionGuardUnaryOperationVar;
		this.op = transitionGuardUnaryOperationOp;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
}
