package cn.diyai.tg.model;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public class Setting {
    private String sleepTime;
    private  int timeParticle;

    public Setting() {
    }

    public Setting(String sleepTime) {
        this.sleepTime = sleepTime;
    }


    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getTimeParticle() {
        return timeParticle;
    }

    public void setTimeParticle(int timeParticle) {
        this.timeParticle = timeParticle;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "sleepTime='" + sleepTime + '\'' +
                ", timeParticle='" + timeParticle + '\'' +
                '}';
    }
}
