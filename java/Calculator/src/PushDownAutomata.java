import java.util.ArrayList;
import java.util.Stack;

public class PushDownAutomata {
	
	private class Transition {
		private State nextState;
		private Character gateChar;
		private Character gateStack;
 	}
	
	
	private class State {
		private ArrayList<Transition> transitions;
		private String name;
		
		public State(String name, ArrayList<Transition> transitions) {
			this.name = name;
			this.transitions = transitions;
		}
	}
	
	private String tape;
	private State initState;
	private Stack<Character> stack;
	
	public PushDownAutomata() {
		this.stack = new Stack<>();
		//init the graph for the pda
		initGraph();
	}

	private void initGraph() {
		ArrayList<Transition> aOutGoing = new ArrayList<>();
		//TODO: Create outgoing transitions for init state
		initState = new State("A", aOutGoing);
		//TODO: Finish the rest of the graph
		
	}
	
	public Calculation parseCalculation(String input) {
		return null;
	}
	
}
