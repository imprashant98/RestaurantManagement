package RestaurantManagement;

import javafx.application.Application;
import javafx.stage.Stage;


public class Restaurant extends Application {

    @Override
    public void start(Stage stage) {

        StageManager.getInstance().homeStage.show();


        }
    

    public static void main(String[] args) {
        launch(args);
    }

}
