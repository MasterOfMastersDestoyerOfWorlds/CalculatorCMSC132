package gui;

public interface State {
	public void addOperator(String operator, CalculatorGUI calculatorGUI);
	public void addNumber(String number, CalculatorGUI gui);
	public void clearEntry(CalculatorGUI gui);
}
