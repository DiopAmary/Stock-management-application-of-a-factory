package ventes.ventesInterface;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import UserDataAccess.Clients;
import application.ListeProduitsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import produitsDataAccess.Produits;
import ventes.ventesDataAccess.BonLivraison;
import ventes.ventesDataAccess.LigneCommande;

public class VentesWindow {
	ListeProduitsHandler handler = new ListeProduitsHandler(this);
	VentesHandler ventesHandler = new VentesHandler(this);
	
	LigneCommande ligneCommandeCliked = null;
	double totalHT = 0;
	double tva = 0;
	double total = 0;
	
	Stage window = new Stage();
	HBox root = new HBox();
	Scene scene = new Scene(root);
	VBox leftBox = new VBox();
	VBox rightBox = new VBox();
	HBox numeroBlBox = new HBox();
	Label detailBlLabel = new Label("Details du BL");
	Label precisionLabel = new Label("Precision ligne commande");
	Label listeProduits = new Label("Liste produits");
	Label numeroLabel = new Label("N° Bon de livraison\t\t");
	TextField numeroBlField = new TextField();
	HBox nomclientsBox = new HBox();
	Label nomClientsLabel = new Label("Client\t\t\t\t\t:");
	HBox dateBox = new HBox();
	Label dateLabel =  new Label("Date\t\t\t\t\t");
	DatePicker datePicker = new DatePicker();
	
	Button enregistrerButton = new Button("Enregistrer");
	
	VBox reglementBlBox = new VBox();
	HBox totalHTBox = new HBox();
	HBox tvaBox = new HBox();
	HBox totalBox = new HBox();
	Label titleBoxLabel = new Label("Réglement du Bl");
	Label totalHTValueLabel = new Label();
	Label tvaValueLabel = new Label();
	Label totalValueLabel = new Label();
	Label totalHTLabel = new Label("Total HT\t\t\t:");
	Label tvaLabel = new Label("TVA 20%\t\t\t:");
	Label totaLabel = new Label("Total\t\t\t\t:");
	
	Label ligneCommandeTitleLabel = new Label("Lignes de commandes");
	TableView<LigneCommande> ligneCommandeTableView= new TableView<LigneCommande>();
	TableColumn<LigneCommande, String> produitsCategorieColumn = new TableColumn<LigneCommande, String>("Catégorie");
	TableColumn<LigneCommande, String> produitsDesignationColumn = new TableColumn<LigneCommande, String>("Désignation");
	TableColumn<LigneCommande, Double> produitsPrixColumn = new TableColumn<LigneCommande, Double>("Prix");
	TableColumn<LigneCommande, Integer> produitsQuantiteColumn = new TableColumn<LigneCommande, Integer>("Quantité");
	TableColumn<LigneCommande, Integer> sousTotal = new TableColumn<LigneCommande, Integer>("Sous total");



	
	HBox formBox = new HBox();
	VBox buttonBox = new VBox();
	Button plusButton = new Button("Ajouter");
	Button moinsButton = new Button("Enlever");
	VBox formBlBox = new VBox();
	HBox categorieBox = new HBox();
	HBox designationBox = new HBox();
	HBox prixBox = new HBox();
	HBox quantiteBox = new HBox();
	Label categorieLabel = new Label("Catégorie\t\t");
	Label designationLabel = new Label("Désignation\t");
	Label prixLabel = new Label("Prix\t\t\t");
	Label quantiteLabel = new Label("Quantité\t\t");
	TextField categorieField = new TextField();
	TextField designationField = new TextField();
	TextField prixField = new TextField();
	TextField quantiteField = new TextField();
	
	TableView<Produits> prodduitsTableView= new TableView<Produits>();
	TableColumn<Produits, Integer> idColumn = new TableColumn<Produits, Integer>("Id");
	TableColumn<Produits, String> designationColumn = new TableColumn<Produits, String>("Désignation");
	TableColumn<Produits, Double> prixVenteColumn = new TableColumn<Produits, Double>("Prix");
	
