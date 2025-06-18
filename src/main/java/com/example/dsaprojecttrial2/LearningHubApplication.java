package com.example.dsaprojecttrial2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LearningHubApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView(getHostServices());
       // Scene scene = new Scene(mainView, 1200, 750);
        Scene scene = new Scene(mainView);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setTitle("DSA Learning Hub");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
