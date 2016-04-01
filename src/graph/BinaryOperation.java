package graph;

public class BinaryOperation extends Operation{
	private String lhs;
	private String rhs;
	private String op;
	
	public BinaryOperation(String lhs,String op, String rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.op = op;
	}
	
	public String getLhs() {
		return lhs;
	}
	public void setLhs(String lhs) {
		this.lhs = lhs;
	}
	public String getRhs() {
		return rhs;
	}
	public void setRhs(String rhs) {
		this.rhs = rhs;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
}