	public ObservableList<Produits> observableList = FXCollections.observableArrayList();
	public ObservableList<LigneCommande> ligneCommandesObservableList = FXCollections.observableArrayList();
	
	
	private void addToNode(Clients clients) {
		Label clientValueLabel = new Label(clients.getNom()+"  "+clients.getPrenom());
		clientValueLabel.setStyle("-fx-font-size: 15.0px");
		numeroBlBox.getChildren().addAll(numeroLabel, numeroBlField);
		nomclientsBox.getChildren().addAll(nomClientsLabel,clientValueLabel);
		dateBox.getChildren().addAll(dateLabel, datePicker);
		
		
		categorieBox.getChildren().addAll(categorieLabel, categorieField);
		designationBox.getChildren().addAll(designationLabel, designationField);
		prixBox.getChildren().addAll(prixLabel, prixField);
		quantiteBox.getChildren().addAll(quantiteLabel, quantiteField);
		formBlBox.getChildren().addAll(categorieBox, designationBox, prixBox, quantiteBox);
		
		totalHTBox.getChildren().addAll(totalHTLabel, totalHTValueLabel);
		tvaBox.getChildren().addAll(tvaLabel, tvaValueLabel);
		totalBox.getChildren().addAll(totaLabel, totalValueLabel);
		
		reglementBlBox.getChildren().addAll(titleBoxLabel, totalHTBox, tvaBox, totalBox);
		rightBox.getChildren().addAll(reglementBlBox, ligneCommandeTitleLabel, ligneCommandeTableView, enregistrerButton);
		
		buttonBox.getChildren().addAll(plusButton, moinsButton);
		formBox.getChildren().addAll(formBlBox, buttonBox);
		prodduitsTableView.getColumns().addAll(idColumn, designationColumn, prixVenteColumn);
		ligneCommandeTableView.getColumns().addAll(produitsCategorieColumn, produitsDesignationColumn, produitsPrixColumn, produitsQuantiteColumn, sousTotal);
		leftBox.getChildren().addAll(detailBlLabel, nomclientsBox, numeroBlBox, dateBox, precisionLabel, formBox, listeProduits, prodduitsTableView);
		prodduitsTableView.setItems(observableList);
		ligneCommandeTableView.setItems(ligneCommandesObservableList);
		root.getChildren().addAll(leftBox, rightBox);
	}
	
	private void addToNode(DetailsBonLivraison detailsBonLivraison) {
		Label clientValueLabel = new Label(detailsBonLivraison.clientValueLabel.getText());
		numeroBlField.setText(detailsBonLivraison.numeroValueLabel.getText());
		ligneCommandesObservableList.addAll(detailsBonLivraison.observableList);
		clientValueLabel.setStyle("-fx-font-size: 15.0px");
		numeroBlBox.getChildren().addAll(numeroLabel, numeroBlField);
		nomclientsBox.getChildren().addAll(nomClientsLabel,clientValueLabel);
		dateBox.getChildren().addAll(dateLabel, datePicker);
		
		
		
		total = Double.valueOf(detailsBonLivraison.totalValueLabel.getText());
		for(LigneCommande lc : ligneCommandesObservableList) {
			totalHT+=lc.getSousTotal();
			categorieField.setText(lc.getProduitsCategorie());
			designationField.setText(lc.getProduitsDesignation());
			prixField.setText(lc.getProduitsPrixAchat()+"");
			quantiteField.setText(lc.getQuantite()+"");
		}
		tva = total-totalHT;
		tvaValueLabel.setText(tva+"");
		totalValueLabel.setText(total+"");
		totalHTValueLabel.setText(totalHT+"");
		
		categorieBox.getChildren().addAll(categorieLabel, categorieField);
		designationBox.getChildren().addAll(designationLabel, designationField);
		prixBox.getChildren().addAll(prixLabel, prixField);
		quantiteBox.getChildren().addAll(quantiteLabel, quantiteField);
		formBlBox.getChildren().addAll(categorieBox, designationBox, prixBox, quantiteBox);
		
		totalHTBox.getChildren().addAll(totalHTLabel, totalHTValueLabel);
		tvaBox.getChildren().addAll(tvaLabel, tvaValueLabel);
		totalBox.getChildren().addAll(totaLabel, totalValueLabel);
		
		reglementBlBox.getChildren().addAll(titleBoxLabel, totalHTBox, tvaBox, totalBox);
		rightBox.getChildren().addAll(reglementBlBox, ligneCommandeTitleLabel, ligneCommandeTableView, enregistrerButton);
		
		buttonBox.getChildren().addAll(plusButton, moinsButton);
		formBox.getChildren().addAll(formBlBox, buttonBox);
		prodduitsTableView.getColumns().addAll(idColumn, designationColumn, prixVenteColumn);
		ligneCommandeTableView.getColumns().addAll(produitsCategorieColumn, produitsDesignationColumn, produitsPrixColumn, produitsQuantiteColumn, sousTotal);
		leftBox.getChildren().addAll(detailBlLabel, nomclientsBox, numeroBlBox, dateBox, precisionLabel, formBox, listeProduits, prodduitsTableView);
		prodduitsTableView.setItems(observableList);
		ligneCommandeTableView.setItems(ligneCommandesObservableList);
		root.getChildren().addAll(leftBox, rightBox);
	}
	
