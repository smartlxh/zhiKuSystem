package com.example.smartlxh;

import java.util.LinkedList;

/**
 * Created by lixianhai on 02/04/2018.
 */
public class FuzzleQueryJson {
    private String returncode;
    private LinkedList<Scholar> items;

    public void setItems(LinkedList<Scholar> items) {
        this.items = items;
    }

    public LinkedList<Scholar> getItems() {
        return items;
    }

    public String getCode(){
        return returncode;
    }

    public FuzzleQueryJson(String returncode,LinkedList list){
        this.returncode  = returncode;
        this.items = list;
    }

}
