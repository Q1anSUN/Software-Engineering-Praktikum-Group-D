package at.jku.se.diary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import java.util.function.Predicate;

public class HelloFX extends Application {
    Stage window;
    Button button, button1, button2, button3, button4;
    Scene scene, scene1, scene2, scene3;
    TableView<Entries> tableView, tableView1;
    TextField title, location, searchField;
    DatePicker date,start,end;
    TextArea text, categoryText;

    ComboBox tag;
    ObservableList<Entries> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        //----------------Scene: HOMEPAGE--------------------
        window = stage;


        window.setTitle("DIARY_FX");
        HBox hBox = new HBox();

        scene = new Scene(hBox, 1200, 750);
        Image img = new Image("https://i.f1g.fr/media/cms/1200x630_crop/2022/02/21/806d983eb123652ee94b2027b8f5487924b2133636999a3dcb56b5236db51093.png");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        hBox.setBackground(bGround);

        button = new Button();
        button.setText("Show overview");
        button1 = new Button();
        button1.setText("New entry");
        button2 = new Button();
        button2.setText("Search Entry");
        button3 = new Button();
        button3.setText("Search for a category");
        button4 = new Button();
        button4.setText("show map");

        button.setFont(Font.font("Arial", 25));
        button1.setFont(Font.font("Arial", 25));
        button2.setFont(Font.font("Arial", 25));
        button3.setFont(Font.font("Arial", 25));
        button4.setFont(Font.font("Arial", 25));

        button.setOnAction(e -> window.setScene(scene2));
        button1.setOnAction(e -> window.setScene(scene1));
        button2.setOnAction(e -> window.setScene(scene3));

        hBox.getChildren().addAll(button, button1, button2, button3, button4);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);


        /*----------------Scence 1: New entry--------------------*/
        Label label1 = new Label("Titel: ");
        Label label2 = new Label("Date: ");
        Label label3 = new Label("Location: ");
        Label label4 = new Label("Text: ");
        Label label5 = new Label("Category: ");
        Label label7 = new Label("Category Information: ");

        /*----------------Scence 2: Overview--------------------*/

        TableColumn<Entries, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Entries, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setMinWidth(200);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Entries, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Entries, String> textColumn = new TableColumn<>("Text");
        textColumn.setMinWidth(200);
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        TableColumn<Entries, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setMinWidth(200);
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));

        TableColumn<Entries, String> categoryTextColumn = new TableColumn<>("Category Text");
        categoryTextColumn.setMinWidth(200);
        categoryTextColumn.setCellValueFactory(new PropertyValueFactory<>("categoryText"));


        title = new TextField();
        title.setPromptText("title...");
        title.setMinWidth(100);

        location = new TextField();
        location.setPromptText("location...");
        location.setMinWidth(100);

        date = new DatePicker();
        date.setPromptText("date...");
        date.setMinWidth(100);

        text = new TextArea();
        text.setPromptText("text...");
        text.setMinWidth(200);

        tag = new ComboBox<>();
        final String[] comboBoxItems = {"Hotel", "Restaurant", "Beach"};
        tag.setEditable(true);
        tag.setValue("Add a tag");
        tag.getItems().addAll(comboBoxItems);

        categoryText = new TextArea();
        categoryText.setPromptText("text...");
        categoryText.setMinWidth(200);

        Button addButton = new Button("Add new entry");
        addButton.setOnAction(e -> {
            addButtonClicked();
            System.out.println("Entry have been created!");
        });
        Button back1 = new Button("back");
        back1.setOnAction(e -> window.setScene(scene));
        Button deleteButton = new Button("Delete entry");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        Button addentry = new Button("Add new Entry");
        addentry.setOnAction(e -> window.setScene(scene1));


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label1, title, label2, date, label3, location, label4, text, label5, tag, label7, categoryText, addButton, back1);

        tableView = new TableView<>();
        tableView.getColumns().addAll(titleColumn, locationColumn, dateColumn, textColumn, tagColumn, categoryTextColumn);

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(vBox);

        scene1 = new Scene(vBox2, 1000, 700);

        Button back2 = new Button("back");
        back2.setOnAction(e -> window.setScene(scene));
        VBox vBox3 = new VBox();
        vBox3.setPadding(new Insets(10, 10, 10, 10));
        vBox3.setSpacing(10);
        vBox3.getChildren().addAll(tableView, addentry, deleteButton, back2);
        scene2 = new Scene(vBox3, 1200, 750);

        /*----------------Scence 3: Search--------------------*/
        Label label6 = new Label("Search or Filter Table: ");
        searchField = new TextField();
        searchField.setPromptText("Search or Filter Table By title, date, location and Text...");
        searchField.setMinWidth(100);

        tableView1 = new TableView<>();
        tableView1.getColumns().addAll(titleColumn, locationColumn, dateColumn, textColumn, tagColumn, categoryTextColumn);

        tableView.setItems(data);
        tableView1.setItems(data);

        Label label8 = new Label("Startdate: ");
        start = new DatePicker();
        start.setPromptText("add startdate...");
        start.setMinWidth(100);

        Label label9 = new Label("Enddate: ");
        end = new DatePicker();
        end.setPromptText("date...");
        end.setMinWidth(100);

        final ObservableList<DisabledRange> rangesToDisable =
                FXCollections.observableArrayList(new DisabledRange(start.getValue(),end.getValue())
                        );

        FilteredList<Entries> filteredData = new FilteredList<>(data, e -> true);
        searchField.setOnKeyPressed(e -> {
            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Entries>) entries -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (entries.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if(rangesToDisable.stream()
                            .filter(r->r.getInitialDate().minusDays(1).isBefore(date.getValue()))
                            .filter(r->r.getEndDate().plusDays(1).isAfter(date.getValue()))
                            .findAny()
                            .isPresent()){
                        if (entries.getDate().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                        }
                    } else if (entries.getLocation().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (entries.getText().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (entries.getTag().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (entries.getCategoryText().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Entries> sorteddata = new SortedList<>(filteredData);
            sorteddata.comparatorProperty().bind(tableView1.comparatorProperty());
            tableView1.setItems(sorteddata);

        });

        Button back3 = new Button("Go back");
        back3.setOnAction(e -> window.setScene(scene));

        VBox vBox4 = new VBox();
        vBox4.setPadding(new Insets(10, 10, 10, 10));
        vBox4.setSpacing(10);
        vBox4.getChildren().addAll(label6, searchField,label8,start,label9,end, tableView1, back3);
        scene3 = new Scene(vBox4, 1200, 750);

        window.setScene(scene);
        window.show();

    }

    public void addButtonClicked() {
        Entries entries = new Entries();
        entries.setTitle(title.getText());
        entries.setDate(date.getEditor().getText());
        entries.setLocation(location.getText());
        entries.setText(text.getText());
        entries.setTag(String.valueOf(tag.getValue()));
        entries.setCategoryText(categoryText.getText());
        title.clear();
        date.setValue(null);
        location.clear();
        text.clear();
        tag.setValue(null);
        categoryText.clear();
        tableView.getItems().add(entries);
    }

    public void deleteButtonClicked() {
        ObservableList<Entries> allEntries, entrySelected;
        allEntries = tableView.getItems();
        entrySelected = tableView.getSelectionModel().getSelectedItems();
        entrySelected.forEach(allEntries::remove);


    }
}





