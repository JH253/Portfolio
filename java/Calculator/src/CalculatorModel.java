
public class CalculatorModel {
	
	private PushDownAutomata pda;
	
	public CalculatorModel() {
		pda = new PushDownAutomata();
	}
	
	public String compute(String input) {
		return pda.parseCalculation(input).result().toString();
	}
	
}
