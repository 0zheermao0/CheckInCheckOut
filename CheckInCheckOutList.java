package CheckInCheckOut;

import java.io.*;
import java.util.*;

/**
 * @Classname CheckInCheckOutList
 * @Description TODO 打卡信息操作类
 * @Author Joey
 * @Date 2020/7/1 22:43
 * @Version 1.0
 **/
public class CheckInCheckOutList{
    //某日期当天对应的所有打卡信息,键:某年月日的字符串，值:当日所有员工的打卡信息的Set
    private HashMap<String, HashSet<DakaInfo>> dakaList = new HashMap<String, HashSet<DakaInfo>>();
    //所有的存储的打卡信息对应的日期的迭代器
    Set<String> date = this.getDakaList().keySet();

    //尝试加载已有打卡信息
    {
        File src = new File("DakaInfos.dat");
        //判断打卡信息文件是否存在
        if(src.exists()){
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(src));
                Object obj = ois.readObject();
                //如果读取到了信息则加载到程序中
                if(obj != null){
                    this.setDakaList((HashMap<String, HashSet<DakaInfo>>) obj);
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

    /*
     * @Description //TODO 根据日期的格式化字符串返回某一天的打卡信息Set
     * @Date 18:42 2020/7/4
     * @Param [string]
     * @return java.util.HashSet<CheckInCheckOut.DakaInfo>
     **/
    public HashSet<DakaInfo> getDakaSet(String string){
        HashSet<DakaInfo> set = new HashSet<>();
                set = dakaList.get(string);
        return set;
    }

    //根据格式化后的日期的字符串获取该日的打卡名单
    public HashSet<DakaInfo> getDakaInfos(String string) {
        return dakaList.get(string);
    }

    public void saveDakaInfos() throws IOException {
        //判断要保存的信息是否为null防止程序崩溃
        if(this.getDakaList()!=null && this.getDakaList().size()>0){
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DakaInfos.dat"));
            oos.writeObject(this.getDakaList());
            oos.close();
        }
    }

    public HashMap<String, HashSet<DakaInfo>> getDakaList() {
        return dakaList;
    }

    public void setDakaList(HashMap<String, HashSet<DakaInfo>> dakaList) {
        this.dakaList = dakaList;
    }

    public Set<String> getDate() {
        return this.dakaList.keySet();
    }

    public void setDate(Set<String> date) {
        this.date = date;
    }

}
