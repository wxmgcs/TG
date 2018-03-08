package cn.diyai.tg.model;

/**
 * Created by wangxiaomin on 2018/3/9.
 */

public class TimeLogger {
    private String start;
    private String end;

    public TimeLogger(String start, String end) {
        this.start = start;
        this.end = end;
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
                '}';
    }
}
