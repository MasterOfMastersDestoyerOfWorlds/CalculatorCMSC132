package gui;

public class ErrorState implements State {

	@Override
	public void addOperator(String operator, CalculatorGUI gui) {
		

	}

	@Override
	public void addNumber(String number, CalculatorGUI gui) {
		gui.clearMemory();
		gui.firstEntry = number;
		
	}

	@Override
	public void clearEntry(CalculatorGUI gui) {
		
	}

	@Override
	public void updateAnswerBox(CalculatorGUI gui) {
			gui.answerBox.setText("Error");		
			gui.clearMemory();
			gui.calculatorState = gui.firstOpState;
	}

}
