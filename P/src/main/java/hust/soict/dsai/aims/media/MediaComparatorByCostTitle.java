package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        int costComparison = Float.compare(m1.getCost(), m2.getCost());
        if (costComparison != 0) {
            return costComparison;
        } else {
            return m1.getTitle().compareTo(m2.getTitle());
        }
    }
}
