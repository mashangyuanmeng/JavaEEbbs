package bbs;

import java.sql.Date;
import java.text.DateFormat;

public class topic {
	private int id;
	//���ӱ��
	private int parentid;
	//���ӵĸ����ӱ��
	private int forumid;
	//�����������������
	private String username;
	//����������
	private String title;
	//��������
	private String content;
	//��������
	private String postdate;
	//���ӷ���ʱ��
	private int reply;
	//���ӻظ�����
	
	public topic()
	{
		
	}
	//���캯��
	public String getContent()
	{
		return content;
	}
	//��ȡ��������
	public int getForumid()
	{
		return forumid;
	}
	//��ȡ������ID��
	public int getId()
	{
		return id;
	}
	//��ȡ����ID���
	public int getParentid()
	{
		return parentid;
	}
	//��ȡ�����ӱ��
	public String getPostdate()
	{
	   return postdate;	
	}
	//��ȡ���ӷ�������
	public int getReply()
	{
		return reply;
	}
	//��ȡ���ӻظ�����
	public String getTitle()
	{
		return title;
	}
	//��ȡ��������
	public String getUsername()
	{
		return username;
	}
	//��ȡ����������
	public void setContent(String content)
	{
		this.content=content;
	}
	//�趨��������
	public void setForumid(int forumid)
	{
		this.forumid=forumid;
	}
	//�趨�����������������
	public void setId(int id)
	{
		this.id=id;
	}
	//�趨����ID���
	public void setParentid(int parentid)
	{
		this.parentid=parentid;
	}
	//�趨�����ӱ��
	public  void setPostdate(String postdate)
	{
		this.postdate=postdate;
	}
	//�趨���ӷ���ʱ��
	public void setTitle(String title)
	{
		this.title=title;
	}
	//�趨��������
	public void setUsername(String username)
	{
		this.username=username;
	}
	//�趨����������
	public void setReply(int reply)
	{
		this.reply=reply;
	}
	//�趨���ӻظ�����

}
