package graph;

public class BinaryExpression extends Expression{
	private String lhs;
	private String rhs;
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
	public BinaryExpression(Guard g,String lhs,String rhs) {
		super(g);
		this.lhs = lhs;
		this.rhs = rhs;
	}
}
