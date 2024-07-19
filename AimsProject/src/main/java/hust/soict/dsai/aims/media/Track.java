package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class Track implements Playable{
    private String title;
    private int length;

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public void play() throws PlayerException{
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: Track length is non-positive.");
        }
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        } if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Track other = (Track) obj;
        return this.title.equals(other.title) && this.length == other.length ;
    }

    public static void main(String[] args) {
        Track t = new Track("Hello", -1);
        try {
            t.play();
        } catch (PlayerException e) {
            System.out.println(e.getMessage());
        }
    }
}
