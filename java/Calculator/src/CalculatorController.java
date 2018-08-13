
public class CalculatorController {
	
	private CalculatorModel model;
	private CalculatorView view;
	
	public CalculatorController(CalculatorView view, CalculatorModel model) {
		this.view = view;
		this.view.setController(this);
		this.model = model;
	}
	

}
