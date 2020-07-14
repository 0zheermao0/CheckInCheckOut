package CheckInCheckOut;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Classname Company
 * @Description TODO 员工操作类
 * @Author Joey
 * @Date 2020/7/1 17:42
 * @Version 1.0
 **/
public class Company implements Iterable {
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    //尝试加载已有员工列表
    {
        this.loadEmployees();
    }

    /*
     * @Description //TODO 新增人员
     * @Date 17:58 2020/7/1
     * @Param [e]
     * @return void
     **/
    public void addEmployee(Employee e){
        employees.add(e);
//        System.out.println("-----添加成功-----");
    }

    /*
     * @Description //TODO 移除人员
     * @Date 18:16 2020/7/1
     * @Param [e]
     * @return void
     **/
    public void removeEmployee(Employee e){
        employees.remove(e);
        System.out.println("-----删除成功-----");
    }

    /*
     * @Description //TODO 根据ID查询并返回一个Employee实例
     * @Date 18:21 2020/7/1
     * @Param [ID]
     * @return CheckInCheckOut.Employee
     **/
    public Employee getEmployee(int ID){
        for(Employee e : employees){
            if(e.getID() == ID){
                return e;
            }
        }
        System.out.println("-----无此ID员工-----");
        return null;
    }

//    /*
//     * @Description //TODO 打印所有员工信息
//     * @Date 18:30 2020/7/1
//     * @Param []
//     * @return void
//     **/
//    public void printEmployee(){
//        System.out.println("-----开始打印员工信息----");
//        for(Employee e : employees){
//            System.out.println(e);
//        }
//    }

    public ArrayList<Employee> getEmployees(){
        return this.employees;
    }

    public void setEmployees(ArrayList<Employee> employees){
        this.employees = employees;
    }

    /*
     * @Description //TODO 保存当前所有的员工信息到根目录下的Employees.dat中
     * @Date 18:29 2020/7/6
     * @Param []
     * @return void
     **/
    public void saveEmployees() throws IOException {
        File src = new File("Employees.dat");
        if(this.employees != null && this.employees.size() != 0){
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Employees.dat"));
            oos.writeObject(this.getEmployees());
            oos.close();
        }
    }

    /*
     * @Description //TODO 加载员工数据
     * @Date 19:28 2020/7/14
     * @Param []
     * @return void
     **/
    public void loadEmployees(){
        File src = new File("Employees.dat");
        if(src.exists()){
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(src));
                Object obj = ois.readObject();
                if(obj != null){
                    this.setEmployees((ArrayList<Employee>) obj);
//                    System.out.println("员工信息初始化成功！");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Iterator iterator() {
        return this.employees.iterator();
    }
}
