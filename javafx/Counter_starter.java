/*
 * EE 422C Fall 2016, Quiz 7
 * Name: <name here>
 * UT EID: <eid here>
 * Unique: 
 */

/*
 * Other comments that will help the TA looking at your code.
 */
package quiz7_javafx;


/**
 * Starter code for Java FX program to increment and display a counter every time a button 
 * is pressed.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Counter_starter extends Application {
	
	// TODO Field here to hold counter value
	int i = 0;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Creating a suitable container to hold the button and the window.
		// GridPane?
		
		// Define Text Field object, and define its value.

		// Add the Text Field object to the container defined above.
		
		// Define Increment Button to press to increment the counter.
		
		// Add the button to the container.
		
		// Add any other label etc.
		
		// Set an action for the Increment button by calling its setOnAction,
		// updating the counter.

		// primaryStage.setScene(new Scene(<container object>, 300, 250));
		primaryStage.show();
	}
}