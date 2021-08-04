package boundaries.announcement_setting;

import java.util.Optional;

import controllers.AnnouncementController;
import controllers.database.product.book.BookDatabase;
import controllers.database.product.dress.DressDatabase;
import controllers.support.Support;
import entities.announcement.Announcement;
import entities.announcement.type.Donation;
import entities.announcement.type.Sale;
import entities.product.Product;
import entities.product.book.Book;
import entities.product.book.type.Novel;
import entities.product.book.type.SchoolBook;
import entities.product.dress.Dress;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnnouncementSettingController {

	private static ObservableList<String> bookTypeList = FXCollections.observableArrayList("Novel", "ScoolBook");
	private static ObservableList<String> dressTypeList = FXCollections.observableArrayList("Man Dress", "Woman Dress");
	private static ObservableList<SizeDressEnum> dressSizeOptions = FXCollections.observableArrayList();

	private AnnouncementController myAnnouncementController;
	private Announcement myAnnouncement;
	private Product myProduct;
	private Stage stage;

	@FXML
	private TextField txtfld_bookAuthor;

	@FXML
	private TextField txtfld_bookEditor;

	@FXML
	private TextField txtfld_dressColor;

	@FXML
	private RadioButton rdbtn_sale;

	@FXML
	private VBox vbx_book;

	@FXML
	private ComboBox<String> cmbx_dressType;

	@FXML
	private ComboBox<String> cmbx_bookType;

	@FXML
	private ToggleGroup type;

	@FXML
	private TextField txtfld_ISBN;

	@FXML
	private ComboBox<SizeDressEnum> cmbx_dressSize;

	@FXML
	private TextField txtfld_dressName;

	@FXML
	private Button btn_ok;

	@FXML
	private TextField txtfld_dressBrand;

	@FXML
	private RadioButton rdbtn_donation;

	@FXML
	private TextField txtfld_bookSubtitle;

	@FXML
	private Button btn_cancel;

	@FXML
	private TextField txtfld_produtCost;

	@FXML
	private VBox vbx_dress;

	@FXML
	private TextField txtfld_booktitle;

	@FXML
	private TextField txtfld_bookPage;

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

		((Book) this.myProduct).setAuthor(txtfld_bookAuthor.getText());
		((Book) this.myProduct).setEditor(txtfld_bookEditor.getText());
		((Book) this.myProduct).setISBN(txtfld_ISBN.getText());
		((Book) this.myProduct).setNumberOfPages(Integer.valueOf(txtfld_bookPage.getText()));
		((Book) this.myProduct).setTitle(txtfld_booktitle.getText());
		((Book) this.myProduct).setSubTitle(txtfld_bookSubtitle.getText());

		if (cmbx_bookType.getValue().equals("Novel"))
			((Book) this.myProduct).setType(new Novel());
		else
			((Book) this.myProduct).setType(new SchoolBook());

		BookDatabase.getInstance().update(((Book) this.myProduct));
	}

	/**
	 * This method checks informations about book.
	 */
	private void DressCheck() throws Exception {

		Support.emptyCheckField(txtfld_dressName.getText(), "'Description'");
		Support.nullCheckField(cmbx_dressSize.getValue(), "'Dress Size'");
		Support.nullCheckField(cmbx_dressType.getValue(), "'Dress Type'");

		((Dress) this.myProduct).setBrand(txtfld_dressBrand.getText());
		((Dress) this.myProduct).setColor(txtfld_dressColor.getText());
		((Dress) this.myProduct).setDressName(txtfld_dressName.getText());
		((Dress) this.myProduct).setSize(cmbx_dressSize.getValue());

		if (cmbx_dressType.getValue().equals("Woman Dress"))
			((Dress) this.myProduct).setType(new WomanDress());
		else
			((Dress) this.myProduct).setType(new ManDress());

		DressDatabase.getInstance().update(((Dress) this.myProduct));
	}

	public void initialization(Stage arg0, Announcement arg1, AnnouncementController arg2) {
		this.stage = arg0;
		this.myAnnouncement = arg1;
		this.myProduct = this.myAnnouncement.getProduct();
		this.myAnnouncementController = arg2;

		// ComboBoxes Initialization...
		for (SizeDressEnum obj : SizeDressEnum.values()) {
			dressSizeOptions.add(obj);
		}

		cmbx_dressSize.setItems(dressSizeOptions);
		cmbx_bookType.setItems(bookTypeList);
		cmbx_dressType.setItems(dressTypeList);

		if (this.myProduct.getProductType().equals(Book.class.getName()))
			vbx_book.setVisible(true);
		else
			vbx_dress.setVisible(true);
		update();
	}

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

	@FXML
	void registerEventHandler(ActionEvent event) {
		/**
		 * Set Announcement Type...
		 */

		if (rdbtn_sale.isSelected()) {
			this.myAnnouncement.setType(new Sale());
			try {
				this.myAnnouncement.getType().setCost(Float.valueOf(txtfld_produtCost.getText()));
			} catch (NumberFormatException e) {
				Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
				return;
			}
		} else
			this.myAnnouncement.setType(new Donation());

		try {
			if (this.myProduct.getClass().getName().equals(Book.class.getName()))
				BookCheck();
			else
				DressCheck();
		} catch (Exception e) {
			Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
			return;
		}

		this.myAnnouncementController.updateAnnouncement(this.myAnnouncement);
		this.stage.close();

	}

	@FXML
	void saleSelectedEventHandler(ActionEvent event) {
		txtfld_produtCost.setDisable(false);
	}

	@FXML
	void donationSelectedEventHandler(ActionEvent event) {
		txtfld_produtCost.setDisable(true);
	}

	public void update() {

		// Setting Type

		if (this.myAnnouncement.getType().getClass().getName().equals(Sale.class.getName())) {
			rdbtn_sale.setSelected(true);
			saleSelectedEventHandler(null);
		} else {
			rdbtn_donation.setSelected(true);
			donationSelectedEventHandler(null);
		}
		
		// Set Cost
		txtfld_produtCost.setText(String.valueOf(this.myAnnouncement.getType().getCost()));

		if (this.myProduct.getClass().getName().equals(Book.class.getName())) {
			txtfld_ISBN.setText(((Book) this.myProduct).getISBN());
			txtfld_booktitle.setText(((Book) this.myProduct).getTitle());
			txtfld_bookSubtitle.setText(((Book) this.myProduct).getSubTitle());
			txtfld_bookAuthor.setText(((Book) this.myProduct).getAuthor());
			txtfld_bookEditor.setText(((Book) this.myProduct).getEditor());
			txtfld_bookPage.setText(String.valueOf(((Book) this.myProduct).getNumberOfPages()));

			cmbx_bookType.setValue(((Book) this.myProduct).getType().toString());
			

		} else {

			txtfld_dressName.setText(((Dress) this.myProduct).getDressName());
			txtfld_dressColor.setText(((Dress) this.myProduct).getColor());
			txtfld_dressBrand.setText(((Dress) this.myProduct).getBrand());
			cmbx_dressSize.setValue(((Dress) this.myProduct).getSize());

			cmbx_dressType.setValue(((Dress) this.myProduct).getType().toString());

		}

	}

}
