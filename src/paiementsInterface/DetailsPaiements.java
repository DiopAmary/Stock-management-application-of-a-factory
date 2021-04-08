package paiementsInterface;

import java.sql.Date;
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
import paimentsDataAccess.Paiements;
import ventes.ventesDataAccess.BonLivraison;
import ventes.ventesDataAccess.LigneCommande;
import ventes.ventesInterface.ListeBonLivraisonWindow;
import ventes.ventesInterface.VentesWindow;

public class DetailsPaiements {
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	
	Label titleLabel = new Label("Détails de paiements");

	Label trancheTitleLabel = new Label("Tranches de paiements");
	
	HBox numeroBlBox = new HBox();
	Label numeroBLLabel = new Label("N° Bon de livraison\t\t:\t");
	Label numeroBLValueLabel = new Label();
	
	HBox clientBox = new HBox();
	Label clientLabel = new Label("Client\t\t\t\t:\t");
	Label clientValueLabel = new Label();
	
	HBox totalPayeBox = new HBox();
	Label totalPayeValueLabel = new Label();
	Label totalPayeLabel = new Label("Total payé \t\t\t:\t");
	
	HBox resteBox = new HBox();
	Label resteValueLabel = new Label();
	Label resteLabel = new Label("Reste \t\t\t\t:\t");
	
	HBox buttonBox = new HBox();
	Button payerButton = new Button("Payer");
	Button popUpButton = new Button("Paiement intégralement éffectué");
	Button quitterButton = new Button("Quitter");
	
	TableView<Paiements> PaiementsTableView= new TableView<Paiements>();
	TableColumn<Paiements, Integer> numeroPaiementColumn = new TableColumn<Paiements, Integer>("N°");
	TableColumn<Paiements, Double> montantPaiementColumn = new TableColumn<Paiements, Double>("Montant");
	TableColumn<Paiements, Date> datePaiementColumn = new TableColumn<Paiements, Date>("Date");
	TableColumn<Paiements, String> typePaiementColumn = new TableColumn<Paiements, String>("Type");
	TableColumn<Paiements, String> numeroChequeColumn = new TableColumn<Paiements, String>("N° Chèque");
	TableColumn<Paiements, String> proprietaireColumn = new TableColumn<Paiements, String>("Propriétaire");
	TableColumn<Paiements, String> banqueColumn = new TableColumn<Paiements, String>("Banque");
	
	ObservableList<Paiements> observableList = FXCollections.observableArrayList();
	
	private void updateColumPaiement() {
		numeroPaiementColumn.setCellValueFactory(new PropertyValueFactory<Paiements, Integer>("numero"));
		numeroPaiementColumn.setPrefWidth(50);
		montantPaiementColumn.setCellValueFactory(new PropertyValueFactory<Paiements, Double>("montant"));
		montantPaiementColumn.setPrefWidth(120);
		datePaiementColumn.setCellValueFactory(new PropertyValueFactory<Paiements, Date>("date"));
		datePaiementColumn.setPrefWidth(120);
		typePaiementColumn.setCellValueFactory(new PropertyValueFactory<Paiements, String>("type"));
		typePaiementColumn.setPrefWidth(100);
		numeroChequeColumn.setCellValueFactory(new PropertyValueFactory<Paiements, String>("numeroCheque"));
		numeroChequeColumn.setPrefWidth(120);
		proprietaireColumn.setCellValueFactory(new PropertyValueFactory<Paiements, String>("proprietaire"));
		proprietaireColumn.setPrefWidth(120);
		banqueColumn.setCellValueFactory(new PropertyValueFactory<Paiements, String>("banque"));
		banqueColumn.setPrefWidth(120);
	}
	

	private void addToNode() {
		clientBox.getChildren().addAll(clientLabel, clientValueLabel);
		numeroBlBox.getChildren().addAll(numeroBLLabel, numeroBLValueLabel);
		resteBox.getChildren().addAll(resteLabel, resteValueLabel);
		totalPayeBox.getChildren().addAll(totalPayeLabel, totalPayeValueLabel);
		if(reste == 0.0) {
			buttonBox.getChildren().addAll(popUpButton, quitterButton);
		}else {
			buttonBox.getChildren().addAll(payerButton, quitterButton);
		}
		PaiementsTableView.getColumns().addAll(numeroPaiementColumn, montantPaiementColumn, datePaiementColumn, typePaiementColumn, numeroChequeColumn, proprietaireColumn, banqueColumn);
		PaiementsTableView.setItems(observableList);
		root.getChildren().addAll(titleLabel, clientBox, numeroBlBox, trancheTitleLabel, PaiementsTableView, totalPayeBox, resteBox, buttonBox);
	}
	
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		trancheTitleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(750);
		trancheTitleLabel.setMinWidth(750);
		PaiementsTableView.getStyleClass().add("cell");
		PaiementsTableView.setMinHeight(350);
		quitterButton.getStyleClass().add("btn-danger");
		root.setSpacing(10);
		popUpButton.getStyleClass().add("btn-primary");
		buttonBox.setSpacing(30);
		payerButton.getStyleClass().add("btn-primary");
	}
	
	private void initWindow() {
		window.setScene(scene);
		window.setMinHeight(700);
		window.setMinWidth(750);
		window.setTitle("Details de paiement");
		window.show();
	}
	
	private void addEvent(BonLivraison bonLivraison) {
		quitterButton.setOnAction(event->{
			new ListeBonLivraisonWindow();
			window.close();
		});
		payerButton.setOnAction(event->{
			new PaiementsWindow(bonLivraison);
			window.close();
		});
	}
	double total;
	double totalPaye;
	double reste;
	private void getBonLivraisonItems(BonLivraison bonLivraison) {
		clientValueLabel.setText(bonLivraison.getNomClients());
		numeroBLValueLabel.setText(bonLivraison.getNumeroBL());
		total = bonLivraison.getTotal();
	}
	
	PaiementsHandler handler = new PaiementsHandler(this);
	
	private void getTranchepaiements(){
		handler.updatePaiementsListeDetails();
		for(Paiements paiements : observableList) {
			totalPaye += paiements.getMontant();
		}
		reste = total - totalPaye;
		totalPayeValueLabel.setText(totalPaye+"");
		resteValueLabel.setText(reste+"");
	}
	
	public DetailsPaiements(BonLivraison bonLivraison){
		getBonLivraisonItems(bonLivraison);
		getTranchepaiements();
		addToNode();
		addStyleToNodes();
		addEvent(bonLivraison);
		updateColumPaiement();
		initWindow();
	}
}
