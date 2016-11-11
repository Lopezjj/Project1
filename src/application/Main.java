package application;

import javafx.scene.shape.Shape;
import javafx.animation.FadeTransition;

import java.awt.List;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.beans.binding.Bindings;
import java.util.Random;

public class Main extends Application {

	Scene startMenu, setGamePlay, playThree, playFive, playSeven;
	ObservableList<String> selectedShapes = FXCollections.observableArrayList(); 	//list of shapes selected in setGamePlay scene
	ObservableList<String> selectedColors = FXCollections.observableArrayList(); 	//list of colors selected in setGamePlay scene
	
	Image blankDice = new Image(Main.class.getResourceAsStream("Blank_Dice.png"));
	ArrayList<ImageView> imageViewArray = new ArrayList<ImageView>();
	ArrayList<StackPane> stackPaneList = new ArrayList<StackPane>();
	ImageView img1 = new ImageView(blankDice);
	ImageView img2 = new ImageView(blankDice);
	ImageView img3 = new ImageView(blankDice);
	ImageView img4 = new ImageView(blankDice);
	ImageView img5 = new ImageView(blankDice);
	ImageView img6 = new ImageView(blankDice);
	ImageView img7 = new ImageView(blankDice);
	
	StackPane sp1 = new StackPane();
	StackPane sp2 = new StackPane();
	StackPane sp3 = new StackPane();
	StackPane sp4 = new StackPane();
	StackPane sp5 = new StackPane();
	StackPane sp6 = new StackPane();
	StackPane sp7 = new StackPane();
	int index = 0;
	int fadeIndex = 0;
	
	FadeTransition ft = new FadeTransition();
	
		
	
	public void fade(FadeTransition ft, ArrayList<ImageView> imageArray, int numSides, Button button){
		
		
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
	
		ft.setNode(imageViewArray.get(fadeIndex));
		
		ft.play();
		fadeIndex++;
		
		if(fadeIndex >= numSides){
			button.setDisable(true);
		}
		
	}

	
	//function to print elements of selected shapes or colors; used for debugging only
	private static void printElements(ObservableList<String> list) {
        
        for (Object o : list) {
            System.out.println(o.toString());
        }
        System.out.println("");
    }
	
	
	
