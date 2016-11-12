package bbs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;


 //�������������û������󣬲�������Ӧ�Ĵ����������ؽ��ҳ��
 
public class TopicServlet
    extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        doGet(request, response);
    }

    
     //�ж������ֲ���
     //1��topic_delete��ɾ������
     //2��topic_add����������
     //3��answer_add���ظ�����
     //4��answer_delete��ɾ�����ӻظ�
     //5��topic_update����������
     //6��answer_update���������ӻظ�
     //7��topic_select����ȡ�����б�
     //8��topic_view����ȡһ�������Լ������ӵĻظ�
     
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        String method = request.getParameter("method");
        //��request�����л�ȡ�������ͣ����������ݲ������͵�����Ӧ�ķ������д���
        if (method == null) {
            return;
        }
        else if (method.equalsIgnoreCase("topic_select")) {
            topic_select(request, response);
            //��ȡĳһ�������������б�
        }
        else if (method.equalsIgnoreCase("topic_view")) {
            topic_view(request, response);
            //��ȡĳһ�������Լ������ӵĻظ�
        }
        else if (method.equalsIgnoreCase("topic_delete")) {
            topic_delete(request, response);
        }
        else if (method.equalsIgnoreCase("topic_add")) {
            topic_add(request, response);
            //����һ��������
        }
        else if (method.equalsIgnoreCase("answer_add")) {
            answer_add(request, response);
            //�ظ�һ������
        }
        else if (method.equalsIgnoreCase("answer_delete")) {
            answer_delete(request, response);
            //ɾ��һ���ظ�
        }
        else if (method.equalsIgnoreCase("topic_update")) {
            topic_update(request, response);
            //����һ������
        }
        else if (method.equalsIgnoreCase("answer_update")) {
            answer_update(request, response);
            //����һ���ظ�
        }
    }

    
    // ��ȡ�������б�
     //1����ȡ���������forum_id
     //2������TopicHandle��select��������ȡָ���������������б�
     //3����������쳣�����쳣��Ϣ���浽request������
     //4������topics.jspҳ��
    private void topic_select(HttpServletRequest request,
                              HttpServletResponse response) {
        boolean isSuccess = false;
        //����ɹ������
        ArrayList array = null;
        //������������
        String forum_id = request.getParameter("forum_id");
        System.out.println(forum_id);
        System.out.println("success1");
        //��ȡ���������forum_id
        String start_index = request.getParameter("start_index");
        //��ʼҳ��ţ���ҳ�㷨��Ҫ�õ�
        if (start_index == null) {
            start_index = "0";
            //��ʼҳ���Ϊ�գ���ʼ��Ϊ��0������ʾ��һҳ
        }
        page pageInfo = null;
        //��ҳ����
        try {
            pageInfo = (page) request.getAttribute("pageInfo");
            //��request�л�ȡ��ҳ����
            if (pageInfo == null) {
                pageInfo = new page();
                request.setAttribute("pageInfo", pageInfo);
                //�����ҳ����Ϊ�գ��ʹ���һ���µķ�ҳ���󣬲����浽request��
            }
            pageInfo.setIndex(Integer.parseInt(start_index));
            //���÷�ҳ�������ʼҳ
            pageInfo.setUrl("topicservlet?method=topic_select&forum_id="+forum_id);
            //����ҳ�����ֶ�Ӧ��URL����
            System.out.println("success3...");
            array = topichandle.select(Integer.valueOf(forum_id).intValue(),pageInfo);
            //����TopicHandle��select������ȡ�������б�
            isSuccess = true;
            System.out.println("success2....");
        }
        catch (Exception es) {
            isSuccess = false;
            request.setAttribute("topic_error", es.getMessage());
            es.printStackTrace();
            array = new ArrayList();
        }
        if (isSuccess) {
            request.removeAttribute("topic_error");
        }
        request.setAttribute("topics", array);
        //����������浽request������
        String forward = "topics.jsp?forum_id=" + forum_id;
        forward(request, response, forward);
    }

    
     //���������Ϣ
     //1�����Ҫ������ӵ����������forum_id�Լ����Ӻ�bbs_id
     //2������TopicHandle��viewTopic��������ȡ���Ϊbbs_id�������Լ������ӵĻظ�
     //3������topic_view.jspҳ��
     
    private void topic_view(HttpServletRequest request,
                            HttpServletResponse response) {
        String forum_id = request.getParameter("forum_id");
        String bbs_id = request.getParameter("bbs_id");
        System.out.println(bbs_id);
        //��request�����л�ȡ���ӵ����������forum_id�Լ����Ӻ�bbs_id
        String start_index = request.getParameter("start_index");
        //��ʼҳ��ţ���ҳ�㷨��Ҫ�õ�
        if (start_index == null) {
            start_index = "0";
        }
        page pageInfo = null;
        ArrayList array = null;
        try {
            pageInfo = (page) request.getAttribute("pageInfo");
            //��request�л�ȡ��ҳ����
            if (pageInfo == null) {
                pageInfo = new page();
                request.setAttribute("pageInfo", pageInfo);
                //�����ҳ����Ϊ�գ��ʹ���һ���µķ�ҳ���󣬲����浽request��
            }
            pageInfo.setIndex(Integer.parseInt(start_index));
            //���÷�ҳ�������ʼҳ
            pageInfo.setUrl("topicservlet?method=topic_view&bbs_id=" + bbs_id +
                            "&forum_id=" + forum_id);
            //����ҳ�����ֶ�Ӧ��URL����
            int id = Integer.parseInt(bbs_id);
            array = topichandle.viewTopic(id, pageInfo);
            //����ҵ���߼�����󣬻�ȡ�����Լ������ӵ����лظ�
        }
        catch (Exception es) {
            es.printStackTrace();
        }
        request.setAttribute("topic_answer", array);
        //�����������request������
        String forward = "topic_view.jsp?forum_id=" + forum_id + "&bbs_id=" +
            bbs_id;
        forward(request, response, forward);
    }

    
     //ɾ�������Լ������ӵ����лظ�
     //1�����Ҫɾ�������ӵ����������forum_id�Լ����Ӻ�bbs_id
     //2������TopicHandle��delete������ɾ�����Ϊbbs_id�������Լ������ӵĻظ�
     //3����������쳣�����쳣��Ϣ���浽request������
     //4������forums.jspҳ��

    private void topic_delete(HttpServletRequest request,
                              HttpServletResponse response) {
        String bbs_id = (String) request.getParameter("bbs_id");
        String forum_id = (String) request.getParameter("forum_id");
        boolean isSuccess = false;
        try {
            topichandle.delete(Integer.valueOf(bbs_id).intValue());
            isSuccess = true;
        }
        catch (Exception es) {
            es.printStackTrace();
            isSuccess = false;
            request.setAttribute("topic_error", es.getMessage());
        }
        if (isSuccess) {
            request.removeAttribute("topic_error");
        }
        String forward = "topicservlet?method=topic_select&forum_id=" +
            forum_id;
//        forward += "&bbs_id=" + bbs_id;
        forward(request, response, forward);
    }

    
     //��������
     //1�����Ҫ��������ӵ����ݺͱ���
     //2���ж��Ƿ�Ϊ�ظ��ύ�����Ϊ�ظ��ύ��ֱ�ӷ�����ҳ�棻�������ִ��
     //3������TopicHandle��insert������������
     //4����������쳣�����쳣��Ϣ���浽request������
     //5����������ɹ�����������ҳ��
     //6���������ʧ�ܣ�����topic_add.jsp������ʾ������Ϣ
     
    private void topic_add(HttpServletRequest request,
                           HttpServletResponse response) {
        //��ȡ�·������ӵı�������ݡ��Լ���������������ID
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String forum_id = request.getParameter("forum_id");
        
        //��session��ȡ��user����
        user user = (user) request.getSession().getAttribute("user");
        boolean isSuccess = false;
        String forward = null;
        System.out.println(user.getUsername());
        if (isRedo(request, "topic_add")) { //�ж��Ƿ����ظ��ύ
            forward = "topicservlet?method=topic_select&forum_id=" + forum_id;
        }
        else { //��������ظ��ύ������ִ��
            try {
                //ʹ�ò�����װ���Ӷ���
                topic ann = new topic();
                if (title != null) {
                    ann.setTitle(title);
                }
                else {
                    ann.setTitle(""); //�������ӱ���
                }
                ann.setContent(content); //������������
                ann.setParentid(0); //���ڷ�������ӣ������ӱ��Ϊ0
                ann.setForumid(Integer.parseInt(forum_id)); //��������������������
                if (user != null) {
                    ann.setUsername(user.getUsername()); //���÷�����
                }
                else {
                    ann.setUsername("�ο�");
                }
                topichandle.insert(ann); //����ҵ���߼��㷽�������ݿ��в���һ����¼
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                request.setAttribute("topic_add_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("topic_add_error");
                forward = "topicservlet?method=topic_select&forum_id=" +
                    forum_id;
            }
            else {
                forward = "topic_add.jsp?forum_id=" + forum_id;
            }
        }
        forward(request, response, forward);
    }

    
     //����ظ�
     //1�����Ҫ��������ӵ����ݡ������Լ�Ҫ�ظ�������ID
     //2���ж��Ƿ�Ϊ�ظ��ύ�����Ϊ�ظ��ύ��ֱ�ӷ�����ҳ�棻�������ִ��
     //3������TopicHandle��insert�����������ӻظ�
     //4����������쳣�����쳣��Ϣ���浽request������
     //5������topic_view.jsp��ҳ��
     
    private void answer_add(HttpServletRequest request,
                            HttpServletResponse response) {
        String content = request.getParameter("content");
        //��û�������
        String forum_id = request.getParameter("forum_id");
        String parent_id = request.getParameter("parent_id");
        //��ȡ������ID��Ҫ�ظ�������ID
        user user = (user) request.getSession().getAttribute("user");
        //��session��ȡ�û���Ϣ
        boolean isSuccess = false;
        //����ɹ�����־
        String forward = null;
        if (content == null || content.trim().length() < 1) 
        {
            //�����������Ϊ�գ�����
            forward = "topicservlet?method=topic_view&forum_id=" + forum_id +
                "&bbs_id=" + parent_id;
        }
        else if (isRedo(request, "answer_add")) {
            //������ظ��ύ������
            forward = "topicservlet?method=topic_view&forum_id=" + forum_id +
                "&bbs_id=" + parent_id;
        }
        else { //���򣬼���ִ��
            try {
            	System.out.println("mjl8");
                topic ann = new topic();
                ann.setTitle("");
                ann.setContent(content);
                if (parent_id != null) {
                    ann.setParentid(Integer.valueOf(parent_id).intValue());
                    //���ø�����ID
                }
                else {
                    ann.setParentid(0);
                }
                ann.setForumid(Integer.parseInt(forum_id));
                //����������ID
                if (user != null) {
                    ann.setUsername(user.getUsername());
                    //���÷������û���
                }
                else {
                    ann.setUsername("�ο�");
                }
                topichandle.insert(ann);
                //�����ݿ��в���һ����¼
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                request.setAttribute("topic_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("topic_error");
            }
            forward = "topicservlet?method=topic_view&forum_id=" + forum_id +
                "&bbs_id=" +parent_id;
        }
        forward(request, response, forward);
    }

    
     //ɾ���ظ�
     //1�����Ҫɾ�������ӵ�ID
     //2������TopicHandle��delete����ɾ������
     //3������topic_view.jsp
     
    private void answer_delete(HttpServletRequest request,
                               HttpServletResponse response) {
        String bbs_id = (String) request.getParameter("bbs_id");
        String forum_id = (String) request.getParameter("forum_id");
        String parent_id = (String) request.getParameter("parent_id");
        boolean isSuccess = false;
        try {
            topichandle.delete(Integer.valueOf(bbs_id).intValue());
            isSuccess = true;
        }
        catch (Exception es) {
            es.printStackTrace();
            isSuccess = false;
            request.setAttribute("topic_error", es.getMessage());
        }
        if (isSuccess) {
            request.removeAttribute("topic_error");
        }
        String forward = null;
        if (bbs_id.equalsIgnoreCase(parent_id)) {
            forward = "topicservlet?method=topic_select&forum_id=" +
                forum_id;
        }
        else {
            forward = "topicservlet?method=topic_view&forum_id=" +
                forum_id + "&bbs_id=" + parent_id;
        }
        forward(request, response, forward);
    }

   
     //����������Ϣ
     //1����ø��º���������⡢���ݡ�ID
     //2���ж��Ƿ�Ϊ�ظ��ύ�����Ϊ�ظ��ύ��ֱ�ӷ��سɹ�ҳ�棻�������ִ��
     //3������TopicHandle��update������������
     //4����������쳣�����쳣��Ϣ���浽request������
     //5����������ɹ�������board.jspҳ��
     //6���������ʧ�ܣ�����topic_update.jsp������ʾ������Ϣ
     
    private void topic_update(HttpServletRequest request,
                              HttpServletResponse response) {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String bbs_id = request.getParameter("bbs_id");
        String forum_id = request.getParameter("forum_id");
        boolean isSuccess = false;
        String forward = null;
        if (isRedo(request, "topic_update")) {
            forward = "topicservlet?method=topic_select&forum_id=" + forum_id;
        }
        else {
            try {
                topic ann = topichandle.getTopic(Integer.parseInt(bbs_id));
                ann.setTitle(title);
                ann.setContent(content);
                topichandle.update(ann);
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                request.setAttribute("topic_update_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("topic_update_error");
                forward = "topicservlet?method=topic_select&forum_id=" +
                    forum_id;
            }
            else {
                forward = "topic_update.jsp?bbs_id=" + bbs_id + "&forum_id=" +
                    forum_id;
            }
        }
        forward(request, response, forward);
    }

    
     //�������ӻظ�
     //1����ø��º���������⡢����
     //2���ж��Ƿ�Ϊ�ظ��ύ�����Ϊ�ظ��ύ��ֱ�ӷ��سɹ�ҳ�棻�������ִ��
     //3������TopicHandle��update������������
     //4����������쳣�����쳣��Ϣ���浽request������
     //5����������ɹ�������topic_view.jspҳ��
     //6���������ʧ�ܣ�����topic_update.jsp������ʾ������Ϣ
     
    private void answer_update(HttpServletRequest request,
                               HttpServletResponse response) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String bbs_id = request.getParameter("bbs_id");
        String parent_id = request.getParameter("parent_id");
        String forum_id = request.getParameter("forum_id");
        boolean isSuccess = false;

        String forward = null;
        if (isRedo(request, "answer_update")) {
            forward = "topicservlet?method=topic_view&forum_id=" + forum_id +
                "&bbs_id=" +
                parent_id;
        }
        else {
            try {
                topic ann = topichandle.getTopic(Integer.parseInt(bbs_id));
                if (bbs_id.equalsIgnoreCase(parent_id)) {
                    ann.setTitle(title);
                }
                ann.setContent(content);
                topichandle.update(ann);
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                request.setAttribute("topic_update_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("topic_update_error");
                forward = "topicservlet?method=topic_view&forum_id=" + forum_id +
                    "&bbs_id=" +
                    parent_id;
            }
            else {
                forward = "topic_update.jsp?bbs_id=" + bbs_id + "&forum_id=" +
                    forum_id + "&parent_id=" + parent_id;
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

   
    // �ж��Ƿ�Ϊ�ظ��ύ
    // 1�����Session���Ƿ���ָ�����ֵ�����
    // 2�����Session��û�и����Ի�������Ϊ�գ�֤���ѱ���������ж�Ϊ�ظ��ύ
    // 3������֤���ǵ�һ�δ����������Դ�Session��ɾ����
     
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
