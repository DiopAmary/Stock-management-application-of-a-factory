package application;

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

public class RechercheProduitsWindow {
	RechercheHandler handler = new RechercheHandler(this);
	public RechercheProduitsWindow() {
		initWindow();
		addToWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Rechercher produits");
	Label designationLabel = new Label("Désignation du produit: ");
	TextField desigantionField = new TextField();
	ObservableList<Produits> observableList = FXCollections.observableArrayList();
	
	Button rechButton = new Button("Rechercher");
	Button cancelButton = new Button("Annuler");
	HBox buttonsBox = new HBox();
	
	
	private void addEvent(){
		window.setOnCloseRequest(event->{event.consume();});
		rechButton.setOnAction(event->{
			handler.updateListeRechercheProduitsWindow();
			new RechercheResultats(observableList);
			window.close();
		});
		cancelButton.setOnAction(event->{window.close();});
	}
	
	private void addStyleToNode() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		titleLabel.setMinHeight(50);
		designationLabel.getStyleClass().add("labelForm");
		designationLabel.setMinHeight(30);
		
		rechButton.getStyleClass().add("buttons");
		cancelButton.getStyleClass().add("buttons");
		rechButton.setMinHeight(40);
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
		root.getChildren().addAll(designationLabel, desigantionField);
		buttonsBox.getChildren().addAll(rechButton, cancelButton);
		root.getChildren().add(buttonsBox);
	}
}