	public void displayShapes(ObservableList<String> listShapes, ObservableList<String> listColors, int numShapes, HBox displayBox, Button button){
			
		Random rng = new Random();
		imageViewArray.add(img1);
		imageViewArray.add(img2);
		imageViewArray.add(img3);
		imageViewArray.add(img4);
		imageViewArray.add(img5);
		imageViewArray.add(img6);
		imageViewArray.add(img7);
		
		stackPaneList.add(sp1);
		stackPaneList.add(sp2);
		stackPaneList.add(sp3);
		stackPaneList.add(sp4);
		stackPaneList.add(sp5);
		stackPaneList.add(sp6);
		stackPaneList.add(sp7);
		
		
		
		for (int i = 0; i < (int) numShapes; i++){
			imageViewArray.get(i).setPreserveRatio(true);
			imageViewArray.get(i).setFitWidth(100);
		}
	
			
				for( int i = 1; i < numShapes+1; i++){
					
					int randomNumShapes = rng.nextInt(listShapes.size());
					int randomNumColors = rng.nextInt(listColors.size());
					
					
					if(listShapes.get(randomNumShapes) == "Rectangle"){
						Rectangle rect = new Rectangle(10, 10, 100, 100);
						stackPaneList.get(index).getChildren().addAll(rect, imageViewArray.get(index));
									
						displayBox.getChildren().add(stackPaneList.get(index));
						

										
						
						if(listColors.get(randomNumColors)== "Blue"){
							rect.setFill(Color.BLUE);
						}else if(listColors.get(randomNumColors)== "Green"){
							rect.setFill(Color.GREEN);
						}else if(listColors.get(randomNumColors)== "Yellow"){
							rect.setFill(Color.YELLOW);
						}else if(listColors.get(randomNumColors)== "Red"){
							rect.setFill(Color.RED);
						}
						
					}else if(listShapes.get(randomNumShapes)== "Circle"){
						Circle circ = new Circle();
						circ.setCenterX(100.0f);
						circ.setCenterY(100.0f);
						circ.setRadius(50.0f);
						stackPaneList.get(index).getChildren().addAll(circ, imageViewArray.get(index));
						
						displayBox.getChildren().add(stackPaneList.get(index));	
						
	
						if(listColors.get(randomNumColors)== "Blue"){
							circ.setFill(Color.BLUE);
						}else if(listColors.get(randomNumColors)== "Green"){
							circ.setFill(Color.GREEN);
						}else if(listColors.get(randomNumColors)== "Yellow"){
							circ.setFill(Color.YELLOW);
						}else if(listColors.get(randomNumColors)== "Red"){
							circ.setFill(Color.RED);
						}
					
					}else if(listShapes.get(randomNumShapes)== "Ellipse"){
						Ellipse el = new Ellipse(); 
						el.setCenterX(50.0f);
						el.setCenterY(50.0f);
						el.setRadiusX(50.0f);
						el.setRadiusY(30.0f);
						
						stackPaneList.get(index).getChildren().addAll(el, imageViewArray.get(index));
						
						displayBox.getChildren().add(stackPaneList.get(index));	
						
	
						if(listColors.get(randomNumColors)== "Blue"){
							el.setFill(Color.BLUE);
						}else if(listColors.get(randomNumColors)== "Green"){
							el.setFill(Color.GREEN);
						}else if(listColors.get(randomNumColors)== "Yellow"){
							el.setFill(Color.YELLOW);
						}else if(listColors.get(randomNumColors)== "Red"){
							el.setFill(Color.RED);
						}

						
					
					}else if(listShapes.get(randomNumShapes)== "Triangle"){
						Polygon tri = new Polygon();
						tri.getPoints().addAll(new Double[]{
								100.0, 20.0,
							    50.0, 100.0,
							    150.0, 100.0 
							    });

						stackPaneList.get(index).getChildren().addAll(tri, imageViewArray.get(index));
						
						displayBox.getChildren().add(stackPaneList.get(index));	
							
							if(listColors.get(randomNumColors)== "Blue"){
								tri.setFill(Color.BLUE);
							}else if(listColors.get(randomNumColors)== "Green"){
								tri.setFill(Color.GREEN);
							}else if(listColors.get(randomNumColors)== "Yellow"){
								tri.setFill(Color.YELLOW);
							}else if(listColors.get(randomNumColors)== "Red"){
								tri.setFill(Color.RED);
						}
					
					}
						
					//System.out.println("Shape "+ i + ": "+ listColors.get(randomNumColors) + " " + listShapes.get(randomNumShapes) + "\n");
					index++;
				}
			
		
		
		
	}
	
	
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
			ObservableList<String> shapes = FXCollections.observableArrayList("Circle", "Rectangle", "Ellipse", "Triangle");
			ListView<String> shapesList = new ListView<String>(shapes);
			shapesList.setPrefWidth(100);
			shapesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			ObservableList<String> colors = FXCollections.observableArrayList("Blue", "Green", "Yellow", "Red");
			ListView<String> colorsList = new ListView<String>(colors);
			colorsList.setPrefWidth(100);
			colorsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			 
			Button playButton = new Button("Play!");
			
			//creates an HBox to store the two listViews, and play button
			HBox playData = new HBox();
			playData.setMaxHeight(102);
			playData.setSpacing(70);
			playData.getChildren().addAll(shapesList, colorsList, playButton);
			
			setGamePlayLayout.add(selectShapesAndColors, 0, 0);
			setGamePlayLayout.add(playData, 0, 1);
			
			
			//Styling elements
			playData.setAlignment(Pos.CENTER);	
			
			
			/************** playThree Scene *****************/
			GridPane setPlayThree = new GridPane();
			setPlayThree.setHgap(20);
			setPlayThree.setVgap(20);
			setPlayThree.setAlignment(Pos.CENTER);
			setPlayThree.setGridLinesVisible(true);
			setPlayThree.setPrefSize(900,500);
			
			//Creates HBox to hold elements for the shape guess and color guess
			HBox guessCombos = new HBox();
			guessCombos.setSpacing(120);
			
