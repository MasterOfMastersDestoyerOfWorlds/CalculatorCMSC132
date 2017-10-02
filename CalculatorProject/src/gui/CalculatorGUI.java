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
		Scene scene;
		VBox wrapperPane;
		HBox[] rows;
		final int NUM_ROWS = 5;
		final int SPACING = 10;
		ArrayList<String> memory = new ArrayList<String>();
	
		@Override
		public void start(Stage primaryStage) throws Exception {
			wrapperPane = new VBox(SPACING);
			rows = new HBox[NUM_ROWS];
			
			for(int i = 3; i >= 0; i--) {
				rows[i+1] = new HBox(SPACING);
				wrapperPane.getChildren().add(rows[i+1]);
				for(int j = 1; j <= 3; j++) { 
					final int NUMBER = ((i-1)*3 + j);
					
					if(NUMBER > -1) {
						Button numberButton = new Button("" + NUMBER);
						
						numberButton.setOnAction(e -> {
							memory.add("" + (NUMBER));
							System.out.println(NUMBER);
						});
						
						rows[i+1].getChildren().add(numberButton);
					}
				}
			}
			
			scene = new Scene(wrapperPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
		public static void main(String[] args) {
			Application.launch(args);
		}
}
