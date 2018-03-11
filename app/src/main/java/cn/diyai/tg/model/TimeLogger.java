package cn.diyai.tg.model;

/**
 * Created by wangxiaomin on 2018/3/9.
 */

public class TimeLogger {
    private String start;
    private String end;
    private int id;

    public TimeLogger(String start, String end) {
        this.start = start;
        this.end = end;
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
                '}';
    }
}
