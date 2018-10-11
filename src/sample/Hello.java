package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Hello extends Application {
    private P2PSocket connection;

    public void setConnection(String host,int port) {
        connection = new P2PSocket(host,port);
    }

    public void waitConnection() {
        connection = new P2PSocket(6789);
    }

    public void sendMessage(String message) {
        String formattedMessage = "<MESSAGE>"+message+"</MESSAGE>";
        connection.sendMessage(formattedMessage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // UI
        primaryStage.setTitle("Hello world");
        primaryStage.setMaxHeight(480);
        primaryStage.setMinHeight(480);
        primaryStage.setMaxWidth(640);
        primaryStage.setMinWidth(640);
        VBox rootPane = new VBox();
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        StackPane messageArea = new StackPane();
        HBox typingArea = new HBox();
        rootPane.getChildren().addAll(messageArea,typingArea);

        TextArea msgList = new TextArea();
        messageArea.getChildren().addAll(msgList);
        messageArea.prefHeightProperty().bind(scene.heightProperty().subtract(32));

        messageArea.prefWidthProperty().bind(scene.widthProperty());
        messageArea.prefHeight(32);
        TextField inputMessage = new TextField();
        inputMessage.prefWidthProperty().bind(typingArea.widthProperty().subtract(48));
        Button sendMessage = new Button();
        sendMessage.setPrefWidth(48);
        sendMessage.setText("Send");
        sendMessage.setOnAction(event-> {
            String msg = inputMessage.getText();
            sendMessage(msg);
        });
        typingArea.getChildren().addAll(inputMessage,sendMessage);

        primaryStage.show();

        // Socket
    }
}