	private void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id"));
		idColumn.setPrefWidth(40);
		designationColumn.setCellValueFactory(new PropertyValueFactory<Produits, String>("designation"));
		designationColumn.setPrefWidth(280);
		prixVenteColumn.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prixVente"));
		prixVenteColumn.setPrefWidth(80);
	}
	private void updateColumLigneCommande() {
		produitsCategorieColumn.setCellValueFactory(new PropertyValueFactory<LigneCommande, String>("produitsCategorie"));
		produitsCategorieColumn.setPrefWidth(150);
		produitsDesignationColumn.setCellValueFactory(new PropertyValueFactory<LigneCommande, String>("produitsDesignation"));
		produitsDesignationColumn.setPrefWidth(280);
		produitsPrixColumn.setCellValueFactory(new PropertyValueFactory<LigneCommande, Double>("produitsPrixAchat"));
		produitsPrixColumn.setPrefWidth(100);
		produitsQuantiteColumn.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("quantite"));
		produitsQuantiteColumn.setPrefWidth(100);
		sousTotal.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("sousTotal"));
		sousTotal.setPrefWidth(120);
	}
	
	private void initWindow() {
		window.setHeight(750);
		window.setWidth(1200);
		window.setTitle("Gestion des ventes");
		window.setScene(scene);
		window.show();
	}
	
	private void addStyleToNode() {
		scene.getStylesheets().add("css/styles.css");
		prixBox.setSpacing(20);
		designationBox.setSpacing(20);
		categorieBox.setSpacing(20);
		quantiteBox.setSpacing(20);
		formBlBox.setMinHeight(140);
		formBlBox.setMinWidth(300);
		buttonBox.setMinWidth(100);
		buttonBox.setSpacing(20);
		formBlBox.setSpacing(10);
		plusButton.setMinHeight(60);
		plusButton.getStyleClass().add("btn-primary");
		moinsButton.setMinHeight(60);
		moinsButton.getStyleClass().add("btn-danger");
		
		enregistrerButton.getStyleClass().add("btn-primary");
		
		
		dateBox.setSpacing(20);
		numeroBlBox.setSpacing(20);
		
		prodduitsTableView.getStyleClass().add("cell");
		prodduitsTableView.setMinHeight(300);
		ligneCommandeTableView.getStyleClass().add("cell");
		ligneCommandeTableView.setMinHeight(450);
		
		listeProduits.getStyleClass().add("titleLabelStyle");
		listeProduits.setMinWidth(400);
		detailBlLabel.getStyleClass().add("titleLabelStyle");
		detailBlLabel.setMinWidth(400);
		precisionLabel.getStyleClass().add("titleLabelStyle");
		precisionLabel.setMinWidth(400);
		
		reglementBlBox.setSpacing(15);
		titleBoxLabel.getStyleClass().add("titleLabelStyle");
		titleBoxLabel.setMinWidth(750);
		root.setSpacing(50);
		leftBox.setSpacing(10);
		rightBox.setSpacing(15);
		ligneCommandeTitleLabel.getStyleClass().add("titleLabelStyle");
		ligneCommandeTitleLabel.setMinWidth(750);
	}
	
	private void initLigneCommande(LigneCommande ligneCommande) {
		ligneCommandeCliked = ligneCommande;
	}
	
	private void addEvent(Clients clients) {
		prodduitsTableView.setOnMouseClicked(event->{
			Produits produit=prodduitsTableView.getItems().get(prodduitsTableView.getSelectionModel().getSelectedIndex());
			categorieField.setText(produit.getcategorie());
			prixField.setText(produit.getPrixVente()+"");
			designationField.setText(produit.getDesignation());
			quantiteField.setText(1+"");
		});
		ligneCommandeTableView.setOnMouseClicked(event->{
			LigneCommande ligneCommande=ligneCommandeTableView.getItems().get(ligneCommandeTableView.getSelectionModel().getSelectedIndex());
			initLigneCommande(ligneCommande);
		});
		plusButton.setOnAction(event->{
			LigneCommande ligneCommande = new LigneCommande(0, Integer.valueOf(quantiteField.getText()), designationField.getText());
			ligneCommandesObservableList.addAll(ligneCommande);
			totalHT+=ligneCommande.getSousTotal();
			tva = 0.2*totalHT;
			total = totalHT+tva;
			totalHTValueLabel.setText("\t"+totalHT);
			tvaValueLabel.setText("\t"+tva);
			totalValueLabel.setText("\t"+total);
		});
		moinsButton.setOnAction(event->{
			ligneCommandesObservableList.removeAll(ligneCommandeCliked);
			double n = Double.valueOf(ligneCommandeCliked.getProduits().getPrixVente()*ligneCommandeCliked.getQuantite())*0.2;
			double m = Double.valueOf(ligneCommandeCliked.getProduits().getPrixVente()*ligneCommandeCliked.getQuantite())+n;
			tva-=n;
			total-=m;
			totalHT-=Double.valueOf(ligneCommandeCliked.getProduits().getPrixVente()*ligneCommandeCliked.getQuantite());
			totalHTValueLabel.setText("\t"+totalHT);
			tvaValueLabel.setText("\t"+tva);
			totalValueLabel.setText("\t"+total);
		});
		enregistrerButton.setOnAction(event->{
			Date date = Date.valueOf(datePicker.getValue());
			BonLivraison bl = new BonLivraison(clients.getNom()+" "+clients.getPrenom(), date, Double.valueOf(totalValueLabel.getText()), numeroBlField.getText());
			List<LigneCommande> list = new ArrayList<LigneCommande>();
			for(LigneCommande ligneCommande : ligneCommandesObservableList) {
				LigneCommande lc = new LigneCommande(0, ligneCommande.getProduitsCategorie(), ligneCommande.getProduitsDesignation(), ligneCommande.getProduitsPrixAchat(), ligneCommande.getQuantite(), ligneCommande.getSousTotal(), numeroBlField.getText());
				list.add(lc);
			}
			ventesHandler.addClick(bl, list);
			new DetailsBonLivraison(bl, list);
			window.close();
		});
	}
	
	private void addEvent(DetailsBonLivraison detailsBonLivraison) {
		prodduitsTableView.setOnMouseClicked(event->{
			Produits produit=prodduitsTableView.getItems().get(prodduitsTableView.getSelectionModel().getSelectedIndex());
			categorieField.setText(produit.getcategorie());
			prixField.setText(produit.getPrixVente()+"");
			designationField.setText(produit.getDesignation());
			quantiteField.setText(1+"");
		});
		ligneCommandeTableView.setOnMouseClicked(event->{
			LigneCommande ligneCommande=ligneCommandeTableView.getItems().get(ligneCommandeTableView.getSelectionModel().getSelectedIndex());
			initLigneCommande(ligneCommande);
		});
		plusButton.setOnAction(event->{
			LigneCommande ligneCommande = new LigneCommande(0, Integer.valueOf(quantiteField.getText()), designationField.getText());
			ligneCommandesObservableList.addAll(ligneCommande);
			totalHT+=ligneCommande.getSousTotal();
			tva = 0.2*totalHT;
			total = totalHT+tva;
			totalHTValueLabel.setText("\t"+totalHT);
			tvaValueLabel.setText("\t"+tva);
			totalValueLabel.setText("\t"+total);
		});
		moinsButton.setOnAction(event->{
			ligneCommandesObservableList.removeAll(ligneCommandeCliked);
			double n = Double.valueOf(ligneCommandeCliked.getProduitsPrixAchat())*0.2;
			double m = Double.valueOf(ligneCommandeCliked.getProduitsPrixAchat())+n;
			tva-=n;
			total-=m;
			totalHT-=Double.valueOf(ligneCommandeCliked.getProduitsPrixAchat());
			totalHTValueLabel.setText("\t"+totalHT);
			tvaValueLabel.setText("\t"+tva);
			totalValueLabel.setText("\t"+total);
		});
		enregistrerButton.setOnAction(event->{
			Date date = Date.valueOf(datePicker.getValue());
			BonLivraison bl = new BonLivraison(detailsBonLivraison.clientValueLabel.getText(), date, Double.valueOf(totalValueLabel.getText()), numeroBlField.getText());
			List<LigneCommande> list = new ArrayList<LigneCommande>();
			for(LigneCommande ligneCommande : ligneCommandesObservableList) {
				LigneCommande lc = new LigneCommande(0, ligneCommande.getProduitsCategorie(), ligneCommande.getProduitsDesignation(), ligneCommande.getProduitsPrixAchat(), ligneCommande.getQuantite(), ligneCommande.getSousTotal(), numeroBlField.getText());
				list.add(lc);
			}
			ventesHandler.update(bl, list, detailsBonLivraison.numeroValueLabel.getText());
			new DetailsBonLivraison(bl, list);
			window.close();
		});
	}
	
	
	
	public VentesWindow(Clients clients) {
		updateColumn();
		updateColumLigneCommande();
		addEvent(clients);
		addToNode(clients);
		addStyleToNode();
		handler.venteWindowUpdateListeProduits();
		initWindow();
	}
	public VentesWindow(DetailsBonLivraison detailsBonLivraison) {
		updateColumn();
		updateColumLigneCommande();
		addToNode(detailsBonLivraison);
		addEvent(detailsBonLivraison);
		addStyleToNode();
		handler.venteWindowUpdateListeProduits();
		initWindow();
	}
}
