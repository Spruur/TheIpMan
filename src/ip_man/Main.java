package ip_man;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main { //extends Application implements EventHandler<ActionEvent> {
    //Button button;

    /*@Override  *//*NEEDED FOR GUI PART*//*
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../ip_man/test.fxml"));
        primaryStage.setTitle("The Ip Man");
        button = new Button("Test!");
        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 1000, 200);
        primaryStage.setScene(scene);








        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button) {
            System.out.println("WORKS!");
        }
    }*/

    public static void main(String[] args) {





        //System.out.println(Ip.binaryToIp("11111100111111011000011110001111"));
        //Ip.ipToBinary("1.1.1.1");




        //launch(args); //NEEDED FOR GUI PART
    }
}
