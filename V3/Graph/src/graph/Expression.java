package graph;

public class Expression {
	private Guard guard;
	public Expression(Guard g){
			g.addExpression(this);
			this.setGuard(g);
	}
	public Guard getGuard() {
		return guard;
	}
	public void setGuard(Guard guard) {
		this.guard = guard;
	}
}
