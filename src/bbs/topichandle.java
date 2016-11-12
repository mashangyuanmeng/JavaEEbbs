package bbs;

import java.sql.*;
import java.util.ArrayList;

public class topichandle {
    public topichandle() {
    }
    //���캯��

    
     //�����ݿ��������
    
    public static void insert(topic bbs) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB(); //��ȡ���Ӿ��
            
           String sql="insert into topic(parentid, forumid, username, title, content, postdate)" 
        	   +" values('" + bbs.getParentid() + "','" + bbs.getForumid() +"','" + bbs.getUsername()+"','"+bbs.getTitle()+"','"+bbs.getContent()+"',now())";
            //������
           System.out.println("mjl9");
           System.out.println(sql);
            DBm.executeUpdate(sql); //ִ�����
            if (bbs.getParentid() != 0) 
            { //����������ǻ���
            	DBm.closeDB(); //�ͷ�����������Ӿ��
                //�������ӵĸ����ӵĻظ���������һ��
            	DBm.connectDB(); //��ȡ���Ӿ��
                String sqll= "update topic set replies=replies+1 where id="
                	          +bbs.getParentid();
                //������
                DBm.executeUpdate(sqll);
                //ִ�����
            }
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    
     //ɾ��ID��PARENT_IDΪ����ֵ������
     
    public static void delete(int id) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB();
            String sql="delete from topic where id="+id+" or parentid="+id;//������
            System.out.println(sql);
            DBm.executeUpdate(sql);//ִ�в������
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    //�������Ӳ���
    public static void update(topic bbs) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB();//��������
            String sql="update topic set title='"
            	+bbs.getTitle()+"', content='"
            	+bbs.getContent()+"' where id='"
            	+bbs.getId()+"'";
            //������
            DBm.executeUpdate(sql);//ִ�����
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    //�������б��л�ȡ������Ϣ
    public static ArrayList select(int forum_id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
    	System.out.println("suc");
        ResultSet rs = null;
        ArrayList array = new ArrayList();
        try {
            DBm.connectDB();//��������
            String sql="select id, username, title, postdate, replies from topic where parentid=0 and forumid="
            	+forum_id;
            System.out.println(sql);
            rs = DBm.executeQuery(sql);//ִ�����
            while (rs.next()) {
                topic bbs = new topic();//δ����ʱ���ϴ����¶���
                bbs.setId(rs.getInt(1));//�����������
                bbs.setUsername(rs.getString(2));//�����û���
                bbs.setTitle(rs.getString(3));//�������
                bbs.setPostdate(StringUtil.getTimestamp(rs.getTimestamp(4)));//����ʱ��
                bbs.setReply(rs.getInt(5));//����ظ�
                array.add(bbs);//�������м�����һ��
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
        return array;
    }

    
     //��ȡָ���������������б�
     
    public static ArrayList select(int forum_id, page page) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        ArrayList array = new ArrayList();
        try {
            DBm.connectDB();
            String sql=  "select id, username, title, postdate, replies from topic where parentid=0 and forumid="+forum_id;
            //��ȡָ���������������������������ظ�
           
            rs = DBm.executeQuery(sql);
            System.out.println("mjl2");
        //    while(rs.next()) 
       //     {
         //       page.setTotal(rs.getInt(1)); //���������������ڷ�ҳ������
          //  }
          //  DBm.closeDB(); //�ͷ�����������Ӿ��
          //  DBm.connectDB();//��������
          //  String sqll=  "select id, username, title, postdate, replies from topic where parentid=0 and forumid="
          //  	+forum_id+" order by id desc limit "
          //  	+page.getIndex()+"','"
           // 	+page.max_row;
            // ����ָ���ķ�ҳ�����ȡ������Ϣ
           
         //   rs = DBm.executeQuery(sqll);//ִ�����
            while (rs.next()) 
            {//�����¶��󲢸��丳ֵ
                topic bbs = new topic();
                bbs.setId(rs.getInt(1));
                bbs.setUsername(rs.getString(2));
                bbs.setTitle(rs.getString(3));
                bbs.setPostdate(StringUtil.getTimestamp(rs.getTimestamp(4)));
                bbs.setReply(rs.getInt(5));
                array.add(bbs);
                System.out.println("mjl3");
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	 DBm.closeDB(); //�ͷ�����������Ӿ��
        }
        return array;
    }

    
     //��ȡָ�������Լ������ӵĻظ�
   
    public static ArrayList viewTopic(int bbs_id, page page) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        ArrayList array = new ArrayList();
        try {
            DBm.connectDB();
            String sql="select id, username, title, content, postdate from topic where parentid="
            	+bbs_id+" or id="+bbs_id;
            //ʹ��SQL�ַ����������������ȡָ��ID�������Լ���������
            System.out.println(sql);
            rs = DBm.executeQuery(sql);//ִ�����
            System.out.println("mjl4");
        //    if (rs.next()) {
          //      page.setTotal(rs.getInt(1));
                //���������������ڷ�ҳ������
           // }
           // DBm.closeDB(); //�ͷ�����������Ӿ��
            
            //DBm.connectDB();//��������
            //String sqll="select id, username, title, content, postdate from topic where parentid="
            	//+bbs_id+"or id="
            	//+bbs_id+"order by id asc limit'"
            	//+page.getIndex()+"','"
            	//+page.max_row;
            //����ָ���ķ�ҳ�����ȡָ��ID�������Լ�����
            
          //  rs = DBm.executeQuery(sqll);//ִ�����
            while (rs.next()) 
            {
            	//�����¶��󲢸�ֵ
                topic bbs = new topic();
                bbs.setId(rs.getInt(1));
                bbs.setUsername(rs.getString(2));
                bbs.setTitle(rs.getString(3));
                bbs.setContent(rs.getString(4));
                bbs.setPostdate(StringUtil.getTimestamp(rs.getTimestamp(5)));
                array.add(bbs);
                //�����������ȡ������Ϣ�����浽������
                System.out.println("mjl5");
            }
            DBm.closeDB(); //�ͷ�����������Ӿ��
            
            DBm.connectDB();//��������
            
            String sqql= "select id, username, title, content, postdate from topic where id="+bbs_id;
            //��ȡ�����ӵı���
           
            rs = DBm.executeQuery(sqql);//ִ�����
            
            if (rs.next())
            {
                ( (topic) array.get(0)).setTitle(rs.getString(3));
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
        return array;
    }

    //��ȡ���ӱ���
    public static topic getTopic(int id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        topic ann = null;
        try {
            DBm.connectDB();//��������
            String sql="select id, title, content from topic where id ="+id;
           
            rs = DBm.executeQuery(sql);
            
            if (rs.next()) 
            {
            	//�����������ʱ�����¶��󲢸��丳ֵ
                ann = new topic();
                ann.setId(rs.getInt(1));
                ann.setTitle(rs.getString(2));
                ann.setContent(rs.getString(3));
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
        return ann;
    }

}