				VBox shapeGuessItems = new VBox(); 				//holds label and combo Box for shape guess
				Label shapeGuessLabel = new Label("Shape Guess:");
				ComboBox shapeGuessBox = new ComboBox(selectedShapes);
				shapeGuessItems.getChildren().addAll(shapeGuessLabel, shapeGuessBox);
				
				VBox colorGuessItems = new VBox();
				Label colorGuessLabel = new Label("Color Guess:");
				ComboBox colorGuessBox = new ComboBox(selectedColors);
				colorGuessItems.getChildren().addAll(colorGuessLabel, colorGuessBox);
			
			guessCombos.getChildren().addAll(shapeGuessItems, colorGuessItems); //adds above VBoxs
			
			Button revealButton = new Button("Reveal!");
			setPlayThree.setHalignment(revealButton, HPos.CENTER); 
				
			HBox displayShapesBox = new HBox();			//HBox to hold shapes to be displayed
			
			displayShapesBox.setSpacing(10);
			
			
			setPlayThree.add(guessCombos, 0, 0);
			setPlayThree.add(revealButton, 0, 1);
			setPlayThree.add(displayShapesBox, 0, 2);
			
				
		
		/********** Creating Scenes ********/		
		startMenu= new Scene(startMenuLayout, 900, 500);
		setGamePlay= new Scene(setGamePlayLayout, 900, 500);
		playThree = new Scene(setPlayThree, 900, 500);
				
		
		/***Linking Stylesheet to scenes***/
		startMenu.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		setGamePlay.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		
		
		//Disabling Continue button if nothing is selected from comboBox dropdown 
		continueButton.setDisable(true);
		numShapes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() { 
	        public void changed(ObservableValue<? extends Integer> observable, Integer t, Integer t1){
	        	
	        	if(numShapes.getValue()== null){
	        		continueButton.setDisable(true);
	        	}else{
	        		continueButton.setDisable(false);
	        	}
		
			}
		});
		
		
	////////Disabling play button if nothing is selected from either one of ListView objects////////////
		playButton.setDisable(true);
		shapesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() { 
	        public void changed(ObservableValue<? extends String> observable, String t, String t1){
	        	
	        	if( (shapesList.getSelectionModel().getSelectedItem() == null) || 
	        		(colorsList.getSelectionModel().getSelectedItem() == null) ) {
	        		playButton.setDisable(true);
	        	} else {
	        		playButton.setDisable(false);
	        	}
			}
		});
		
		colorsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() { 
	        public void changed(ObservableValue<? extends String> observable, String t, String t1){
	        	
	        	if( (shapesList.getSelectionModel().getSelectedItem() == null) || 
	        		(colorsList.getSelectionModel().getSelectedItem() == null) ) {
	        		playButton.setDisable(true);
	        	} else {
	        		playButton.setDisable(false);
	        	}
			}
		});
		
		

		/***** Event handler for when play button is pressed: adds selected items to lists ********/
		playButton.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle( ActionEvent event ) {
					
					selectedShapes.addAll(shapesList.getSelectionModel().getSelectedItems());   
					selectedColors.addAll(colorsList.getSelectionModel().getSelectedItems());
				//	printElements(selectedShapes);
				//	printElements(selectedColors);
					
					window.setScene(playThree);
					
					displayShapes(selectedShapes, selectedColors, (int) numShapes.getValue(), displayShapesBox, revealButton);
					
					
					
					
					
				/*if( (int) numShapes.getValue() == 3){
					displayShapesBox.getChildren().addAll(imageViewArray.get(0), imageViewArray.get(1), imageViewArray.get(2));
				}*/
					
					}
		});
		
		
		
		revealButton.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle( ActionEvent event ) {
				
				fade(ft, imageViewArray, (int) numShapes.getValue(), revealButton );	
				
				/*ft.setFromValue(1.0);
					ft.setToValue(0.0);
				
					ft.setNode(imageViewArray.get(fadeIndex));
					
					ft.play();*/
				
				}
	});
		
		
		
		
		window.setScene(startMenu);
		window.show();
		
	
	}
	public static void main(String[] args) {
		launch(args);
	}
}
