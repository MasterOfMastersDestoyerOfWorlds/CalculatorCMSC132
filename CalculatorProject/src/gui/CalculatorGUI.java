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
		private final int NUM_ROWS = 6;
		private final int SPACING = 10;
		protected ArrayList<String> memory = new ArrayList<String>();
		protected State calculatorState;
		protected State firstOpState = new FirstOperatorState();
		protected State secondOpState = new SecondOperatorState();
		protected State errorState = new ErrorState();
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
							memory.add("" + (NUMBER));
							updateAnswerBox();
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
				String entry = memory.remove(memory.size());
				
				try {
					Integer.parseInt(entry);
				}
				catch(NumberFormatException nfe) {
					calculatorState = firstOpState;
				}
				updateAnswerBox();
				
			});
			
			Button squareRootButton = new Button("√");
			squareRootButton.setOnAction(e -> {
				calculatorState.addOperator("sqrt", this);
				resetMem();
				updateAnswerBox();
			});
			
			Button inverseButton = new Button("1/x");
			inverseButton.setOnAction(e -> {
				calculatorState.addOperator("1/x", this);
				resetMem();
				updateAnswerBox();
			});
			
			Button clearButton = new Button("C");
			clearButton.setOnAction(e -> {
				memory.clear();
				calculatorState = firstOpState;
				updateAnswerBox();
				
			});
			
			rows[1].getChildren().addAll(squareRootButton, inverseButton, clearEntryButton, clearButton);

			addOperatorButton("-", 2);
			addOperatorButton("+", 3);
			addOperatorButton("/", 4);
			
			Button equalsButton = new Button("=");
			equalsButton.setOnAction(e -> {
				resetMem();
				updateAnswerBox();
			});
			
			Button decimalButton = new Button(".");
			clearButton.setOnAction(e -> {
				memory.add(".");
				updateAnswerBox();
			});
			
			rows[5].getChildren().addAll( decimalButton, equalsButton);
			
			addOperatorButton("*", 5);
			
			scene = new Scene(wrapperPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
		private void addOperatorButton(String op, int row) {
			Button button = new Button(op);
			button.setOnAction(e -> {
				calculatorState.addOperator(op, this);
				updateAnswerBox();
			});
			rows[row].getChildren().add(button);
		}
		
		protected void resetMem() {
			int solution = solve(memory);
			memory.clear();
			memory.add("" + solution);
			calculatorState = firstOpState;
		}
		
		protected void updateAnswerBox() {
			String compialation = "";
			for(String op : memory) {
				compialation += op;
			}
			answerBox.setText(compialation);
		}
		
		public static int solve(ArrayList<String> memory) {
			return 0;
		}
		
		public static void main(String[] args) {
			Application.launch(args);
		}
}
