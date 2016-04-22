package graph;


public class Transition extends Edge {
	private String name;
	private String description;
	private String trigger;
	private Guard guard;
	private String action;
	
	public Transition(String name,String description,INode stateFrom, INode stateTo,String trigger,Guard guard, String action) throws Exception {
		super(stateFrom, stateTo);
		this.name = name;
		//trigger qualification check
		int i;
		
		
		//System.out.println("Check: "+trigger+" "+State.class.cast(stateFrom).getClickable().get(0));
		for(i = 0;i< 0;i++){
			if(trigger.equals(State.class.cast(stateFrom).getClickable().get(i))){
				this.trigger = trigger;
				//System.out.println("Check Inside");
				break;
			}
		}
		
		if(i==State.class.cast(stateFrom).getClickable().size())
			System.out.println("Trigger for the transition "+this.getEdgeId()+"; Name- "+this.getName()+"; Desc- "+this.getDescription()+" could not be added\n\t BECAUSE "+trigger+" is not a clickable object of state "+State.class.cast(stateFrom).getName());		
		//guard
		int flag = 1;
		if(guard ==null) {
			System.out.println("Check");
			return;
		}
		else
			System.out.println(guard.toString());
		for(i = 0;i<guard.getExpGuardList().size();i++){
			if(guard.getExpGuardList().get(i) instanceof BinaryOperation){
				int lFound = 0,	rFound =0;
				String lhsOperation = BinaryOperation.class.cast(guard.getExpGuardList().get(i)).getLhs();
				String opOperation = BinaryOperation.class.cast(guard.getExpGuardList().get(i)).getOp();
				String rhsOperation = BinaryOperation.class.cast(guard.getExpGuardList().get(i)).getRhs();	//TODO- accommodate constants, additions type operations
				for(int j=0;j<State.class.cast(stateFrom).getInput().size();j++){				
					String stateInput = State.class.cast(stateFrom).getInput().get(j);
					if(lhsOperation.equals(stateInput))
						lFound = 1;
					if(rhsOperation.equals(stateInput))
						rFound = 1;
					if(lFound == 1 && rFound == 1)
						break;
					//it's not constant or action (DB,Session) key.
				}
				if(lFound == 0){
					System.out.println("For Operation- "+lhsOperation+opOperation+rhsOperation+" the input element "+lhsOperation+" from the state "+State.class.cast(stateFrom).getName()+" for transition"+this.getName()+" could not be found");
					flag = 0;
					break;
				}
				if(rFound == 0){
					System.out.println("For Operation- "+lhsOperation+opOperation+rhsOperation+" the input element "+rhsOperation+" from the state "+State.class.cast(stateFrom).getName()+" for transition"+this.getName()+" could not be found");
					flag = 0;
					break;
				}
			}
			if(guard.getExpGuardList().get(i) instanceof UnaryOperation){
				int unaryFlag = 0;
				String varOperation = UnaryOperation.class.cast(guard.getExpGuardList().get(i)).getVar();
				String opOperation = UnaryOperation.class.cast(guard.getExpGuardList().get(i)).getOp();
				for(int j=0;j<State.class.cast(stateFrom).getInput().size();j++){
					String stateInput = State.class.cast(stateFrom).getInput().get(j);
					if(varOperation.equals(stateInput)){
						unaryFlag = 1;
						break;
					}
				}
				if(unaryFlag == 0){
					System.out.println("For Operation- "+opOperation+varOperation+" the input element "+varOperation+" from the state "+State.class.cast(stateFrom).getName()+" for transition"+this.getName()+" could not be found");
					flag = 0;
					break;
				}
			}
		}
		if(flag == 1)
			this.guard = guard;
		this.action = action;
		//TODO- use many hashmaps (like for login,etc) to store key value (or key object/arraylist pairs)
	}
	/*public Transition(INode stateFrom, INode stateTo, String trig) throws Exception {
		super(stateFrom, stateTo);
		this.trigger=trig;
	}*/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Transition(INode stateFrom, INode stateTo) throws Exception {
		super(stateFrom,stateTo);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public Guard getGuard() {
		return guard;
	}
	public void setGuard(Guard guard) {
		this.guard = guard;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	//parent properties accessors
	public IGraph getStateChart() {
		return this.mGraph;
	}
	public void setStateChart(IGraph graph) {
		this.mGraph = graph;
	}
	public INode getStateFrom() {
		return this.mTail;
	}	
	public void setStateFrom(INode state) {
		mTail = state;
	}
	public INode getStateTo() {
		return this.mHead;
	}
	public void setStateTo(INode state) {
		mHead = state;
	}
	public int getTransitionId() {
		return edgeId;
	}
	public void setTransitionId(int edgeId) {
		this.edgeId = edgeId;
	}
}
