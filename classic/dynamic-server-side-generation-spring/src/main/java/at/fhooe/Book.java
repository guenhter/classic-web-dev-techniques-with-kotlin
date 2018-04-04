package at.fhooe;

import java.util.List;

public class Book {
    private final String name;
    private final List<String> chapters;
    private final String imageUrl;

    public Book(String name, List<String> chapters, String imageUrl) {
        this.name = name;
        this.chapters = chapters;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public List<String> getChapters() {
        return chapters;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
