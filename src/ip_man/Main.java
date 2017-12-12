package ip_man;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main class. Uses JavaFX
 * @author      Karl Hendrik Leppmets
 * @version     0.0.3
 */
public class Main extends Application implements EventHandler<ActionEvent> {
    private String window = "calculator";
    private TextField ipField = new TextField();
    private TextField subnetField = new TextField();
    private Button submitButton = new Button("Calculate");
    private Text contentError = new Text();
    private Text contentAddress = new Text();
    private Text contentAddressBinary = new Text();
    private Text contentSubnet = new Text();
    private Text contentSubnetBinary = new Text();
    private Text contentNetwork = new Text();
    private Text contentNetworkBinary = new Text();
    private Text contentBroadcast = new Text();
    private Text contentBroadcastBinary = new Text();
    private Text contentFirstAddress = new Text();
    private Text contentFirstAddressBinary = new Text();
    private Text contentLastAddress = new Text();
    private Text contentLastAddressBinary = new Text();
    private Text contentHosts = new Text();
    private Text contentNetType = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts JavaFX part. Sets main settings of window
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("The IP Man - IP subnetter and calculator by Karl H. Leppmets");
        BorderPane border = new BorderPane();

        // Determine which content to display. In case I will add more content windows later
        switch (window) {
            case "calculator":
                border.setCenter(addCalculatorContent());
                break;
        }

        //border.setRight(addSideMenu());

        Scene scene = new Scene(border, 1000, 800);
        primaryStage.setScene(scene);

        scene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());

        // Handling key presses
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.ENTER) {
                handleSubmit();
            }
        });



        primaryStage.show();
    }

    /**
     * Creates content for calculator window
     * @return  Returns content as GridPane
     */
    private GridPane addCalculatorContent() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        Text heading = new Text("Calculator");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        grid.add(heading, 1, 0);

        ipField.setPromptText("Enter ip (192.168.1.1)");
        grid.add(ipField, 1, 1);

        subnetField.setPromptText("Enter subnet mask");
        grid.add(subnetField, 2, 1);

        grid.add(submitButton, 3, 1);

        contentError.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        contentError.setId("errormsg");
        grid.add(contentError, 4, 1);
        
        
        
        
        Text labelAddress = new Text("Ip address: ");
        labelAddress.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(labelAddress, 1, 3);

        contentAddress.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        contentAddress.setId("copyable");
        grid.add(contentAddress, 2, 3);

        contentAddressBinary.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentAddressBinary, 3, 3);
        
        
        

        Text labelSubnet = new Text("Subnet mask: ");
        labelSubnet.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(labelSubnet, 1, 4);

        contentSubnet.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentSubnet, 2, 4);


        contentSubnetBinary.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentSubnetBinary, 3, 4);
        
        
        
        

        Text labelNetwork = new Text("Network address: ");
        labelNetwork.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(labelNetwork, 1, 5);

        contentNetwork.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentNetwork, 2, 5);

        contentNetworkBinary.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentNetworkBinary, 3, 5);



        Text labelBroadcast = new Text("Broadcast address: ");
        labelBroadcast.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(labelBroadcast, 1, 6);

        contentBroadcast.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentBroadcast, 2, 6);

        contentBroadcastBinary.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentBroadcastBinary, 3, 6);
        



        Text labelFirstAddress = new Text("First host: ");
        labelFirstAddress.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(labelFirstAddress, 1, 7);

        contentFirstAddress.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentFirstAddress, 2, 7);

        contentFirstAddressBinary.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentFirstAddressBinary, 3, 7);




        Text labelLastAddress = new Text("Last host: ");
        labelLastAddress.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(labelLastAddress, 1, 8);

        contentLastAddress.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentLastAddress, 2, 8);


        contentLastAddressBinary.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentLastAddressBinary, 3, 8);



        
        Text labelHosts = new Text("Hosts: ");
        labelHosts.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(labelHosts, 1, 9);

        contentHosts.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentHosts, 2, 9);

        contentNetType.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(contentNetType, 3, 9);


        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                handleSubmit();
            }
        });

        return grid;
    }


    /**
     * Default handle override needed by JavaFX
     * @param e Event
     */
    @Override
    public void handle(ActionEvent e) {}

    /**
     * Creates IP object based on input data and displays content for calculator text fields
     */
    private void handleSubmit() {
        String address = ipField.getText();
        String subnet = subnetField.getText();

        if (Ip.isValidAddress(address)) {
            // This long subnet check is must be. This because entered subnet can be as IP address, as prefix or as binary.
            if (subnet.length() == 0 || subnet.length() == 32 || Ip.isValidAddress(subnet) || Ip.isValidSubnet(subnet) || (subnet.matches("[-+]?\\d*\\.?\\d+") && Ip.isValidSubnetPrefix(Integer.parseInt(subnet)))) {
                Ip ip = new Ip(address, subnet);
                contentAddress.setText(ip.getAddress());
                contentSubnet.setText(ip.getSubnetMask() + "    (/" + ip.getSubnetPrefix() + ")");
                contentNetwork.setText(ip.getNetworkAddress());
                contentAddressBinary.setText(ip.getBinaryAddress());
                contentSubnetBinary.setText(ip.getBinarySubnetMask());
                contentNetworkBinary.setText(ip.getBinaryNetworkAddress());
                contentBroadcastBinary.setText(ip.getBinaryBroadcastAddress());
                contentBroadcast.setText(ip.getBroadcastAddress());
                contentFirstAddress.setText(ip.getFirstHostAddress());
                contentFirstAddressBinary.setText(ip.getBinaryFirstHostAddress());
                contentLastAddress.setText(ip.getLastHostAddress());
                contentLastAddressBinary.setText(ip.getBinaryLastHostAddress());


                contentHosts.setText(String.valueOf(ip.getAmountOfUsableAddresses()));
                contentError.setText("");
            }
            else {
                contentError.setText("Entered subnet is not valid!");
            }
        }
        else {
            contentError.setText("Entered IP is not valid!");
        }
    }
}
