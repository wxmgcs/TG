package cn.diyai.tg.model;

/**
 * Created by wangxiaomin on 2018/3/9.
 */

public class TimeLogger {
    private String start;
    private String end;
    private int id;
    private String date;
    private String flag;

    public TimeLogger() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimeLogger{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", id=" + id +
                ", date='" + date + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
