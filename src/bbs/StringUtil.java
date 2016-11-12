package bbs;

import java.sql.Date;
import java.text.DateFormat;
import java.sql.Timestamp;

public class StringUtil {

    
     //得到当前系统日期时间的字符串表示 如："2016-12-12 12:12:12"
     
    public static String getDateTime(Date date) 
    {
    	//设定日期格式
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.
            MEDIUM,
            DateFormat.
            MEDIUM);
        return mediumDateFormat.format(new java.util.Date(date.getTime()));
    }
    
    //获得时间戳
    public static String getTimestamp(Timestamp date) 
    {
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.
            MEDIUM,
            DateFormat.
            MEDIUM);
        return mediumDateFormat.format(date);
    }

    //修改时间显示方式
    public static String getBRString(String text) {
        if(text != null && text.indexOf("<") > -1){
            text = text.replaceAll("<","&lt;");
        }
        if(text != null && text.indexOf(">") > -1){
            text = text.replaceAll(">","&gt;");
        }
        if(text != null && text.indexOf("\n") > -1){
            text = text.replaceAll("\n", "<br>");
        }
        return text;
    }
    
    public static void main(String[]args) 
    {
    }

}
