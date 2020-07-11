package CheckInCheckOut;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Classname TableView
 * @Description TODO
 * @Author Joey
 * @Date 2020/7/11 16:52
 * @Version 1.0
 **/
public class TableView implements Initializable {
    //准备属性绑定
    private final StringProperty IDProperty;
    private final StringProperty checkInProperty;
    private final StringProperty checkOutProperty;

    public TableView(String ID, String checkIn, String checkOut) {
        this.IDProperty = new SimpleStringProperty(ID);
        this.checkInProperty = new SimpleStringProperty(checkIn);
        this.checkOutProperty = new SimpleStringProperty(checkOut);
    }

    public TableView(StringProperty IDProperty, StringProperty checkInProperty, StringProperty checkOutProperty) {
        this.IDProperty = IDProperty;
        this.checkInProperty = checkInProperty;
        this.checkOutProperty = checkOutProperty;
    }

        public TableView() {
        IDProperty = new SimpleStringProperty(this,"IDCol");
        checkInProperty = new SimpleStringProperty(this,"checkInCol");
        checkOutProperty = new SimpleStringProperty(this,"checkOutCol");
    }

    public String getIDProperty() {
        return IDProperty.get();
    }

    public StringProperty IDPropertyProperty() {
        return IDProperty;
    }

    public void setIDProperty(String IDProperty) {
        this.IDProperty.set(IDProperty);
    }

    public String getCheckInProperty() {
        return checkInProperty.get();
    }

    public StringProperty checkInPropertyProperty() {
        return checkInProperty;
    }

    public void setCheckInProperty(String checkInProperty) {
        this.checkInProperty.set(checkInProperty);
    }

    public String getCheckOutProperty() {
        return checkOutProperty.get();
    }

    public StringProperty checkOutPropertyProperty() {
        return checkOutProperty;
    }

    public void setCheckOutProperty(String checkOutProperty) {
        this.checkOutProperty.set(checkOutProperty);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
