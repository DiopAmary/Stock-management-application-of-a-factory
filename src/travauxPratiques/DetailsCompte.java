package travauxPratiques;

import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailsCompte {
	DetailsHandler handler = new DetailsHandler(this);
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Détails du Compte");
	HBox numeroBox = new HBox();
	Label numeroLabel = new Label("Compte\t:\t");
	TextField numeroField = new TextField();
	Button addButton = new Button("Afficher détails");
	
	HBox nomBox = new HBox();
	Label nomLabel = new Label("Nom\t\t:\t");
	Label nomValueLabel = new Label();
	HBox soldeBox = new HBox();
	Label soldeLabel = new Label("Solde\t:\t");
	Label soldeValueLabel = new Label();
	

	TableView<Operation> operationTableView= new TableView<Operation>();
	TableColumn<Operation, Date> dateColumn = new TableColumn<Operation, Date>("Date");
	TableColumn<Operation, String> typeColumn = new TableColumn<Operation, String>("Operation");
	TableColumn<Operation, Double> montantColumn = new TableColumn<Operation, Double>("Montant");
	ObservableList<Operation> observableList = FXCollections.observableArrayList();
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(500);
		window.setHeight(600);
		window.setTitle("Details Compte");
	}
	
	private void addToWindow(){
		operationTableView.getColumns().addAll(dateColumn, typeColumn, montantColumn);
		operationTableView.setItems(observableList);
		numeroBox.getChildren().addAll(numeroLabel, numeroField, addButton);
		nomBox.getChildren().addAll(nomLabel, nomValueLabel);
		soldeBox.getChildren().addAll(soldeLabel, soldeValueLabel);
		root.getChildren().addAll(titleLabel, numeroBox, nomBox, soldeBox, operationTableView);
	}
	
	private void updateColumn() {
		dateColumn.setCellValueFactory(new PropertyValueFactory<Operation, Date>("date"));
		dateColumn.setPrefWidth(100);
		typeColumn.setCellValueFactory(new PropertyValueFactory<Operation, String>("type"));
		typeColumn.setPrefWidth(300);
		montantColumn.setCellValueFactory(new PropertyValueFactory<Operation, Double>("montant"));
		montantColumn.setPrefWidth(100);;
	}
	
	private void addStyleToNode() {
		scene.getStylesheets().add("css/exam.css");
		operationTableView.getStyleClass().add("cell");
		operationTableView.setMinHeight(500);
		titleLabel.getStyleClass().add("titleLabelStyleMin");
		titleLabel.setMinWidth(window.getWidth());
		titleLabel.setMinHeight(50);
		addButton.getStyleClass().add("btn-primary");
		numeroBox.setSpacing(20);
		root.setSpacing(15);
	}
	double solde =0;
	private void addEvent(){
		addButton.setOnAction(event->{
			handler.updateDetailCompte();
			for(Operation operation:observableList) {
				if(operation.getType().equals("Vers")) {
					solde+=operation.getMontant();
				}else {
					solde-=operation.getMontant();
				}
			}
			
			soldeValueLabel.setText("\t"+solde);
		});
	}
	public  DetailsCompte(){
		updateColumn();
		initWindow();
		addToWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}	
}
