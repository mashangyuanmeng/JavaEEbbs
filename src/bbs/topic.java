package bbs;

import java.sql.Date;
import java.text.DateFormat;

public class topic {
	private int id;
	//帖子编号
	private int parentid;
	//帖子的父帖子编号
	private int forumid;
	//帖子所在讨论区编号
	private String username;
	//发帖人姓名
	private String title;
	//帖子主题
	private String content;
	//帖子内容
	private String postdate;
	//帖子发表时间
	private int reply;
	//帖子回复次数
	
	public topic()
	{
		
	}
	//构造函数
	public String getContent()
	{
		return content;
	}
	//获取帖子内容
	public int getForumid()
	{
		return forumid;
	}
	//获取讨论区ID号
	public int getId()
	{
		return id;
	}
	//获取帖子ID编号
	public int getParentid()
	{
		return parentid;
	}
	//获取父帖子编号
	public String getPostdate()
	{
	   return postdate;	
	}
	//获取帖子发表日期
	public int getReply()
	{
		return reply;
	}
	//获取帖子回复次数
	public String getTitle()
	{
		return title;
	}
	//获取帖子主题
	public String getUsername()
	{
		return username;
	}
	//获取发帖人姓名
	public void setContent(String content)
	{
		this.content=content;
	}
	//设定帖子内容
	public void setForumid(int forumid)
	{
		this.forumid=forumid;
	}
	//设定帖子所在讨论区编号
	public void setId(int id)
	{
		this.id=id;
	}
	//设定帖子ID编号
	public void setParentid(int parentid)
	{
		this.parentid=parentid;
	}
	//设定父帖子编号
	public  void setPostdate(String postdate)
	{
		this.postdate=postdate;
	}
	//设定帖子发表时间
	public void setTitle(String title)
	{
		this.title=title;
	}
	//设定帖子主题
	public void setUsername(String username)
	{
		this.username=username;
	}
	//设定发帖人姓名
	public void setReply(int reply)
	{
		this.reply=reply;
	}
	//设定帖子回复次数

}
