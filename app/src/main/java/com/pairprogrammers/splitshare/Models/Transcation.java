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


    public boolean Calculate(Map<String,Integer> grpMembers,int slpitType){
        PaidTo =  new HashMap<>();

        if(slpitType==2){//unequally
            for (String grpMember :grpMembers.keySet() ) {
                PaidTo.put(grpMember,-1*grpMembers.get(grpMember));
            }
            Integer payerTally  =  0;
            if(PaidTo.containsKey(PaidBy))
                payerTally = PaidTo.get(PaidBy);
            payerTally += totAmountPaid;
            PaidTo.put(PaidBy,payerTally);
        }
        else if(slpitType==1){//equally between some people
          int totalPeopleInvolved = grpMembers.keySet().size();
          int perPerson = totAmountPaid /totalPeopleInvolved;

            for (String grpMember :grpMembers.keySet() ) {
               PaidTo.put(grpMember,-1*perPerson);
            }

            Integer payerTally  =  0;
            if(PaidTo.containsKey(PaidBy))
                payerTally = PaidTo.get(PaidBy);
            payerTally += totAmountPaid;
            PaidTo.put(PaidBy,payerTally);
        }

        Integer totalTally = 0;
        for (int perPersonCost :PaidTo.values() ) {
            totalTally += perPersonCost;
        }
        return  totalTally == 0;
    }
}
//pnl n actual transac data shld be seperat
//all may not neccesarly have entry in transaction
//caluclate group level PNL matrix when tran is added/modified