package bbs;

import java.sql.*;




 //������Ҫ�����ݿ�򽻵����ʲ�����user�У��������г���
 //�û�����ģ�鴦����
 //1���û�ע��
 //2�������û�����ȡ�û���Ϣ
 //3���û���¼
 //4���û�ע����Ϣ�޸�
 //5��ɾ���û�

public class userhandle {
	
     //�û�ע��
     //ע��ɹ�����true;���򷵻�false
     
    public  user userLogin(String username, String password)
    {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        boolean flag = false;
        try {
            DBm.connectDB();
            //�����������ݿ���
            String sql=null;
            sql="select * from user where username= '"+username+"' and password= '"+password+"'";
            System.out.println(sql);
           
            rs = DBm.executeQuery(sql);//ִ�����
            
            System.out.println("ms2");
            user user = null;//�����¶���
            while(rs.next()) //�ж��Ƿ񻹴���
            {
                    user = new user();
                    user.setId(rs.getInt(1));//�趨ID
                    user.setEmail(rs.getString(4));//�趨email��ַ
                    user.setNickname(rs.getString(5));//�趨�ǳ�
                    user.setDegree(rs.getInt(6));//�趨�ȼ�
                    user.setRegisterdate(rs.getTimestamp(7));//�趨ע������
                    System.out.println("ms3");
                    
                    return user;
                    
                }
            return user;
        }
        catch (Exception es) {
           es.printStackTrace();
           return null;
        }
        finally {
        	 DBm.closeDB(); //�ͷ�����������Ӿ��
        }
        
    }

   
     //�����û�����ȡ�û���Ϣ
     //1����ȡ����
     //2�����������
     //3��ִ��SQL����ȡ�����
     //4���ӽ�����л�ȡ����
     //5��ʹ����֮��ر������Դ��������������������
     
    public static user getUser(String userName) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        user user = null;
        try {
            DBm.connectDB();   //��������
            String sql="select * from user where username='"+userName+"'"; //���������
      
            rs = DBm.executeQuery(sql);     //ִ������ȡ�����
            
            if (rs != null) 
            {
                rs.next();
                user = new user();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setNickname(rs.getString(5));
                user.setDegree(rs.getInt(6));
                user.setRegisterdate(rs.getTimestamp(7));
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
            //�ر��ѻ�ȡ����Դ
        	 DBm.closeDB(); //�ͷ�����������Ӿ��
        }
        return user;
    }

    
     //�û�ע��
     
    public static void userReg(user user) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	DBm.connectDB();   //��������
            String sql="insert into user(userName, passWord, email, nickname, registerdate) " 
                        + "values ('" + user.getUsername() + "' , '" + user.getPassword() +"' , '" + user.getEmail()+"','"+user.getNickname()+"',now())";
            
           System.out.println(sql);
            DBm.executeUpdate(sql);//ִ�����
           System.out.println(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	 DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    
     //�޸��û���Ϣ
     
    public static void userUpdate(user user) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	DBm.connectDB();   //��������
            String sql="update user set password= ' "
            	+user.getPassword()+"' , email = ' "
            	+user.getEmail()+" ', nickname = '"
            	+user.getNickname()+"' where username = '"
            	+user.getUsername()+"'";
            
            
            System.out.println(sql);
            DBm.executeUpdate(sql);//ִ�����
            System.out.println(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	 DBm.closeDB(); //�ͷ�����������Ӿ��
        }
    }

    
     //ɾ���û�
     
    public static void userDelete(user user) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	DBm.connectDB();   //��������
            String sql="delete from user where username = '"+user.getUsername()+"'";
           
            System.out.println(sql);
            DBm.executeUpdate(sql);//ִ�����
            System.out.println(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	 DBm.closeDB(); //�ͷ�����������Ӿ��
        }

    }
   


}
