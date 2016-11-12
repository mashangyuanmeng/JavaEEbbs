package bbs;

import java.sql.*;
import java.util.ArrayList;
//������Ҫ�İ�


//������Ҫ�����ݿ�򽻵����ʲ���forum����һ��
// ����������ģ�鴦���࣬��װ���²���
//1�������µ�������
//2��ɾ��������
//3���޸�����������
//4����ȡ�������б�
//5����ȡĳһ����������

public class forumhandle {

    // �������´���һ��������
    // �����ݿ��в���һ����¼
     
    public static void insert(forum forum) throws Exception {
        SqlManger DBm = SqlManger.createInstance();
        try {
        	
    		DBm.connectDB(); //��ȡ���Ӿ��
    		String sql = "insert into forum(sort,name,description) "
    			+ "values ('" + forum.getSort() + "' , '" + forum.getName() +"' , '" + forum.getDescription() + "' )";
    			
    		DBm.executeUpdate(sql); //ִ�����
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    
     //ɾ���������Լ����ڸ�����������������
    
   
    public static void delete(int id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	//��Ҫɾ��������
        	DBm.connectDB(); //��ȡ���Ӿ��
           String sql= "delete from forum where id=��"+id+"'";//�������Ӿ��
            
           DBm.executeUpdate(sql);//ִ����䣬ɾ������Ϊid��������
           DBm.closeDB(); //�ͷ�����������Ӿ��
           
           //����ɾ�����������������
           String sqll="delete from topic where forumid='"+id+"'";//�������Ӿ��
               
           
           DBm.executeUpdate(sql);//ִ����䣬ɾ������Ϊid���������������������
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    
     //ɾ���������Լ����ڸ�����������������
    
    public static void deleteTest(int id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	//��Ҫɾ��������
        	DBm.connectDB(); //��ȡ���Ӿ��
           String sql= "delete from forum where id='"+id+"'";//�������Ӿ��
            
           DBm.executeUpdate(sql);//ִ����䣬ɾ������Ϊid��������
      
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    
     // �޸���������Ϣ
    public static void update(forum forum) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB(); //��ȡ���Ӿ��
            String sql= "update forum set name='"
            	+forum.getName()+"', sort='"
            	+forum.getSort()+"', description='"
            	+forum.getDescription()+"' where id='"
            	+forum.getId()+"'";
            
           // ���������
           
            //ִ�����
            DBm.executeUpdate(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    
     //��ȡ�������б������浽������
     
    public static ArrayList select() throws Exception {
        ArrayList array = new ArrayList();
        SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        try {
        	 DBm.connectDB(); //��ȡ���Ӿ��
           String sql="select * from forum order by sort asc";
            //ʹ��SQL�ַ������������
           System.out.print(sql);
            rs = DBm.executeQuery(sql);
            //ִ��SQL����ȡ�����
            while (rs.next()) {
                //�������������������������
                forum forum = new forum();
                forum.setId(rs.getInt(1));
                forum.setSort(rs.getInt(2));
                forum.setName(rs.getString(3));
                forum.setDescription(rs.getString(4));
                //����Forum���󣬲��ӽ����ȡ���������forum
                array.add(forum);
                //��forum�������������
            }
        }
        catch (Exception ex) {
            throw ex;
            //��������쳣,���쳣�׳�,���ⲿ������
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
            
        }
        return array;
        //��������
    }

    //�ú�����ȡ��̳���ƣ��贫����̳���
    public static String getForumName(String id) throws Exception {
        int forum_id = 0;
        try {
            forum_id = Integer.parseInt(id);
        }
        catch (Exception es) {}
        return getForumInfo(forum_id).getName();
    }

    //�ú�����ȡ��̳��Ϣ����Ҫ������̳���
    public static forum getForumInfo(int id) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        forum forum = null;
        try {
            DBm.connectDB();
            String sql="select * from forum where id ="+id;//�����ѯ���
            System.out.println(sql);
            rs = DBm.executeQuery(sql);
            if (rs.next()) {//���������
                forum = new forum();
                forum.setId(rs.getInt(1));
                forum.setSort(rs.getInt(2));
                forum.setName(rs.getString(3));
                forum.setDescription(rs.getString(4));
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //�ͷ�����������Ӿ��
        }
        return forum;
    }

}
