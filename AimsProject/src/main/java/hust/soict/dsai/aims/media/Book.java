package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private String category;
    private float cost;
    private List<String> authors = new ArrayList<>();

    public Book(int id, String title, String category, float cost, List<String> authors) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Added " + authorName + " to the author list");
        } else {
            System.out.println(authorName + " is already in the author list");
        }
    }

    public void removeAuthor(String authorName){
        if (authors.contains(authorName)){
            authors.remove(authorName);
            System.out.println("Removed " + authorName + " from the author list");
        } else {
            System.out.println(authorName + "is not in the author list");
        }
    }
}
