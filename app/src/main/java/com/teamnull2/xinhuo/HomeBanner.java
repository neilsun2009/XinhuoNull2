package com.teamnull2.xinhuo;

/**
 * Created by lenovo on 2015/11/23.
 */
public class HomeBanner {
    private String title;
    private int image;
    private String target;
    public HomeBanner(String _title, int _image, String _target) {
        title=_title;
        image=_image;
        target=_target;
    }
    public String getTitle() {
        return title;
    }
    public int getImage() {
        return image;
    }
    public String getTarget() {
        return target;
    }
    public void setTitle(String _title) {
        title=_title;
    }
    public void setImage(int _image) {
        image=_image;
    }
    public void setTarget(String _target) {
        target=_target;
    }
}
