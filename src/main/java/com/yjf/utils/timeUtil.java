package com.yjf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 余俊锋
 * @date 2020/9/28 13:09
 * @Description
 */
public class timeUtil {


    /**
     *@Description TODO:返回 YYYY-MM-dd HH:mm:ss
     *@author 余俊锋
     *@date 2020/9/28 13:15
     *@params dateTime
     *@return java.lang.String
     */
    public static String dateTimetoStr(String dateTime){
        dateTime=dateTime.replace("T"," ");
        int index=dateTime.indexOf(".");
        return  dateTime.substring(0,index);
    }
    public static String dateTimetoStander(String dateTime){
        dateTime=dateTime.replace("T"," ");
        int index=dateTime.indexOf(".");
        dateTime=  dateTime.substring(0,index);
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date=null;
        try {
             date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat2=new SimpleDateFormat("YYYY年MM月dd日 HH时mm分ss秒");
        return dateFormat2.format(date);
    }

    public static String dateTimeAddT(String dateTime){
       return dateTime.replace(" ","T");
    }



}
