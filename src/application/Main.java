package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;  // Contains EventHandler & ActionEvent
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.input.*; // Contains MouseEvent & KeyEvent
import javafx.stage.*; // Contains Stage and StageStyle
import javafx.scene.*; // Contains Scene, Group, and Node
import javafx.scene.control.*; // Contains Button
import javafx.scene.image.*; // Contains ImageView & Image
import javafx.scene.layout.*; // Contains subclasses of anchorPane
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	Scene startMenu, setGamePlay, playThree, playFive, playSeven;
	
	
	
	public void start(Stage window){
	
	/************** startMenu Scene *****************/
		
		GridPane startMenuLayout = new GridPane();
		startMenuLayout.setHgap(20);
		startMenuLayout.setVgap(20);
		startMenuLayout.setAlignment(Pos.CENTER);
//		startMenuLayout.setGridLinesVisible(true);
		startMenuLayout.setPrefSize(900,500);
		Label header = new Label("The Shapes Are Right");
		
		
		
		
		//VBox below contains comboBox to choose number of shapes, and it's label
		VBox selectNumShapesBox = new VBox();
		Label selectNumShapes = new Label("Select the number of shapes");
		ObservableList<Integer> numShapesOptions = FXCollections.observableArrayList( 3, 5, 7);
		ComboBox numShapes = new ComboBox(numShapesOptions);
			
		Button continueButton = new Button("Continue");
		continueButton.setOnAction(e -> window.setScene(setGamePlay)); 		//proceed to setGamePlay scene when continue button is clicked
		HBox selectShapesAndContinue = new HBox(); 			//contains (comboBox and it's label), and continue button
		selectShapesAndContinue.setSpacing(20);
		
		/******Adding elements to layout*******/
			//first must add numShapes and selectNumShapes to selectNumShapesBox
				selectNumShapesBox.getChildren().addAll(selectNumShapes, numShapes);
			//next, must had selectShapesBox and continue button to selectShapesAndContinue HBox
				selectShapesAndContinue.getChildren().addAll(selectNumShapesBox, continueButton);
			//Finally, add the header text, and HBox containing the box to select the number of shapes,
			//its label, and the continue button to the main layout
				startMenuLayout.add(header, 0, 0);
				startMenuLayout.add(selectShapesAndContinue, 0, 1);
				startMenuLayout.getStyleClass().add("mainTheme");
				
			//Styling position of Elements
			startMenuLayout.setHalignment(header, HPos.CENTER); 			//Centers header label in cell

		
			
			/************** setGamePlay Scene *****************/
			GridPane setGamePlayLayout = new GridPane();
			setGamePlayLayout.setHgap(20);
			setGamePlayLayout.setVgap(20);
			setGamePlayLayout.setAlignment(Pos.CENTER);
//			setGamePlayLayout.setGridLinesVisible(true);
			setGamePlayLayout.setPrefSize(900,500);
			Label selectShapesAndColors = new Label("Select the kind(s) of shape(s) and color(s) you'd like "
					+ "to play with (hold command for multiple selections): ");
			
			//Creating listView objects for shapes and colors
			ObservableList<String> shapes = FXCollections.observableArrayList("Circle", "Rectangle", "Eclipse", "Triangle");
			ListView<String> shapesList = new ListView<String>(shapes);
			
			ObservableList<String> colors = FXCollections.observableArrayList("Blue", "Green", "Yellow", "Red");
			ListView<String> colorsList = new ListView<String>(colors);
			colorsList.setPrefWidth(70);
			colorsList.setPrefHeight(1);
			 
			Button playButton = new Button("Play!");
			
			//creates an HBox to store the two listViews, and play button
			HBox playData = new HBox();
			
			playData.getChildren().addAll(shapesList, colorsList, playButton);
			
			setGamePlayLayout.add(selectShapesAndColors, 0, 0);
			setGamePlayLayout.add(playData, 0, 1);
		
				
		
		/********** Creating Scenes ********/		
		startMenu= new Scene(startMenuLayout, 900, 500);
		setGamePlay= new Scene(setGamePlayLayout, 900, 500);
				
		
		/***Linking Stylesheet to scenes***/
		startMenu.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		setGamePlay.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		
		
		continueButton.setDisable(true);
		//Disabling Continue button if nothing is selected from comboBox dropdown 
		numShapes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() { 
	        public void changed(ObservableValue<? extends Integer> observable, Integer t, Integer t1){
	        	
	        	if(numShapes.getValue()== null){
	        		continueButton.setDisable(true);
	        	}else{
	        		continueButton.setDisable(false);
	        	}
		
			}
		});
		
		window.setScene(startMenu);
		window.show();
		
	
	}
	public static void main(String[] args) {
		launch(args);
	}
}
