import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AlarmApp extends Application {
    public void start(Stage primaryStage) {
        BorderPane aPane = new BorderPane();
        Pane innerPane1 = new Pane();
        Pane innerPane2 = new Pane();
        Pane innerPane3 = new Pane();
        innerPane1.setStyle("-fx-background-color: white;\n" +
                "  -fx-border-color: gray;  -fx-padding: 4 4;");
        innerPane2.setStyle("-fx-background-color: white;\n" +
                "  -fx-border-color: gray;  -fx-padding: 4 4;");
        innerPane3.setStyle("-fx-background-color: white;\n" +
                "  -fx-border-color: gray;  -fx-padding: 4 4;");

        TextField CurrentTime = new TextField();
        CurrentTime.relocate(100, 20);
        CurrentTime.setPrefSize(300, 30);








        primaryStage.setTitle("Alarm App");
        primaryStage.setScene(new Scene(aPane, 500,250));
        primaryStage.show();
}
    public static void main(String[] args) {
        launch(args);
    }
}