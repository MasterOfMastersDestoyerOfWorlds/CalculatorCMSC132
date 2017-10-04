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
		private final int NUM_ROWS = 5;
		private final int SPACING = 10;
		protected ArrayList<String> memory = new ArrayList<String>();
		protected State calculatorState;
		protected State firstOpState = new FirstOperatorState();
		protected State secondOpState = new SecondOperatorState();
	
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
							memory.add("" + (NUMBER));
						});
						
						rows[4-i].getChildren().add(numberButton);
					}
				}
			}
			
			Button squareRootButton = new Button("√");
			squareRootButton.setOnAction(e -> {
				calculatorState.addOperator("sqrt");
				int solution = solve(memory);
				memory.clear();
				memory.add("" + solution);
				calculatorState = firstOpState;
			});
			rows[0].getChildren().add(squareRootButton);
			
			Button inverseButton = new Button("1/x");
			inverseButton.setOnAction(e -> {
				calculatorState.addOperator("1/x");
				int solution = solve(memory);
				memory.clear();
				memory.add("" + solution);
				calculatorState = firstOpState;
			});
			rows[0].getChildren().add(inverseButton);
			
			Button plusButton = new Button("+");
			plusButton.setOnAction(e -> {
				calculatorState.addOperator("+");
			});
			rows[0].getChildren().add(plusButton);
			
			Button minusButton = new Button("-");
			minusButton.setOnAction(e -> {
				calculatorState.addOperator("-");
			});
			rows[1].getChildren().add(minusButton);
			
			Button divideButton = new Button("/");
			divideButton.setOnAction(e -> {
				calculatorState.addOperator("/");
			});
			rows[2].getChildren().add(divideButton);
			
			Button timesButton = new Button("*");
			timesButton.setOnAction(e -> {
				calculatorState.addOperator("*");
			});
			rows[3].getChildren().add(timesButton);
			
			Button equalsButton = new Button("=");
			equalsButton.setOnAction(e -> {
				int solution = solve(memory);
				memory.clear();
				memory.add("" + solution);
				calculatorState = firstOpState;
				
			});
			
			Button clearButton = new Button("C");
			clearButton.setOnAction(e -> {
				int solution = solve(memory);
				memory.clear();
				memory.add("" + solution);
				calculatorState = firstOpState;
				
			});
			
			Button clearEntryButton = new Button("CE");
			clearEntryButton.setOnAction(e -> {
				String entry = memory.remove(memory.size());
				try {
					Integer.parseInt(entry);
				}
				catch(NumberFormatException nfe) {
					calculatorState = firstOpState;
				}
				
			});
			
			rows[4].getChildren().addAll(clearButton, clearEntryButton, equalsButton);
			
			scene = new Scene(wrapperPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
		public static int solve(ArrayList<String> memory) {
			return 0;
		}
		
		public static void main(String[] args) {
			Application.launch(args);
		}
}
