package com.example.smartlxh;

import java.util.LinkedList;

/**
 * Created by lixianhai on 30/11/2016.
 */
public class ResultJson {
    private String returncode;
    private LinkedList<Scholar> items;

    public void setCode(String returncode){
        this.returncode = returncode;

    }

    public String getCode(){
        return returncode;
    }
    public void setList(LinkedList<Scholar> items){
        this.items = items;
    }

    public LinkedList<Scholar> getItems(){
        return items;
    }

    public ResultJson(String returncode,LinkedList<Scholar> items){
        this.returncode = returncode;
        this.items = items;
    }


}
