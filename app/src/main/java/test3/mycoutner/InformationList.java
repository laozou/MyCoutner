package test3.mycoutner;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/20.
 */
public class InformationList implements Serializable{
    private String mInfo;

    public InformationList(String info){
        mInfo = info;
    }

    public String getmInfo(){ return mInfo;}

    public void setmInfo(String info){ mInfo = info;}



}
