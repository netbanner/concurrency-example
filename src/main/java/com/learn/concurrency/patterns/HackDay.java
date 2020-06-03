package com.learn.concurrency.patterns;

import java.util.Arrays;

/**
 * @author zhuwh
 * @date 2018/6/22 14:13
 * @desc
 */
public class HackDay {
    public final static    StringBuffer stringBuffer = new StringBuffer();
    String string = "123";
    String predict(String input){
        if(input==null) return  null;
        String str ="";
        if(input=="") return "";

       int len = input.length();

       if(len==1) return input;
       for(int i=0,j=i+1;i<len&&j<len;i++,j++){
               if(input.charAt(i)==input.charAt(j)) {
                   str= str+input.charAt(i);
               }else{
                    str = str+(string.replace(input.charAt(i)+"","").replace(input.charAt(j)+"",""));
               }

       }

     //   System.out.println(str);
        return  predict(str);
    }

    public static void main(String []args){

    long start = System.currentTimeMillis();
    System.out.println(start);
        HackDay hackDay = new HackDay();

        System.out.println(hackDay.predict("2221213322233211313312321221222231331232313323133211322332222131212123132333132222332111133212333122"));
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println(end-start);
    }

}
