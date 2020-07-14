package CheckInCheckOut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Classname EmployeeManagementController
 * @Description TODO
 * @Author Joey
 * @Date 2020/7/13 1:15
 * @Version 1.0
 **/
public class EmployeeManagement extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EmployeeView.fxml"));
        primaryStage.setTitle("员工打卡后台管理");
        primaryStage.setScene(new Scene(root, 590, 700));
        primaryStage.show();
    }
}
