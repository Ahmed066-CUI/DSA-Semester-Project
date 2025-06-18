package com.example.dsaprojecttrial2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.awt.Desktop;
import java.net.URI;
import java.util.Arrays;
import java.util.List;


public class QuestionView extends VBox {
    private ListView<Question> questionListView;
    private QuestionDataProvider dataProvider;
    private Label noResultsLabel;

    public QuestionView() {
        dataProvider = new QuestionDataProvider();
        initializeUI();
        displayQuestions(dataProvider.getAllQuestions());
    }


    private void initializeUI() {
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f9f9fb;"); // Creamy off-white

        // Create title
        Label titleLabel = new Label("Practice Questions");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        titleLabel.setStyle("-fx-text-fill: #2c3e50;"); // Dark slate

        // Create list view for questions
        questionListView = new ListView<>();
        questionListView.setPrefHeight(400);
        questionListView.setStyle(
                "-fx-background-color: #ffffff; " +
                        "-fx-border-color: #b2d8d8; " + // Mint green
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8;"
        );

        // Custom cell factory for question display
        questionListView.setCellFactory(param -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question question, boolean empty) {
                super.updateItem(question, empty);
                if (empty || question == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox cellContent = new VBox(10);
                    cellContent.setPadding(new Insets(10));
                    cellContent.setStyle("-fx-background-color: #f9f9fb;"); // Creamy off-white

                    Label title = new Label(question.getTitle() + " " + question.getDifficultyEmoji());
                    title.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
                    title.setStyle("-fx-text-fill: #2c3e50;"); // Dark slate

                    Label difficulty = new Label("Difficulty: " + question.getDifficulty());
                    difficulty.setFont(Font.font("Segoe UI", 14));
                    difficulty.setStyle("-fx-text-fill: " + question.getDifficultyColor() + ";"); // Difficulty color

                    Label topic = new Label("Topic: " + question.getTopic());
                    topic.setFont(Font.font("Segoe UI", 14));
                    topic.setStyle("-fx-text-fill: #6b7280;"); // Muted gray

                    Label category = new Label("Category: " + question.getCategory());
                    category.setFont(Font.font("Segoe UI", 14));
                    category.setStyle("-fx-text-fill: #6b7280;"); // Muted gray

                    HBox buttonBox = new HBox(10);
                    Button viewButton = new Button("View Details");
                    viewButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
                    viewButton.setStyle(
                            "-fx-background-color: linear-gradient(to right, #8a9a5b, #a8d5ba); " + // Olive to sage green
                                    "-fx-text-fill: #2c3e50; " +
                                    "-fx-border-radius: 8; " +
                                    "-fx-background-radius: 8; " +
                                    "-fx-cursor: hand; " +
                                    "-fx-padding: 8 16;"
                    );


                    viewButton.setOnAction(e -> showQuestionDetails(question));

                    Button solveButton = new Button("Solve on LeetCode");
                    solveButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
                    solveButton.setStyle(
                            "-fx-background-color: linear-gradient(to right, #b2d8d8, #a8d5ba); " + // Mint to sage green
                                    "-fx-text-fill: #2c3e50; " +
                                    "-fx-border-radius: 8; " +
                                    "-fx-background-radius: 8; " +
                                    "-fx-cursor: hand; " +
                                    "-fx-padding: 8 16;"
                    );
                    solveButton.setVisible(question.isLeetCode());


                    solveButton.setOnAction(e -> openLeetCodeLink(question.getLeetcodeLink()));

                    buttonBox.getChildren().addAll(viewButton, solveButton);
                    cellContent.getChildren().addAll(title, difficulty, topic, category, buttonBox);
                    setGraphic(cellContent);
                }
            }
        });

        // No results label
        noResultsLabel = new Label("No questions found for this topic.");
        noResultsLabel.setFont(Font.font("Segoe UI", 18));
        noResultsLabel.setStyle("-fx-text-fill: #6b7280;"); // Muted gray
        noResultsLabel.setVisible(false);
        noResultsLabel.setAlignment(Pos.CENTER);

        getChildren().addAll(titleLabel, questionListView, noResultsLabel);
    }


    private void displayQuestions(List<Question> questions) {
        questionListView.getItems().clear();
        if (questions.isEmpty()) {
            questionListView.setVisible(false);
            noResultsLabel.setVisible(true);
        } else {
            questionListView.getItems().addAll(questions);
            questionListView.setVisible(true);
            noResultsLabel.setVisible(false);
        }
    }

    public void searchQuestionsByTopic(String searchTerm) {
        List<Question> filteredQuestions = dataProvider.filterByTopic(searchTerm);
        displayQuestions(filteredQuestions);
    }



    private void showQuestionDetails(Question question) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Practice Question Details");
        alert.setHeaderText(question.getTitle() + " " + question.getDifficultyEmoji());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #f9f9fb;");

        VBox content = new VBox(15);
        content.setPadding(new Insets(15));
        content.setStyle("-fx-background-color: #f9f9fb;");

        Label difficulty = new Label("Difficulty: " + question.getDifficulty());
        difficulty.setStyle("-fx-text-fill: " + question.getDifficultyColor() + "; -fx-font-size: 14px;");

        Label topic = new Label("Topic: " + question.getTopic());
        topic.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");

        Label category = new Label("Category: " + question.getCategory());
        category.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");

        Label description = new Label("Description:\n" + question.getDescription());
        description.setWrapText(true);
        description.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px;");

        Label constraints = new Label("Constraints:\n" + (question.getConstraints() != null ? question.getConstraints() : "None"));
        constraints.setWrapText(true);
        constraints.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px;");

        Label examples = new Label("Examples:\n" + String.join("\n\n", question.getExamples()));
        examples.setWrapText(true);
        examples.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px;");

        Label hints = new Label("Hints:\n" + String.join("\n", question.getHints()));
        hints.setWrapText(true);
        hints.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");

        Label solution = new Label("Solution:\n" + (question.getSolution() != null ? question.getSolution() : "Not provided"));
        solution.setWrapText(true);
        solution.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px;");

        Label timeComplexity = new Label("Time Complexity: " + (question.getTimeComplexity() != null ? question.getTimeComplexity() : "Not provided"));
        timeComplexity.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");

        Label spaceComplexity = new Label("Space Complexity: " + (question.getSpaceComplexity() != null ? question.getSpaceComplexity() : "Not provided"));
        spaceComplexity.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");

        content.getChildren().addAll(difficulty, topic, category, description, constraints, examples, hints, solution, timeComplexity, spaceComplexity);
        scrollPane.setContent(content);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setContent(scrollPane);
        dialogPane.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 14px; -fx-background-color: #f9f9fb;");

        alert.showAndWait();
    }


    private void openLeetCodeLink(String link) {
        if (link != null && !link.isEmpty()) {
            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (Exception e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Cannot Open Link");
                errorAlert.setContentText("Failed to open the LeetCode link: " + e.getMessage());
                errorAlert.getDialogPane().setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 14px; -fx-background-color: #f9f9fb;");
                errorAlert.showAndWait();
            }
        }
    }
}