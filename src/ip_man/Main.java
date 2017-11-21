package ip_man;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("The Ip Man");

        GridPane grid = new GridPane();
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("The IP Man - IP subnetter and calculator");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label addressFieldLabel = new Label("Enter IP address");
        grid.add(addressFieldLabel, 0,  1);

        TextField addressFieldText = new TextField();
        grid.add(addressFieldText, 1, 1);


        Button btn = new Button("Calculate");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 1);


        final Text contentText = new Text();
        grid.add(contentText, 1, 6);


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                contentText.setFill(Color.FIREBRICK);
                contentText.setText("Button pressed");
            }
        });



        Scene scene = new Scene(grid, 1000, 800);
        primaryStage.setScene(scene);

        scene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());








        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {

    }

    public static void main(String[] args) {

        //Ip ip = new Ip("192.168.1.1", "11111111111111111111111100000000");

        /*System.out.println(ip.getAddress());
        System.out.println(ip.getSubnetMask());
        System.out.println(ip.getBinaryAddress());
        System.out.println(ip.getBinarySubnetMask());
        System.out.println(ip.getNetworkAddress());
        System.out.println(ip.getBinaryNetworkAddress());*/
        //Ip.anding("11111100111111011000011110001111", "11111111111111110000000000000000");
        //System.out.println(Ip.binaryToPrefix("11111111111111110000000000000000"));
        //System.out.println(Ip.binaryToPrefix("11111111111111111111000000000000"));
        //System.out.println(Ip.binaryToIp("11111100111111011000011110001111"));
        //Ip.ipToBinary("1.1.1.1");




        launch(args); //NEEDED FOR GUI PART
    }
}
