package com.example.dsaprojecttrial2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.PriorityQueue;
import javafx.application.HostServices;

public class WebsiteSearchUI extends VBox {

    private VBox websiteResultsBox;
    private WebsiteSearchAPI searchAPI;
    private final HostServices hostServices;


    public WebsiteSearchUI(HostServices hostServices) {
        super(10);
        this.hostServices = hostServices;
        searchAPI = new WebsiteSearchAPI();
        initializeUI();
    }

    private void initializeUI() {
        setPadding(new Insets(10));
        setAlignment(Pos.TOP_CENTER);

        websiteResultsBox = new VBox(15);
        websiteResultsBox.setPadding(new Insets(10));
        websiteResultsBox.setAlignment(Pos.TOP_CENTER);

        Label placeHolderLAbel = new Label("Enter a search term in the global search bar above to find websites.");
        placeHolderLAbel.setWrapText(true);
        placeHolderLAbel.setMaxWidth(600);
        placeHolderLAbel.setAlignment(Pos.CENTER);
        websiteResultsBox.getChildren().add(placeHolderLAbel);

        ScrollPane scrollPane = new ScrollPane(websiteResultsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        getChildren().add(scrollPane);
    }

    public void searchWebsitesByTopic(String query) {
        if (query == null || query.trim().isEmpty()) {
            clearSearch();
            return;
        }
        displayWebsites(query.trim());
    }

    public void clearSearch() {
        websiteResultsBox.getChildren().clear();
        Label placeholderLabel = new Label("Enter a search term in the global search bar above to find websites.");
        placeholderLabel.setWrapText(true);
        placeholderLabel.setMaxWidth(600);
        placeholderLabel.setAlignment(Pos.CENTER);
        websiteResultsBox.getChildren().add(placeholderLabel);
    }

    private void displayWebsites(String query) {
        websiteResultsBox.getChildren().clear();

        PriorityQueue<WebsiteResult> websites = searchAPI.search(query);

        if (websites.isEmpty()) {
            Label noResultsLabel = new Label("No websites found for \"" + query + "\".");
            noResultsLabel.setWrapText(true);
            noResultsLabel.setMaxWidth(600);
            noResultsLabel.setAlignment(Pos.CENTER);
            websiteResultsBox.getChildren().add(noResultsLabel);
            return;
        }

        while (!websites.isEmpty()) {
            WebsiteResult result = websites.poll();
            Hyperlink link = new Hyperlink(result.getTitle());
            link.setOnAction(e -> hostServices.showDocument(result.getUrl()));

            Label urlLabel = new Label(result.getUrl());

            VBox resultBox = new VBox(5, link, urlLabel);
            websiteResultsBox.getChildren().add(resultBox);
        }
    }

}