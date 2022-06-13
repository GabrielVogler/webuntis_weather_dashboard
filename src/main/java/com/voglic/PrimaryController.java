package com.voglic;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.voglic.backend.SchoolDay;
import com.voglic.backend.Time;
import com.voglic.backend.Weather;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PrimaryController implements Initializable {

    /**
     * Clock Label
     */
    @FXML
    private Label clock;

    /**
     * Date Label
     */
    @FXML
    private Label date;

    /**
     * Icon Label
     */
    @FXML
    private ImageView icon;

    /**
     * Conditions Label
     */
    @FXML
    private Label condition;

    /**
     * Temperature Label
     */
    @FXML
    private Label temp;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson1;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson2;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson3;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson4;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson5;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson6;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson7;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson8;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson9;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson10;

    /**
     * Lesson Label
     */
    @FXML
    private Label lesson11;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher1;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher2;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher3;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher4;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher5;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher6;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher7;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher8;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher9;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher10;

    /**
     * Teacher Label
     */
    @FXML
    private Label teacher11;

    /**
     * Room Label
     */
    @FXML
    private Label room1;

    /**
     * Room Label
     */
    @FXML
    private Label room2;

    /**
     * Room Label
     */
    @FXML
    private Label room3;

    /**
     * Room Label
     */
    @FXML
    private Label room4;

    /**
     * Room Label
     */
    @FXML
    private Label room5;

    /**
     * Room Label
     */
    @FXML
    private Label room6;

    /**
     * Room Label
     */
    @FXML
    private Label room7;

    /**
     * Room Label
     */
    @FXML
    private Label room8;

    /**
     * Room Label
     */
    @FXML
    private Label room9;

    /**
     * Room Label
     */
    @FXML
    private Label room10;

    /**
     * Room Label
     */
    @FXML
    private Label room11;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox1;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox2;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox3;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox4;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox5;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox6;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox7;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox8;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox9;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox10;

    /**
     * Lesson HBOX
     */
    @FXML
    private HBox hbox11;

    private Label[][] containers = {
            { lesson1, teacher1, room1 },
            { lesson2, teacher2, room2 },
            { lesson3, teacher3, room3 },
            { lesson4, teacher4, room4 },
            { lesson5, teacher5, room5 },
            { lesson6, teacher6, room6 },
            { lesson7, teacher7, room7 },
            { lesson8, teacher8, room8 },
            { lesson9, teacher9, room9 },
            { lesson10, teacher10, room10 },
            { lesson11, teacher11, room11 },
    };

    private HBox[] hbox = { hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10, hbox11 };

    /**
     * Clock and Date Thread Loop
     */
    private void clockLoop() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    clock.setText(Time.getTime()); // set Clock
                    date.setText(Time.getDate()); // set Date
                });
            }
        };

        Timer t = new Timer();
        t.scheduleAtFixedRate(
                task, // called Method
                0, // delay before first execution
                1000L); // time between executions 1000 ms = 1s
    }

    /**
     * Weather Thread Loop
     */
    private void checkWeather() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Path imageFile = Paths.get(Weather.getIcon("Vienna")); // make Path
                    try {
                        icon.setImage(new Image(imageFile.toUri().toURL().toExternalForm())); // set new Icon
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    condition.setText(Weather.getWeather("Vienna")); // set Condition
                    temp.setText(Weather.getTempString("Vienna") + "°C"); // set Temperature
                });
            }
        };

        Timer t = new Timer();
        t.scheduleAtFixedRate(
                task, // called Method
                0, // delay before first execution
                60000L); // time between executions 60000 ms = 60s
    }

    /**
     * Lesson Update Loop
     */
    private void updateLessons() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    SchoolDay day = new SchoolDay("files/JSON_Stunden/timetable.json");
                    String[][] lessons = day.getSubjects(); // get Lessons
                    // System.out.println(Arrays.deepToString(lessons)); // print Lessons
                    for (int i = 0; i < lessons.length; i++) {
                        for (int j = 0; j < lessons[i].length; j++) {
                            if (j < 3) {
                                System.out.println(lessons[i][j]);
                                //containers[i][j].setText(lessons[i][j]); // set Lessons                                
                            } else {
                                //hbox[i].setStyle("-fx-background-color: " + lessons[i][3]); // set Color
                            }

                        }
                    }
                    lesson1.setText(lessons[0][0]);
                    teacher1.setText(lessons[0][1]);
                    room1.setText(lessons[0][2]);
                    hbox1.setStyle("-fx-background-color: " + lessons[0][3]);

                    lesson2.setText(lessons[1][0]);
                    teacher2.setText(lessons[1][1]);
                    room2.setText(lessons[1][2]);
                    hbox2.setStyle("-fx-background-color: " + lessons[1][3]);

                    lesson3.setText(lessons[2][0]);
                    teacher3.setText(lessons[2][1]);
                    room3.setText(lessons[2][2]);
                    hbox3.setStyle("-fx-background-color: " + lessons[2][3]);

                    lesson4.setText(lessons[3][0]);
                    teacher4.setText(lessons[3][1]);
                    room4.setText(lessons[3][2]);
                    hbox4.setStyle("-fx-background-color: " + lessons[3][3]);

                    lesson5.setText(lessons[4][0]);
                    teacher5.setText(lessons[4][1]);
                    room5.setText(lessons[4][2]);
                    hbox5.setStyle("-fx-background-color: " + lessons[4][3]);

                    lesson6.setText(lessons[5][0]);
                    teacher6.setText(lessons[5][1]);
                    room6.setText(lessons[5][2]);
                    hbox6.setStyle("-fx-background-color: " + lessons[5][3]);

                    lesson7.setText(lessons[6][0]);
                    teacher7.setText(lessons[6][1]);
                    room7.setText(lessons[6][2]);
                    hbox7.setStyle("-fx-background-color: " + lessons[6][3]);

                    lesson8.setText(lessons[7][0]);
                    teacher8.setText(lessons[7][1]);
                    room8.setText(lessons[7][2]);
                    hbox8.setStyle("-fx-background-color: " + lessons[7][3]);

                    lesson9.setText(lessons[8][0]);
                    teacher9.setText(lessons[8][1]);
                    room9.setText(lessons[8][2]);
                    hbox9.setStyle("-fx-background-color: " + lessons[8][3]);

                    lesson10.setText(lessons[9][0]);
                    teacher10.setText(lessons[9][1]);
                    room10.setText(lessons[9][2]);
                    hbox10.setStyle("-fx-background-color: " + lessons[9][3]);

                    lesson11.setText(lessons[10][0]);
                    teacher11.setText(lessons[10][1]);
                    room11.setText(lessons[10][2]);
                    hbox11.setStyle("-fx-background-color: " + lessons[10][3]);
                });
            }
        };

        Timer t = new Timer();
        t.scheduleAtFixedRate(
                task, // called Method
                0, // delay before first execution
                10000L); // time between executions 10000 ms = 10s
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        clockLoop();
        checkWeather();
        updateLessons();
    }
}
