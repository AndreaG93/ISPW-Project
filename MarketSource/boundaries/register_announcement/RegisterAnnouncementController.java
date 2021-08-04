package boundaries.register_announcement;

import java.io.File;
import java.util.Optional;
import controllers.AccountController;
import controllers.database.announcement.AnnouncementDatabase;
import controllers.database.product.book.BookDatabase;
import controllers.database.product.dress.DressDatabase;
import controllers.support.Support;
import entities.announcement.status.Testing;
import entities.announcement.type.Donation;
import entities.announcement.type.Sale;
import entities.product.book.type.Novel;
import entities.product.book.type.SchoolBook;
import entities.product.dress.SizeDressEnum;
import entities.product.dress.type.ManDress;
import entities.product.dress.type.WomanDress;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterAnnouncementController {
	private static ObservableList<String> productTypeList = FXCollections.observableArrayList("Book", "Dress");
	private static ObservableList<String> bookTypeList = FXCollections.observableArrayList("Novel", "ScoolBook");
	private static ObservableList<String> dressTypeList = FXCollections.observableArrayList("Man Dress", "Woman Dress");
	private static ObservableList<SizeDressEnum> dressSizeOptions = FXCollections.observableArrayList();

	private AccountController myAccountController;
	private Stage stage;

	@FXML
	private TextField txtfld_bookAuthor;

	@FXML
	private ComboBox<String> cmbx_payment_method;

	@FXML
	private TextField txtfld_bookEditor;

	@FXML
	private TextField txtfld_dressName;

	@FXML
	private TextField txtfld_dressColor;

	@FXML
	private RadioButton rdbtn_sale;

	@FXML
	private ComboBox<String> cmbx_dressType;

	@FXML
	private ComboBox<String> cmbx_bookType;

	@FXML
	private ToggleGroup type;

	@FXML
	private ComboBox<SizeDressEnum> cmbx_dressSize;

	@FXML
	private Button btn_ok;

	@FXML
	private TextField txtfld_dressBrand;

	@FXML
	private ComboBox<String> cmbx_shipping_method;

	@FXML
	private ComboBox<String> cmbx_product_type;

	@FXML
	private RadioButton rdbtn_donation;

	@FXML
	private TextField txtfld_bookSubtitle;

	@FXML
	private Button btn_cancel;

	@FXML
	private TextField txtfld_produtCost;

	@FXML
	private TextField txtfld_booktitle;

	@FXML
	private TextField txtfld_bookPage;

	@FXML
	private VBox vbx_dress;

	@FXML
	private VBox vbx_book;

	@FXML
	private TextField txtfld_ISBN;

	@FXML
	private ImageView imgv_panel;

	/**
	 * Book Field
	 */
	private String isbn;
	private String title;
	private String subtitle;
	private String author;
	private String editor;
	private int pages;
	private String bookType;

	/**
	 * Dress Field
	 */
	private String dressName;
	private String brand;
	private String color;
	private SizeDressEnum size;
	private String dressType;

	/**
	 * This method checks informations about book.
	 */
	private void BookCheck() throws Exception {

		Support.emptyCheckField(txtfld_ISBN.getText(), "'ISBN'");
		Support.emptyCheckField(txtfld_booktitle.getText(), "'Title'");
		Support.emptyCheckField(txtfld_bookAuthor.getText(), "'Author'");
		Support.emptyCheckField(txtfld_bookEditor.getText(), "'Editor'");
		Support.onlyDigitCheckField(txtfld_bookPage.getText(), "'Pages'");
		Support.nullCheckField(cmbx_bookType.getValue(), "'Book Type'");

		this.pages = Integer.valueOf(txtfld_bookPage.getText());
		this.isbn = txtfld_ISBN.getText();
		this.title = txtfld_booktitle.getText();
		this.subtitle = txtfld_bookSubtitle.getText();
		this.author = txtfld_bookAuthor.getText();
		this.editor = txtfld_bookEditor.getText();

		if (cmbx_bookType.getValue().equals("Novel"))
			this.bookType = Novel.class.getName();
		else
			this.bookType = SchoolBook.class.getName();
	}

	/**
	 * This method checks informations about book.
	 */
	private void DressCheck() throws Exception {

		Support.emptyCheckField(txtfld_dressName.getText(), "'Description'");
		Support.nullCheckField(cmbx_dressSize.getValue(), "'Dress Size'");
		Support.nullCheckField(cmbx_dressType.getValue(), "'Dress Type'");

		this.dressName = txtfld_dressName.getText();
		this.brand = txtfld_dressBrand.getText();
		this.color = txtfld_dressColor.getText();
		this.size = cmbx_dressSize.getValue();

		if (cmbx_dressType.getValue().equals("Woman Dress"))
			this.dressType = WomanDress.class.getName();
		else
			this.dressType = ManDress.class.getName();
	}

	/**
	 * This method register an {@code Account}, a {@code PersonalData} and an
	 * {@code Address} object into database.
	 * 
	 * @param event
	 *            - Represents a {@code ActionEvent} object.
	 */
	@FXML
	public void registerEventHandler(ActionEvent event) {

		/**
		 * Data check
		 */

		try {
			if (cmbx_product_type.getValue().equals("Book"))
				BookCheck();
			else
				DressCheck();
		} catch (Exception e) {
			Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
			return;
		}

		/**
		 * Announcement registration
		 */

		AnnouncementDatabase myDB = AnnouncementDatabase.getInstance();
		String announcementType;
		float announcementPrice;

		if (rdbtn_sale.isSelected()) {
			announcementType = Sale.class.getName();
			try {
				announcementPrice = Float.valueOf(txtfld_produtCost.getText());
			} catch (NumberFormatException e) {
				Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
				return;
			}
		} else {
			announcementType = Donation.class.getName();
			announcementPrice = 0;
		}

		int ID = myDB.getNewID();
		myDB.insert(ID, this.myAccountController.getCurrentLoggedAccount().getID(), announcementPrice, announcementType,
				Testing.class.getName());

		/**
		 * Product registration
		 */

		if (cmbx_product_type.getValue().equals("Book"))
			BookDatabase.getInstance().insert(ID, isbn, title, subtitle, author, editor, pages, bookType);
		else
			DressDatabase.getInstance().insert(ID, dressName, brand, color, dressType, size);

		Support.getDialog(Alert.AlertType.INFORMATION, "Message", "Registration complete.").show();
		this.myAccountController.sendUpdateToObserver();
		stage.close();
	}

	/**
	 * This method is used to initialize the boundary.
	 * 
	 * @param arg0
	 *            - Represents a {@code Stage} object.
	 * @param arg1
	 *            - Represents an {@code int}.
	 */
	public void initialization(Stage arg0, AccountController arg1) {
		this.stage = arg0;
		this.myAccountController = arg1;

		// ComboBoxes Initialization...
		for (SizeDressEnum obj : SizeDressEnum.values()) {
			dressSizeOptions.add(obj);
		}

		cmbx_dressSize.setItems(dressSizeOptions);
		cmbx_product_type.setItems(productTypeList);
		cmbx_bookType.setItems(bookTypeList);
		cmbx_dressType.setItems(dressTypeList);

		/**
		 * Image Panel initialization...
		 */
		File file = new File("src/panel.png");
		Image image = new Image(file.toURI().toString());
		this.imgv_panel.setImage(image);
		this.imgv_panel.fitHeightProperty().bind(this.stage.heightProperty());
	}

	/**
	 * This method is used to close current stage.
	 * 
	 * @param arg0
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	void closeEventHandler(ActionEvent event) {

		/**
		 * Open a dialog.
		 */
		Optional<ButtonType> result = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "You want exit?")
				.showAndWait();

		if (result.get() == ButtonType.OK) {
			this.stage.close();
		} else
			return;
	}

	/**
	 * This method is used to disable "product's cost" field.
	 * 
	 * @param arg0
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	void donationSelectedEventHandler(ActionEvent event) {
		txtfld_produtCost.setDisable(true);
	}

	/**
	 * This method is used to enable "product's cost" field.
	 * 
	 * @param arg0
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	void saleSelectedEventHandler(ActionEvent event) {
		txtfld_produtCost.setDisable(false);
	}

	/**
	 * This method is used to view a form conform to chosen product's type.
	 * 
	 * @param arg0
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	void productChosenEventHandler(ActionEvent event) {
		if (cmbx_product_type.getValue() == "Book") {
			vbx_book.setVisible(true);
			vbx_dress.setVisible(false);
		} else {
			vbx_book.setVisible(false);
			vbx_dress.setVisible(true);
		}
		btn_ok.setDisable(false);
	}
}
