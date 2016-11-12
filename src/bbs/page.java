package bbs;

public class page {
	public static int max_row=15;
	//һ����ʾ����¼��
	public int index;
	//��ǰ��ʼλ��
	public int page_num;
	//�������ܵ�ҳ��
	public int total;
	//��������ѯ�����С
	public String url;
	//����ʱ������
	
	//���캯��
	public page()
	{
		index=0;//���ó�ʼλ��
		page_num=1;//�趨��������ҳ��
		total=0; //�趨��ѯ���ֵ
	}
	
	public int getTotal()
	{
		return total;
	}
	//��ȡ����ֵ
	public int getIndex()
	{
		return index;
	}
	//��ȡ��ʼֵ
	public int getPagenum()
	{
		return page_num;
	}
	//��ȡ������ҳ��
	
	//�����趨���ؽ��
	public void setTotal(int num)
	{
		total=num;   //��ȷ������ֵ����
		if(total % max_row ==0 )//�жϷ���ֵ��ҳ���Ĺ�ϵ
		{
			page_num=total/max_row;
			//�����������ҳ��ֱ�ӿ���ȡ��
		}
		else
		{
			page_num=total/max_row+1;
			//�������������һ
		}
	}
	
	//�������ı��в���ҳ����Ϊnum�ı��
	private void inserttag(StringBuffer text, int num)
	{
		int temp=index/max_row;
		//�趨��ʱ�����������洢����ҳ��λ��
		if(temp==num)
		{
            text.append(num + 1).append("  ");
		}
		//���������ʱ�����ı�������
		else
		{
			text.append("<a href=" + url + "&start_index=");
            text.append(num * max_row).append(">").append(num + 1).append("</a>  ");
		}
		//���߲���ʱ����������
	}
	
	//���ı��в���λ��Ϊ��ʼλ��Ϊnum,ҳ����Ϊstr�ı��
	private void inserttag(StringBuffer text, int num, String str)
	{
		text.append("<a href=" + url + "&start_index=");
		//�趨��������
        text.append(num).append(">").append(str).append("</a>  ");
	}
	
	public String getUrl()
	{
		return url;
	}
	//��ȡ����·��
	
	public void setUrl(String url)
	{
		this.url=url;
	}
	//�趨����·��ֵ
	
	public void setIndex(int index)
	{
		this.index=index;
	}
	//�趨��ʼλ�õ�ֵ
	
	//���溯����ȡҳ��ֵ
    public String getFooter()
    {
        if (total <= max_row) 
        {
            return "";
        }
        //�������ֵ����������¼�������ؿ�
        
        StringBuffer str = new StringBuffer("��ҳ��");
        //�趨�±����洢��ʾ����
        
        //��������ҳ��С������¼��
        if (page_num <= max_row) 
        {
            for (int i = 0; i < page_num; i++) 
            {
                inserttag(str, i);
                //��ÿһҳ�������str
            }
            if (index + max_row < total) 
            {
                inserttag(str, index + max_row, "��һҳ");
                //������һҳ��ƪ��ʱ������һҳ���
            }
            else 
            {
                str.append("��һҳ");
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
                    //����ǰ��ļ�¼���в���ֵ
                }
                String tt = "��" + max_row + "ҳ";
                inserttag(str, i * max_row, tt);
                return str.toString();
            }
            //�������м�����10ҳ������
            else 
            { 
                //page��ʾҳ��section��ʾ���ҳ��ɵ���
                //��ǰҳ�����ڵ�ҳ�š�
                int currentPage = index / max_row;
                //��ǰҳ���ڵ���
                int currentSection = currentPage / max_row + 1;
                //��һ��Ŀ�ʼҳ
                int lastPage = (currentSection - 1) * max_row - 1;
                //��һ��Ŀ�ʼҳ�Ŀ�ʼ��ı��
                int lastPageRow = lastPage * max_row;
                //��һ��Ŀ�ʼҳ���
                int nextPage = currentSection * max_row;
                //��һ��Ŀ�ʼҳ�Ŀ�ʼ��ı��
                int nextPageRow = nextPage * max_row;
                String tt = "��" + max_row + "ҳ";
                inserttag(str, lastPageRow, tt);
                //���������һ��
                if (nextPageRow <= total - 1) 
                {
                    for (int i = 0; i < max_row; i++) 
                    {
                        inserttag(str, i + lastPage + 1);
                    }
                    tt = "��" + max_row + "ҳ";
                    inserttag(str, nextPageRow, tt);
                }
                //���û����һ��
                else 
                { 

                    //��ǰ���ҳ������
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
