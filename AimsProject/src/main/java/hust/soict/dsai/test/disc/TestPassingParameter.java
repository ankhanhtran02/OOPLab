package hust.soict.dsai.test.disc;

import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class TestPassingParameter {
//    public static void main(String[] args) {
//        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
//        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
//        Wrap o1 = new Wrap(jungleDVD);
//        Wrap o2 = new Wrap(cinderellaDVD);
//        swap(o1, o2);
//        System.out.println("jungle dvd title: " + o1.disc.getTitle());
//        System.out.println("cinderella dvd title: " + o2.disc.getTitle());
//
//        changeTitle(jungleDVD, cinderellaDVD.getTitle());
//        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
//    }

    public static void swap(Wrap o1, Wrap o2) {
        DigitalVideoDisc tmp = o1.disc;
        o1.disc = o2.disc;
        o2.disc = tmp;
    }

//    public static void changeTitle(DigitalVideoDisc dvd, String title){
//        String oldTitle = dvd.getTitle();
//        dvd.setTitle(title);
//        dvd = new DigitalVideoDisc(oldTitle);
//    }
}
class Wrap {
    DigitalVideoDisc disc;
    Wrap(DigitalVideoDisc d) {
        this.disc = d;}
}