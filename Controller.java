package CheckInCheckOut;

import com.sun.javafx.beans.IDProperty;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname Main
 * @Description TODO 主控台测试类
 * @Author Joey
 * @Date 2020/7/1 18:38
 * @Version 1.0
 **/
public class Controller implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private javafx.scene.control.TableView<TableView> table1 ;
    @FXML
    private TableColumn<TableView, String> IDCol;
    @FXML
    private TableColumn<TableView,String> checkInCol;
    @FXML
    private TableColumn<TableView,String> checkOutCol;
    @FXML
    private TextField menu;

    Scanner sc = new Scanner(System.in);
    private Company com = new Company();
    private CheckInCheckOutList list = new CheckInCheckOutList();
    //用于输出准确打卡时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    //用于比较是否为同一天
    private SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
    private ObservableList<CheckInCheckOutList> data = FXCollections.observableArrayList(list);

    /*
     * @Description //TODO 打卡系统交互节面
     * @Date 18:39 2020/7/4
     * @Param []
     * @return void
     **/
    public void mainMenu(){
        try{
            boolean flag = true;
            while(flag){
                System.out.println("----员工打卡系统----");
                System.out.println("输入  0--------退出");
                System.out.println("输入  1--------签到");
                System.out.println("输入  2--------签退");
                System.out.println("输入  3--------查看签到信息");
                System.out.println("输入  4--------添加新员工信息");
                System.out.println("输入  5--------移除员工信息");
                System.out.println("输入  6--------打印当前员工列表");
                System.out.println("请输入想执行的操作：");

                int choice = sc.nextInt();
                switch (choice){
                    case 1:this.checkIn();break;
                    case 2:this.checkOut();break;
                    case 3:this.printInfo();break;
                    case 4:this.addEmployee();break;
                    case 5:this.removeEmployee();break;
                    case 6: this.com.printEmployee();break;
                    case 0:
                        flag = false;
                        this.quit();
                        break;
                    default:System.out.println("非法的选择，请重试！");break;
                }
            }
        }catch (InputMismatchException ime){
            System.out.println("非法的选择，请重试！"); //阻止用户输入数字外的其他字符
            sc.nextLine();
            this.mainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * @Description //TODO 添加新员工到列表中
     * @Date 18:09 2020/7/6
     * @Param []
     * @return void
     **/
    public void addEmployee(){
        System.out.println("请输入员工ID和姓名 >");
        int ID = sc.nextInt();
        for(Employee e : com.getEmployees()){
            if(e.getID() == ID){
                System.out.println("ID重复，请选择其他ID!");
                this.addEmployee();
            }
        }
        sc.nextLine();
        System.out.println("可用的ID，请输入新员工姓名 >");
        String name = sc.nextLine();
        com.addEmployee(new Employee(ID,name));
        System.out.println("添加成功!");
    }

    /*
     * @Description //TODO 调用Company的remove函数以移除一个员工
     * @Date 18:23 2020/7/6
     * @Param []
     * @return void
     **/
    public void removeEmployee(){
        System.out.println("请输入想删除的员工的ID >");
        int ID = sc.nextInt();
        for(Employee e : com.getEmployees()){
            if(e.getID() == ID){
                com.removeEmployee(e);
                System.out.println("员工信息成功删除");
                return;
            }
        }
        System.out.println("输入的员工ID有误!");
    }

    /*
     * @Description //TODO 根据输入的签到员工ID创建相应打卡信息
     * @Date 20:10 2020/7/2
     * @Param []
     * @return void
     **/
    @FXML
    private void checkIn() {
        int ID = Integer.parseInt(menu.getText());

        //先判断是否有该员工，后判断该员工是否已经有今日的打卡记录，若已有则提示，若无则新建
        if(this.com.getEmployee(ID)!=null){
            ArrayList<DakaInfo> set = list.getDakaSet(sim.format(new Date()));

            //判断是否已经有当日打卡信息的集合，若有则将新打卡信息加入，若无则新建集合并加入打卡信息
            if(set != null){
                for(DakaInfo dki : set){

                    //判断当日打卡信息集合中是否已经有该员工的签到信息，若有则给出提示，若无则新建打卡信息并加入当日打卡信息集合
                    if(dki.getID() == ID && this.todayIsThatday(dki.getCheckin())){
                        label1.setText("卡号为"+ID+"的该员工今天已签到！");
                        return;
                    }
                }
                set.add(new DakaInfo(ID,new Date(),null));
                list.getDakaMap().put(sim.format(new Date()),set);
                label1.setText("卡号为"+ID+"的员工打卡成功!");
                return;
            }else{
                set = new ArrayList<>();
                set.add(new DakaInfo(ID,new Date(),null));
                list.getDakaMap().put(sim.format(new Date()),set);
                label1.setText("卡号为"+ID+"的员工打卡成功!");
                return;
            }
        }
        label1.setText("无此ID员工");

    }

    /*
     * @Description //TODO 根据输入ID对员工进行签退并将信息储存到数据库中
     * @Date 17:53 2020/7/3
     * @Param []
     * @return void
     **/
    @FXML
    private void checkOut(){
        int ID = Integer.parseInt(menu.getText());
        //判断是否有该ID对应的员工
        if(this.com.getEmployee(ID) != null){
            ArrayList<DakaInfo> set = list.getDakaSet(sim.format(new Date()));
            //判断是否已经有当日打卡信息的集合，若有则判断是否已打卡
            if(set != null){
                for(DakaInfo dki : set){

                    //判断当日是否已经签退
                    if(dki.getID() == ID && dki.getCheckout() != null){
                        label1.setText("请勿重复签退!");
                        return;
                    }

                    //判断当日打卡信息集合中是否已经有该员工的签到信息，若有则执行附加签退操作，若无则给出提示
                    if(dki.getID() == ID && this.todayIsThatday(dki.getCheckin())){
                        dki.setCheckout(new Date());
                        label1.setText("卡号为"+ID+"的员工签退成功！");
                        return;
                    }
                }
            }else{
                label1.setText("卡号为"+ID+"的该员工今天还没有签到！");
                return;
            }
            label1.setText("卡号为"+ID+"的该员工今天还没有签到！");
        }
    }

    /*
     * @Description //TODO 打印迄今为止所有的打卡信息
     * @Date 18:38 2020/7/4
     * @Param []
     * @return void
     **/
    @FXML
    private void printInfo(){
        //判断是否已经有打卡记录，若无则提示并返回
        if(this.list.getDakaMap().size() == 0){
            label1.setText("还没有打卡记录!");
            return;
        }

        //获取打卡信息Map的键值的Set便于遍历
        Set keySet = this.list.getDate();
//        dateCol.setCellFactory(new PropertyValueFactory<CheckInCheckOutList,String>("日期"));
//        IDCol.setCellFactory(new PropertyValueFactory("员工ID"));
//        //根据日期键遍历打卡记录
//        for (String key : (Iterable<String>) keySet) {
//            HashSet<DakaInfo> dakaSet = this.list.getDakaSet(key);
//            System.out.println("--------------------");
//            System.out.println(key+"当天的打卡信息为:");
//
//
//            //遍历该日期下的所有打卡信息实例
//            for(DakaInfo dki : dakaSet){
//
//                System.out.println(dki);
//            }
//        }

        ObservableList<TableView> data = FXCollections.observableArrayList();
        if(this.list.getDakaInfos() != null){
            for(DakaInfo dki : list.getDakaInfos()){
                if(dki.getCheckout() != null){
                    data.add(new TableView(dki.getID()+"",sdf.format(dki.getCheckin()),sdf.format(dki.getCheckout())));
                }else{
                    data.add(new TableView(dki.getID()+"",sdf.format(dki.getCheckin()),null));
                }
            }
        }else{
            label1.setText("还没有任何打卡信息!");
        }

        //属性和列表绑定
        IDCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIDProperty()));
        checkInCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckInProperty()));
        checkOutCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckOutProperty()));

        table1.setItems(data);
        table1.setVisible(true);

        //存在测试
        System.out.println(data);

