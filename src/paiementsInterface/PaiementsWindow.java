package paiementsInterface;


import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import paimentsDataAccess.Paiements;
import produitsDataAccess.Produits;
import ventes.ventesDataAccess.BonLivraison;
import ventes.ventesDataAccess.BonLivraisonDAOImplementation;

public class PaiementsWindow {
	PaiementsHandler handler = new PaiementsHandler(this);
	Stage window = new Stage();
	HBox root = new HBox();
	Scene scene = new Scene(root);
	
	VBox leftBox = new VBox();
	Label detailsBlLabel = new Label("\t\tDetails du bon de livraison");
	Label tranchePaimentTitleLabel = new Label("\t\tTranches de paiement");
	
	HBox dateBox = new HBox();
	Label dateLabel =  new Label("\t\tDate\t\t\t\t\t:\t");
	Label dateVAlueLabel =  new Label();
	
	HBox nuemroBlBox = new HBox();
	Label numeroBlLabel = new Label("\t\tN° Bon de livraison\t\t:\t");
	Label numeroBlValueLabel = new Label();
	
	HBox clientBox = new HBox();
	Label clientLabel = new Label("\t\tClient\t\t\t\t:\t");
	Label clientValueLabel = new Label();
	
	HBox totalBox = new HBox();
	Label totalValueLabel = new Label();
	Label totalLabel = new Label("\t\tTotal \t\t\t\t:\t");
	
	HBox totalPayeBox = new HBox();
	Label totalPayeValueLabel = new Label();
	Label totalPayeLabel = new Label("\t\tTotal payé\t\t\t:\t");
	
	HBox resteBox = new HBox();
	Label resteValueLabel = new Label();
	Label resteLabel = new Label("\t\tReste \t\t\t\t:\t");
	
	TableView<Paiements> PaiementsTableView= new TableView<Paiements>();
	TableColumn<Paiements, Integer> numeroPaiementColumn = new TableColumn<Paiements, Integer>("N°");
	TableColumn<Paiements, Double> montantPaiementColumn = new TableColumn<Paiements, Double>("Montant");
	TableColumn<Paiements, Date> datePaiementColumn = new TableColumn<Paiements, Date>("Date");
	TableColumn<Paiements, String> typePaiementColumn = new TableColumn<Paiements, String>("Type");
	TableColumn<Paiements, String> numeroChequeColumn = new TableColumn<Paiements, String>("N° Chèque");
	TableColumn<Paiements, String> proprietaireColumn = new TableColumn<Paiements, String>("Propriétaire");
	TableColumn<Paiements, String> banqueColumn = new TableColumn<Paiements, String>("Banque");
	
	Button enregistrerButton = new Button("Enregistrer");
	
	
	double reste;
	double totalPaye;
	
	
	//------------------------------------------right
	VBox rightBox = new VBox();
	
	Button moinsButton = new Button("Enlever");
	
	Label detailPaiementTitleLabel = new Label("\tDétails de paiements");
	
	HBox numeroPaimentBox = new HBox();
	Label numeroPaiementLabel = new Label("N° paiement\t\t\t:\t");
	TextField numeroPaiementField = new TextField();
	
	HBox datePaimentBox = new HBox();
	Label datepaiementLabel = new Label("Date paiement\t\t\t:\t");
	DatePicker datePaiementDatePicker = new DatePicker();
	
	HBox montantPaimentBox = new HBox();
	Label montantPaiementLabel = new Label("Montant paiment\t\t:\t");
	TextField montantPaimentField = new TextField();
	
	HBox typePaimentBox = new HBox();
	Label typePaiement = new Label("Type paiment\t\t\t:\t");
	ChoiceBox typePaiementChoiceBox = new ChoiceBox(FXCollections.observableArrayList("ESPECE", "CHEQUE"));
	
	HBox numeroChequeBox = new HBox();
	Label nuemroChequeLabel = new Label("N° chèque\t\t\t:\t");
	TextField numeroChequeField = new TextField();
	
	HBox banqueBox = new HBox();
	Label banqueLabel = new Label("Banque\t\t\t\t:\t");
	ChoiceBox banqueChoiceBox = new ChoiceBox(FXCollections.observableArrayList("", "AttijariWB", "Banque P", "BMCE" , "CAM", "CM"));
	
	HBox proprietaireBox = new HBox();
	Label proprietaireLabel = new Label("Propriétaire\t\t\t:\t");
	TextField proprietaireField = new TextField();
	
	HBox dateEcheanceBox = new HBox();
	Label dateEcheanceLabel = new Label("Date échéance\t\t\t:\t");
	DatePicker DateEcheanceDatePicker = new DatePicker();
	
	Button ajouterButton = new Button("Ajouter");
	
	Button popUpButton = new Button("Paiement intégralement éffectué");
	
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
		nuemroBlBox.getChildren().addAll(numeroBlLabel, numeroBlValueLabel);
		dateBox.getChildren().addAll(dateLabel, dateVAlueLabel);
		totalBox.getChildren().addAll(totalLabel, totalValueLabel);
		resteBox.getChildren().addAll(resteLabel, resteValueLabel);
		totalPayeBox.getChildren().addAll(totalPayeLabel, totalPayeValueLabel);
		leftBox.getChildren().addAll(detailsBlLabel, clientBox, nuemroBlBox, dateBox, totalBox, totalPayeBox, resteBox, tranchePaimentTitleLabel, PaiementsTableView, enregistrerButton);
		
