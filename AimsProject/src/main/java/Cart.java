public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private DigitalVideoDisc[] itemsOrdered = new DigitalVideoDisc[MAX_NUMBER_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < 20){
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered ++;
            System.out.println("The disc has been added");
        }
        else {
            System.out.println("The cart is almost full");
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        if (qtyOrdered + dvdList.length <= 20){
            for (DigitalVideoDisc dvd: dvdList) {
                itemsOrdered[qtyOrdered] = dvd;
                qtyOrdered ++;
            }
            System.out.println("The discs have been added.");
        } else {
            System.out.println("Maximum number of orders exceeded.");
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc d1, DigitalVideoDisc d2) {
        if (qtyOrdered <= 18){
            addDigitalVideoDisc(d1);
            addDigitalVideoDisc(d2);
            System.out.println("The discs have been added.");
        } else {
            System.out.println("Maximum number of orders exceeded.");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        boolean removed = false;
        for (int i = 0; i < qtyOrdered; i++){
            if (itemsOrdered[i] == disc){
                for (int j = i; j < qtyOrdered - 1; j++){
                    itemsOrdered[j] = itemsOrdered[j+1];
                }
                removed = true;
                qtyOrdered --;
                break;
            }
        }
        if (removed) {
            System.out.println("The disc has been removed");
        }
        else {
            System.out.println("The disc is not found");
        }
    }

    public float totalCost() {
        float sum = 0;
        for (int i = 0; i < qtyOrdered; i++){
            sum += itemsOrdered[i].getCost();
        }
        return sum;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 1; i <= qtyOrdered; i++ ) {
            System.out.println(i + ". " + itemsOrdered[i-1].toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }

    public void searchById(int id) {
        boolean found = false;
        System.out.println("Search: " + id);
        System.out.println("Search result:");
        for (int i = 0; i < qtyOrdered; i++ ) {
            DigitalVideoDisc dvd = itemsOrdered[i];
            if (dvd.getId() == id) {
                System.out.println(dvd.toString());
                found = true;
                break;
            }
            }
        if (!found) {
            System.out.println("No match is found.");
        }
    }

    public void searchByTitle(String title) {
        System.out.println("Search: " + title);
        System.out.println("Search result:");
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            DigitalVideoDisc dvd = itemsOrdered[i];
            if (dvd.isMatch(title)) {
                System.out.println(dvd.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match is found.");
        }
    }
}

