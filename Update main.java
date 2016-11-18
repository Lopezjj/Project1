package application;

import javafx.scene.shape.Shape;
import javafx.animation.FadeTransition;

import java.awt.List;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
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

	Scene startMenu, setGamePlay, playGame, quitOrNewGame;				//Creating all scenes
	ObservableList<String> selectedShapes = FXCollections.observableArrayList(); 	//list of shapes selected in setGamePlay scene
	ObservableList<String> selectedColors = FXCollections.observableArrayList(); 	//list of colors selected in setGamePlay scene
	ArrayList<String> shapesForPrint = new ArrayList<String>(); 	/*List of the shapes and their specific colors together 
	 																 (i.e the list contains "Blue Circle" or "Green Rectangle" */
																							
	
	Image cardImage = new Image(Main.class.getResourceAsStream("Blank_Dice.png"));	//just a placeholder image for now, will create real image soon
	ArrayList<ImageView> imageViewArray = new ArrayList<ImageView>(); //list of imageview objects, to hold card images 
	ArrayList<StackPane> stackPaneList = new ArrayList<StackPane>(); //list of stackPaneo objects, to hold shapes and card images on top of shapes
	/*Creating the image view objects, one for each potential shape (could probably be done more efficiently) */
	
	/*Creating the stackPane objects, one for each potential shape and it's corresponding image*/
	StackPane sp1 = new StackPane();
	StackPane sp2 = new StackPane();
	StackPane sp3 = new StackPane();
	StackPane sp4 = new StackPane();
	StackPane sp5 = new StackPane();
	StackPane sp6 = new StackPane();
	StackPane sp7 = new StackPane();
	int index = 0; 		//	Used in the for-loop in displayShapes() function, basically increments by one each time the loop is executed,
						//	and is used to add the correct ImageView object to the correct stackPane --> don't worry about until you've read
						//	the displayShapes() function
	
	int fadeIndex = 0; 	//	Used in fade() function, keeps track of the current place in the imageViewArray so that the fade transition is added to
						//	the correct image
	
	int gamePlayIndex = 0;
	int numPoints = 0;
	int trial = 1;
	
	FadeTransition ft = new FadeTransition(); 	//Used to fade the cardImages

	
		
	//Applies the fade transition to the card images for each corresponding shape
	//Takes in the fade transition, the list of imageView objects, the number of shapes, and the button that is doing the fading
	public void fade(FadeTransition ft, ArrayList<ImageView> imageArray, int numShapes, Button button){ 		
		ft.setFromValue(1.0); 	//set the initial value to full visibility
		ft.setToValue(0.0);		//set the end value to full transparency 
	
		ft.setNode(imageArray.get(fadeIndex)); 	//set the node to be faded to the correct index in the imageViewArray
		
		ft.play();
		fadeIndex++; 		//increment the fadeIndex so that the next time the fade is executed, it is executed on the next card to the right
		
		if(fadeIndex >= numShapes){		//once every shape has been revealed, disable the reveal button
			button.setDisable(true);
		}
		
	}

	
	/*Big daddy function, displays the chosen shapes, and adds cardImage on top of each shape*/
	public void displayShapes(ObservableList<String> listShapes, ObservableList<String> listColors, int numShapes, 
							HBox displayBox, int index){  /*takes in the list of chosen shapes, list of chosen colors, the number of shapes
												and an HBox to display each shape in	*/
		
		Random rng = new Random();	//random object, used below to create random integers for purpose of printing shapes
		index= 0;
	
		ImageView img1 = new ImageView(cardImage);
		ImageView img2 = new ImageView(cardImage);
		ImageView img3 = new ImageView(cardImage);
		ImageView img4 = new ImageView(cardImage);
		ImageView img5 = new ImageView(cardImage);
		ImageView img6 = new ImageView(cardImage);
		ImageView img7 = new ImageView(cardImage);
		
		//Adding each imageView object to array of imageView objects
		imageViewArray.add(img1);
		imageViewArray.add(img2);
		imageViewArray.add(img3);
		imageViewArray.add(img4);
		imageViewArray.add(img5);
		imageViewArray.add(img6);
		imageViewArray.add(img7);
		
		//Adding each stackPane object to array of stackPane objects
		stackPaneList.add(sp1);
		stackPaneList.add(sp2);
		stackPaneList.add(sp3);
		stackPaneList.add(sp4);
		stackPaneList.add(sp5);
		stackPaneList.add(sp6);
		stackPaneList.add(sp7);
		
		
		//setting the size of each necessary imageView object 
		for (int i = 0; i < (int) numShapes; i++){
			imageViewArray.get(i).setPreserveRatio(true);
			imageViewArray.get(i).setFitWidth(110);
		}
	
			//complicated loop, fasten your seatbelt
			//basically loops through the list of shapes, and creates the object that the current index of the list is on
				for( int i = 1; i < numShapes+1; i++){
					
					int randomNumShapes = rng.nextInt(listShapes.size()); 	//	creates a random integer that corresponds to one of the index's in the 
																			//	list of shapes
					
					int randomNumColors = rng.nextInt(listColors.size());	//	ditto, but for the list of colors
					
					
					if(listShapes.get(randomNumShapes) == "Rectangle"){ 	//if the index of the randomNumShapes is a rectangle...
						Rectangle rect = new Rectangle(10, 10, 100, 100);	//creates a new rectangle object to be displayed
						stackPaneList.get(index).getChildren().addAll(rect, imageViewArray.get(index)); //	Add the rectangle and it's corresponding
																										//	imageView to the stackPane list
									
						displayBox.getChildren().add(stackPaneList.get(index)); //	add the correct stackPane from the stackPane list to the displayBox
																				//	this is what actually displays the shape and it's card on top of it
					//This entire if-else statement just assigns the correct color to the shape that was just creates
						if(listColors.get(randomNumColors)== "Blue"){
							rect.setFill(Color.BLUE);
						}else if(listColors.get(randomNumColors)== "Green"){
							rect.setFill(Color.GREEN);
						}else if(listColors.get(randomNumColors)== "Yellow"){
							rect.setFill(Color.YELLOW);
						}else if(listColors.get(randomNumColors)== "Red"){
							rect.setFill(Color.RED);
						}
						
						//	Adds the color and the type of shape that was just created to a list that contains the final shapes that are created
						//	This list contains items like "Blue Rectangle" or "Green Triangle" 
						shapesForPrint.add(listColors.get(randomNumColors) + " " + listShapes.get(randomNumShapes));
						
					}else if(listShapes.get(randomNumShapes)== "Circle"){		//see first part of corresponding if-else statement
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
					
						shapesForPrint.add(listColors.get(randomNumColors) + " " + listShapes.get(randomNumShapes));
						
					}else if(listShapes.get(randomNumShapes)== "Ellipse"){		//see first part of corresponding if-else statement
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

						shapesForPrint.add(listColors.get(randomNumColors) + " " + listShapes.get(randomNumShapes));
					
					}else if(listShapes.get(randomNumShapes)== "Triangle"){			//see first part of corresponding if-else statement
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
							shapesForPrint.add(listColors.get(randomNumColors) + " " + listShapes.get(randomNumShapes));
					}
						
					index++;		//increment the index after each iteration of the loop
				}	
	}
	
	public void printInstructions(ArrayList<String> list, int numShapes ,Label label){
		// Setting instructions to include the correct shapes/colors, and shuffling them
		ArrayList<String> shuffledList = new ArrayList<String>(list);
		Collections.shuffle(shuffledList);
	
	
		/*        Adding the correct shapes to a string to be set as the instructions text		*/
		String instructionsText = ""; 		//the string to be printed to the user
		String shapes = "";					//string containing just the specific number of shapes for this game
		
			//The loops below are used to add the correct number of shapes to the string "shapes",
			//so that they can be printed to the user in the "instructions" label
				for(int i =0; i < (int) numShapes-1; i++){
					shapes+=("a " + shuffledList.get(i) + ", ");
				}	
				for(int i =(int) numShapes-1 ;i < (int) numShapes; i++){		// second loop needed to add the word
					shapes+=("and a " + shuffledList.get(i) + ", ");								//"and" before the final shape
				}
				
	instructionsText+=("You have " + shapes + "guess the order. "); //creating the final string to be set as the text for the 
																	//instructions label
	
		label.setText(instructionsText);
		
	}
	
	
	public void start(Stage window){
	
	try{
		
	/************** startMenu Scene ****************/
		
		GridPane startMenuLayout = new GridPane();
		startMenuLayout.getStyleClass().add("maintheme");
		startMenuLayout.setAlignment(Pos.CENTER);
//		startMenuLayout.setGridLinesVisible(true);
		startMenuLayout.setPrefSize(900,500);
		Label header = new Label("The Shapes Are Right");
		header.getStyleClass().add("titletheme");
		
	
		//VBox below contains comboBox to choose number of shapes, and it's label
		VBox selectNumShapesBox = new VBox();
		selectNumShapesBox.getStyleClass().add("spacetheme");
		Label selectNumShapes = new Label("Select the number of shapes");
		selectNumShapes.getStyleClass().add("mainlabeltheme");
		ObservableList<Integer> numShapesOptions = FXCollections.observableArrayList( 3, 5, 7);
		ComboBox<Integer> numShapes = new ComboBox<Integer>(numShapesOptions);
			
		Button continueButton = new Button("Continue");
		continueButton.getStyleClass().add("buttontheme");
		continueButton.setOnAction(e -> window.setScene(setGamePlay)); 		//proceed to setGamePlay scene when continue button is clicked
		HBox selectShapesAndContinue = new HBox(); 			//contains (comboBox and it's label), and continue button
		selectShapesAndContinue.getStyleClass().add("spacetheme");
		
		
			//first must add numShapes and selectNumShapes to selectNumShapesBox
				selectNumShapesBox.getChildren().addAll(selectNumShapes, numShapes);
			//next, must had selectShapesBox and continue button to selectShapesAndContinue HBox
				selectShapesAndContinue.getChildren().addAll(selectNumShapesBox, continueButton);
			//Finally, add the header text, and HBox containing the box to select the number of shapes,
			//its label, and the continue button to the main layout
				startMenuLayout.add(header, 0, 0);
				startMenuLayout.add(selectShapesAndContinue, 0, 5);
				startMenuLayout.getStyleClass().add("mainTheme");
				
			//Styling position of Elements
			GridPane.setHalignment(header, HPos.CENTER); 			//Centers header label in cell

			/************** setGamePlay Scene *****************/
			GridPane setGamePlayLayout = new GridPane();
			setGamePlayLayout.getStyleClass().add("maintheme");
			setGamePlayLayout.setAlignment(Pos.CENTER);
//			setGamePlayLayout.setGridLinesVisible(true);
			setGamePlayLayout.setPrefSize(900,500);
			Label selectShapesAndColors = new Label("Select the kind(s) of shape(s) and color(s) you'd like "
					+ "to play with (hold command for multiple selections): ");
			selectShapesAndColors.getStyleClass().add("mainlabeltheme");
			
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
			playButton.getStyleClass().add("buttontheme");
			
			//creates an HBox to store the two listViews, and play button
			HBox playData = new HBox();
			playData.setMaxHeight(102);
			playData.setSpacing(70);
			playData.getChildren().addAll(shapesList, colorsList, playButton);
			
			setGamePlayLayout.add(selectShapesAndColors, 0, 0);
			setGamePlayLayout.add(playData, 0, 3);
			
			
			//Styling elements
			playData.setAlignment(Pos.CENTER);	
			
			
			/************** playGame Scene *****************/
			GridPane setPlayGame = new GridPane();
			setPlayGame.getStyleClass().add("gametheme");
			setPlayGame.setAlignment(Pos.CENTER);
			//setPlayGame.setGridLinesVisible(true);
			setPlayGame.setPrefSize(900,500);
			
			//Label for instructions to be printed to user
			Label instructions = new Label();
			instructions.setAlignment(Pos.CENTER);
			instructions.getStyleClass().add("gamelabeltheme");
			
			//Creates HBox to hold elements for the shape guess and color guess
			HBox guessCombos = new HBox();
			guessCombos.setSpacing(120);
			
				VBox shapeGuessItems = new VBox(); 				//holds label and combo Box for shape guess
				shapeGuessItems.getStyleClass().add("boxtheme");
				Label shapeGuessLabel = new Label("Shape Guess:");
				shapeGuessLabel.getStyleClass().add("gamelabeltheme");
				ComboBox<String> shapeGuessBox = new ComboBox<String>(selectedShapes);
				shapeGuessItems.getChildren().addAll(shapeGuessLabel, shapeGuessBox);
				
				VBox colorGuessItems = new VBox();
				colorGuessItems.getStyleClass().add("boxtheme");
				Label colorGuessLabel = new Label("Color Guess:");
				colorGuessLabel.getStyleClass().add("gamelabeltheme");
				ComboBox<String> colorGuessBox = new ComboBox<String>(selectedColors);
				colorGuessItems.getChildren().addAll(colorGuessLabel, colorGuessBox);
			
			guessCombos.getChildren().addAll(colorGuessItems, shapeGuessItems); //adds above VBoxs
			
			guessCombos.setAlignment(Pos.CENTER);
			
			Button revealButton = new Button("Reveal!");
			revealButton.getStyleClass().add("buttontheme2");
			GridPane.setHalignment(revealButton, HPos.CENTER); 
			

			HBox nextOrQuitBox = new HBox();
			nextOrQuitBox.setSpacing(120);
				
				Button nextTrial = new Button("Next Trial!");
				nextTrial.getStyleClass().add("buttontheme2");
				Button quitGame = new Button("End Game");
				quitGame.getStyleClass().add("buttontheme2");
				
				nextOrQuitBox.getChildren().add(nextTrial);
				nextOrQuitBox.getChildren().add(quitGame);
				
				
			
				nextTrial.setDisable(true);
				//quitGame.setDisable(true);
				
			nextOrQuitBox.setAlignment(Pos.CENTER);
				
				
			HBox displayShapesBox = new HBox();			//HBox to hold shapes to be displayed
			displayShapesBox.setAlignment(Pos.CENTER);
			
			displayShapesBox.setSpacing(10);
			
			
			Label finalScoreLabel = new Label(null); //Displays the user's final score, once said score has been calculated. Set to null for this reason
			finalScoreLabel.getStyleClass().add("scorelabeltheme");
			finalScoreLabel.setText("You must play 3 full trials to win");
			
			setPlayGame.add(instructions, 0, 0);
			setPlayGame.add(guessCombos, 0, 1);
			setPlayGame.add(revealButton, 0, 2);
			setPlayGame.add(displayShapesBox, 0, 3);
			setPlayGame.add(nextOrQuitBox, 0, 4);
			
			/************* Creating quitOrNewGame Scene ************/
		
		GridPane quitOrNewGameLayout = new GridPane();
		quitOrNewGameLayout.getStyleClass().add("scoretheme");
	
		quitOrNewGameLayout.setAlignment(Pos.CENTER);
		//quitOrNewGameLayout.setGridLinesVisible(true);
		quitOrNewGameLayout.setPrefSize(900,500);
		
		HBox quitOrNewGameBox = new HBox();
			
			Button newGame = new Button("New Game!");
			newGame.getStyleClass().add("buttontheme2");
			Button quitButton = new Button("Quit!");
			quitButton.getStyleClass().add("buttontheme2");
			
			quitOrNewGameBox.getChildren().add(newGame);
			quitOrNewGameBox.getChildren().add(quitButton);
			
			quitOrNewGameBox.setSpacing(450);
		
		quitOrNewGameLayout.add(quitOrNewGameBox, 0, 17);
		quitOrNewGameLayout.add(finalScoreLabel, 0, 5);
		
						
		
		/********** Creating Scenes ********/		
		startMenu= new Scene(startMenuLayout, 900, 500);
		setGamePlay= new Scene(setGamePlayLayout, 900, 500);
		playGame = new Scene(setPlayGame, 900, 500);
		quitOrNewGame = new Scene(quitOrNewGameLayout, 900, 500);
		
				
		
		/***Linking Stylesheet to scenes***/
		startMenu.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		setGamePlay.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		playGame.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		quitOrNewGame.getStylesheets().add( 
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
		
		
	////////Disabling play button if nothing is selected from either one of ListView objects in setGamePlay Scene////////////
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
		
		
		///////////Disabling reveal if nothing is selected from either one of the comboBox objects in playScene
		revealButton.setDisable(true);
		shapeGuessBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() { 
	        public void changed(ObservableValue<? extends String> observable, String t, String t1){
	        	
	        	if( (shapeGuessBox.getValue() == null) || 
	        		(colorGuessBox.getValue() == null) ) {
	        		revealButton.setDisable(true);
	        	} else {
	        		revealButton.setDisable(false);
	        	}
			}
		});
		
		colorGuessBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() { 
	        public void changed(ObservableValue<? extends String> observable, String t, String t1){
	        	
	        	if( (colorGuessBox.getValue() == null) || 
	        		(shapeGuessBox.getValue() == null) ) {
	        		revealButton.setDisable(true);
	        	} else {
	        		revealButton.setDisable(false);
	        	}
			}
		});
		
		

		/***** Event handler for when play button is pressed: adds selected items to lists ********/
		playButton.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle( ActionEvent event ) {
					
					/*Adding the selected shapes and colors to two different lists, which will be used to print the shapes, 
					 *and do a lot of other tasks inside the 'displayShapes' function*/
					selectedShapes.addAll(shapesList.getSelectionModel().getSelectedItems());   
					selectedColors.addAll(colorsList.getSelectionModel().getSelectedItems());
					
					window.setScene(playGame); //set the stage to the playGame scene
				
					/*Giant function that creates and displays the chosen shapes, as well as adds the card on top of each shape*/
					displayShapes(selectedShapes, selectedColors, (int) numShapes.getValue(), displayShapesBox, index);
					
					printInstructions(shapesForPrint, (int) numShapes.getValue(), instructions);
					
					System.out.println(shapesForPrint);
					}
		});
		
		
		//Handles the fade transition for each card
		revealButton.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle( ActionEvent event ) {
				
				/* Fades each card one by one, and then disables the reveal button once all cards have been revealed*/
				fade( ft, imageViewArray, (int) numShapes.getValue(), revealButton );	
				
				String guess = colorGuessBox.getValue() + " " + shapeGuessBox.getValue();
				
				System.out.println(shapesForPrint.get(gamePlayIndex));
				
				if(guess.equals(shapesForPrint.get(gamePlayIndex))){
					numPoints++;
					System.out.println("Num Points: " + numPoints);
				}	
				gamePlayIndex++;
				

				//once all the shapes have been revealed, allow the user to proceed to next trial
				if(gamePlayIndex >= (int) numShapes.getValue()){
					
					nextTrial.setDisable(false);
					trial++;		//increment the trial counter so that the correct number of trials is played
					
				}
					
				//Once the last trial is completed, this code is executed
				if(trial > 3){
					nextTrial.setDisable(true);
					System.out.println("Game Over");
					int scoreCalulate = 3*(int) numShapes.getValue();
					finalScoreLabel.setText("Final Score: "+ numPoints + " out of " + scoreCalulate + " points!" );	//display the user's final score
				}
					
				}
	});
		
		/**** Event Handler for when the nextTrial Button is pressed ****/
		//Basically clears everything from previous trial and starts trial over
		nextTrial.setOnAction(new EventHandler<ActionEvent>() {
			public void handle( ActionEvent event ) {
				imageViewArray.removeAll(imageViewArray);
				shapesForPrint.removeAll(shapesForPrint);
				gamePlayIndex = 0;
				fadeIndex = 0;
				stackPaneList.removeAll(stackPaneList);
				
				sp1.getChildren().clear();
				sp2.getChildren().clear();
				sp3.getChildren().clear();
				sp4.getChildren().clear();
				sp5.getChildren().clear();
				sp6.getChildren().clear();
				sp7.getChildren().clear();
				
				nextTrial.setDisable(true);
				
				displayShapesBox.getChildren().clear();
			
				displayShapes(selectedShapes, selectedColors, (int) numShapes.getValue(), displayShapesBox, index);
				
				printInstructions(shapesForPrint, (int) numShapes.getValue(), instructions);
				
				System.out.println(shapesForPrint);
				
				revealButton.setDisable(false);
				
				window.setScene(playGame);
					
			}
		});
		
		//Brings user to quitOrNewGame scene, to choose whether to quit the game or play a new one
		quitGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle( ActionEvent event ) {
		
				window.setScene(quitOrNewGame);
		
			}
		});
		
		//Clears EVERYTHING from previous game
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle( ActionEvent event ) {
				window.setScene(startMenu);
			
				numShapes.valueProperty().set(null);
				colorGuessBox.valueProperty().set(null);
				shapeGuessBox.valueProperty().set(null);
				
				shapesList.getSelectionModel().clearSelection();
				colorsList.getSelectionModel().clearSelection();
				
				selectedShapes.clear();
				selectedColors.clear();
				
				finalScoreLabel.setText("You must play 3 full trials to win");
				
				fadeIndex = 0; 	
				gamePlayIndex = 0;
				numPoints = 0;
				trial = 1;
				
				imageViewArray.removeAll(imageViewArray);
				shapesForPrint.removeAll(shapesForPrint);
				stackPaneList.removeAll(stackPaneList);
				
				sp1.getChildren().clear();
				sp2.getChildren().clear();
				sp3.getChildren().clear();
				sp4.getChildren().clear();
				sp5.getChildren().clear();
				sp6.getChildren().clear();
				sp7.getChildren().clear();
				
				nextTrial.setDisable(true);
				
				displayShapesBox.getChildren().clear();
			}
		});
		
		//Quit game if quitButton is selected
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle( ActionEvent event ) {
				window.hide();
			}
		});
		
		
		window.setScene(startMenu);
		window.show();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	}
	public static void main(String[] args) {
		launch(args);
	}
}
