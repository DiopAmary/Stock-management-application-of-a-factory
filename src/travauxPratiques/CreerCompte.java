package travauxPratiques;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreerCompte {
	CompteHandler handler = new CompteHandler(this);
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouveau Compte");
	
	HBox nomBox = new HBox();
	HBox prenomBox = new HBox();
	HBox numeroBox = new HBox();
	
	Label nomLabel = new Label("Nom\t\t:\t");
	Label prenomLabel = new Label("Prenom\t:\t");
	Label numeroLabel = new Label("Compte\t:\t");
	
	TextField nomField = new TextField();
	TextField prenomField = new TextField();
	TextField numeroField = new TextField();
	
	Button addButton = new Button("Créer le compte");
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(300);
		window.setHeight(265);
		window.setTitle("Nouveau Compte");
	}
	private void addToWindow(){
		nomBox.getChildren().addAll(nomLabel, nomField);
		prenomBox.getChildren().addAll(prenomLabel, prenomField);
		numeroBox.getChildren().addAll(numeroLabel, numeroField);
		root.getChildren().addAll(titleLabel, nomBox, prenomBox, numeroBox, addButton);
	}
	private void addStyleToNode() {
		scene.getStylesheets().add("css/exam.css");
		titleLabel.getStyleClass().add("titleLabelStyleMin");
		titleLabel.setMinWidth(window.getWidth());
		titleLabel.setMinHeight(50);
		addButton.getStyleClass().add("btn-primary");
		addButton.setMinHeight(40);
		root.setSpacing(15);
	}
	private void addEvent(){
		window.setOnCloseRequest(event->{event.consume();});
		addButton.setOnAction(event->{
			handler.add();
			window.close();
		});
	}

	
	
	public  CreerCompte(){
		initWindow();
		addToWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}	
}
