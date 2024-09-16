package com.pairprogrammers.splitshare.Models;


import android.os.Build;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class Transcation {
    public String tid;
    public int totAmountPaid;
    public String title;
    public String PaidBy;
    public Map<String, Integer> PaidTo;

    public Transcation(){
            tid = Instant.now().getEpochSecond()+"";
    }


    public void Calculate(Map<String,Integer> grpMembers,int slpitType){
        PaidTo =  new HashMap<>();

        if(slpitType==2){//unequally
            PaidTo = grpMembers;
        }
        else if(slpitType==1){//equally between some people
          int totalPeopleInvolved = grpMembers.keySet().size();
          int perPerson = totAmountPaid /totalPeopleInvolved;

            for (String grpMember :grpMembers.keySet() ) {
                if(grpMember.equals(PaidBy)){
                    PaidTo.put(grpMember,(totalPeopleInvolved-1)*perPerson);
                }
                else{
                    PaidTo.put(grpMember,-1*perPerson);
                }
            }
        }
    }
}
