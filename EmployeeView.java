package CheckInCheckOut;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Classname EmployeeView
 * @Description TODO
 * @Author Joey
 * @Date 2020/7/14 15:04
 * @Version 1.0
 **/
public class EmployeeView implements Initializable {
    private final StringProperty emIDProperty;
    private final StringProperty emNameProperty;

    public EmployeeView(StringProperty emIDProperty, StringProperty emNameProperty) {
        this.emIDProperty = emIDProperty;
        this.emNameProperty = emNameProperty;
    }

    public EmployeeView(String ID, String name) {
        this.emIDProperty = new SimpleStringProperty(ID);
        this.emNameProperty = new SimpleStringProperty(name);
    }

    public String getEmIDProperty() {
        return emIDProperty.get();
    }

    public StringProperty emIDPropertyProperty() {
        return emIDProperty;
    }

    public void setEmIDProperty(String emIDProperty) {
        this.emIDProperty.set(emIDProperty);
    }

    public String getEmNameProperty() {
        return emNameProperty.get();
    }

    public StringProperty emNamePropertyProperty() {
        return emNameProperty;
    }

    public void setEmNameProperty(String emNameProperty) {
        this.emNameProperty.set(emNameProperty);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
