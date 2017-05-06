package hash;
/**
 * @Course: SDEV 350 ~ Java Programming II
 * @Author Name: Adam Flammino
 * @Assignment Name: aflammino_week14
 * @Date: May 5, 2017
 * @Description: Uses HashMap class and interface from introduction to java programing within a GUI
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Define panes */
        BorderPane bPane = new BorderPane();
        VBox topBox = new VBox();
        GridPane leftGrid = new GridPane();
        GridPane rightGrid = new GridPane(); // Needed for margins
        HBox bottomBox = new HBox(15);

        /* Set VBox attributes */
        topBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        topBox.setAlignment(Pos.CENTER);

        /* Set HBox attributes */
        bottomBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        bottomBox.setAlignment(Pos.CENTER);

        /* Set GridPane Attributes */
        leftGrid.setAlignment(Pos.CENTER_LEFT);
        leftGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        leftGrid.setHgap(5.5);
        leftGrid.setVgap(5.5);
        // leftGrid.setPrefSize(175,175);
        rightGrid.setAlignment(Pos.CENTER_LEFT);
        rightGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        rightGrid.setHgap(5.5);
        rightGrid.setVgap(20);
        //   rightGrid.setPrefWidth(175);

        /* Add panes to BorderPanes, set borderpane attributes */
        bPane.setTop(topBox);
        bPane.setLeft(leftGrid);
        bPane.setRight(rightGrid);
        bPane.setBottom(bottomBox);

        /* Declare top vBox components */
        Text banner = new Text("HashMaps");
        banner.setFont(Font.font("Bodoni MT Black", FontWeight.BOLD,
                FontPosture.ITALIC, 24));
        Text instructions = new Text("Enter name and age to add, or a name to remove");
        instructions.setFont(Font.font("Bodoni MT Black", FontPosture.ITALIC, 14));
        topBox.getChildren().add(banner);
        topBox.getChildren().add(instructions);

        /* Add left GridPane components */
        Label lblName = new Label("Name:");
        Label lblAge = new Label("Age:");
        TextField txtName = new TextField();
        txtName.setPrefWidth(150);
        TextField txtAge = new TextField();
        txtAge.setPrefWidth(50);
        TextArea txaConsole = new TextArea();
        txaConsole.setPrefWidth(250);
        txaConsole.setEditable(false);
        txaConsole.setWrapText(true);
        leftGrid.add(lblName, 0, 0);
        leftGrid.add(lblAge, 2, 0);
        leftGrid.add(txtName, 1, 0);
        leftGrid.add(txtAge, 3, 0);
        leftGrid.add(txaConsole, 0, 2,4,1);

        /* Add right GridPane components */
        TextArea txaResults = new TextArea();
        txaResults.setPrefSize(275,220);
        txaResults.setEditable(false);
        txaResults.setWrapText(true);
        rightGrid.add(txaResults, 0, 0);

        /* Add bottom hBox components */
        Button btnEnter = new Button("Enter");
        Button btnFind = new Button("Find");
        Button btnRemove = new Button("Remove");
        Button btnClear = new Button("Clear");
        Button btnExit = new Button("Exit");
        bottomBox.getChildren().add(btnEnter);
        bottomBox.getChildren().add(btnFind);
        bottomBox.getChildren().add(btnRemove);
        bottomBox.getChildren().add(btnClear);
        bottomBox.getChildren().add(btnExit);

        /* Creates scene, sets stage */
        Scene scene = new Scene(bPane);
        primaryStage.setTitle("Maps");
        primaryStage.setScene(scene);
        primaryStage.show();

        Validation v = new Validation();
        MyMap<String, Integer> map = new MyHashMap<>();

        btnEnter.setOnAction((ActionEvent e) ->{
            String name = txtName.getText().trim();
            String age = txtAge.getText().trim();
            int a;
            if(!v.checkFilled(name)){
                missingInput("name");
                txtName.requestFocus();
            }
            else if(!v.checkFilled(age)){
                missingInput("age");
            }
            else if (map.containsKey(name)){
                txaConsole.setText(name + " is already present on the table. If this is a different person with the same" +
                        " name, add a unique identifier to the second occurrence.");
            }
            else {
                try {
                    a = Integer.parseInt(age);
                    map.put(name, a);
                    txaConsole.setText(name + " was added to the table");
                    txaResults.appendText(name + " is " + age + " years old.\n");
                    txtName.requestFocus();
                    txtName.clear();
                    txtAge.clear();
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Not an Integer!");
                    alert.setContentText("Please enter " + name + "'s age "
                            + "as an integer");
                    alert.showAndWait();
                    txtAge.requestFocus();
                }
            }
        });

        btnFind.setOnAction((ActionEvent e)->{
            String name = txtName.getText().trim();
            String age = txtAge.getText().trim();
            int a;
            if(map.isEmpty()){
                txaConsole.setText("The table contains no values.");
            }
            else if(!v.checkFilled(name)){
                missingInput("name");
            }
            else{
                if(map.containsKey(name)){
                    a = map.get(name);
                    txaConsole.setText(name + " is " + a + " years old.");
                }
                else{
                    txaConsole.setText(name + " is not in the table.");
                }
            }
            if(v.checkFilled(age)){
                extraInput("Age");
            }
            txtName.clear();
            txtName.requestFocus();
        });

        btnRemove.setOnAction((ActionEvent e)->{
            String name = txtName.getText().trim();
            String age = txtAge.getText().trim();
            String output;
            if(map.isEmpty()){
                txaConsole.setText("The table contains no values.");
            }
            else if(!v.checkFilled(name)){
                missingInput("name");
            }
            else if(!map.containsKey(name)){
                txaConsole.setText(name + " is not in the table.");
            }
            else{
                map.remove(name);
                txaConsole.setText(name + " has been removed from the table.");
                output = String.valueOf(map); // gets map
                output = output.replace("][", "years old\n"); //  Adds a line between keys
                // instead of brackets, adds "years old" after age
                output = output.replace("[",""); // Removes opening brackets
                output = output.replace("]",""); // Removes closing brackets
                output = output.replace(",", " is "); // Replaces "," with " is " between name
                // and age
                txaResults.setText(output);
            }
            if (v.checkFilled(age)){
                extraInput(age);
            }
            txtName.clear();
            txtName.requestFocus();
        });

        btnClear.setOnAction((ActionEvent e)->{
            txtName.clear();
            txtAge.clear();
            map.clear();
            txaResults.clear();
            txaConsole.setText("The HashMap is now empty");
        });

        btnExit.setOnAction((ActionEvent e)->{
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Really quit?");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });
    }

    /**
     * Warns user about missing required fields
     * @param field
     */
    private void missingInput(String field){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No " + field + " found");
        alert.setContentText("Please enter a valid " + field + " and try again.");
        alert.showAndWait();
    }

    /**
     * Warns user about filled unused fields
     * @param field
     */
    private void extraInput(String field){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(field + " is not used");
        alert.setContentText(field + " is not used for the selected operation.");
        alert.showAndWait();
    }
}