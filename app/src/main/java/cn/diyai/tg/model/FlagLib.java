package cn.diyai.tg.model;

/**
 * Created by wangxiaomin on 2018/3/11.
 */
public class FlagLib {
    private String name;
    private int selected;
    private int id;

    public FlagLib(){

    }

    public FlagLib(int id,String name, int selected) {
        this.name = name;
        this.selected = selected;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int statue) {
        this.selected = statue;
    }

    @Override
    public String toString() {
        return "FlagLib{" +
                "name='" + name + '\'' +
                ", selected=" + selected +
                ", id=" + id +
                '}';
    }
}
