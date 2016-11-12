package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;

/*数据库连接管理器，用于实现数据库操作的封装*/
public class SqlManger {
	private static SqlManger p = null;			//静态成员，支持单态模式
	private PropertyResourceBundle bundle;		//配置资源文件
	private static String jdbcDriver = null;	//jdbc驱动类型	
	private static String split = null;			//文件分割符
	private String DBType = null;				//数据库类型
	private String DBhost = "localhost";		//数据库主机地址
	private String DBname = "";					//数据库名
	private String DBport = "";					//数据库端口
	private String DBuser = "";					//数据库用户名
	private String DBpasswd = "";				//数据库密码
	
	private Connection Sqlconn = null;
	private Statement Sqlstmt = null;
	private String strCon = null;
												//私有构造函数
	private SqlManger(){
		try{
			bundle = new PropertyResourceBundle(	//读取配置文件
					SqlManger.class.getResourceAsStream("./sysConfig.properties"));
			this.DBhost = getString("DBhost");		//读取数据库主机
			this.DBname = getString("DBname");		//读取数据库名
			this.DBport = getString("DBport");		//读取数据库端口
			this.DBuser = getString("DBuser");		//读取数据库用户名
			this.DBpasswd = getString("DBpasswd");	//读取数据库密码
			
			String system_type = getString("system-type");//读取操作系统类型
			
			if(system_type != null){				//根据操作系统配置文件分割符
				if(system_type.toLowerCase().equals("windows")){
					split = ";";
				}
				else{
					split = ":";
				}
			}
			String database_type = getString("database-type");
			this.DBType = database_type;
			if(database_type.toLowerCase().equals("mysql")){
				jdbcDriver = "com.mysql.jdbc.Driver";
				strCon = "jdbc:mysql://" + DBhost + ":" + DBport + "/" + DBname + "?useUnicode=true&characterEncoding=utf8";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String getString(String s){			//读取资源文件中的字符串
		return this.bundle.getString(s);
	}
	
	public static SqlManger createInstance(){	//单态模式获得实例
		if(p == null){
			p = new SqlManger();
			p.initDB();
		}
		return p;
	}
	
	public void initDB(){						//初始化连接参数
		System.out.println(strCon);
		System.out.println(jdbcDriver);
		try{
			Class.forName(jdbcDriver);			//装载驱动
		}catch(Exception ex){
			System.err.println("Can't find Database Driver.");
		}
	}
	
	public void connectDB(){					//建立数据库连接
		try{
	//		System.out.println("SqlManger: connecting to database..");
			Sqlconn = DriverManager.getConnection(strCon, DBuser, DBpasswd);//获取连接
			Sqlstmt = Sqlconn.createStatement();//创建查询
		}catch(SQLException ex){
			System.err.println("connectDB" + ex.getMessage());
		}
	}
	
	public void closeDB() {
		try{
	//		System.out.println("SqlManger:close connection to database ...");
			Sqlstmt.close();//关闭查询
			Sqlconn.close();//关闭连接
		}catch(SQLException ex){
			System.err.println("closeDB" + ex.getMessage());
		}
	//	System.out.println("SqlManger:Close connection successful.");
	}
	
	//执行服务返回数据的数据查询，返回值是被改变的数据项数
	public int executeUpdate(String sql) {
		int ret = 0;
		try{
			ret = Sqlstmt.executeUpdate(sql);
		}catch(SQLException ex){
			System.err.println("executeUpdate:" + ex.getMessage());
		}
		return ret;
	}
	//查询数据库的操作接口
	public ResultSet executeQuery(String sql){
		ResultSet rs = null;
		try{
			Statement st = Sqlconn.createStatement();//创建查询;
			rs = st.executeQuery(sql);
		}catch(SQLException ex){
			System.err.println("executeQuery:" + ex.getMessage());
		}
		return rs;
	}
	
	public static void main(String[] args){
		SqlManger.createInstance().connectDB();
		SqlManger.createInstance().closeDB();
	}
	
}