package bbs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;


 //控制器，接受用户的请求，并调用相应的处理方法，返回结果页面
 
public class TopicServlet
    extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        doGet(request, response);
    }

    
     //判断是哪种操作
     //1，topic_delete：删除贴子
     //2，topic_add：发表新贴
     //3，answer_add：回复贴子
     //4，answer_delete：删除贴子回复
     //5，topic_update：更新贴子
     //6，answer_update：更新贴子回复
     //7，topic_select：获取帖子列表
     //8，topic_view：获取一个帖子以及该帖子的回复
     
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        String method = request.getParameter("method");
        //从request对象中获取操作类型，后面代码根据操作类型调用相应的方法进行处理
        if (method == null) {
            return;
        }
        else if (method.equalsIgnoreCase("topic_select")) {
            topic_select(request, response);
            //获取某一讨论区的帖子列表
        }
        else if (method.equalsIgnoreCase("topic_view")) {
            topic_view(request, response);
            //获取某一个帖子以及该帖子的回复
        }
        else if (method.equalsIgnoreCase("topic_delete")) {
            topic_delete(request, response);
        }
        else if (method.equalsIgnoreCase("topic_add")) {
            topic_add(request, response);
            //创建一个新帖子
        }
        else if (method.equalsIgnoreCase("answer_add")) {
            answer_add(request, response);
            //回复一个帖子
        }
        else if (method.equalsIgnoreCase("answer_delete")) {
            answer_delete(request, response);
            //删除一个回复
        }
        else if (method.equalsIgnoreCase("topic_update")) {
            topic_update(request, response);
            //更新一个帖子
        }
        else if (method.equalsIgnoreCase("answer_update")) {
            answer_update(request, response);
            //更新一个回复
        }
    }

    
    // 获取讨论区列表
     //1，获取讨论区编号forum_id
     //2，调用TopicHandle的select方法，获取指定讨论区的帖子列表
     //3，如果出现异常，将异常信息保存到request对象中
     //4，返回topics.jsp页面
    private void topic_select(HttpServletRequest request,
                              HttpServletResponse response) {
        boolean isSuccess = false;
        //处理成功与否标记
        ArrayList array = null;
        //用来保存结果集
        String forum_id = request.getParameter("forum_id");
        System.out.println(forum_id);
        System.out.println("success1");
        //获取讨论区编号forum_id
        String start_index = request.getParameter("start_index");
        //起始页编号，分页算法中要用到
        if (start_index == null) {
            start_index = "0";
            //起始页编号为空，初始化为“0”，表示第一页
        }
        page pageInfo = null;
        //分页对象
        try {
            pageInfo = (page) request.getAttribute("pageInfo");
            //从request中获取分页对象
            if (pageInfo == null) {
                pageInfo = new page();
                request.setAttribute("pageInfo", pageInfo);
                //如果分页对象为空，就创建一个新的分页对象，并保存到request中
            }
            pageInfo.setIndex(Integer.parseInt(start_index));
            //设置分页对象的起始页
            pageInfo.setUrl("topicservlet?method=topic_select&forum_id="+forum_id);
            //设置页面数字对应的URL链接
            System.out.println("success3...");
            array = topichandle.select(Integer.valueOf(forum_id).intValue(),pageInfo);
            //调用TopicHandle的select方法获取讨论区列表
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
        //将结果集保存到request对象中
        String forward = "topics.jsp?forum_id=" + forum_id;
        forward(request, response, forward);
    }

    
     //浏览帖子信息
     //1，获得要浏览贴子的讨论区编号forum_id以及贴子号bbs_id
     //2，调用TopicHandle的viewTopic方法，获取编号为bbs_id的贴子以及该贴子的回复
     //3，返回topic_view.jsp页面
     
    private void topic_view(HttpServletRequest request,
                            HttpServletResponse response) {
        String forum_id = request.getParameter("forum_id");
        String bbs_id = request.getParameter("bbs_id");
        System.out.println(bbs_id);
        //从request对象中获取贴子的讨论区编号forum_id以及贴子号bbs_id
        String start_index = request.getParameter("start_index");
        //起始页编号，分页算法中要用到
        if (start_index == null) {
            start_index = "0";
        }
        page pageInfo = null;
        ArrayList array = null;
        try {
            pageInfo = (page) request.getAttribute("pageInfo");
            //从request中获取分页对象
            if (pageInfo == null) {
                pageInfo = new page();
                request.setAttribute("pageInfo", pageInfo);
                //如果分页对象为空，就创建一个新的分页对象，并保存到request中
            }
            pageInfo.setIndex(Integer.parseInt(start_index));
            //设置分页对象的起始页
            pageInfo.setUrl("topicservlet?method=topic_view&bbs_id=" + bbs_id +
                            "&forum_id=" + forum_id);
            //设置页面数字对应的URL链接
            int id = Integer.parseInt(bbs_id);
            array = topichandle.viewTopic(id, pageInfo);
            //调用业务逻辑层对象，获取帖子以及该帖子的所有回复
        }
        catch (Exception es) {
            es.printStackTrace();
        }
        request.setAttribute("topic_answer", array);
        //将结果保存在request对象中
        String forward = "topic_view.jsp?forum_id=" + forum_id + "&bbs_id=" +
            bbs_id;
        forward(request, response, forward);
    }

    
     //删除贴子以及该贴子的所有回复
     //1，获得要删除的贴子的讨论区编号forum_id以及贴子号bbs_id
     //2，调用TopicHandle的delete方法，删除编号为bbs_id的贴子以及该贴子的回复
     //3，如果出现异常，将异常信息保存到request对象中
     //4，返回forums.jsp页面

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

    
     //发表贴子
     //1，获得要发表的贴子的内容和标题
     //2，判断是否为重复提交，如果为重复提交，直接返回主页面；否则继续执行
     //3，调用TopicHandle的insert方法增加贴子
     //4，如果出现异常，将异常信息保存到request对象中
     //5，如果操作成功，返回帖子页面
     //6，如果操作失败，返回topic_add.jsp，并提示出错信息
     
    private void topic_add(HttpServletRequest request,
                           HttpServletResponse response) {
        //获取新发表帖子的标题和内容、以及帖子所属讨论区ID
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String forum_id = request.getParameter("forum_id");
        
        //从session中取出user对象
        user user = (user) request.getSession().getAttribute("user");
        boolean isSuccess = false;
        String forward = null;
        System.out.println(user.getUsername());
        if (isRedo(request, "topic_add")) { //判断是否是重复提交
            forward = "topicservlet?method=topic_select&forum_id=" + forum_id;
        }
        else { //如果不是重复提交，继续执行
            try {
                //使用参数封装帖子对象
                topic ann = new topic();
                if (title != null) {
                    ann.setTitle(title);
                }
                else {
                    ann.setTitle(""); //设置帖子标题
                }
                ann.setContent(content); //设置帖子内容
                ann.setParentid(0); //对于发表的帖子，父帖子编号为0
                ann.setForumid(Integer.parseInt(forum_id)); //设置帖子所属的讨论区
                if (user != null) {
                    ann.setUsername(user.getUsername()); //设置发帖人
                }
                else {
                    ann.setUsername("游客");
                }
                topichandle.insert(ann); //调用业务逻辑层方法往数据库中插入一条记录
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

    
     //发表回复
     //1，获得要发表的贴子的内容、主题以及要回复的贴子ID
     //2，判断是否为重复提交，如果为重复提交，直接返回主页面；否则继续执行
     //3，调用TopicHandle的insert方法增加贴子回复
     //4，如果出现异常，将异常信息保存到request对象中
     //5，返回topic_view.jsp主页面
     
    private void answer_add(HttpServletRequest request,
                            HttpServletResponse response) {
        String content = request.getParameter("content");
        //获得回帖内容
        String forum_id = request.getParameter("forum_id");
        String parent_id = request.getParameter("parent_id");
        //获取讨论区ID和要回复的帖子ID
        user user = (user) request.getSession().getAttribute("user");
        //从session获取用户信息
        boolean isSuccess = false;
        //处理成功与否标志
        String forward = null;
        if (content == null || content.trim().length() < 1) 
        {
            //如果回帖内容为空，返回
            forward = "topicservlet?method=topic_view&forum_id=" + forum_id +
                "&bbs_id=" + parent_id;
        }
        else if (isRedo(request, "answer_add")) {
            //如果是重复提交，返回
            forward = "topicservlet?method=topic_view&forum_id=" + forum_id +
                "&bbs_id=" + parent_id;
        }
        else { //否则，继续执行
            try {
            	System.out.println("mjl8");
                topic ann = new topic();
                ann.setTitle("");
                ann.setContent(content);
                if (parent_id != null) {
                    ann.setParentid(Integer.valueOf(parent_id).intValue());
                    //设置父帖子ID
                }
                else {
                    ann.setParentid(0);
                }
                ann.setForumid(Integer.parseInt(forum_id));
                //设置讨论区ID
                if (user != null) {
                    ann.setUsername(user.getUsername());
                    //设置发帖的用户名
                }
                else {
                    ann.setUsername("游客");
                }
                topichandle.insert(ann);
                //往数据库中插入一条记录
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

    
     //删除回复
     //1，获得要删除的贴子的ID
     //2，调用TopicHandle的delete方法删除贴子
     //3，返回topic_view.jsp
     
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

   
     //更新贴子信息
     //1，获得更新后的贴子主题、内容、ID
     //2，判断是否为重复提交，如果为重复提交，直接返回成功页面；否则继续执行
     //3，调用TopicHandle的update方法更新贴子
     //4，如果出现异常，将异常信息保存到request对象中
     //5，如果操作成功，返回board.jsp页面
     //6，如果操作失败，返回topic_update.jsp，并提示出错信息
     
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

    
     //更新贴子回复
     //1，获得更新后的贴子主题、内容
     //2，判断是否为重复提交，如果为重复提交，直接返回成功页面；否则继续执行
     //3，调用TopicHandle的update方法更新贴子
     //4，如果出现异常，将异常信息保存到request对象中
     //5，如果操作成功，返回topic_view.jsp页面
     //6，如果操作失败，返回topic_update.jsp，并提示出错信息
     
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

   
     //将控制流程转到url所表示的页面
     

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

   
    // 判断是否为重复提交
    // 1，检查Session中是否含有指定名字的属性
    // 2，如果Session中没有该属性或者属性为空，证明已被处理过，判断为重复提交
    // 3，否则，证明是第一次处理，并将属性从Session中删除。
     
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
