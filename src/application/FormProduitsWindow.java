package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import produitsDataAccess.Produits;

public class FormProduitsWindow {
	Produits produits = null;
	addProduitsHandler handler = new addProduitsHandler(this);
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouveau Produit");
	Label designationLabel = new Label("Désignation : ");
	Label prixAchatLabel = new Label("Prix d'achat : ");
	Label prixVenteLabel = new Label("Prix de vente : ");
	Label categorieLabel = new Label("Catégorie : ");
	
	TextField desigantionField = new TextField();
	TextField prixAchatField = new TextField();
	TextField prixVenTeField = new TextField();
	TextField categorieField = new TextField();
	
	Button addButton = new Button("Enregistrer");
	Button cancelButton = new Button("Annuler");
	HBox buttonsBox = new HBox();
	
	
	public FormProduitsWindow(){
		initWindow();
		addToWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}
	private void initForm(Produits produits) {
		desigantionField.setText(produits.getDesignation());
		prixAchatField.setText(produits.getPrixAchat()+"");
		prixVenTeField.setText(produits.getPrixVente()+"");
		categorieField.setText(produits.getcategorie());
	}
	public FormProduitsWindow(Produits produits){
		this.produits = produits;
		initWindow();
		addToWindow(produits);
		addStyleToNode();
		addEvent(produits);
		window.show();
	}
	
	private void addEvent(){
		window.setOnCloseRequest(event->{event.consume();});
		addButton.setOnAction(event->{
			handler.addClick();
			window.close();
			new ListeProduitsWindow();
		});
		cancelButton.setOnAction(event->{window.close();});
	}
	private void addEvent(Produits produits) {
		window.setOnCloseRequest(event->{event.consume();});
		addButton.setOnAction(event->{
			handler.editClick(produits);
			window.close();
			new ListeProduitsWindow();
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
		prixAchatLabel.getStyleClass().add("labelForm");
		prixAchatLabel.setMinHeight(30);
		prixVenteLabel.getStyleClass().add("labelForm");
		prixVenteLabel.setMinHeight(30);
		categorieLabel.getStyleClass().add("labelForm");
		categorieLabel.setMinHeight(30);
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
		window.setHeight(400);
		window.setTitle("Nouveau produit");
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void addToWindow(){
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(designationLabel, desigantionField);
		root.getChildren().addAll(prixAchatLabel, prixAchatField);
		root.getChildren().addAll(prixVenteLabel, prixVenTeField);
		root.getChildren().addAll(categorieLabel, categorieField);
		buttonsBox.getChildren().addAll(addButton, cancelButton);
		root.getChildren().add(buttonsBox);
	}
	private void addToWindow(Produits produits){
		initForm(produits);
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(designationLabel, desigantionField);
		root.getChildren().addAll(prixAchatLabel, prixAchatField);
		root.getChildren().addAll(prixVenteLabel, prixVenTeField);
		root.getChildren().addAll(categorieLabel, categorieField);
		buttonsBox.getChildren().addAll(addButton, cancelButton);
		root.getChildren().add(buttonsBox);
	}
}
