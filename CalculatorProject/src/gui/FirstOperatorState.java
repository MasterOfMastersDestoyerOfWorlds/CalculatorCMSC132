package gui;

public class FirstOperatorState implements State{

	@Override
	public void addOperator(String operator, CalculatorGUI gui) {
		gui.memory.add(operator);
		
	}

}
