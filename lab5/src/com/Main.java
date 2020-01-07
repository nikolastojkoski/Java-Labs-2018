package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 5");

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Setup Lab 1
        String[] months = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December"};

        ChoiceBox monthSelector = (ChoiceBox)scene.lookup("#lab1_monthSelector");
        Arrays.asList(months).forEach(month->monthSelector.getItems().add(month));

        TextField dayField = (TextField)scene.lookup("#lab1_dayField");
        TextField yearField = (TextField)scene.lookup("#lab1_yearField");
        Label outputLabel = (Label)scene.lookup("#lab1_outputLabel");

        Button calculateButton = (Button)scene.lookup("#lab1_calculateButton");
        calculateButton.setOnAction(e-> outputLabel.setText(Lab1.calculateAge(dayField.getText(),
                (String)monthSelector.getValue(), yearField.getText())));

        //Setup Lab 2
        TextField numObjectsField = (TextField)scene.lookup("#lab2_numObjectsField");
        Button lab2StartButton = (Button)scene.lookup("#lab2_startButton");
        TextArea lab2TextArea = (TextArea)scene.lookup("#lab2_textArea");

        lab2StartButton.setOnAction(e->{
            lab2TextArea.clear();
            Lab2.Main.start(numObjectsField.getText(), lab2TextArea);});

        //Setup Lab 3
        TextField lab3NumField = (TextField)scene.lookup("#lab3_numField");
        Button lab3StartButton = (Button)scene.lookup("#lab3_startButton");
        TextArea lab3TextArea = (TextArea)scene.lookup("#lab3_textArea");

        lab3StartButton.setOnAction(e->{
            lab3TextArea.clear();
            new Lab3().start(lab3NumField.getText(), lab3TextArea);
        });

        //Setup Lab 4
        TextField lab4NumBoxesField = (TextField)scene.lookup("#lab4_numBoxes");
        TextField lab4NumWorkersField = (TextField)scene.lookup("#lab4_numWorkers");
        Button lab4startButton = (Button)scene.lookup("#lab4_startButton");
        TextArea lab4TextArea = (TextArea)scene.lookup("#lab4_textArea");

        lab4startButton.setOnAction(e->{
            lab4TextArea.clear();
            new Lab4().start(lab4NumWorkersField.getText(), lab4NumBoxesField.getText(), lab4TextArea);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
