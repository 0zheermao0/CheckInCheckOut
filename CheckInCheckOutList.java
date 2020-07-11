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
    private HashMap<String, ArrayList<DakaInfo>> dakaMap = new HashMap<String, ArrayList<DakaInfo>>();
    //所有的存储的打卡信息对应的日期的迭代器
    Set<String> date = this.getDakaMap().keySet();

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
                    this.setDakaMap((HashMap<String, ArrayList<DakaInfo>>) obj);
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
    public ArrayList<DakaInfo> getDakaSet(String string){
        ArrayList<DakaInfo> set = dakaMap.get(string);
        return set;
    }

    /*
     * @Description //TODO Get all the DakaInfos in file
     * @Date 17:31 2020/7/11
     * @Param [string]
     * @return java.util.HashSet<CheckInCheckOut.DakaInfo>
     **/
    public ArrayList<DakaInfo> getDakaInfos() {
        ArrayList<DakaInfo> temp = new ArrayList<>();
        for(String s : this.getDate()){
            if(this.getDate() == null){
                return null;
            }
            assert false;
            temp.addAll(dakaMap.get(s));
        }
        return temp;
    }

    public void saveDakaInfos() throws IOException {
        //判断要保存的信息是否为null防止程序崩溃
        if(this.getDakaMap()!=null && this.getDakaMap().size()>0){
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DakaInfos.dat"));
            oos.writeObject(this.getDakaMap());
            oos.close();
        }
    }

    public HashMap<String, ArrayList<DakaInfo>> getDakaMap() {
        return dakaMap;
    }

    public void setDakaMap(HashMap<String, ArrayList<DakaInfo>> dakaMap) {
        this.dakaMap = dakaMap;
    }

    public Set<String> getDate() {
        return this.dakaMap.keySet();
    }

    public void setDate(Set<String> date) {
        this.date = date;
    }

}
