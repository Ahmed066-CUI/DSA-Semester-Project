package com.example.dsaprojecttrial2;


public class Question {
    private String title;
    private String description;
    private String difficulty;
    private String category;
    private String topic;
    private String constraints;
    private String[] examples;
    private String[] hints;
    private String solution;
    private String timeComplexity;
    private String spaceComplexity;
    private String leetcodeLink;
    private boolean isLeetCode;

    public Question() {
        this.examples = new String[0];
        this.hints = new String[0];
    }

    public Question(String title, String description, String difficulty, String category, String topic) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.category = category;
        this.topic = topic;
        this.examples = new String[0];
        this.hints = new String[0];
        this.isLeetCode = false;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getConstraints() { return constraints; }
    public void setConstraints(String constraints) { this.constraints = constraints; }

    public String[] getExamples() { return examples; }
    public void setExamples(String[] examples) { this.examples = examples; }

    public String[] getHints() { return hints; }
    public void setHints(String[] hints) { this.hints = hints; }

    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }

    public String getTimeComplexity() { return timeComplexity; }
    public void setTimeComplexity(String timeComplexity) { this.timeComplexity = timeComplexity; }

    public String getSpaceComplexity() { return spaceComplexity; }
    public void setSpaceComplexity(String spaceComplexity) { this.spaceComplexity = spaceComplexity; }

    public String getLeetcodeLink() { return leetcodeLink; }
    public void setLeetcodeLink(String leetcodeLink) { this.leetcodeLink = leetcodeLink; }

    public boolean isLeetCode() { return isLeetCode; }
    public void setLeetCode(boolean leetCode) { isLeetCode = leetCode; }


    public String getDifficultyColor() {
        switch (difficulty.toLowerCase()) {
            case "easy": return "#22c55e";
            case "medium": return "#f59e0b";
            case "hard": return "#ef4444";
            default: return "#6b7280";
        }
    }


    public String getDifficultyEmoji() {
        switch (difficulty.toLowerCase()) {
            case "easy": return "🟢";
            case "medium": return "🟡";
            case "hard": return "🔴";
            default: return "⚪";
        }
    }

    @Override
    public String toString() {
        return title + " (" + difficulty + ")";
    }
}