package com.teamnull2.xinhuo;

/**
 * Created by lenovo on 2015/11/23.
 */
//主页ListItem的item
public class HomeListItem {
    private int image;
    private String title;
    private int special;//右上角突出资源
    private String target;
    public HomeListItem(int Image, String Title, int Special, String Target) {
        image= Image;
        title=Title;
        special= Special;
        target= Target;
    }
    public int getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }
    public int getSpecial() {
        return special;
    }
    public String getTarget() {
        return target;
    }
    public void setImage(int Image) {
        image=Image;
    }
    public void setTitle(String Title) {
        title= Title;
    }
    public void setSpecial(int Special) {
        special=Special;
    }
    public void setTarget(String Target) {
        target=Target;
    }
}
