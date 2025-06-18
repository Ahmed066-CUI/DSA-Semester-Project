

package com.example.dsaprojecttrial2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

//ui of pdf view
public class PDFView extends BorderPane {
    private PDFService pdfService;
    private ScrollPane scrollPane;
    private VBox contentContainer;
//scrollpane mai vbox hoga or hr vbox mai content display hoga
    public PDFView() {
        this.pdfService = new PDFService();
        initializeComponents();
    }

    private void initializeComponents() {
        setStyle("-fx-background-color: #f5f5f5;");
        //content area
        contentContainer = new VBox(15);
        contentContainer.setPadding(new Insets(20));
        contentContainer.setAlignment(Pos.TOP_CENTER);

        scrollPane = new ScrollPane(contentContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f5f5f5; -fx-background-color: #f5f5f5;");

        setCenter(scrollPane);

        //load by default all pdfs
        loadTopRatedPDFs();
    }

    private void loadTopRatedPDFs() {
        contentContainer.getChildren().clear();
        List<PDF> topPDFs = pdfService.getTopRatedPDFs(10); // Show more PDFs
        displayPDFs(topPDFs);
    }

    //called from main view
    public void searchPDFsByTopic(String searchTerm) {
        contentContainer.getChildren().clear();

        Label sectionTitle = new Label("🔍 Search Results for: \"" + searchTerm + "\"");
        sectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        sectionTitle.setTextFill(Color.web("#2c3e50"));

        contentContainer.getChildren().add(sectionTitle);

        List<PDF> searchResults = pdfService.searchPDFs(searchTerm, 10);
        displayPDFs(searchResults);
    }


    private void displayPDFs(List<PDF> pdfs) {
        if (pdfs.isEmpty()) {
            Label noResultsLabel = new Label("No PDFs found matching your criteria.");
            noResultsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            noResultsLabel.setTextFill(Color.web("#7f8c8d"));
            contentContainer.getChildren().add(noResultsLabel);
            return;
        }

        for (PDF pdf : pdfs) {
            HBox pdfCard = createPDFCard(pdf);
            contentContainer.getChildren().add(pdfCard);
        }
    }

    private HBox createPDFCard(PDF pdf) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPrefWidth(800);

        ImageView thumbnail = new ImageView();
        try {
            thumbnail.setImage(new Image(pdf.getThumbnailUrl(), true));
        } catch (Exception e) {
            // Create a simple text-based thumbnail
            VBox thumbnailBox = new VBox();
            thumbnailBox.setPrefSize(80, 100);
            thumbnailBox.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7; -fx-border-width: 1;");
            thumbnailBox.setAlignment(Pos.CENTER);
            Label pdfIcon = new Label("📄");
            pdfIcon.setStyle("-fx-font-size: 30px;");
            thumbnailBox.getChildren().add(pdfIcon);
        }
        thumbnail.setFitWidth(80);
        thumbnail.setFitHeight(100);
        thumbnail.setPreserveRatio(true);
        // Content section
        VBox contentSection = new VBox(8);
        contentSection.setPrefWidth(500);
        // Title
        Label titleLabel = new Label(pdf.getTitle());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#2c3e50"));
        titleLabel.setWrapText(true);
        // Description
        Label descLabel = new Label(pdf.getDescription());
        descLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        descLabel.setTextFill(Color.web("#7f8c8d"));
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(480);
        // Metadata
        HBox metadataBox = new HBox(10);
        //rating label
        Label ratingLabel = new Label("⭐ " + pdf.getRating());
        ratingLabel.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-padding: 2 6; -fx-background-radius: 10; -fx-font-size: 11px;");
        //source where we got it from?
        Label sourceLabel = new Label("📚 " + pdf.getSource());
        sourceLabel.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-padding: 2 6; -fx-background-radius: 10; -fx-font-size: 11px;");

        Label difficultyLabel = new Label("🎯 " + pdf.getDifficulty());
        String difficultyColor = pdf.getDifficulty().equals("Beginner") ? "#27ae60" :
                pdf.getDifficulty().equals("Intermediate") ? "#f39c12" : "#e74c3c";
        difficultyLabel.setStyle("-fx-background-color: " + difficultyColor + "; -fx-text-fill: white; -fx-padding: 2 6; -fx-background-radius: 10; -fx-font-size: 11px;");

        Label pagesLabel = new Label("📄 " + pdf.getPageCount() + " pages");
        pagesLabel.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-padding: 2 6; -fx-background-radius: 10; -fx-font-size: 11px;");

        metadataBox.getChildren().addAll(ratingLabel, sourceLabel, difficultyLabel, pagesLabel);

        contentSection.getChildren().addAll(titleLabel, descLabel, metadataBox);
        // Action buttons
        VBox actionSection = new VBox(8);
        actionSection.setAlignment(Pos.CENTER);
        actionSection.setPrefWidth(120);

        Button openButton = new Button("📖 Open PDF");
        openButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6 12; -fx-background-radius: 5; -fx-font-size: 12px;");
        openButton.setPrefWidth(110);
        openButton.setOnAction(e -> openPDF(pdf));

        actionSection.getChildren().addAll(openButton);

        card.getChildren().addAll(thumbnail, contentSection, actionSection);
        return card;
    }

    private void openPDF(PDF pdf) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(pdf.getUrl()));
            } else {
                showAlert("Error", "Desktop browsing not supported on this system.");
            }
        } catch (Exception e) {
            showAlert("Error", "Could not open PDF: " + e.getMessage());
        }
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}