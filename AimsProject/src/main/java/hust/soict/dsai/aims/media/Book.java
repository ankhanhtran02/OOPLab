package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors = new ArrayList<>();

    public Book(int id, String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        this.setId(id);
        this.authors = authors;
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

    @Override
    public String toString() {
        return "Book{" +
                "Authors=" + authors + '\'' +
                ", ID=" + getId() +
                ", Title='" + getTitle() + '\'' +
                ", Category='" + getCategory() + '\'' +
                ", Cost=" + getCost() +
                "}\n";
    }
}
