package bbs;

public class page {
	public static int max_row=15;
	//一行显示最大记录数
	public int index;
	//当前开始位置
	public int page_num;
	//讨论区总的页数
	public int total;
	//讨论区查询结果大小
	public String url;
	//返回时的链接
	
	//构造函数
	public page()
	{
		index=0;//设置初始位置
		page_num=1;//设定讨论区总页数
		total=0; //设定查询结果值
	}
	
	public int getTotal()
	{
		return total;
	}
	//获取返回值
	public int getIndex()
	{
		return index;
	}
	//获取起始值
	public int getPagenum()
	{
		return page_num;
	}
	//获取讨论区页数
	
	//下面设定返回结果
	public void setTotal(int num)
	{
		total=num;   //先确定返回值个数
		if(total % max_row ==0 )//判断返回值和页数的关系
		{
			page_num=total/max_row;
			//如果被整除，页数直接可以取商
		}
		else
		{
			page_num=total/max_row+1;
			//不被整除，多加一
		}
	}
	
	//下面在文本中插入页面编号为num的标记
	private void inserttag(StringBuffer text, int num)
	{
		int temp=index/max_row;
		//设定临时变量，用来存储所在页面位置
		if(temp==num)
		{
            text.append(num + 1).append("  ");
		}
		//当两者相等时，给文本加入标记
		else
		{
			text.append("<a href=" + url + "&start_index=");
            text.append(num * max_row).append(">").append(num + 1).append("</a>  ");
		}
		//两者不等时，设置链接
	}
	
	//在文本中插入位置为起始位置为num,页面标号为str的标记
	private void inserttag(StringBuffer text, int num, String str)
	{
		text.append("<a href=" + url + "&start_index=");
		//设定插入链接
        text.append(num).append(">").append(str).append("</a>  ");
	}
	
	public String getUrl()
	{
		return url;
	}
	//获取帖子路径
	
	public void setUrl(String url)
	{
		this.url=url;
	}
	//设定帖子路径值
	
	public void setIndex(int index)
	{
		this.index=index;
	}
	//设定开始位置的值
	
	//下面函数获取页脚值
    public String getFooter()
    {
        if (total <= max_row) 
        {
            return "";
        }
        //如果返回值不大于最大记录数，返回空
        
        StringBuffer str = new StringBuffer("分页：");
        //设定新变量存储显示名称
        
        //当讨论区页数小于最大记录数
        if (page_num <= max_row) 
        {
            for (int i = 0; i < page_num; i++) 
            {
                inserttag(str, i);
                //在每一页上面插入str
            }
            if (index + max_row < total) 
            {
                inserttag(str, index + max_row, "下一页");
                //当超过一页的篇幅时插入下一页标记
            }
            else 
            {
                str.append("下一页");
            }
            return str.toString();
        }
        else 
        {
            int unit = max_row * max_row;
            if (index < unit) 
            {
                int i;
                for (i = 0; i < max_row; i++) 
                {
                    inserttag(str, i);
                    //现在前面的记录数中插入值
                }
                String tt = "下" + max_row + "页";
                inserttag(str, i * max_row, tt);
                return str.toString();
            }
            //处理在中间或最后10页的问题
            else 
            { 
                //page表示页；section表示多个页组成的面
                //当前页面所在的页号。
                int currentPage = index / max_row;
                //当前页所在的面
                int currentSection = currentPage / max_row + 1;
                //上一面的开始页
                int lastPage = (currentSection - 1) * max_row - 1;
                //上一面的开始页的开始项的编号
                int lastPageRow = lastPage * max_row;
                //下一面的开始页编号
                int nextPage = currentSection * max_row;
                //下一面的开始页的开始项的编号
                int nextPageRow = nextPage * max_row;
                String tt = "上" + max_row + "页";
                inserttag(str, lastPageRow, tt);
                //如果还有下一面
                if (nextPageRow <= total - 1) 
                {
                    for (int i = 0; i < max_row; i++) 
                    {
                        inserttag(str, i + lastPage + 1);
                    }
                    tt = "下" + max_row + "页";
                    inserttag(str, nextPageRow, tt);
                }
                //如果没有下一面
                else 
                { 

                    //当前面的页面总数
                    int lengthOfPage = total - (lastPage + 1) * max_row;
                    lengthOfPage = lengthOfPage / max_row + 1;

                    for (int i = 0; i < lengthOfPage; i++) 
                    {
                        inserttag(str, i + 1 + lastPage);
                    }
                }
                return str.toString();

            }
        }
    }
	
	
}
