package CheckInCheckOut;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashSet;

/**
 * @Classname Employee
 * @Description TODO 员工信息类
 * @Author Joey
 * @Date 2020/7/1 17:27
 * @Version 1.0
 **/
public class Employee implements Serializable {
    //员工ID
    private int ID;
    //员工姓名
    private String name;

    //有参构造器
    public Employee(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    //无参构造器
    public Employee() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "员工ID:"+this.ID+",员工姓名:"+this.name;
    }
}
