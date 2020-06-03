package com.learn.concurrency.other.java_copy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuwh
 * @date 2018/8/10 10:08
 * @desc
 */
public class test {


    public static  void main(String []args){
        String str = "basicInfo.addresses.1.phone.2.PhoneLocationTypeCode";
        Object obj = new Object();
        String []a = str.split(".");
        for(int i=0;i<a.length;i++){
            if(checkData(a[i])){

            }
        }
    }

    public static boolean checkData(String s){

        return !s.matches("/^[0-9]+.?[0-9]*/");
    }
}
