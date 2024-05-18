package hust.soict.dsai.aims;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Aims {
    private static final Store mainStore = new Store();
    private static final Cart mainCart = new Cart();
    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }
    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("----------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("----------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static int getIntegerInput(String text) {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(text);
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                valid = true;
            } else {
                System.out.println("Please enter a valid number:");
                scanner.next();
            }
        }
        return result;
    }
    public static String getStringInput(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        return scanner.nextLine();
    }

    public static void seeMediaDetails() {
        String title = getStringInput("Enter title: ");
        Media media = mainStore.searchByTitle(title);
        if (media != null) {
            System.out.println(media);
            mediaDetailsMenu(media);
        } else {
            System.out.println("Media not found.");
        }
    }
    public static void mediaDetailsMenu(Media media) {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            if (media instanceof Playable) {
                System.out.println("2. Play");
            }
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");

            int choice = getIntegerInput("");
            switch (choice) {
                case 1:
                    mainCart.addMedia(media);
                    System.out.println("Added to cart.");
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void addMediaToCart() {
        String title = getStringInput("Enter title: ");
        Media media = mainStore.searchByTitle(title);
        if (media != null) {
            mainCart.addMedia(media);
            System.out.println("Added to cart.");
        } else {
            System.out.println("Media not found.");
        }
    }
    public static void playMedia() {
        String title = getStringInput("Enter title:");
        Media media = mainStore.searchByTitle(title);
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played or is not found.");
        }
    }
    public static void seeCurrentCart() {
        mainCart.print();  // Method to display cart contents
        while (true) {
            cartMenu();
            int choice = getIntegerInput("");
            switch (choice) {
                case 1:
                    int filterBy = getIntegerInput("Which filter option would you like?\n1.  id\n2. title");
                    if (filterBy == 1){
                        int id = getIntegerInput("Input the ID to filter: ");
                        mainCart.filter(id);
                    }
                    else{
                        String title = getStringInput("Input your title to filter: ");
                        mainCart.filter(title);
                    }
                    break;
                case 2:
                    int sortBy = getIntegerInput("Which sort option would you like?\n1.  cost\n2. title");
                    mainCart.sort(sortBy);
                    break;
                case 3:
                    System.out.println("Enter the title of the media to remove:");
                    String title = getStringInput("Enter the title of the media to remove:");

                    mainCart.removeMedia(mainCart.searchByTitle(title));
                    System.out.println("Removed from cart.");
                    break;
                case 4:
                    playMediaInCart();
                    break;
                case 5:
                    mainCart.placeOrder();
                    System.out.println("Order placed.");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void playMediaInCart() {
        String title = getStringInput("Enter title:");
        Media media = mainCart.searchByTitle(title);
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played or was not found.");
        }
    }
    public static void viewStore() {
        while (true) {
            mainStore.print();  // Method to display all items in the store
            storeMenu();
            int choice = getIntegerInput("");
            switch (choice) {
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCart();
                    break;
                case 3:
                    playMedia();
                    break;
                case 4:
                    seeCurrentCart();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void updateStore() {
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        int choice = getIntegerInput("");
        switch (choice) {
            case 1:
                // Code to add media to the store
                break;
            case 2:
                String title = getStringInput("Enter the title of the media to remove:");
                mainStore.removeMedia(mainStore.searchByTitle(title));
                System.out.println("Removed from store.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    public static void main(String[] args) {
        CompactDisc cd = new CompactDisc("Thriller", "Music", 51.5f);
        DigitalVideoDisc dvd = new DigitalVideoDisc("Contratiempo", "Crime", "Oriol Paulo", 106, 30.0f);
        Book book = new Book(5, "Harry Potter", "Fantasy", 11.4f,  new ArrayList<>(Arrays.asList("J.K Rowling")));
        mainStore.addMedia(cd);
        mainStore.addMedia(dvd);
        mainStore.addMedia(book);

        while (true) {
            showMenu();
            int choice = getIntegerInput("");
            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Closing");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}