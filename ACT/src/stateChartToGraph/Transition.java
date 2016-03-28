package stateChartToGraph;

public class Transition {
	State source;
	State destination;
	Guard guard;
	Action action;
	Trigger trigger;
	
	public Transition(State source,State destination,Guard guard,Action action,Trigger trigger){
		this.source=source;
		this.destination=destination;
		this.guard=guard;
		this.action=action;
		this.trigger=trigger;
	}

	public State getSource() {
		return source;
	}

	public void setSource(State source) {
		this.source = source;
	}

	public State getDestination() {
		return destination;
	}

	public void setDestination(State destination) {
		this.destination = destination;
	}

	public Guard getGuard() {
		return guard;
	}

	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
	
	
}
