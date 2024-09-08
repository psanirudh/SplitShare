package com.pairprogrammers.splitshare.Models;


import android.os.Build;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class Transcation {
    public String tid;
    public int totAmountPaid;
    public String title;
    public String PaidBy;
    public Dictionary<String, Integer> PaidTo;

    public Transcation(){
            tid = Instant.now().getEpochSecond()+"";
    }


    public void Calculate(List<String> grpMembers){
        PaidTo =  new Hashtable<>();
        int perPerson = totAmountPaid / grpMembers.size();

        for (String grpMember :grpMembers ) {
            PaidTo.put(grpMember,perPerson);
        }

    }
}
