package bbs;

import java.sql.*;
import java.util.ArrayList;
//导入需要的包


//这里需要与数据库打交道，故不与forum合在一起
// 讨论区管理模块处理类，封装如下操作
//1，增加新的讨论区
//2，删除讨论区
//3，修改讨论区对象
//4，获取讨论区列表
//5，获取某一讨论区对象

public class forumhandle {

    // 功能如下创建一个讨论区
    // 往数据库中插入一条记录
     
    public static void insert(forum forum) throws Exception {
        SqlManger DBm = SqlManger.createInstance();
        try {
        	
    		DBm.connectDB(); //获取连接句柄
    		String sql = "insert into forum(sort,name,description) "
    			+ "values ('" + forum.getSort() + "' , '" + forum.getName() +"' , '" + forum.getDescription() + "' )";
    			
    		DBm.executeUpdate(sql); //执行语句
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    
     //删除讨论区以及属于该讨论区的所有帖子
    
   
    public static void delete(int id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	//先要删除讨论区
        	DBm.connectDB(); //获取连接句柄
           String sql= "delete from forum where id=’"+id+"'";//创建连接句柄
            
           DBm.executeUpdate(sql);//执行语句，删除代号为id的讨论区
           DBm.closeDB(); //释放语句句柄和连接句柄
           
           //以下删除讨论区下面的帖子
           String sqll="delete from topic where forumid='"+id+"'";//创建连接句柄
               
           
           DBm.executeUpdate(sql);//执行语句，删除代号为id的讨论区下面的所有帖子
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    
     //删除讨论区以及属于该讨论区的所有帖子
    
    public static void deleteTest(int id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
        	//先要删除讨论区
        	DBm.connectDB(); //获取连接句柄
           String sql= "delete from forum where id='"+id+"'";//创建连接句柄
            
           DBm.executeUpdate(sql);//执行语句，删除代号为id的讨论区
      
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    
     // 修改讨论区信息
    public static void update(forum forum) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB(); //获取连接句柄
            String sql= "update forum set name='"
            	+forum.getName()+"', sort='"
            	+forum.getSort()+"', description='"
            	+forum.getDescription()+"' where id='"
            	+forum.getId()+"'";
            
           // 创建语句句柄
           
            //执行语句
            DBm.executeUpdate(sql);
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    
     //获取讨论区列表，并保存到数组中
     
    public static ArrayList select() throws Exception {
        ArrayList array = new ArrayList();
        SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        try {
        	 DBm.connectDB(); //获取连接句柄
           String sql="select * from forum order by sort asc";
            //使用SQL字符串创建语句句柄
           System.out.print(sql);
            rs = DBm.executeQuery(sql);
            //执行SQL语句获取结果集
            while (rs.next()) {
                //遍历结果集，并保存在数组中
                forum forum = new forum();
                forum.setId(rs.getInt(1));
                forum.setSort(rs.getInt(2));
                forum.setName(rs.getString(3));
                forum.setDescription(rs.getString(4));
                //创建Forum对象，并从结果集取出数据填充forum
                array.add(forum);
                //将forum对象放入数组中
            }
        }
        catch (Exception ex) {
            throw ex;
            //如果出现异常,将异常抛出,由外部程序处理
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
            
        }
        return array;
        //返回数组
    }

    //该函数获取论坛名称，需传递论坛编号
    public static String getForumName(String id) throws Exception {
        int forum_id = 0;
        try {
            forum_id = Integer.parseInt(id);
        }
        catch (Exception es) {}
        return getForumInfo(forum_id).getName();
    }

    //该函数获取论坛信息，需要传递论坛编号
    public static forum getForumInfo(int id) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        forum forum = null;
        try {
            DBm.connectDB();
            String sql="select * from forum where id ="+id;//插入查询语句
            System.out.println(sql);
            rs = DBm.executeQuery(sql);
            if (rs.next()) {//遍历结果集
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
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
        return forum;
    }

}
