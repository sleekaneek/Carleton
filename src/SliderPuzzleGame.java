import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SliderPuzzleGame extends Application {

    public void start(Stage primaryStage) {
        Pane mainPane = new Pane();

        Button[][] buttons;
        buttons = new Button[4][4];
        String[][] buttonImages = {{"Lego_00.png","Lego_01.png", "Lego_03.png","Lego_04.png","Lego_05.png","Lego_06.png",
                "Lego_07.png","Lego_08.png","Lego_09.png","Lego_10.png","Lego_11.png","Lego_12.png","Lego_13.png",
                "Lego_14.png","Lego_15.png","Lego_16.png","Lego_17.png","Lego_18.png","Lego_19.png","Lego_20.png",
                "Lego_21.png","Lego_22.png","Lego_23.png","Lego_24.png","Lego_25.png","Lego_26.png","Lego_27.png",
                "Lego_28.png","Lego_29.png","Lego_30.png","Lego_31.png","Lego_32.png","Lego_33.png"},{"Numbers.00.png",
                "Numbers_01.png","Numbers_02.png","Numbers_03.png","Numbers_04.png","Numbers_05.png","Numbers_06.png",
                "Numbers_07.png","Numbers_08.png","Numbers_09.png","Numbers_10.png","Numbers_11.png","Numbers_12.png",
                "Numbers_13.png","Numbers_14.png","Numbers_15.png","Numbers_16.png","Numbers_17.png","Numbers_18.png",
                "Numbers_19.png","Numbers_20.png","Numbers_21.png","Numbers_22.png","Numbers_23.png","Numbers_24.png",
                "Numbers_25.png","Numbers_26.png","Numbers_27.png","Numbers_28.png","Numbers_29.png","Numbers_30.png",
                "Numbers_31.png","Numbers_32.png","Numbers_33.png"},{"Pets_00.png","Pets_01.png","Pets_02.png",
                "Pets_03.png","Pets_04.png","Pets_05.png","Pets_06.png","Pets_07.png","Pets_08.png","Pets_09.png",
                "Pets_10.png","Pets_11.png","Pets_12.png","Pets_13.png","Pets_14.png","Pets_15.png","Pets_16.png",
                "Pets_17.png","Pets_18.png","Pets_19.png","Pets_20.png","Pets_21.png","Pets_22.png","Pets_23.png",
                "Pets_24.png","Pets_25.png","Pets_26.png","Pets_27.png","Pets_28.png","Pets_29.png","Pets_30.png",
                "Pets_31.png","Pets_32.png","Pets_33.png"},{"Scenery_00.png","Scenery_01.png","Scenery_02.png",
                "Scenery_03.png","Scenery_04.png","Scenery_05.png","Scenery_06.png","Scenery_07.png","Scenery_08.png","Scenery_09.png",
                "Scenery_10.png","Scenery_11.png","Scenery_12.png","Scenery_13.png","Scenery_14.png","Scenery_15.png","Scenery_16.png",
                "Scenery_17.png","Scenery_18.png","Scenery_19.png","Scenery_20.png","Scenery_21.png","Scenery_22.png","Scenery_23.png",
                "Scenery_24.png","Scenery_25.png","Scenery_26.png","Scenery_27.png","Scenery_28.png","Scenery_29.png","Scenery_30.png",
                "Scenery_31.png","Scenery_32.png","Scenery_33.png"}};
        for (int i = 0; i<4; i++) {
            for (int j =0; j<4; j++){
                buttons[i][j] = new Button();
                buttons[i][j].relocate(10+j*187,10+i*187);
                buttons[i][j].setPrefSize(187,187);
                buttons[i][j].setPadding(new Insets(1,1,1,1));
                buttons[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("BLANK.png"))));
                mainPane.getChildren().addAll(buttons[i][j]);

            }

        }



//        public void swap(i,j){
//
//        }


        // Listviews
        ListView<String> puzzlesList = new ListView<>();
        String[] puzzles = {"Pets","Scenery","Logo","Numbers"};
        puzzlesList.setItems(FXCollections.observableArrayList(puzzles));
        puzzlesList.relocate(768, 207);
        puzzlesList.setPrefSize(187, 150);

        puzzlesList.getSelectionModel().select(0);

        // Thumbnail
        Label thumbNail = new Label();
        thumbNail.setPrefSize(187,187);
        thumbNail.relocate(768,10);
        thumbNail.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Lego_Thumbnail.png"))));

        // Start Button
        Button startStop = new Button();
        startStop.relocate(768,367);
        startStop.setPrefSize(187,25);
        startStop.setStyle("-fx-color: DARKGREEN");
        startStop.setText("Start");


        startStop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    startStop.setText("Stop");
                    startStop.setStyle("-fx-color: DARKRED");
                    thumbNail.setDisable(true);
                    puzzlesList.setDisable(true);
                }
            }
        });
        if(startStop.getText().equals("Stop"))
        startStop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent f) {
                if (f.getButton() == MouseButton.PRIMARY) {
                    startStop.setText("Start");
                    startStop.setStyle("-fx-color: DARKGREEN");
                    thumbNail.setDisable(false);
                    puzzlesList.setDisable(false);
                }
            }
        });
//        startStop.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent e) {
//                if (e.getButton() == MouseButton.PRIMARY) {
//                    puzzlesList.setOnAction(e )
//                }
//            }
//        });
        // Time
        Label timeLabel = new Label("Time:");
        timeLabel.relocate(768,406);
        TextField timeField = new TextField();
        timeField.relocate(852,402);
        timeField.setPrefSize(105,25);
        timeField.setAlignment(Pos.CENTER_LEFT);
        timeField.setText("0:00");

        mainPane.getChildren().addAll(thumbNail,puzzlesList,startStop,timeLabel,timeField);
        primaryStage.setTitle("Slider Puzzle Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, 955,748));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
