package bbs;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


 //�������������û������󣬲�������Ӧ�Ĵ����������ؽ��ҳ��
 
public class ForumServlet
    extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        doGet(request, response);
    }

    
     //�ж������ֲ���
     //1��forum_delete��ɾ��������
     //2��forum_add������������
     //3��forum_update������������
     //4��forum_select����ȡ�������б�
     //5��forum_view����ȡĳһ����������ϸ��Ϣ
     
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        String method = request.getParameter("method");
        //��request�����л�ȡ�������ͣ����������ݲ������͵�����Ӧ�ķ������д���
        if (method == null) {
            return;
        }
        else if (method.equalsIgnoreCase("forum_delete")) {
            forum_delete(request, response);
            //ת��ɾ������������
        }
        else if (method.equalsIgnoreCase("forum_add")) {
            forum_add(request, response);
            //ת����������������
        }
        else if (method.equalsIgnoreCase("forum_update")) {
            forum_update(request, response);
            //ת����������������
        }
        else if (method.equalsIgnoreCase("forum_select")) {
            forum_select(request, response);
            //ת����ȡ�������б����
        }
        else if (method.equalsIgnoreCase("forum_view")) {
            forum_view(request, response);
            //ת����ȡĳһ����������Ϣ����
        }
        else if (method.equalsIgnoreCase("forum_edit")) {
            forum_edit(request, response);
            //ת����ȡĳһ����������Ϣ���뵽�༭ҳ��
        }

    }

    
     //ɾ��������
     //1�����Ҫɾ�������������forum_id
     //2������ForumHandle��delete������ɾ�����Ϊforum_id��������
     //3����������쳣�����쳣��Ϣ���浽request������
     //4����������ɹ������سɹ�ҳ��
     //5���������ʧ�ܣ�������ҳ�棬����ʾ������Ϣ
     
    private void forum_delete(HttpServletRequest request,
                              HttpServletResponse response) {
        //���Ҫɾ�������������forum_id
        String forum_id = (String) request.getParameter("forum_id");
        boolean isSuccess = false;
        try {
            //����ForumHandle��delete������ɾ�����Ϊforum_id��������
            forumhandle.delete(Integer.valueOf(forum_id).intValue());
            isSuccess = true;
        }
        catch (Exception es) {
            es.printStackTrace();
            isSuccess = false;
            request.setAttribute("forum_error", es.getMessage());
        }
        String forward = null;
        if (isSuccess) {
            request.removeAttribute("forum_error");
            forward = "forumservlet?method=forum_select";
        }
        else {
            forward = "index.jsp";
        }
        forward(request, response, forward);
    }

   
     //����������
     //1�����Ҫ���ӵ������������֡�˳��š�����
     //2���ж��Ƿ�Ϊ�ظ��ύ�����Ϊ�ظ��ύ��ֱ�ӷ�����ҳ�棻�������ִ��
     //3������ForumHandle��insert��������������
     //4����������쳣�����쳣��Ϣ���浽request������
     //5����������ɹ���������ҳ��
     //6���������ʧ�ܣ�����forum_add.jsp������ʾ������Ϣ
     
    private void forum_add(HttpServletRequest request,
                           HttpServletResponse response) {
        //���Ҫ���ӵ������������֡�˳��š�����
        String name = (String) request.getParameter("name");
        String sort = (String) request.getParameter("sort");
        String description = (String) request.getParameter("description");
        boolean isSuccess = false;
        String forward = null;
        //�ж��Ƿ�Ϊ�ظ��ύ�����Ϊ�ظ��ύ��ֱ�ӷ�����ҳ�棻�������ִ��
        if (isRedo(request, "forum_add")) {
            forward = "index.jsp";
        }
        else {
            try {
                //�����������װ��ҵ�����
                forum forum = new forum();
                forum.setName(name);
                int seq = Integer.parseInt(sort);
                forum.setSort(seq);
                forum.setDescription(description);
                //����ҵ���߼�����������
                forumhandle.insert(forum);
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                //��������Ϣ������request�����У�����չʾ���õ�
                request.setAttribute("forum_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("forum_error");
                forward = "forumservlet?method=forum_select";
            }
            else {
                forward = "forum_add.jsp";
            }
        }
        forward(request, response, forward);
    }

    
     //��ȡ�������б�
     //1������ҵ���߼���ȡ�������б�
     //2�����������б�����request������
     //3����������쳣�����쳣��Ϣ���浽request������
     //4��������ҳ��
     
    private void forum_select(HttpServletRequest request,
                              HttpServletResponse response) {
        boolean isSuccess = false;
        //�жϴ���ɹ����
        String forward = null;
        try {
            request.setAttribute("forums", forumhandle.select());
            //����ҵ���߼���ȡ�������б������浽request������
            isSuccess = true;
            //����ɹ�
        }
        catch (Exception es) {
            es.printStackTrace();
            isSuccess = false;
            //����ʧ��
            request.setAttribute("forum_error", es.getMessage());
            //��ʧ����Ϣ���浽request��
        }
        if (isSuccess) {
            request.removeAttribute("forum_error");
            //�������ɹ�����request������ɾ��������Ϣ
        }
        forward = "forums.jsp";
        forward(request, response, forward);
        //ת����������ת����ҳ��
    }

    
     //��ȡĳһ������������ϸ��Ϣ
    
    private void forum_view(HttpServletRequest request,
                            HttpServletResponse response) {
        boolean isSuccess = false;
        String forward = null;
        try {
            String id = (String) request.getParameter("forum_id");
            int tid = Integer.parseInt(id);
            request.setAttribute("forum", forumhandle.getForumInfo(tid));
            isSuccess = true;
        }
        catch (Exception es) {
            es.printStackTrace();
            isSuccess = false;
            request.setAttribute("forum_error", es.getMessage());
        }
        if (isSuccess) {
            request.removeAttribute("forum_error");
        }
        forward = "forum_view.jsp";
        forward(request, response, forward);
    }

    
     //��ȡĳһ������������ϸ��Ϣ,��������༭ҳ��
     
    private void forum_edit(HttpServletRequest request,
                            HttpServletResponse response) {
        boolean isSuccess = false;
        String forward = null;
        String id = "";
        try {
            id = (String) request.getParameter("forum_id");
            int tid = Integer.parseInt(id);
            request.setAttribute("forum", forumhandle.getForumInfo(tid));
            isSuccess = true;
        }
        catch (Exception es) {
            es.printStackTrace();
            isSuccess = false;
            request.setAttribute("forum_error", es.getMessage());
        }
        if (isSuccess) {
            request.removeAttribute("forum_error");
        }
        forward = "forum_update.jsp";
        forward(request, response, forward);
    }

    
     //�޸���������Ϣ
     //1�����Ҫ�޸ĵ����������forum_id�Լ���������������Ϣ�����֡�˳��š�����
     //2���ж��Ƿ�Ϊ�ظ��ύ�����Ϊ�ظ��ύ��ֱ�ӷ��سɹ�ҳ�棻�������ִ��
     //3������ForumHandle��update��������������
     //4����������쳣�����쳣��Ϣ���浽request������
     //5����������ɹ������سɹ�ҳ��
     //6���������ʧ�ܣ�����forum_update.jsp������ʾ������Ϣ
     
    private void forum_update(HttpServletRequest request,
                              HttpServletResponse response) {
        //���Ҫ�޸ĵ����������forum_id
        String id = (String) request.getParameter("forum_id");
        //��ȡ��������������Ϣ��
        String name = (String) request.getParameter("name");
        String sort = (String) request.getParameter("sort");
        String description = (String) request.getParameter("description");
        boolean isSuccess = false;
        String forward = null;
        //�ж��Ƿ�Ϊ�ظ��ύ
        if (isRedo(request, "forum_update")) {
            forward = "success.jsp";
        }
        else {
            try {
                //ʹ���޸ĺ����Ϣ��װ����������
                forum forum = new forum();
                forum.setName(name);
                int seq = Integer.parseInt(sort);
                forum.setSort(seq);
                forum.setId(Integer.parseInt(id));
                forum.setDescription(description);
                //����ҵ���߼��޸���������Ϣ
                forumhandle.update(forum);
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                request.setAttribute("forum_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("forum_error");
                forward = "forumservlet?method=forum_select";
            }
            else {
                forward = "forum_update.jsp?forum_id=" + id;
            }
        }
        forward(request, response, forward);
    }

    
     //����������ת��url����ʾ��ҳ��
   
    private void forward(HttpServletRequest request,
                         HttpServletResponse response, String url) {
        try {
            request.getRequestDispatcher(response.encodeURL(url)).
                forward(request, response);
        }
        catch (Exception es) {
            es.printStackTrace();
        }
    }

    
     //�ж��Ƿ�Ϊ�ظ��ύ
     //1�����Session���Ƿ���ָ�����ֵ�����
     //2�����Session��û�и����Ի�������Ϊ�գ�֤���ѱ���������ж�Ϊ�ظ��ύ
     //3������֤���ǵ�һ�δ����������Դ�Session��ɾ����
    
     
    private boolean isRedo(HttpServletRequest request, String key) {
        String value = (String) request.getSession().getAttribute(key);
        if (value == null) {
            return true;
        }
        else {
            request.getSession().removeAttribute(key);
            return false;
        }
    }

}
