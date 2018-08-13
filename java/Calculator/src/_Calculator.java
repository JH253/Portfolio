

public class _Calculator {

	private CalculatorController ctrl;
	private CalculatorView view;
	private CalculatorModel model;
	
	
	public _Calculator() {
		view = new CalculatorView();
		model = new CalculatorModel();
		ctrl = new CalculatorController(view, model);
		
	}
	
	
	public static void main(String[] args) {
		//new Calculator();
		CalculatorModel model = new CalculatorModel();
		//Testing add
		System.out.println("2+3 = " + model.compute("2+3"));
		//Testing mult
		System.out.println("3*2 = " + model.compute("3*2"));
		//Testing div
		System.out.println("8/2 = " + model.compute("8/2"));
		//Testing sub
		System.out.println("7-3 = " + model.compute("7-3"));
		
	}

}
