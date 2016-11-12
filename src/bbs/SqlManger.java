package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;

/*���ݿ����ӹ�����������ʵ�����ݿ�����ķ�װ*/
public class SqlManger {
	private static SqlManger p = null;			//��̬��Ա��֧�ֵ�̬ģʽ
	private PropertyResourceBundle bundle;		//������Դ�ļ�
	private static String jdbcDriver = null;	//jdbc��������	
	private static String split = null;			//�ļ��ָ��
	private String DBType = null;				//���ݿ�����
	private String DBhost = "localhost";		//���ݿ�������ַ
	private String DBname = "";					//���ݿ���
	private String DBport = "";					//���ݿ�˿�
	private String DBuser = "";					//���ݿ��û���
	private String DBpasswd = "";				//���ݿ�����
	
	private Connection Sqlconn = null;
	private Statement Sqlstmt = null;
	private String strCon = null;
												//˽�й��캯��
	private SqlManger(){
		try{
			bundle = new PropertyResourceBundle(	//��ȡ�����ļ�
					SqlManger.class.getResourceAsStream("./sysConfig.properties"));
			this.DBhost = getString("DBhost");		//��ȡ���ݿ�����
			this.DBname = getString("DBname");		//��ȡ���ݿ���
			this.DBport = getString("DBport");		//��ȡ���ݿ�˿�
			this.DBuser = getString("DBuser");		//��ȡ���ݿ��û���
			this.DBpasswd = getString("DBpasswd");	//��ȡ���ݿ�����
			
			String system_type = getString("system-type");//��ȡ����ϵͳ����
			
			if(system_type != null){				//���ݲ���ϵͳ�����ļ��ָ��
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
	
	private String getString(String s){			//��ȡ��Դ�ļ��е��ַ���
		return this.bundle.getString(s);
	}
	
	public static SqlManger createInstance(){	//��̬ģʽ���ʵ��
		if(p == null){
			p = new SqlManger();
			p.initDB();
		}
		return p;
	}
	
	public void initDB(){						//��ʼ�����Ӳ���
		System.out.println(strCon);
		System.out.println(jdbcDriver);
		try{
			Class.forName(jdbcDriver);			//װ������
		}catch(Exception ex){
			System.err.println("Can't find Database Driver.");
		}
	}
	
	public void connectDB(){					//�������ݿ�����
		try{
	//		System.out.println("SqlManger: connecting to database..");
			Sqlconn = DriverManager.getConnection(strCon, DBuser, DBpasswd);//��ȡ����
			Sqlstmt = Sqlconn.createStatement();//������ѯ
		}catch(SQLException ex){
			System.err.println("connectDB" + ex.getMessage());
		}
	}
	
	public void closeDB() {
		try{
	//		System.out.println("SqlManger:close connection to database ...");
			Sqlstmt.close();//�رղ�ѯ
			Sqlconn.close();//�ر�����
		}catch(SQLException ex){
			System.err.println("closeDB" + ex.getMessage());
		}
	//	System.out.println("SqlManger:Close connection successful.");
	}
	
	//ִ�з��񷵻����ݵ����ݲ�ѯ������ֵ�Ǳ��ı����������
	public int executeUpdate(String sql) {
		int ret = 0;
		try{
			ret = Sqlstmt.executeUpdate(sql);
		}catch(SQLException ex){
			System.err.println("executeUpdate:" + ex.getMessage());
		}
		return ret;
	}
	//��ѯ���ݿ�Ĳ����ӿ�
	public ResultSet executeQuery(String sql){
		ResultSet rs = null;
		try{
			Statement st = Sqlconn.createStatement();//������ѯ;
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