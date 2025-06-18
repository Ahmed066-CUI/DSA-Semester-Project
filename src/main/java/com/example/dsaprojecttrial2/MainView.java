
package com.example.dsaprojecttrial2;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class MainView extends BorderPane {
    private ScrollPane contentScrollPane;
    private VideoView videoView;
    private PDFView pdfView;
    private QuestionView questionView;
    private CheatSheetView cheatSheetView;
    private WebsiteSearchUI websiteSearchUI;
    private ImageSearchUI imageSearchUI; // Added ImageSearchUI
    private VBox currentContent;
    private TextField searchField;
    private Button searchButton;
    private String currentSearchTerm = "";
    private ToggleButton videosButton;
    private ToggleButton pdfsButton;
    private ToggleButton imagesButton;
    private ToggleButton websitesButton;
    private ToggleButton questionsButton;
    private ToggleButton cheatCodeButton;
    private ToggleGroup tabGroup;
    private final HostServices hostServices;

    public MainView(HostServices hostServices) {
        this.hostServices = hostServices;
        initializeUI();
        videosButton.setSelected(true);
        showContent("Videos");
    }

    private void initializeUI() {
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(0);
        mainContainer.setStyle("-fx-background-color: linear-gradient(to bottom, #e6f0fa, #8a9a5b);");

        VBox headerSection = createHeader();
        HBox searchSection = createSearchSection();
        HBox tabsSection = createTabsSection();

        contentScrollPane = new ScrollPane();
        contentScrollPane.setFitToWidth(true);
        contentScrollPane.setFitToHeight(true);
        contentScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        contentScrollPane.setStyle("-fx-background-color: #f9f9fb; -fx-background: #f9f9fb;");

        videoView = new VideoView();
        pdfView = new PDFView();
        questionView = new QuestionView();
        cheatSheetView = new CheatSheetView();
        websiteSearchUI = new WebsiteSearchUI(hostServices);
        imageSearchUI = new ImageSearchUI(hostServices); // Initialize ImageSearchUI

        mainContainer.getChildren().addAll(headerSection, searchSection, tabsSection);

        setTop(mainContainer);
        setCenter(contentScrollPane);

        setStyle("-fx-background-color: linear-gradient(to bottom, #e6f0fa, #8a9a5b);");
    }

    private VBox createHeader() {
        VBox headerContainer = new VBox();
        headerContainer.setPadding(new Insets(10, 15, 10, 15));
        headerContainer.setStyle("-fx-background-color: linear-gradient(to right, #a8d5ba, #8a9a5b);");

        DropShadow headerShadow = new DropShadow();
        headerShadow.setColor(Color.rgb(0, 0, 0, 0.2));
        headerShadow.setOffsetY(2);
        headerShadow.setRadius(8);
        headerContainer.setEffect(headerShadow);

        Label titleLabel = new Label("🌿 DSA Learning Hub");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));
        titleLabel.setStyle("-fx-text-fill: #2c3e50; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 2, 0, 1, 1);");
        titleLabel.setAlignment(Pos.CENTER_LEFT);

        headerContainer.getChildren().add(titleLabel);
        return headerContainer;
    }

    private HBox createSearchSection() {
        HBox searchContainer = new HBox();
        searchContainer.setPadding(new Insets(10, 15, 10, 15));
        searchContainer.setAlignment(Pos.CENTER);
        searchContainer.setSpacing(15);
        searchContainer.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95);");

        DropShadow searchShadow = new DropShadow();
        searchShadow.setColor(Color.rgb(0, 0, 0, 0.1));
        searchShadow.setOffsetY(1);
        searchShadow.setRadius(5);
        searchContainer.setEffect(searchShadow);

        searchField = new TextField();
        searchField.setPromptText("🔍 Search across all categories - videos, PDFs, images, websites, questions, cheat codes...");
        searchField.setPrefHeight(50);
        searchField.setMaxWidth(Double.MAX_VALUE);
        searchField.setFont(Font.font("Segoe UI", 15));
        searchField.setStyle(
                "-fx-background-color: #f1f5f9; " +
                        "-fx-border-color: #b2d8d8; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 30; " +
                        "-fx-background-radius: 30; " +
                        "-fx-padding: 0 25 0 25; " +
                        "-fx-font-size: 15px; " +
                        "-fx-prompt-text-fill: #7c8ba1;"
        );



        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                performGlobalSearch();
            }
        });

        searchButton = new Button("🔍 Search All");
        searchButton.setPrefHeight(50);
        searchButton.setPrefWidth(140);
        searchButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        searchButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #a8d5ba, #b2d8d8); " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-border-radius: 30; " +
                        "-fx-background-radius: 30; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow(gaussian, rgba(168, 213, 186, 0.4), 8, 0, 0, 2);"
        );


        searchButton.setOnAction(e -> performGlobalSearch());

        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchContainer.getChildren().addAll(searchField, searchButton);

        return searchContainer;
    }

    private void performGlobalSearch() {
        String searchTerm = searchField.getText().trim();

        if (searchTerm.isEmpty()) {
            showSearchAlert("Please enter a search term to search across all categories");
            return;
        }

        currentSearchTerm = searchTerm;

        videoView.searchVideosByTopic(searchTerm);
        pdfView.searchPDFsByTopic(searchTerm);
        questionView.searchQuestionsByTopic(searchTerm);
        cheatSheetView.filterByTopic(searchTerm);
        if (websiteSearchUI != null) {
            websiteSearchUI.searchWebsitesByTopic(searchTerm);
        }
        if (imageSearchUI != null) {
            imageSearchUI.searchImagesByTopic(searchTerm);
        }

    }


    private void showSearchAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search Information");
        alert.setHeaderText(null);
        alert.setContentText(message);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 14px; -fx-background-color: #f9f9fb;");

        alert.showAndWait();
    }

    private HBox createTabsSection() {
        HBox tabsContainer = new HBox();
        tabsContainer.setPadding(new Insets(10, 20, 15, 20));
        tabsContainer.setSpacing(0);
        tabsContainer.setAlignment(Pos.CENTER);
        tabsContainer.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);");

        DropShadow tabsShadow = new DropShadow();
        tabsShadow.setColor(Color.rgb(0, 0, 0, 0.1));
        tabsShadow.setOffsetY(2);
        tabsShadow.setRadius(8);
        tabsContainer.setEffect(tabsShadow);

        tabGroup = new ToggleGroup();

        videosButton = CreateTabButtons("🎥 Videos", "Videos");
        pdfsButton = CreateTabButtons("📄 PDFs", "PDFs");
        imagesButton = CreateTabButtons("🖼️ Images", "Images");
        websitesButton = CreateTabButtons("🌐 Websites", "Websites");
        questionsButton = CreateTabButtons("❓ Practice", "Practice Questions");
        cheatCodeButton = CreateTabButtons("📋 Cheat Code", "Cheat Code");

        videosButton.setToggleGroup(tabGroup);
        pdfsButton.setToggleGroup(tabGroup);
        imagesButton.setToggleGroup(tabGroup);
        websitesButton.setToggleGroup(tabGroup);
        questionsButton.setToggleGroup(tabGroup);
        cheatCodeButton.setToggleGroup(tabGroup);

        ToggleButton[] buttons = {videosButton, pdfsButton, imagesButton, websitesButton, questionsButton, cheatCodeButton};
        for (ToggleButton button : buttons) {
            HBox.setHgrow(button, Priority.ALWAYS);
            button.setMaxWidth(Double.MAX_VALUE);
        }

        tabsContainer.getChildren().addAll(buttons);
        return tabsContainer;
    }

    private ToggleButton CreateTabButtons(String text, String contentType) {
        ToggleButton button = new ToggleButton(text);
        button.setPrefHeight(55);
        button.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));

        String defaultStyle =
                "-fx-background-color: linear-gradient(to bottom, #f3f4f6, #e5e7eb); " +
                        "-fx-text-fill: #4b5563; " +
                        "-fx-border-color: #d1d5db; " +
                        "-fx-border-width: 1 1 1 1; " +
                        "-fx-background-radius: 8; " +
                        "-fx-border-radius: 8; " +
                        "-fx-padding: 12 18; " +
                        "-fx-cursor: hand;";

        String selectedStyle =
                "-fx-background-color: linear-gradient(to bottom, #8a9a5b, #a8d5ba); " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-border-color: #b2d8d8; " +
                        "-fx-border-width: 1 1 1 1; " +
                        "-fx-background-radius: 8; " +
                        "-fx-border-radius: 8; " +
                        "-fx-padding: 12 18; " +
                        "-fx-cursor: hand;";

        button.setStyle(defaultStyle);

        button.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                button.setStyle(selectedStyle);
                InnerShadow innerShadow = new InnerShadow();
                innerShadow.setColor(Color.rgb(0, 0, 139, 0.3));
                innerShadow.setRadius(6);
                innerShadow.setOffsetY(2);
                button.setEffect(innerShadow);
                showContent(contentType);
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
                scaleTransition.setFromX(1.0);
                scaleTransition.setFromY(1.0);
                scaleTransition.setToX(1.02);
                scaleTransition.setToY(1.02);
                scaleTransition.setAutoReverse(true);
                scaleTransition.setCycleCount(2);
                scaleTransition.play();
            } else {
                button.setStyle(defaultStyle);
                button.setEffect(null);
            }
        });

        button.setOnMouseEntered(e -> {
            if (!button.isSelected()) {
                button.setStyle(
                        "-fx-background-color: linear-gradient(to bottom, #e5e7eb, #d1d5db); " +
                                "-fx-text-fill: #374151; " +
                                "-fx-border-color: #b2d8d8; " +
                                "-fx-border-width: 1 1 1 1; " +
                                "-fx-background-radius: 8; " +
                                "-fx-border-radius: 8; " +
                                "-fx-padding: 12 18; " +
                                "-fx-cursor: hand;"
                );
                DropShadow hoverShadow = new DropShadow();
                hoverShadow.setColor(Color.rgb(0, 0, 0, 0.2));
                hoverShadow.setRadius(4);
                button.setEffect(hoverShadow);
            }
        });

        button.setOnMouseExited(e -> {
            if (!button.isSelected()) {
                button.setStyle(defaultStyle);
                button.setEffect(null);
            }
        });

        return button;
    }

    private void showContent(String contentType) {
        VBox content;

        switch (contentType) {
            case "Videos":
                content = new VBox(videoView);
                if (!currentSearchTerm.isEmpty()) {
                    videoView.searchVideosByTopic(currentSearchTerm);
                }
                break;
            case "PDFs":
                content = new VBox(pdfView);
                if (!currentSearchTerm.isEmpty()) {
                    pdfView.searchPDFsByTopic(currentSearchTerm);
                }
                break;
            case "Practice Questions":
                content = new VBox(questionView);
                if (!currentSearchTerm.isEmpty()) {
                    questionView.searchQuestionsByTopic(currentSearchTerm);
                }
                break;
            case "Cheat Code":
                content = new VBox(cheatSheetView);
                if (!currentSearchTerm.isEmpty()) {
                    cheatSheetView.filterByTopic(currentSearchTerm);
                }
                break;
            case "Websites":
                content = new VBox(websiteSearchUI);
                if (!currentSearchTerm.isEmpty()) {
                    websiteSearchUI.searchWebsitesByTopic(currentSearchTerm);
                }
                break;
            case "Images":
                content = new VBox(imageSearchUI);
                if (!currentSearchTerm.isEmpty()) {
                    imageSearchUI.searchImagesByTopic(currentSearchTerm);
                }
                break;
            default:
                content = createPlaceholderContent(contentType, getDescriptionForContent(contentType));
                break;
        }

        content.setStyle("-fx-background-color: #f9f9fb;");

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(400), content);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);

        contentScrollPane.setContent(content);
        currentContent = content;
        fadeTransition.play();
    }

    private String getDescriptionForContent(String contentType) {
        switch (contentType) {
            case "Images": return "Visual learning materials and diagrams for better understanding";
            case "Websites": return "Curated web resources and interactive coding platforms";
            case "Practice Questions": return "Coding challenges and algorithmic problem sets";
            case "Cheat Code": return "Quick reference guides for DSA concepts and algorithms";
            default: return "Learning materials and resources";
        }
    }

    private VBox createPlaceholderContent(String sectionName, String description) {
        VBox placeholder = new VBox(35);
        placeholder.setAlignment(Pos.CENTER);
        placeholder.setPadding(new Insets(100));
        placeholder.setStyle("-fx-background-color: #f9f9fb;");

        String icon = getIconForSection(sectionName);
        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-font-size: 90px;");

        DropShadow iconShadow = new DropShadow();
        iconShadow.setColor(Color.rgb(0, 0, 0, 0.2));
        iconShadow.setRadius(10);
        iconLabel.setEffect(iconShadow);

        Label titleLabel = new Label(sectionName);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));
        titleLabel.setStyle("-fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label(description);
        descriptionLabel.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 18px;");
        descriptionLabel.setFont(Font.font("Segoe UI", 18));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(600);
        descriptionLabel.setAlignment(Pos.CENTER);

        Label statusLabel = new Label("🚧 Coming Soon!");
        statusLabel.setStyle(
                "-fx-background-color: linear-gradient(to right, #8a9a5b, #a8d5ba); " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-padding: 18 35; " +
                        "-fx-background-radius: 30; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 17px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(138, 154, 91, 0.4), 8, 0, 0, 2);"
        );

        placeholder.getChildren().addAll(iconLabel, titleLabel, descriptionLabel, statusLabel);
        return placeholder;
    }

    private String getIconForSection(String sectionName) {
        switch (sectionName.toLowerCase()) {
            case "images": return "🖼️";
            case "websites": return "🌐";
            case "practice questions": return "❓";
            case "cheat code": return "📋";
            default: return "📚";
        }
    }
}
