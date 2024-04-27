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

//    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
//        if (qtyOrdered + dvdList.length <= 20){
//            for (DigitalVideoDisc dvd: dvdList) {
//                itemsOrdered[qtyOrdered] = dvd;
//                qtyOrdered ++;
//            }
//            System.out.println("The discs have been added.");
//        } else {
//            System.out.println("Maximum number of orders exceeded.");
//        }
//    }

    public void addDigitalVideoDisc(DigitalVideoDisc... dvdList){
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
}
