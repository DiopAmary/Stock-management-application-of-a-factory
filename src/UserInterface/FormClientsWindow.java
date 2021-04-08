package UserInterface;

import UserDataAccess.Clients;
import application.ListeProduitsWindow;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import produitsDataAccess.Produits;

public class FormClientsWindow {
	addClientsHandler handler = new addClientsHandler(this);
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouveau client");
	Label nomLabel = new Label("Nom : ");
	Label prenomLabel = new Label("Prenom : ");
	Label telephoneLabel = new Label("Téléphone : ");
	Label emailLabel = new Label("Email : ");
	Label adressLabel = new Label("Adresse : ");
	
	TextField nomField = new TextField();
	TextField prenomField = new TextField();
	TextField telephoneField = new TextField();
	TextField emailField = new TextField();
	TextField adressField = new TextField();
	
	Button addButton = new Button("Enregistrer");
	Button cancelButton = new Button("Annuler");
	HBox buttonsBox = new HBox();
	
	
	public FormClientsWindow(){
		initWindow();
		addToWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}
	private void initForm(Clients clients) {
		nomField.setText(clients.getNom());
		prenomField.setText(clients.getPrenom());
		telephoneField.setText(clients.getTelephone());
		emailField.setText(clients.getEmail());
		adressField.setText(clients.getAdresse());
	}
	public FormClientsWindow(Clients clients){
		initWindow();
		addToWindow(clients);
		addStyleToNode();
		addEvent(clients);
		window.show();
	}
	
	private void addEvent(Clients clients) {
		window.setOnCloseRequest(event->{event.consume();});
		addButton.setOnAction(event->{
			handler.editClick(clients);
			window.close();
			new ListeClientsWindow();
		});
		cancelButton.setOnAction(event->{window.close();});
	}
	
	private void addEvent(){
		window.setOnCloseRequest(event->{event.consume();});
		addButton.setOnAction(event->{
			handler.addClick();
			new DisplayClients(handler.getClients());
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
		nomLabel.setMinHeight(30);
		prenomLabel.getStyleClass().add("labelForm");
		prenomLabel.setMinHeight(30);
		telephoneLabel.getStyleClass().add("labelForm");
		telephoneLabel.setMinHeight(30);
		emailLabel.getStyleClass().add("labelForm");
		emailLabel.setMinHeight(30);
		adressLabel.getStyleClass().add("labelForm");
		adressField.setMinHeight(30);
		addButton.getStyleClass().add("buttons");
		cancelButton.getStyleClass().add("buttons");
		addButton.setMinHeight(40);
		cancelButton.setMinHeight(40);
		root.setSpacing(5);
		buttonsBox.setSpacing(15);
	}
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(450);
		window.setHeight(460);
		window.setTitle("Nouveau Client");
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void addToWindow(){
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(nomLabel, nomField);
		root.getChildren().addAll(prenomLabel, prenomField);
		root.getChildren().addAll(telephoneLabel, telephoneField);
		root.getChildren().addAll(emailLabel, emailField);
		root.getChildren().addAll(adressLabel, adressField);
		buttonsBox.getChildren().addAll(addButton, cancelButton);
		root.getChildren().add(buttonsBox);
	}
	private void addToWindow(Clients clients){
		initForm(clients);
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(nomLabel, nomField);
		root.getChildren().addAll(prenomLabel, prenomField);
		root.getChildren().addAll(telephoneLabel, telephoneField);
		root.getChildren().addAll(emailLabel, emailField);
		root.getChildren().addAll(adressLabel, adressField);
		buttonsBox.getChildren().addAll(addButton, cancelButton);
		root.getChildren().add(buttonsBox);
	}
}
