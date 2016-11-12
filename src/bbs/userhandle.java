package bbs;

import java.sql.*;




 //由于需要和数据库打交道，故不放入user中，而单独列出来
 //用户管理模块处理类
 //1，用户注册
 //2，根据用户名获取用户信息
 //3，用户登录
 //4，用户注册信息修改
 //5，删除用户

public class userhandle {
	
     //用户注册
     //注册成功返回true;否则返回false
     
    public  user userLogin(String username, String password)
    {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        boolean flag = false;
        try {
            DBm.connectDB();
            //插入连接数据库句柄
            String sql=null;
            sql="select * from user where username= '"+username+"' and password= '"+password+"'";
            System.out.println(sql);
           
            rs = DBm.executeQuery(sql);//执行语句
            
            System.out.println("ms2");
            user user = null;//建立新对象
            while(rs.next()) //判断是否还存在
            {
                    user = new user();
                    user.setId(rs.getInt(1));//设定ID
                    user.setEmail(rs.getString(4));//设定email地址
                    user.setNickname(rs.getString(5));//设定昵称
                    user.setDegree(rs.getInt(6));//设定等级
                    user.setRegisterdate(rs.getTimestamp(7));//设定注册日期
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
        	 DBm.closeDB(); //释放语句句柄和连接句柄
        }
        
    }

   
     //根据用户名获取用户信息
     //1，获取连接
     //2，创建语句句柄
     //3，执行SQL语句获取结果集
     //4，从结果集中获取数据
     //5，使用完之后关闭相关资源：结果集、语句句柄、连接
     
    public static user getUser(String userName) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        user user = null;
        try {
            DBm.connectDB();   //建立连接
            String sql="select * from user where username='"+userName+"'"; //创建语句句柄
      
            rs = DBm.executeQuery(sql);     //执行语句获取结果集
            
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
            //关闭已获取的资源
        	 DBm.closeDB(); //释放语句句柄和连接句柄
        }
        return user;
    }

    
     //用户注册
     
    public static void userReg(user user) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	DBm.connectDB();   //建立连接
            String sql="insert into user(userName, passWord, email, nickname, registerdate) " 
                        + "values ('" + user.getUsername() + "' , '" + user.getPassword() +"' , '" + user.getEmail()+"','"+user.getNickname()+"',now())";
            
           System.out.println(sql);
            DBm.executeUpdate(sql);//执行语句
           System.out.println(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	 DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    
     //修改用户信息
     
    public static void userUpdate(user user) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	DBm.connectDB();   //建立连接
            String sql="update user set password= ' "
            	+user.getPassword()+"' , email = ' "
            	+user.getEmail()+" ', nickname = '"
            	+user.getNickname()+"' where username = '"
            	+user.getUsername()+"'";
            
            
            System.out.println(sql);
            DBm.executeUpdate(sql);//执行语句
            System.out.println(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	 DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    
     //删除用户
     
    public static void userDelete(user user) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	DBm.connectDB();   //建立连接
            String sql="delete from user where username = '"+user.getUsername()+"'";
           
            System.out.println(sql);
            DBm.executeUpdate(sql);//执行语句
            System.out.println(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	 DBm.closeDB(); //释放语句句柄和连接句柄
        }

    }
   


}
