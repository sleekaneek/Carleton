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

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SliderPuzzleGame extends Application {

    boolean startState = true; // true : Start, false : Stop
    Button[][] buttons;
    Label thumbNail, timeLabel;
    ListView<String> puzzlesList;
    Button startStop;
    String[][] buttonImages;
    Pane mainPane;
    public void start(Stage primaryStage) {
        mainPane = new Pane();
        // Listviews
        puzzlesList = new ListView<>();
        String[] puzzles = {"Pets", "Scenery", "Lego", "Numbers"};
        puzzlesList.setItems(FXCollections.observableArrayList(puzzles));
        puzzlesList.relocate(768, 207);
        puzzlesList.setPrefSize(187, 150);
        puzzlesList.getSelectionModel().select(0);

        // Thumbnail
        thumbNail = new Label();
        thumbNail.setPrefSize(187, 187);
        thumbNail.relocate(768, 10);
        thumbNail.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Pets_Thumbnail.png"))));

        // Start Button
        startStop = new Button();
        startStop.relocate(768, 367);
        startStop.setPrefSize(187, 25);
        startStop.setStyle("-fx-color: DARKGREEN");
        startStop.setText("Start");

        timeLabel = new Label("Time:");
        timeLabel.relocate(768, 406);
        TextField timeField = new TextField();//

        // Time
        timeField.relocate(852, 402);
        timeField.setPrefSize(105, 25);
        timeField.setAlignment(Pos.CENTER_LEFT);
        timeField.setText("0:00");

        // Buttons imgaes
        buttons = new Button[4][4];
        buttonImages = new String[][] {{"Lego_00.png", "Lego_01.png", "Lego_03.png", "Lego_10.png", "Lego_11.png", "Lego_12.png", "Lego_13.png",
                "Lego_20.png", "Lego_21.png", "Lego_22.png", "Lego_23.png", "Lego_30.png", "Lego_31.png", "Lego_32.png", "Lego_33.png"}, {"Numbers.00.png",
                "Numbers_01.png", "Numbers_02.png", "Numbers_03.png", "Numbers_10.png", "Numbers_11.png", "Numbers_12.png",
                "Numbers_13.png", "Numbers_20.png", "Numbers_21.png", "Numbers_22.png", "Numbers_23.png", "Numbers_30.png",
                "Numbers_31.png", "Numbers_32.png", "Numbers_33.png"}, {"Pets_00.png", "Pets_01.png", "Pets_02.png",
                "Pets_03.png", "Pets_10.png", "Pets_11.png", "Pets_12.png", "Pets_13.png", "Pets_20.png", "Pets_21.png", "Pets_22.png", "Pets_23.png", "Pets_30.png",
                "Pets_31.png", "Pets_32.png", "Pets_33.png"}, {"Scenery_00.png", "Scenery_01.png", "Scenery_02.png",
                "Scenery_03.png", "Scenery_10.png", "Scenery_11.png", "Scenery_12.png", "Scenery_13.png", "Scenery_20.png", "Scenery_21.png", "Scenery_22.png", "Scenery_23.png",
                "Scenery_31.png", "Scenery_32.png", "Scenery_33.png"}};

        int tileCount = buttonImages[2].length - 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].relocate(10 + j * 187, 10 + i * 187);
                buttons[i][j].setPrefSize(187, 187);
                buttons[i][j].setPadding(new Insets(1, 1, 1, 1));
                String tile = buttonImages[2][tileCount];
                buttons[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream(tile))));
                mainPane.getChildren().addAll(buttons[i][j]);
                tileCount--;
            }

        }


//        public void swap(i,j){
//
//        }




        // Updates the thumbnail
        puzzlesList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    String choice = puzzlesList.getSelectionModel().getSelectedItem();
                    String thumbName = "BLANK.png";

                    if (choice.equals("Lego")){
                        thumbName= "Lego_Thumbnail.png";
                    }
                    else if (choice.equals("Pets")){
                        thumbName = "Pets_Thumbnail.png";
                    }
                    else if (choice.equals("Numbers")){
                        thumbName = "Numbers_Thumbnail.png";
                    }
                    else if (choice.equals("Scenery")){
                        thumbName = "Scenery_Thumbnail.png";
                    }

                    thumbNail.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(thumbName))));

                    ShufflePuzzleImages(choice);
                }
            }
        });







        startStop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    if (startState) { // start state
                        startStop.setText("Stop");
                        startStop.setStyle("-fx-color: DARKRED");
                        thumbNail.setDisable(true);
                        puzzlesList.setDisable(true);
                        startState = false;
                    } else { // stop state
                        startStop.setText("Start");
                        startStop.setStyle("-fx-color: DARKGREEN");
                        thumbNail.setDisable(false);
                        puzzlesList.setDisable(false);
                        startState = true;
                    }
                }
            }
        });



        mainPane.getChildren().addAll(thumbNail, puzzlesList, startStop, timeLabel, timeField);
        primaryStage.setTitle("Slider Puzzle Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, 955, 748));
        primaryStage.show();
    }

    public void ShufflePuzzleImages(String listChoice){
        // get the corresponding array of images to listChoice
        int index = -1; // invalid
        if (listChoice.equals("Lego")){
            index= 0;
        }
        else if (listChoice.equals("Pets")){
            index = 2;
        }
        else if (listChoice.equals("Numbers")){
            index = 1;
        }
        else if (listChoice.equals("Scenery")){
            index = 3;
        }

        String[] tiles = buttonImages[index];

        // shuffle the array

        Random rnd = ThreadLocalRandom.current();
        for (int i = tiles.length - 1; i > 0; i--)
        {
            int ind = rnd.nextInt(i + 1);
            // Simple swap
            String a = tiles[ind];
            tiles[ind] = tiles[i];
            tiles[i] = a;
        }

        int tileCount = tiles.length - 1;
        // load the array on to the tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream(tiles[tileCount]))));
                tileCount--;
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
