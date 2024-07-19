package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

public class CartScreenController {
    private Cart cart;
    private CartScreen cartScreen;
    private Store store;

//    private StoreScreen storeScreen;
    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, String> colMediaTitle;
    @FXML
    private TableColumn<Media, String> colMediaCategory;
    @FXML
    private TableColumn<Media, Float> colMediaCost;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private MenuItem viewStoreMenuItem;
    @FXML
    private Button btnPlaceOrder;
    @FXML
    private MenuItem addBookBtn;
    @FXML
    private MenuItem addCDBtn;
    @FXML
    private MenuItem addDVDBtn;
    @FXML
    private Label lblTotalCost;
    public CartScreenController(Cart cart, CartScreen cartScreen, Store store) {
        super();
        this.cart = cart;
        this.cartScreen = cartScreen;
        this.store = store;
//        this.storeScreen = storeScreen;
    }

    @FXML
    public void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        if (!this.cart.getItemOrdered().isEmpty()) {
            tblMedia.setItems(this.cart.getItemOrdered());
        }
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Media>() {
                @Override
                public void changed(ObservableValue<? extends Media> observableValue, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }
            }
        );
        lblTotalCost.setText(Float.toString(cart.totalCost()) + " ");
    }

    private void updateButtonBar(Media media){
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    @FXML
    public void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
    }
    @FXML
    public void btnViewStorePressed(ActionEvent event){
        new StoreScreen(store, cart);
        cartScreen.dispose();
    }
    @FXML
    public void btnPlayPressed(ActionEvent event){
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Playing Media");
        alert.setHeaderText(null);
        alert.setContentText("Now playing: " + selectedMedia.getTitle());
        alert.showAndWait();
    }
    @FXML
    public void btnPlaceOrderPressed(ActionEvent event){
        cart.placeOrder();
        tblMedia.getItems().clear();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!");

        alert.showAndWait();
    }

    @FXML
    public void addBookBtnPressed(ActionEvent event) {
        new AddBookToStoreScreen(store, cart);
    }
    @FXML
    public void addCDBtnPressed(ActionEvent event) {
        new AddCompactDiscToStoreScreen(store, cart);
    }

    @FXML
    public void addDVDBtnPressed(ActionEvent event) {
        new AddDigitalVideoDiscToStoreScreen(store, cart);
    }
}
