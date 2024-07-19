package hust.soict.dsai.aims.media;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;

public class DigitalVideoDisc extends Disc implements Playable {
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
        return "DigitalVideoDisc{" +
                "Title='" + this.getTitle() + '\'' +
                ", Category='" + this.getCategory() + '\'' +
                ", Director='" + director + '\'' +
                ", Length=" + length +
                ", Cost=" + this.getCost() +
                ", ID=" + this.getId() +
                "}\n";
    }



    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD's length is non-positive.");
        }
    }

}
