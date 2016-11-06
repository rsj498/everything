package counter;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 

public class Counter extends Application {
    public static void main(String[] args) {
        launch(args);
    }
     
    static int i = 0;
    @Override
    public void start(Stage primaryStage) {
    	GridPane grid = new GridPane();
    	
        primaryStage.setTitle("counter");
        Label counterLabel = new Label(i + " ");
        
        Button btn = new Button();
        btn.setText("increase it");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                counterLabel.setText(Integer.toString(i));
                i++;
               
            }
        });
        
       // StackPane root = new StackPane();
        grid.getChildren().add(btn);
       
		grid.add(counterLabel, 0 ,1);
        primaryStage.setScene(new Scene(grid, 300, 250));
        primaryStage.show();
    }
}