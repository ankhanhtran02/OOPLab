package hust.soict.dsai.aims;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aims {
    public static void main(String[] args) {
        List<Media> media = new ArrayList<Media>();
        CompactDisc cd = new CompactDisc("Thriller", "Music", 51.5f);
        DigitalVideoDisc dvd = new DigitalVideoDisc("Contratiempo", "Crime", "Oriol Paulo", 106, 30.0f);
        Book book = new Book(5, "Harry Potter", "Fantasy", 11.4f,  new ArrayList<>(Arrays.asList("J.K Rowling")));
        media.add(cd);
        media.add(dvd);
        media.add(book);

        media.sort(Media.COMPARE_BY_COST_TITLE);
        for (Media m: media) {
            System.out.println(m.toString());
        }
    }
}


