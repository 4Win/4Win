package win;
import javafx.*;
import javafx.stage.Popup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;	

public class Main extends Application{
	Button button;
public static void main(String[] args) {
	launch(args);
	EventListener e = new EventListener();
}

@Override
public void start(Stage stage) throws Exception {
	Pane root = (Pane) FXMLLoader.load(getClass().getResource("javafxscene.fxml"));
	Scene scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
}
}
