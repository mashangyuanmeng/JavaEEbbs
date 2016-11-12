package bbs;

import java.sql.Date;
import java.text.DateFormat;
import java.sql.Timestamp;

public class StringUtil {

    
     //�õ���ǰϵͳ����ʱ����ַ�����ʾ �磺"2016-12-12 12:12:12"
     
    public static String getDateTime(Date date) 
    {
    	//�趨���ڸ�ʽ
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.
            MEDIUM,
            DateFormat.
            MEDIUM);
        return mediumDateFormat.format(new java.util.Date(date.getTime()));
    }
    
    //���ʱ���
    public static String getTimestamp(Timestamp date) 
    {
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.
            MEDIUM,
            DateFormat.
            MEDIUM);
        return mediumDateFormat.format(date);
    }

    //�޸�ʱ����ʾ��ʽ
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
