package UserInterface;

import UserDataAccess.Clients;
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

public class RechercherClientsWindow {
	RechercherClientsHandler handler = new RechercherClientsHandler(this);
	public RechercherClientsWindow() {
		initWindow();
		addToWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Rechercher clients");
	Label nomLabel = new Label("Nom du client: ");
	TextField nomField = new TextField();
	ObservableList<Clients> observableList = FXCollections.observableArrayList();
	
	Button rechButton = new Button("Rechercher");
	Button cancelButton = new Button("Annuler");
	HBox buttonsBox = new HBox();
	
	
	private void addEvent(){
		window.setOnCloseRequest(event->{event.consume();});
		rechButton.setOnAction(event->{
			handler.updateListeRechercheClientsWindow();
			new RechercherClientsResultats(observableList);
			window.close();
		});
		cancelButton.setOnAction(event->{window.close();});
	}
	
	private void addStyleToNode() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		titleLabel.setMinHeight(50);
		nomLabel.getStyleClass().add("labelForm");
		nomField.setMinHeight(30);
		
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
		root.getChildren().addAll(nomLabel, nomField);
		buttonsBox.getChildren().addAll(rechButton, cancelButton);
		root.getChildren().add(buttonsBox);
	}
}
