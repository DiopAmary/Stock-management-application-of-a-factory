package ventes.ventesInterface;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import paiementsInterface.DetailsPaiements;
import paiementsInterface.PaiementsWindow;
import ventes.ventesDataAccess.BonLivraison;
import ventes.ventesDataAccess.BonLivraisonDAOImplementation;
import ventes.ventesDataAccess.LigneCommande;
import ventes.ventesDataAccess.LigneCommandeDAOImplementation;

public class DetailsBonLivraison {
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	
	Label titleLabel = new Label("Détails de livraison");
	
	HBox dateBox = new HBox();
	Label dateLabel =  new Label("Date\t\t\t\t\t:\t");
	Label dateVAlueLabel =  new Label();
	
	HBox nuemroBox = new HBox();
	Label numeroLabel = new Label("N° Bon de livraison\t\t:\t");
	Label numeroValueLabel = new Label();
	
	HBox clientBox = new HBox();
	Label clientLabel = new Label("Client\t\t\t\t:\t");
	Label clientValueLabel = new Label();
	
	HBox totalBox = new HBox();
	Label totalValueLabel = new Label();
	Label totalLabel = new Label("Total \t\t\t:\t");
	
	HBox buttonBox = new HBox();
	Button detailsPaiementsButton = new Button("Détails de paiements");
	Button editerButton = new Button("Editer");
	Button supprimerButton = new Button("Supprimer");
	Button quitterButton = new Button("Quitter");
	
	ObservableList<LigneCommande> observableList = FXCollections.observableArrayList();

	
	Label ligneCommandeTitleLabel = new Label("Lignes de commandes");
	TableView<LigneCommande> ligneCommandeTableView= new TableView<LigneCommande>();
	TableColumn<LigneCommande, String> produitsCategorieColumn = new TableColumn<LigneCommande, String>("Catégorie");
	TableColumn<LigneCommande, String> produitsDesignationColumn = new TableColumn<LigneCommande, String>("Désignation");
	TableColumn<LigneCommande, Double> produitsPrixColumn = new TableColumn<LigneCommande, Double>("Prix");
	TableColumn<LigneCommande, Integer> produitsQuantiteColumn = new TableColumn<LigneCommande, Integer>("Quantité");
	TableColumn<LigneCommande, Integer> sousTotal = new TableColumn<LigneCommande, Integer>("Sous total");
	
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
		window.setScene(scene);
		window.setMinHeight(700);
		window.setMinWidth(750);
		window.setTitle("Details Bon livraison");
		window.show();
	}
	
	private void addToNode() {
		ligneCommandeTableView.getColumns().addAll(produitsCategorieColumn, produitsDesignationColumn, produitsPrixColumn, produitsQuantiteColumn, sousTotal);
		ligneCommandeTableView.setItems(observableList);
		clientBox.getChildren().addAll(clientLabel, clientValueLabel);
		nuemroBox.getChildren().addAll(numeroLabel, numeroValueLabel);
		dateBox.getChildren().addAll(dateLabel, dateVAlueLabel);
		totalBox.getChildren().addAll(totalLabel, totalValueLabel);
		buttonBox.getChildren().addAll(detailsPaiementsButton, editerButton, supprimerButton, quitterButton);
		root.getChildren().addAll(titleLabel, clientBox, nuemroBox, dateBox, ligneCommandeTitleLabel, ligneCommandeTableView, totalBox, buttonBox);
	}
	
	private void getBonLivraisonItems(BonLivraison bonLivraison,  List<LigneCommande> list) {
		clientValueLabel.setText(bonLivraison.getNomClients());
		numeroValueLabel.setText(bonLivraison.getNumeroBL());
		dateVAlueLabel.setText(bonLivraison.getDate()+"");
		totalValueLabel.setText(bonLivraison.getTotal()+"");
		observableList.addAll(list);
	}
	
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		ligneCommandeTitleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(750);
		ligneCommandeTitleLabel.setMinWidth(750);
		ligneCommandeTableView.getStyleClass().add("cell");
		ligneCommandeTableView.setMinHeight(350);
		quitterButton.getStyleClass().add("buttons");
		supprimerButton.getStyleClass().add("btn-danger");
		editerButton.getStyleClass().add("btn-primary");
		detailsPaiementsButton.getStyleClass().add("buttons");
		buttonBox.setSpacing(20);
		root.setSpacing(15);
	}
	VentesHandler handler = new VentesHandler(this);
	private void addEvent(BonLivraison bonLivraison) {
		quitterButton.setOnAction(event->{
			new ListeBonLivraisonWindow();
			window.close();
		});
		supprimerButton.setOnAction(event->{
			handler.supprimerBL(numeroValueLabel.getText());
			window.close();
			new ListeBonLivraisonWindow();
		});
		editerButton.setOnAction(event->{
			new VentesWindow(this);
		});
		detailsPaiementsButton.setOnAction(event->{
			new DetailsPaiements(bonLivraison);
			window.close();
		});
	}
	
	DetailsBonLivraison(BonLivraison bonLivraison, List<LigneCommande> list){
		getBonLivraisonItems(bonLivraison, list);
		addToNode();
		addStyleToNodes();
		addEvent(bonLivraison);
		updateColumLigneCommande();
		initWindow();
	}
}
