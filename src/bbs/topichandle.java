package bbs;

import java.sql.*;
import java.util.ArrayList;

public class topichandle {
    public topichandle() {
    }
    //构造函数

    
     //往数据库插入新贴
    
    public static void insert(topic bbs) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB(); //获取连接句柄
            
           String sql="insert into topic(parentid, forumid, username, title, content, postdate)" 
        	   +" values('" + bbs.getParentid() + "','" + bbs.getForumid() +"','" + bbs.getUsername()+"','"+bbs.getTitle()+"','"+bbs.getContent()+"',now())";
            //插入句柄
           System.out.println("mjl9");
           System.out.println(sql);
            DBm.executeUpdate(sql); //执行语句
            if (bbs.getParentid() != 0) 
            { //如果该帖子是回帖
            	DBm.closeDB(); //释放语句句柄和连接句柄
                //将该帖子的父帖子的回复次数增加一次
            	DBm.connectDB(); //获取连接句柄
                String sqll= "update topic set replies=replies+1 where id="
                	          +bbs.getParentid();
                //插入句柄
                DBm.executeUpdate(sqll);
                //执行语句
            }
           
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    
     //删除ID或PARENT_ID为给定值的帖子
     
    public static void delete(int id) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB();
            String sql="delete from topic where id="+id+" or parentid="+id;//插入句柄
            System.out.println(sql);
            DBm.executeUpdate(sql);//执行插入语句
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    //更新帖子操纵
    public static void update(topic bbs) throws Exception {
    	 SqlManger DBm = SqlManger.createInstance();
        try {
            DBm.connectDB();//建立连接
            String sql="update topic set title='"
            	+bbs.getTitle()+"', content='"
            	+bbs.getContent()+"' where id='"
            	+bbs.getId()+"'";
            //插入句柄
            DBm.executeUpdate(sql);//执行语句
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
    }

    //从数组列表中获取帖子信息
    public static ArrayList select(int forum_id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
    	System.out.println("suc");
        ResultSet rs = null;
        ArrayList array = new ArrayList();
        try {
            DBm.connectDB();//建立连接
            String sql="select id, username, title, postdate, replies from topic where parentid=0 and forumid="
            	+forum_id;
            System.out.println(sql);
            rs = DBm.executeQuery(sql);//执行语句
            while (rs.next()) {
                topic bbs = new topic();//未结束时不断创建新对象
                bbs.setId(rs.getInt(1));//插入相关数据
                bbs.setUsername(rs.getString(2));//插入用户名
                bbs.setTitle(rs.getString(3));//插入标题
                bbs.setPostdate(StringUtil.getTimestamp(rs.getTimestamp(4)));//插入时间
                bbs.setReply(rs.getInt(5));//插入回复
                array.add(bbs);//在数组中加入这一条
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
        return array;
    }

    
     //获取指定讨论区的帖子列表
     
    public static ArrayList select(int forum_id, page page) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        ArrayList array = new ArrayList();
        try {
            DBm.connectDB();
            String sql=  "select id, username, title, postdate, replies from topic where parentid=0 and forumid="+forum_id;
            //获取指定讨论区的帖子数量，不包含回复
           
            rs = DBm.executeQuery(sql);
            System.out.println("mjl2");
        //    while(rs.next()) 
       //     {
         //       page.setTotal(rs.getInt(1)); //将帖子数量保存在分页对象中
          //  }
          //  DBm.closeDB(); //释放语句句柄和连接句柄
          //  DBm.connectDB();//建立连接
          //  String sqll=  "select id, username, title, postdate, replies from topic where parentid=0 and forumid="
          //  	+forum_id+" order by id desc limit "
          //  	+page.getIndex()+"','"
           // 	+page.max_row;
            // 按照指定的分页区间获取帖子信息
           
         //   rs = DBm.executeQuery(sqll);//执行语句
            while (rs.next()) 
            {//创建新对象并给其赋值
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
        	 DBm.closeDB(); //释放语句句柄和连接句柄
        }
        return array;
    }

    
     //获取指定帖子以及该帖子的回复
   
    public static ArrayList viewTopic(int bbs_id, page page) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        ArrayList array = new ArrayList();
        try {
            DBm.connectDB();
            String sql="select id, username, title, content, postdate from topic where parentid="
            	+bbs_id+" or id="+bbs_id;
            //使用SQL字符串创建语句句柄，获取指定ID的帖子以及回帖数量
            System.out.println(sql);
            rs = DBm.executeQuery(sql);//执行语句
            System.out.println("mjl4");
        //    if (rs.next()) {
          //      page.setTotal(rs.getInt(1));
                //将帖子数量保存在分页对象中
           // }
           // DBm.closeDB(); //释放语句句柄和连接句柄
            
            //DBm.connectDB();//建立连接
            //String sqll="select id, username, title, content, postdate from topic where parentid="
            	//+bbs_id+"or id="
            	//+bbs_id+"order by id asc limit'"
            	//+page.getIndex()+"','"
            	//+page.max_row;
            //按照指定的分页区间获取指定ID的帖子以及回帖
            
          //  rs = DBm.executeQuery(sqll);//执行语句
            while (rs.next()) 
            {
            	//创建新对象并赋值
                topic bbs = new topic();
                bbs.setId(rs.getInt(1));
                bbs.setUsername(rs.getString(2));
                bbs.setTitle(rs.getString(3));
                bbs.setContent(rs.getString(4));
                bbs.setPostdate(StringUtil.getTimestamp(rs.getTimestamp(5)));
                array.add(bbs);
                //遍历结果集获取帖子信息并保存到数组中
                System.out.println("mjl5");
            }
            DBm.closeDB(); //释放语句句柄和连接句柄
            
            DBm.connectDB();//建立连接
            
            String sqql= "select id, username, title, content, postdate from topic where id="+bbs_id;
            //获取该帖子的标题
           
            rs = DBm.executeQuery(sqql);//执行语句
            
            if (rs.next())
            {
                ( (topic) array.get(0)).setTitle(rs.getString(3));
            }
        }
        catch (Exception es) {
            throw es;
        }
        finally {
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
        return array;
    }

    //获取帖子标题
    public static topic getTopic(int id) throws Exception {
    	SqlManger DBm = SqlManger.createInstance();
        ResultSet rs = null;
        topic ann = null;
        try {
            DBm.connectDB();//建立连接
            String sql="select id, title, content from topic where id ="+id;
           
            rs = DBm.executeQuery(sql);
            
            if (rs.next()) 
            {
            	//当结果集存在时创建新对象并给其赋值
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
        	DBm.closeDB(); //释放语句句柄和连接句柄
        }
        return ann;
    }

}
