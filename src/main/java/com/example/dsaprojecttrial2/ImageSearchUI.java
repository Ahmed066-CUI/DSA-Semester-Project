package com.example.dsaprojecttrial2;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.concurrent.Task;
import java.util.PriorityQueue;

public class ImageSearchUI extends VBox {
    private FlowPane imageResultsBox;
    private APIImageSearchEngine searchEngine;
    private final HostServices hostServices;

    public ImageSearchUI(HostServices hostServices) {
        super(10);
        this.hostServices = hostServices;
        searchEngine = new APIImageSearchEngine();
        initializeUI();
    }

    private void initializeUI() {
        setPadding(new Insets(10));
        setAlignment(Pos.TOP_CENTER);

        imageResultsBox = new FlowPane();
        imageResultsBox.setPadding(new Insets(10));
        imageResultsBox.setHgap(15);
        imageResultsBox.setVgap(15);
        imageResultsBox.setPrefWrapLength(550); // Wrap after this width
        //imageResultsBox.setPrefWrapLength(730);
        imageResultsBox.setAlignment(Pos.TOP_CENTER);

        Label placeholderLabel = new Label("Enter a search term in the global search bar above to find images.");
        placeholderLabel.setWrapText(true);
        placeholderLabel.setMaxWidth(600);
        placeholderLabel.setAlignment(Pos.CENTER);
        imageResultsBox.getChildren().add(placeholderLabel);

        ScrollPane scrollPane = new ScrollPane(imageResultsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        getChildren().add(scrollPane);
    }

    public void searchImagesByTopic(String query) {
        if (query == null || query.trim().isEmpty()) {
            clearSearch();
            return;
        }
        displayImages(query.trim());
    }

    public void clearSearch() {
        imageResultsBox.getChildren().clear();
        Label placeholderLabel = new Label("Enter a search term in the global search bar above to find images.");
        placeholderLabel.setWrapText(true);
        placeholderLabel.setMaxWidth(600);
        placeholderLabel.setAlignment(Pos.CENTER);
        imageResultsBox.getChildren().add(placeholderLabel);
    }

    private void displayImages(String query) {
        imageResultsBox.getChildren().clear();

        // Use Task for background processing
        Task<Void> imageLoadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                PriorityQueue<ImageResult> images = searchEngine.searchImages(query);
                int maxImages = 20; // may set limit to any top images
                while (!images.isEmpty() && maxImages-- > 0) {
                    ImageResult result = images.poll();
                    try {
                        Image img = new Image(result.getUrl(), 180, 180, true, true);
                        ImageView imageView = new ImageView(img);
                        imageView.setSmooth(true);
                        imageView.setPreserveRatio(true);

                        Label titleLabel = new Label(result.getTitle());
                        titleLabel.setWrapText(true);
                        titleLabel.setMaxWidth(180);

                        VBox imageBox = new VBox(5, imageView, titleLabel);
                        imageBox.setAlignment(Pos.CENTER);
                        imageBox.setPadding(new Insets(8));

                        final VBox finalImageBox = imageBox; //used threads to save program form freezing
                        Platform.runLater(() -> {
                            imageBox.setOnMouseClicked(e -> hostServices.showDocument(result.getUrl()));
                            imageResultsBox.getChildren().add(finalImageBox);
                        });
                    } catch (Exception ex) {
                        System.out.println("Failed to load image: " + result.getUrl());
                    }
                }
                return null;
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    Label errorLabel = new Label("Failed to load images for \"" + query + "\".");
                    errorLabel.setWrapText(true);
                    errorLabel.setMaxWidth(600);
                    errorLabel.setAlignment(Pos.CENTER);
                    imageResultsBox.getChildren().add(errorLabel);
                });
            }
        };

        new Thread(imageLoadTask).start();
    }
}