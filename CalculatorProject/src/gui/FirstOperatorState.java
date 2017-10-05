package gui;

public class FirstOperatorState implements State{

	@Override
	public void addOperator(String operator, CalculatorGUI gui) {
		gui.operator = operator;
		gui.calculatorState = gui.secondOpState;
		
	}

	@Override
	public void addNumber(String number, CalculatorGUI gui) {
		gui.firstEntry += number;
		
	}


	@Override
	public void clearEntry(CalculatorGUI gui) {
		gui.firstEntry = "";
		
	}

}
