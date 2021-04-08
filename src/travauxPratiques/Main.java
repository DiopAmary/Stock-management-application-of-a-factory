package travauxPratiques;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel =new Label("Gestion des comptes Bancaire");
	HBox buttonBox = new HBox();
	Button creerButton = new Button("Créer un compte");
	Button displayButton = new Button("Détail d'un compte");
	
	
	private void addToNode() {
		buttonBox.getChildren().addAll(creerButton, displayButton);
		root.getChildren().addAll(titleLabel, buttonBox);
	}
	private void addStyleToNode() {
		scene.getStylesheets().add("css/exam.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		creerButton.getStyleClass().add("btn-primary");
		displayButton.getStyleClass().add("btn-primary");
		creerButton.setMinHeight(40);
		creerButton.setMinWidth(150);
		displayButton.setMinHeight(40);
		displayButton.setMinWidth(150);
		root.setSpacing(20);
		buttonBox.setSpacing(80);
		
	}
	private void addEvent() {
		creerButton.setOnAction(event->{
			new CreerCompte();
		});
		displayButton.setOnAction(event->{
			new DetailsCompte();
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {	
		addToNode();
		addStyleToNode();
		addEvent();
		window.setTitle("Gestion des comptes");
		window.setMinWidth(400);
		window.setMinHeight(150);
		window.setScene(scene);
		window.show();
	}
}
