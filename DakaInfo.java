package CheckInCheckOut;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname DakaInfo
 * @Description TODO 打卡信息类
 * @Author Joey
 * @Date 2020/7/1 18:32
 * @Version 1.0
 **/
public class DakaInfo implements Serializable {
    //签到员工的ID
    private int ID;
    //签到时间
    private Date checkin;
    //签退时间
    private Date checkout;
    //格式化输出日期属性便于查看
    private SimpleDateFormat sdf = new SimpleDateFormat("打卡时间yyyy-MM-dd  HH:mm:ss");

    public DakaInfo(int ID, Date checkin, Date checkout) {
        this.ID = ID;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public DakaInfo(int ID) {
        this.ID = ID;
    }

    public DakaInfo() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        if(this.getCheckin() != null && this.getCheckout() != null){
            return "ID为:"+this.getID()+"的员工\t签到时间为:"+this.sdf.format(this.getCheckin())+"\t签退时间为:"+this.sdf.format(this.getCheckout());
        }else if(this.getCheckin() != null && this.getCheckout() == null){
            return "ID为:"+this.getID()+"的员工\t签到时间为:"+this.sdf.format(this.getCheckin())+"\t签退时间为:"+null;
        }else{
            return "ID为:"+this.getID()+"的员工\t签到时间为:"+null+"\t签退时间为:"+null;
        }
    }
}
