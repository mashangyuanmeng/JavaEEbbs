package bbs;
/* *
 创建讨论区对象
 */
public class forum {
	private int id;
	//讨论区ID号
	
	private int sort;
	//讨论区在列表中的序列号
	
	private String name;
	//讨论区的名字
	
	private String description;
	//讨论区的描述信息 
	
	public forum()
	{
	
	}
	//构造函数调用
	
	public String getDescription()
	{
		return description;
	}
	//获取讨论区的描述信息
	
	public int getId()
	{
		return id;
	}
	//获取讨论区的ID号
	
	public String getName()
	{
		return name;
	}
	//获取讨论区的名称
	
	public int getSort()
	{
		return sort;
	}
	//获取讨论区的顺序
	
	public void setDescription(String description)
	{
		this.description=description;
	}
	//设置讨论区的描述信息
	
	public void setId(int id)
	{
		this.id=id;
	}
	//设置讨论区的ID号
	
	public void setName(String name)
	{
		this.name=name;
	}
	//设置讨论区的名称
	
	public void setSort(int sort)
	{
		this.sort=sort;
	}
	//设置讨论区的顺序号
	

}
