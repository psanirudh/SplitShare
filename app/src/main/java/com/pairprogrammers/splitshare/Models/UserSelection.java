package com.pairprogrammers.splitshare.Models;

import java.util.HashSet;
import java.util.List;

public class UserSelection {
    public String name;
    public  boolean isSelected;
    public List<String> groups;
    public UserSelection(String name,boolean isSelected){
         this.name = name;
        this.isSelected = isSelected;
    }
    public  UserSelection(){

    }
}
