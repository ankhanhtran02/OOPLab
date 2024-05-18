package hust.soict.dsai.aims.media;
import hust.soict.dsai.aims.media.Media;

public class DigitalVideoDisc extends Media {
    private String director;
    private int length;
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        nbDigitalVideoDiscs ++;
        this.setId(nbDigitalVideoDiscs);
        this.length = length;
    }
    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "DVD - " + this.getTitle() + " - " + this.getCategory() + " - " +
                director + " - " + length + ": " + this.getCost() + " $";
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
}
