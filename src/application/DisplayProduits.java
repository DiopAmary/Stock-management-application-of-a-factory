package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import produitsDataAccess.Produits;

public class DisplayProduits {
	DeleteHandler handler = new DeleteHandler();
	VBox root = new VBox();
	Stage window = new Stage();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Details du Produit");
	Button editerButton = new Button("Editer");
	Button quitterButton = new Button("Quitter");
	Button deleteButton = new Button("Supprimer");
	HBox buttonBox = new HBox();
	HBox idBox = new HBox();
	HBox designationBox = new HBox();
	HBox prixAchatBox = new HBox();
	HBox prixVenteBox = new HBox();
	HBox categorieBox = new HBox();
	
	Label idLabel = new Label("Id\t\t\t\t:\t");
	Label designationLabel = new Label("Désignation\t\t:\t");
	Label prixAchatLabel = new Label("Prix d'achat\t\t:\t");
	Label prixVenteLabel = new Label("Prix de vente\t\t:\t");
	Label categorieLabel = new Label("Catégorie\t\t\t:\t");
	
	private List<Label> getProduitItems(Produits produits){
		List<Label> list = new ArrayList<Label>();
		Label idValueLabel = new Label(produits.getId()+"");
		Label designationValueLabel = new Label(produits.getDesignation());
		Label prixAchatValueLabel = new Label(produits.getPrixAchat()+"");
		Label prixVenteValueLabel = new Label(produits.getPrixVente()+"");
		Label categorieValueLabel = new Label(produits.getcategorie());
		list.add(idValueLabel);
		list.add(designationValueLabel);
		list.add(prixAchatValueLabel);
		list.add(prixVenteValueLabel);
		list.add(categorieValueLabel);
		return list;
	}
	private void addToNode(Produits produits) {
		List<Label> list= getProduitItems(produits);
		idBox.getChildren().addAll(idLabel, list.get(0));
		designationBox.getChildren().addAll(designationLabel, list.get(1));
		prixAchatBox.getChildren().addAll(prixAchatLabel, list.get(2));
		prixVenteBox.getChildren().addAll(prixVenteLabel, list.get(3));
		categorieBox.getChildren().addAll(categorieLabel, list.get(4));
		buttonBox.getChildren().addAll(editerButton, deleteButton, quitterButton);
		root.getChildren().addAll(titleLabel, idBox, designationBox, prixAchatBox, prixVenteBox, categorieBox, buttonBox);
		addStyleToNode(list);
	}
	private void addStyleToNode(List<Label> list) {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(400);
		titleLabel.setMinHeight(50);
		idLabel.getStyleClass().add("labelForm");
		idLabel.setMinHeight(30);
		designationLabel.getStyleClass().add("labelForm");
		designationLabel.setMinHeight(30);
		prixAchatLabel.getStyleClass().add("labelForm");
		prixAchatLabel.setMinHeight(30);
		prixVenteLabel.getStyleClass().add("labelForm");
		prixVenteLabel.setMinHeight(30);
		categorieLabel.getStyleClass().add("labelForm");
		categorieLabel.setMinHeight(30);
		for(Label label :  list) {
			label.getStyleClass().add("labelForm");
			label.setMinHeight(30);
		}
		idBox.setMinHeight(40);
		designationBox.setMinHeight(40);
		prixAchatBox.setMinHeight(40);
		prixVenteBox.setMinHeight(40);
		categorieBox.setMinHeight(40);
		quitterButton.getStyleClass().add("btn-primary");
		deleteButton.getStyleClass().add("btn-danger");
		editerButton.getStyleClass().add("buttons");
		root.setSpacing(5);
		buttonBox.setSpacing(20);
	}
	private void addEvent(Produits produits) {
		quitterButton.setOnAction(event->{
			window.close();
			new ListeProduitsWindow();
		});
		editerButton.setOnAction(event->{
			new FormProduitsWindow(produits);
			window.close();
		});
		deleteButton.setOnAction(event->{
			handler.updateListeProduitByDelete(produits.getId());
			new ListeProduitsWindow();
			window.close();
		});
	}
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(400);
		window.setHeight(350);
		window.setTitle("Details du Produit");
		window.show();
	}
	public DisplayProduits(Produits produits) {
		addToNode(produits);
		addEvent(produits);
		initWindow();
	}
}
