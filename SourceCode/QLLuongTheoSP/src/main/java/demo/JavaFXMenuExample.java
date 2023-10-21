package demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFXMenuExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Menu Example");

        // Tạo MenuBar
        MenuBar menuBar = new MenuBar();

        // Tạo menu "File"
        Menu fileMenu = new Menu("File");

        // Tạo các mục trong menu "File"
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");

        // Thêm các mục vào menu "File"
        fileMenu.getItems().addAll(newItem, openItem, saveItem, exitItem);

        // Xử lý sự kiện cho mục "Exit"
        exitItem.setOnAction(e -> System.exit(0));

        // Thêm menu "File" vào MenuBar
        menuBar.getMenus().add(fileMenu);

        // Tạo một layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        // Hiển thị scene
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