//        table1.setItems(rowmaps);
//        IDCol.setCellValueFactory(new ObservableMapValueFactory<Integer>(IDCol));
        label1.setText("日志操作时间:"+sdf.format(new Date()));
    }

    /*
     * @Description //TODO 判断给定的某一天是不是今天
     * @Date 16:51 2020/7/3
     * @Param [date]
     * @return boolean
     **/
    public boolean todayIsThatday(Date date){
        //获取当前系统时间
        Date current = new Date();
        //返回参数的日期是否是本日的布尔值
        return sim.format(date).equals(sim.format(current));
    }

    /*
     * @Description //TODO 程序结束时调用，将当前所有的员工打卡信息文件保存
     * @Date 16:56 2020/7/6
     * @Param []
     * @return void
     **/
    @FXML
    private void quit() throws IOException {
        //保存员工信息
        this.com.saveEmployees();
        //保存打卡信息
        this.list.saveDakaInfos();
        label1.setText("感谢使用本系统，所有信息已保存，再见!");
        Platform.exit();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IDCol.setCellValueFactory(new PropertyValueFactory<TableView,String>("IDCol"));
        checkInCol.setCellValueFactory(new PropertyValueFactory<TableView,String>("checkInCol"));
        checkOutCol.setCellValueFactory(new PropertyValueFactory<TableView,String>("checkOut"));
    }
}
