package com.example.dsaprojecttrial2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
//displaying cheat sheet
public class CheatSheetView extends VBox {
    private ListView<CheatSheet> cheatSheetListView;
    private ObservableList<CheatSheet> cheatSheets;
    private Label noResultsLabel;

    public CheatSheetView() {
        initializeUI();
        loadCheatSheets();
    }
    private void initializeUI() {
        setPadding(new Insets(20));
        setSpacing(15);
        setStyle("-fx-background-color: #f9f9fb;"); // Creamy off-white
        //lsitview for sheatsheet
        cheatSheetListView = new ListView<>();
        cheatSheetListView.setStyle(
                "-fx-background-color: #ffffff; " +
                        "-fx-border-color: #b2d8d8; " + // Mint green
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 10; " +
                        "-fx-background-radius: 10;"
        );

        // Customize ListView cells
        cheatSheetListView.setCellFactory(param -> new ListCell<CheatSheet>() {
            @Override
            protected void updateItem(CheatSheet cheatSheet, boolean empty) {
                super.updateItem(cheatSheet, empty);
                if (empty || cheatSheet == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox cellContent = new VBox(10);
                    cellContent.setPadding(new Insets(10));
                    cellContent.setStyle(
                            "-fx-background-color: #f1f5f9; " + // Light slate
                                    "-fx-background-radius: 8; " +
                                    "-fx-border-color: #d1d5db; " + // Soft gray
                                    "-fx-border-width: 1; " +
                                    "-fx-border-radius: 8;"
                    );

                    Label titleLabel = new Label(cheatSheet.getTitle());
                    titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
                    titleLabel.setStyle("-fx-text-fill: #2c3e50;"); // Dark slate

                    Label topicLabel = new Label("Topic: " + cheatSheet.getTopic());
                    topicLabel.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;"); // Muted gray

                    Label categoryLabel = new Label("Category: " + cheatSheet.getCategory());
                    categoryLabel.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;"); // Muted gray

                    Button viewButton = new Button("View Details");
                    viewButton.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
                    viewButton.setStyle(
                            "-fx-background-color: linear-gradient(to right, #a8d5ba, #b2d8d8); " + // Sage to mint green
                                    "-fx-text-fill: #2c3e50; " + // Dark slate
                                    "-fx-background-radius: 20; " +
                                    "-fx-cursor: hand; " +
                                    "-fx-padding: 8 20;"
                    );

                    viewButton.setOnMouseEntered(e -> viewButton.setStyle(
                            "-fx-background-color: linear-gradient(to right, #b2d8d8, #a8d5ba); " +
                                    "-fx-text-fill: #2c3e50; " +
                                    "-fx-background-radius: 20; " +
                                    "-fx-cursor: hand; " +
                                    "-fx-padding: 8 20;"
                    ));

                    viewButton.setOnMouseExited(e -> viewButton.setStyle(
                            "-fx-background-color: linear-gradient(to right, #a8d5ba, #b2d8d8); " +
                                    "-fx-text-fill: #2c3e50; " +
                                    "-fx-background-radius: 20; " +
                                    "-fx-cursor: hand; " +
                                    "-fx-padding: 8 20;"
                    ));

                    viewButton.setOnAction(e -> showCheatSheetDetails(cheatSheet));

                    cellContent.getChildren().addAll(titleLabel, topicLabel, categoryLabel, viewButton);
                    setGraphic(cellContent);
                }
            }
        });

        // No results label
        noResultsLabel = new Label("No cheat sheets found.");
        noResultsLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        noResultsLabel.setStyle("-fx-text-fill: #6b7280;"); // Muted gray
        noResultsLabel.setAlignment(Pos.CENTER);
        noResultsLabel.setVisible(false);

        getChildren().addAll(cheatSheetListView, noResultsLabel);
        VBox.setVgrow(cheatSheetListView, Priority.ALWAYS);
    }


    private void loadCheatSheets() {
        cheatSheets = FXCollections.observableArrayList(CheatSheet.getSampleCheatSheets());
        cheatSheetListView.setItems(cheatSheets);
        updateNoResultsLabel();
    }

    public void filterByTopic(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            cheatSheetListView.setItems(cheatSheets);
        } else {
            String lowerCaseTerm = searchTerm.toLowerCase();
            List<CheatSheet> filtered = cheatSheets.stream()
                    .filter(sheet -> sheet.getTopic().toLowerCase().contains(lowerCaseTerm))
                    .collect(Collectors.toList());
            cheatSheetListView.setItems(FXCollections.observableArrayList(filtered));
        }
        updateNoResultsLabel();
    }




    private void updateNoResultsLabel() {
        noResultsLabel.setVisible(cheatSheetListView.getItems().isEmpty());
    }

    //details dailog box
    private void showCheatSheetDetails(CheatSheet cheatSheet) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(cheatSheet.getTitle());
        dialog.getDialogPane().setStyle("-fx-background-color: #f9f9fb; -fx-font-family: 'Segoe UI';");

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b2d8d8; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label categoryLabel = new Label("Category: " + cheatSheet.getCategory());
        categoryLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-font-weight: bold;");

        Label topicLabel = new Label("Topic: " + cheatSheet.getTopic());
        topicLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-font-weight: bold;");

        Label conceptsLabel = new Label("Key Concepts:\n" + cheatSheet.getKeyConcepts());
        conceptsLabel.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");
        conceptsLabel.setWrapText(true);

        Label timeLabel = new Label("Time Complexity: " + cheatSheet.getTimeComplexity());
        timeLabel.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");

        Label spaceLabel = new Label("Space Complexity: " + cheatSheet.getSpaceComplexity());
        spaceLabel.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");

        VBox codeBox = new VBox(10);
        codeBox.setStyle("-fx-background-color: #f1f5f9; -fx-padding: 10; -fx-border-color: #d1d5db; -fx-border-width: 1; -fx-border-radius: 8;");
        Label codeTitle = new Label("Code Snippets:");
        codeTitle.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-font-weight: bold;");
        codeBox.getChildren().add(codeTitle);
        for (String snippet : cheatSheet.getCodeSnippets()) {
            TextArea codeArea = new TextArea(snippet);
            codeArea.setFont(Font.font("Consolas", 13));
            codeArea.setEditable(false);
            codeArea.setWrapText(true);
            codeArea.setPrefRowCount(5);
            codeBox.getChildren().add(codeArea);
        }

        VBox refBox = new VBox(5);
        refBox.setStyle("-fx-padding: 10;");
        Label refTitle = new Label("References:");
        refTitle.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-font-weight: bold;");
        refBox.getChildren().add(refTitle);
        for (String ref : cheatSheet.getReferences()) {
            Hyperlink link = new Hyperlink(ref);
            link.setStyle("-fx-text-fill: #8a9a5b; -fx-font-size: 13px;"); // Olive green
            link.setOnAction(e -> openLink(ref));
            refBox.getChildren().add(link);
        }

        content.getChildren().addAll(categoryLabel, topicLabel, conceptsLabel, timeLabel, spaceLabel, codeBox, refBox);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500);
        scrollPane.setStyle("-fx-background-color: #f9f9fb; -fx-border-color: #b2d8d8;");

        dialog.getDialogPane().setContent(scrollPane);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot open link");
            alert.setContentText("Failed to open: " + url);
            alert.showAndWait();
        }
    }
}
