package gui;

public class SecondOperatorState implements State {

	@Override
	public void addOperator(String operator, CalculatorGUI gui) {
		gui.resetMem();
		gui.operator = operator;
		
	}

	@Override
	public void addNumber(String number, CalculatorGUI gui) {
		gui.secondEntry += number;
		
	}

	@Override
	public void clearEntry(CalculatorGUI gui) {
		if(gui.secondEntry.equals("")) {
			gui.operator = "";
			gui.calculatorState = gui.firstOpState;
		}
		else {
			gui.secondEntry = "";
		}
		
	}

}
