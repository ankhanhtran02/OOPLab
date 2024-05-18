package hust.soict.dsai.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
    public Media() {}
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public boolean isMatch(String searchStr) {
        boolean matched = false;
        String[] searchArr = searchStr.split(" ", 0);
        String title = this.getTitle();
        for (String word: searchArr) {
            String lowerCaseTitle = title.toLowerCase();
            int index = lowerCaseTitle.indexOf(word.toLowerCase());
            if (index != -1) {
                matched = true;
                break;
            }
        }
        return matched;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        } if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Media other = (Media) obj;
        return this.title.equals(other.title);
    }
}