		numeroPaimentBox.getChildren().addAll(numeroPaiementLabel, numeroPaiementField);
		montantPaimentBox.getChildren().addAll(montantPaiementLabel, montantPaimentField);
		datePaimentBox.getChildren().addAll(datepaiementLabel, datePaiementDatePicker);
		typePaimentBox.getChildren().addAll(typePaiement, typePaiementChoiceBox);
		numeroChequeBox.getChildren().addAll(nuemroChequeLabel, numeroChequeField);
		banqueBox.getChildren().addAll(banqueLabel, banqueChoiceBox);
		proprietaireBox.getChildren().addAll(proprietaireLabel, proprietaireField);
		dateEcheanceBox.getChildren().addAll(dateEcheanceLabel, DateEcheanceDatePicker);
		rightBox.getChildren().addAll(detailPaiementTitleLabel, numeroPaimentBox, montantPaimentBox, datePaimentBox, typePaimentBox, numeroChequeBox, dateEcheanceBox, banqueBox, proprietaireBox, ajouterButton, moinsButton);
		
		if(reste == 0.0) {
			rightBox.getChildren().add(popUpButton);
		}
		
		root.getChildren().addAll(leftBox, rightBox);
		PaiementsTableView.getColumns().addAll(numeroPaiementColumn, montantPaiementColumn, datePaiementColumn, typePaiementColumn, numeroChequeColumn, proprietaireColumn, banqueColumn);
		PaiementsTableView.setItems(observableList);
	}
	
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		detailsBlLabel.getStyleClass().add("titleLabelStyle");
		tranchePaimentTitleLabel.getStyleClass().add("titleLabelStyle");
		detailsBlLabel.setMinWidth(750);
		tranchePaimentTitleLabel.setMinWidth(750);
		PaiementsTableView.getStyleClass().add("cell");
		PaiementsTableView.setMinHeight(350);
		moinsButton.getStyleClass().add("btn-danger");
		leftBox.setSpacing(8);
		root.setSpacing(50);
		
		detailPaiementTitleLabel.getStyleClass().add("titleLabelStyle");
		detailPaiementTitleLabel.setMinWidth(400);
		enregistrerButton.getStyleClass().add("btn-primary");
		ajouterButton.getStyleClass().add("btn-primary");
		rightBox.setSpacing(15);
		
		popUpButton.getStyleClass().add("btn-danger");
	}
	
	private void initWindow() {
		window.setScene(scene);
		window.setMinHeight(700);
		window.setMinWidth(1200);
		window.setTitle("Gestion des paiements");
		window.show();
	}
	
	private void getBLInfo(BonLivraison bonLivraison) {
		totalPaye = 0;
		reste = bonLivraison.getTotal();
		clientValueLabel.setText(bonLivraison.getNomClients());
		numeroBlValueLabel.setText(bonLivraison.getNumeroBL());
		dateVAlueLabel.setText(bonLivraison.getDate()+"");
		totalValueLabel.setText(bonLivraison.getTotal()+"");
		totalPayeValueLabel.setText(totalPaye+"");
		resteValueLabel.setText(reste+"");
		montantPaimentField.setText(reste+"");
	}
	
	private Paiements paiementClicked = null;
	private void initPaiementClicked(Paiements paiements) {
		paiementClicked = paiements;
	}
	
	private void addEvent() {
		PaiementsTableView.setOnMouseClicked(event->{
			Paiements paiements=PaiementsTableView.getItems().get(PaiementsTableView.getSelectionModel().getSelectedIndex());
			initPaiementClicked(paiements);
		});
		
		ajouterButton.setOnAction(event->{
			int numero = Integer.valueOf(numeroPaiementField.getText());
			double montant = Double.valueOf(montantPaimentField.getText());
			Date date = Date.valueOf(datePaiementDatePicker.getValue());
			String type = typePaiementChoiceBox.getValue()+"";
			String numeroCheque = numeroChequeField.getText();
			String banque;
			if(type.equals("ESPECE"))banque = "";else banque = banqueChoiceBox.getValue()+"";
			String proprietaire = proprietaireField.getText();
			Paiements paiements = new Paiements(numero, montant, date, type, numeroCheque, banque, proprietaire);
			totalPaye += montant;
			reste-=montant;
			resteValueLabel.setText(reste+"");
			totalPayeValueLabel.setText(totalPaye+"");
			montantPaimentField.setText(reste+"");
			numeroPaiementField.setText("");
			proprietaireField.setText("");
			observableList.addAll(paiements);
			
		});
		
		moinsButton.setOnAction(event->{
			observableList.removeAll(paiementClicked);
			totalPaye-=paiementClicked.getMontant();
			reste+=paiementClicked.getMontant();
			totalPayeValueLabel.setText(totalPaye+"");
			resteValueLabel.setText(reste+"");
			montantPaimentField.setText(reste+"");
			
		});
		
		enregistrerButton.setOnAction(event->{
			handler.addClick();
			String numeroBL = numeroBlValueLabel.getText();
			BonLivraisonDAOImplementation idao = new BonLivraisonDAOImplementation();
			BonLivraison bonLivraison = idao.find(numeroBL);
			new DetailsPaiements(bonLivraison);
			window.close();
		});
	}
	public void getInfoPaiements(){
		handler.updatePaiementsListe();
		double payer = 0;
		for(Paiements paiements : observableList) {
			payer+= paiements.getMontant();
		}
		reste =Double.valueOf(totalValueLabel.getText())-payer; 
		totalPaye = payer;
		totalPayeValueLabel.setText(totalPaye+"");
		resteValueLabel.setText(reste+"");
		montantPaimentField.setText(reste+"");
	}
	public PaiementsWindow(BonLivraison bonLivraison) {
		getBLInfo(bonLivraison);
		getInfoPaiements();
		updateColumPaiement();
		addToNode();
		addStyleToNodes();
		addEvent();
		initWindow();
	}
}
