package bbs;
/* *
 ��������������
 */
public class forum {
	private int id;
	//������ID��
	
	private int sort;
	//���������б��е����к�
	
	private String name;
	//������������
	
	private String description;
	//��������������Ϣ 
	
	public forum()
	{
	
	}
	//���캯������
	
	public String getDescription()
	{
		return description;
	}
	//��ȡ��������������Ϣ
	
	public int getId()
	{
		return id;
	}
	//��ȡ��������ID��
	
	public String getName()
	{
		return name;
	}
	//��ȡ������������
	
	public int getSort()
	{
		return sort;
	}
	//��ȡ��������˳��
	
	public void setDescription(String description)
	{
		this.description=description;
	}
	//������������������Ϣ
	
	public void setId(int id)
	{
		this.id=id;
	}
	//������������ID��
	
	public void setName(String name)
	{
		this.name=name;
	}
	//����������������
	
	public void setSort(int sort)
	{
		this.sort=sort;
	}
	//������������˳���
	

}
