package bbs;

import java.sql.Timestamp;

public class user {
	
	public final static int superadmin=1;
	//设定超级管理员
	
	public final static int general=0;
	//设定一般用户
	private int id; //ID编号
	private String username;//用户名称
	private String password;//账号密码
	private String email;//邮箱地址
	private String nickname;//用户昵称
	
	private Timestamp registerdate;//注册日期
	private int degree;//用户等级
	
	public user()
	{
		
	}
	//构造函数
    public user(String username, String password, String email, String nickname) 
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.degree = general;
    }
    //普通用户的构造函数
    
    public int getDegree()
    {
       return degree;	
    }
    //获取用户的等级
    
    public String getEmail()
    {
    	return email;
    }
    //获取用户的邮箱地址
    
    public String getNickname()
    {
    	return nickname;
    }
	//获取用户的昵称
    
    public String getPassword()
    {
    	return password;
    }
    //获取用户的密码
    
    public Timestamp getRegisterdate()
    {
    	return registerdate;
    }
    //获取用户注册日期
    
    public String getUsername() 
    {
        return username;
    }
    //获取用户名称

    public void setDegree(int degree) 
    {
        this.degree = degree;
    }
    //设定用户级别

    public void setEmail(String email) 
    {
        this.email = email;
    }
    //设定用户邮箱地址

    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }
    //设定用户昵称

    public void setPassword(String password) 
    {
        this.password = password;
    }
    //设定用户密码

    public void setRegisterdate(Timestamp registerdate) 
    {
        this.registerdate = registerdate;
    }
    //设定注册日期

    public void setUsername(String username) 
    {
        this.username = username;
    }
    //设定用户名称
    public int getId() 
    {
    	return id;
    }
    //获取账号ID
    public void setId(int id) 
    {
    	this.id = id;
    }
    //设定ID编号

}
