package bbs;

import java.sql.Timestamp;

public class user {
	
	public final static int superadmin=1;
	//�趨��������Ա
	
	public final static int general=0;
	//�趨һ���û�
	private int id; //ID���
	private String username;//�û�����
	private String password;//�˺�����
	private String email;//�����ַ
	private String nickname;//�û��ǳ�
	
	private Timestamp registerdate;//ע������
	private int degree;//�û��ȼ�
	
	public user()
	{
		
	}
	//���캯��
    public user(String username, String password, String email, String nickname) 
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.degree = general;
    }
    //��ͨ�û��Ĺ��캯��
    
    public int getDegree()
    {
       return degree;	
    }
    //��ȡ�û��ĵȼ�
    
    public String getEmail()
    {
    	return email;
    }
    //��ȡ�û��������ַ
    
    public String getNickname()
    {
    	return nickname;
    }
	//��ȡ�û����ǳ�
    
    public String getPassword()
    {
    	return password;
    }
    //��ȡ�û�������
    
    public Timestamp getRegisterdate()
    {
    	return registerdate;
    }
    //��ȡ�û�ע������
    
    public String getUsername() 
    {
        return username;
    }
    //��ȡ�û�����

    public void setDegree(int degree) 
    {
        this.degree = degree;
    }
    //�趨�û�����

    public void setEmail(String email) 
    {
        this.email = email;
    }
    //�趨�û������ַ

    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }
    //�趨�û��ǳ�

    public void setPassword(String password) 
    {
        this.password = password;
    }
    //�趨�û�����

    public void setRegisterdate(Timestamp registerdate) 
    {
        this.registerdate = registerdate;
    }
    //�趨ע������

    public void setUsername(String username) 
    {
        this.username = username;
    }
    //�趨�û�����
    public int getId() 
    {
    	return id;
    }
    //��ȡ�˺�ID
    public void setId(int id) 
    {
    	this.id = id;
    }
    //�趨ID���

}
