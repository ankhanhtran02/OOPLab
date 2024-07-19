package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private ArrayList<Track> tracks;

    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
    }
    public CompactDisc(String title, String category, float cost, String artist, ArrayList<Track> tracks) {
        super(title, category, cost);
        this.artist = artist;
        this.tracks = tracks;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track){
        if (!tracks.contains(track)){
            tracks.add(track);
            System.out.println("Added the input track to the list of tracks");
        }
        else{
            System.out.println("The input track is already in the list of tracks");
        }
    }

    public void removeTrack(Track track){
        if (tracks.contains(track)){
            tracks.remove(track);
            System.out.println("Removed the input track from the list of tracks");
        }
        else{
            System.out.println("The input track doesn't exist in the list of tracks");
        }
    }

    public int getLength(){
        int totalLength = 0;
        for (Track track : tracks){
            totalLength += track.getLength();
        }
        return totalLength;
    }

    public void play() throws PlayerException{
        if (this.getLength() > 0) {
            java.util.Iterator iter = tracks.iterator();
            Track nextTrack;
            while (iter.hasNext()) {
                nextTrack = (Track) iter.next();
                try {
                    nextTrack.play();
                } catch (PlayerException e) {
                    throw e;
                }
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

    }

    @Override
    public String toString() {
        return "CompactDisc{" +
                "Artist='" + artist + '\'' +
                ", Tracks=" + tracks + '\'' +
                ", ID=" + getId()  +
                ", Title='" + getTitle() + '\'' +
                ", Category='" + getCategory() + '\'' +
                ", Cost=" + getCost() +
                "}\n";
    }

}
