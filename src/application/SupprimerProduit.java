package application;


import application.ListeProduitsWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import produitsDataAccess.Produits;

public class SupprimerProduit {
	DeleteHandler deleteHandler = new DeleteHandler(this);
	public SupprimerProduit() {
		initWindow();
		addToWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Supprimer un produit");
	Label idLabel = new Label("Id du produit: ");
	TextField idField = new TextField();
	
	Button suppButton = new Button("Supprimer");
	Button cancelButton = new Button("Annuler");
	HBox buttonsBox = new HBox();
	
	
	private void addEvent(){
		window.setOnCloseRequest(event->{event.consume();});
		suppButton.setOnAction(event->{
			deleteHandler.updateListeProduitByDelete();
			new ListeProduitsWindow();
			window.close();
		});
		cancelButton.setOnAction(event->{window.close();});
	}
	
	private void addStyleToNode() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		titleLabel.setMinHeight(50);
		idLabel.getStyleClass().add("labelForm");
		idLabel.setMinHeight(30);
		
		suppButton.getStyleClass().add("btn-danger");
		cancelButton.getStyleClass().add("buttons");
		suppButton.setMinHeight(40);
		cancelButton.setMinHeight(40);
		root.setSpacing(5);
		buttonsBox.setSpacing(15);
	}
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(550);
		window.setHeight(210);
		window.setTitle("Recherche de Produits");
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void addToWindow(){
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(idLabel, idField);
		buttonsBox.getChildren().addAll(suppButton, cancelButton);
		root.getChildren().add(buttonsBox);
	}
}

