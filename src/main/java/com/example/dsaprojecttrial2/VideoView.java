package com.example.dsaprojecttrial2;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.Desktop;
import java.net.URI;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class VideoView extends VBox {
    private VideoService videoService;
    private VBox videoContainer;

    public VideoView() {
        videoService = new VideoService();
        initializeUI();
    }


    private void initializeUI() {
        //main container ui
        setSpacing(20);
        setPadding(new Insets(30));
        setStyle("-fx-background-color: white;");
        //video content section
        ScrollPane videoScrollPane = createVideoScrollPane();
        // Add scroll pane to the main container
        getChildren().add(videoScrollPane);
        //make scroll pane fill the available space
        VBox.setVgrow(videoScrollPane, Priority.ALWAYS);
    }

    //creating video scrollpane
    private ScrollPane createVideoScrollPane() {
        videoContainer = new VBox(20);
        videoContainer.setPadding(new Insets(20));
        videoContainer.setAlignment(Pos.TOP_CENTER);

        // Initial placeholder content
        VBox placeholderContent = createPlaceholderContent();
        videoContainer.getChildren().add(placeholderContent);

        ScrollPane scrollPane = new ScrollPane(videoContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: #f8f9fa; -fx-background: #f8f9fa;");

        return scrollPane;
    }

    //it shows when nothing is searched
    private VBox createPlaceholderContent() {
        VBox placeholder = new VBox(20);
        placeholder.setAlignment(Pos.CENTER);
        placeholder.setPadding(new Insets(60));

        Label iconLabel = new Label("🎥");
        iconLabel.setStyle("-fx-font-size: 60px;");

        Label messageLabel = new Label("Use the search bar above to find educational videos");
        messageLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 18px;");
        messageLabel.setWrapText(true);

        Label hintLabel = new Label("Try searching for topics like 'Binary Search', 'Linked List', or 'Dynamic Programming'");
        hintLabel.setStyle("-fx-text-fill: #adb5bd; -fx-font-size: 14px;");
        hintLabel.setWrapText(true);

        placeholder.getChildren().addAll(iconLabel, messageLabel, hintLabel);
        return placeholder;
    }


    public void searchVideosByTopic(String topic) {
        if (topic == null || topic.trim().isEmpty()) {
            showErrorContent("Please enter a valid search topic");
            return;
        }

        // Clear video container and show searching placeholder
        videoContainer.getChildren().clear();
        VBox searchingPlaceholder = createSearchingPlaceholder(topic);
        videoContainer.getChildren().add(searchingPlaceholder);

        // Create a background task to fetch videos
        Task<List<Video>> fetchVideosTask = new Task<>() {
            @Override
            protected List<Video> call() throws Exception {
                return videoService.searchVideosByTopic(topic);
            }

            @Override
            protected void succeeded() {
                List<Video> videos = getValue();
                Platform.runLater(() -> displayVideos(videos, topic));
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> showErrorContent("Failed to search for videos about " + topic + ". Please try again."));
            }
        };

        // Start the background task
        new Thread(fetchVideosTask).start();
    }

    //when searching is being done this content is shown
    private VBox createSearchingPlaceholder(String topic) {
        VBox searchingPlaceholder = new VBox(20);
        searchingPlaceholder.setAlignment(Pos.CENTER);
        searchingPlaceholder.setPadding(new Insets(60));

        Label iconLabel = new Label("🔍");
        iconLabel.setStyle("-fx-font-size: 50px;");

        Label searchingLabel = new Label("Searching for videos about " + topic + "...");
        searchingLabel.setStyle("-fx-text-fill: #1e88e5; -fx-font-size: 16px; -fx-font-weight: bold;");

        searchingPlaceholder.getChildren().addAll(iconLabel, searchingLabel);
        return searchingPlaceholder;
    }

    //display in form of list
    private void displayVideos(List<Video> videos, String searchTopic) {
        videoContainer.getChildren().clear();

        if (videos.isEmpty()) {
            showNoResultsContent(searchTopic);
            return;
        }
        //when found this shows
        HBox resultsHeader = new HBox();
        resultsHeader.setAlignment(Pos.CENTER_LEFT);
        resultsHeader.setPadding(new Insets(0, 0, 20, 0));

        Label resultsLabel = new Label("Found " + videos.size() + " videos for '" + searchTopic + "'");
        resultsLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 18px; -fx-font-weight: bold;");
        resultsHeader.getChildren().add(resultsLabel);

        videoContainer.getChildren().add(resultsHeader);
        //creating videos card for every video
        for (Video video : videos) {
            VBox videoCard = createVideoCard(video);
            videoContainer.getChildren().add(videoCard);
        }
    }

    //when no videos or available
    private void showNoResultsContent(String searchTopic) {
        VBox noResultsContent = new VBox(20);
        noResultsContent.setAlignment(Pos.CENTER);
        noResultsContent.setPadding(new Insets(60));

        Label iconLabel = new Label("😔");
        iconLabel.setStyle("-fx-font-size: 50px;");

        Label messageLabel = new Label("No videos found for '" + searchTopic + "'");
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 18px; -fx-font-weight: bold;");

        Label suggestionLabel = new Label("Try a different search term or check your spelling");
        suggestionLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 14px;");

        noResultsContent.getChildren().addAll(iconLabel, messageLabel, suggestionLabel);
        videoContainer.getChildren().add(noResultsContent);
    }

    //
    private void showErrorContent(String errorMessage) {
        videoContainer.getChildren().clear();

        VBox errorContent = new VBox(20);
        errorContent.setAlignment(Pos.CENTER);
        errorContent.setPadding(new Insets(60));

        Label iconLabel = new Label("⚠️");
        iconLabel.setStyle("-fx-font-size: 50px;");

        Label messageLabel = new Label(errorMessage);
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 16px;");
        messageLabel.setWrapText(true);

        errorContent.getChildren().addAll(iconLabel, messageLabel);
        videoContainer.getChildren().add(errorContent);
    }

   //creating card
    private VBox createVideoCard(Video video) {
        VBox videoCard = new VBox(15);
        videoCard.setPadding(new Insets(20));
        videoCard.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 12; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);"
        );

        // Create thumbnail and info container
        HBox contentContainer = new HBox(20);
        contentContainer.setAlignment(Pos.CENTER_LEFT);

        // Create thumbnail
        VBox thumbnailContainer = new VBox();
        thumbnailContainer.setAlignment(Pos.CENTER);

        ImageView thumbnail = new ImageView();
        thumbnail.setFitWidth(250);
        thumbnail.setFitHeight(150);
        thumbnail.setPreserveRatio(true);

        // Load thumbnail image
        try {
            Image image = new Image(video.getThumbnailUrl(), true);
            thumbnail.setImage(image);
        } catch (Exception e) {
            // If thumbnail loading fails, display a placeholder
            Rectangle placeholderRect = new Rectangle(250, 150);
            placeholderRect.setStyle("-fx-fill: linear-gradient(to bottom, #e9ecef, #dee2e6);");
            StackPane placeholder = new StackPane(placeholderRect);
            Label placeholderLabel = new Label("🎥");
            placeholderLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #6c757d;");
            placeholder.getChildren().add(placeholderLabel);
            thumbnailContainer.getChildren().add(placeholder);
        }

        if (thumbnail.getImage() != null) {
            thumbnail.setStyle("-fx-background-radius: 8;");
            thumbnailContainer.getChildren().add(thumbnail);
        }

        // Create video details container
        VBox detailsBox = new VBox(8);
        detailsBox.setAlignment(Pos.TOP_LEFT);
        HBox.setHgrow(detailsBox, Priority.ALWAYS);

        // Title
        Label titleLabel = new Label(video.getTitle());
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-text-fill: #2c3e50;");

        // Channel name
        Label channelLabel = new Label("" + video.getChannelName());
        channelLabel.setStyle("-fx-text-fill: #1e88e5; -fx-font-weight: bold;");

        // View count and duration
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        String ViewsOnVideo = formatter.format(video.getViewCount()) + " views";
        Label viewsAndDurationLabel = new Label("" + ViewsOnVideo + "   "  + video.getFormattedDuration());
        viewsAndDurationLabel.setStyle("-fx-text-fill: #6c757d;");

        // Description
        Label descriptionLabel = new Label(truncateDescription(video.getDescription(), 150));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 14px;");
        detailsBox.getChildren().addAll(titleLabel, channelLabel, viewsAndDurationLabel, descriptionLabel);
        contentContainer.getChildren().addAll(thumbnailContainer, detailsBox);

        // Watch button
        Button watchButton = new Button("Watch Video");
        watchButton.setPrefWidth(150);
        watchButton.setPrefHeight(40);
        watchButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #f1c40f, #f39c12); " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 20; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand;"
        );
        watchButton.setOnAction(e -> openVideoInBrowser(video));

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.getChildren().add(watchButton);

        videoCard.getChildren().addAll(contentContainer, buttonContainer);

        return videoCard;
    }


    private String truncateDescription(String description, int maxLength) {
        if (description == null || description.length() <= maxLength) {
            return description;
        }
        return description.substring(0, maxLength) + "...";
    }

    //method to open video in browser
    private void openVideoInBrowser(Video video) {
        try {
            Desktop.getDesktop().browse(new URI(video.getUrl()));
        } catch (Exception e) {
            showAlert("Failed to open video in browser. URL: " + video.getUrl());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}