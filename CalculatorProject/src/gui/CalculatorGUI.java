package gui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

@SuppressWarnings("restriction")
public class CalculatorGUI extends Application{
	private Scene scene;
	private VBox wrapperPane;
	private HBox[] rows;
	private final int NUM_ROWS = 7;
	private final int SPACING = 10;
	protected String firstEntry = "";
	protected String operator = "";
	protected String secondEntry = "";
	protected State calculatorState;
	protected State firstOpState = new FirstOperatorState();
	protected State secondOpState = new SecondOperatorState();
	protected  State errorState = new ErrorState();
	protected TextField answerBox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		wrapperPane = new VBox(SPACING);
		rows = new HBox[NUM_ROWS];
		calculatorState = firstOpState;
		
		for(int i = 0; i < NUM_ROWS; i++) {
			rows[i] = new HBox(SPACING);
			wrapperPane.getChildren().add(rows[i]);
		}
		
		for(int i = 3; i >= 0; i--) {
			for(int j = 1; j <= 3; j++) { 
				final int NUMBER = ((i-1)*3 + j);
				
				if(NUMBER > -1) {
					Button numberButton = new Button("" + NUMBER);
					
					numberButton.setOnAction(e -> {
						calculatorState.addNumber("" + (NUMBER), this);
						calculatorState.updateAnswerBox(this);
					});
					
					rows[NUM_ROWS-1-i].getChildren().add(numberButton);
				}
			}
		}
		
		answerBox = new TextField();
		answerBox.setEditable(false);
		rows[0].getChildren().add(answerBox);
		
		Button clearEntryButton = new Button("CE");
		clearEntryButton.setOnAction(e -> {
			calculatorState.clearEntry(this);
			calculatorState.updateAnswerBox(this);
			
		});
		
		Button squareRootButton = new Button("√");
		squareRootButton.setOnAction(e -> {
			calculatorState.addOperator("sqrt", this);
			resetMemFunction();
			calculatorState.updateAnswerBox(this);
		});
		
		Button inverseButton = new Button("1/x");
		inverseButton.setOnAction(e -> {
			calculatorState.addOperator("1/x", this);
			resetMemFunction();
			calculatorState.updateAnswerBox(this);
		});
		
		Button clearButton = new Button("C");
		clearButton.setOnAction(e -> {
			firstEntry = "";
			secondEntry = "";
			operator ="";
			calculatorState = firstOpState;
			calculatorState.updateAnswerBox(this);
			
		});
		
		rows[1].getChildren().addAll(squareRootButton, inverseButton, clearEntryButton, clearButton);

		Button squareButton = new Button("x^2");
		squareButton.setOnAction(e -> {
			calculatorState.addOperator("x^2", this);
			resetMemFunction();
			calculatorState.updateAnswerBox(this);
		});
		
		Button cubedButton = new Button("x^3");
		cubedButton.setOnAction(e -> {
			calculatorState.addOperator("x^3", this);
			resetMemFunction();
			calculatorState.updateAnswerBox(this);
		});
		
		rows[2].getChildren().addAll(squareButton, cubedButton);
		
		addOperatorButton("^", 2);
		addOperatorButton("-", 3);
		addOperatorButton("+", 4);
		addOperatorButton("/", 5);
		
		Button equalsButton = new Button("=");
		equalsButton.setOnAction(e -> {
			resetMem();
			calculatorState.updateAnswerBox(this);
		});
		
		Button decimalButton = new Button(".");
		decimalButton.setOnAction(e -> {
			calculatorState.addNumber(".", this);
			calculatorState.updateAnswerBox(this);
		});
		
		rows[6].getChildren().addAll( decimalButton, equalsButton);
		
		addOperatorButton("*", 6);
		
		scene = new Scene(wrapperPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
		
		private void addOperatorButton(String op, int row) {
			Button button = new Button(op);
			button.setOnAction(e -> {
				calculatorState.addOperator(op, this);
				calculatorState.updateAnswerBox(this);
			});
			rows[row].getChildren().add(button);
		}
		
		protected void resetMem() {
			double solution = solve(firstEntry, operator, secondEntry);
			String solutionStr = "" + solution;
			String[] decimal = solutionStr.split("[.]");
			
			if(Long.parseLong(decimal[1]) == 0) {
				solutionStr = decimal[0];
			}
			
			clearMemory();
			
			if(calculatorState != errorState) {
				calculatorState = firstOpState;
			}
			
			calculatorState.addNumber(solutionStr, this);
		}
		protected void resetMemFunction() {
			double solution = solveFunction(firstEntry, operator);
			String solutionStr = "" + solution;
			String[] decimal = solutionStr.split("[.]");
			
			if(Long.parseLong(decimal[1]) == 0) {
				solutionStr = decimal[0];
			}
			
			clearMemory();
			
			if(calculatorState != errorState) {
				calculatorState = firstOpState;
			}
			
			calculatorState.addNumber(solutionStr, this);
		}

		
		public double solve(String first, String op, String second) {
			if(second.equals("")) {
				calculatorState = errorState;
			}
			
			try {
				double value = 0;
				double firstNum = Double.parseDouble(first);
				double secondNum = Double.parseDouble(second);
				
				switch (op){
					case "+": return firstNum + secondNum;
					case "-": return firstNum - secondNum;
					case "*": return firstNum * secondNum;
					case "/": return firstNum / secondNum;
					case "^": return Math.pow(firstNum, secondNum);
				}
			}
			catch(NumberFormatException e) {
				calculatorState = errorState;
			}
			
			return 0;
		}
		
		public double solveFunction(String first, String op) {
			if((op != "sqrt" && op != "1/x" && op != "x^2" && op != "x^3")) {
				calculatorState = errorState;
			}
			
			try {
				double value = 0;
				double firstNum = Double.parseDouble(first);
				
				switch (op){
					case "sqrt": return Math.sqrt(firstNum);
					case "1/x": return 1/firstNum;
					case "x^2": return Math.pow(firstNum, 2);
					case "x^3": return Math.pow(firstNum, 3);
				}
			}
			catch(NumberFormatException e) {
				calculatorState = errorState;
			}
			
			return 0;
		}
		
		protected void clearMemory() {
			firstEntry = "";
			secondEntry = "";
			operator ="";
		}
		
		public static void main(String[] args) {
			Application.launch(args);
		}
}
