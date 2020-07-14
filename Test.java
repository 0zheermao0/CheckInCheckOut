package CheckInCheckOut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Classname Test
 * @Description TODO Main测试类
 * @Author Joey
 * @Date 2020/7/10 19:50
 * @Version 1.0
 **/
public class Test  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("员工打卡系统");
        primaryStage.setScene(new Scene(root, 590, 700));
        primaryStage.show();
    }
}
