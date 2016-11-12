package bbs;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


 //控制器，接受用户的请求，并调用相应的处理方法，返回结果页面
 
public class ForumServlet
    extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        doGet(request, response);
    }

    
     //判断是哪种操作
     //1，forum_delete：删除讨论区
     //2，forum_add：新增讨论区
     //3，forum_update：更新讨论区
     //4，forum_select：获取讨论区列表
     //5，forum_view：获取某一个讨论区详细信息
     
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        String method = request.getParameter("method");
        //从request对象中获取操作类型，后面代码根据操作类型调用相应的方法进行处理
        if (method == null) {
            return;
        }
        else if (method.equalsIgnoreCase("forum_delete")) {
            forum_delete(request, response);
            //转到删除讨论区操作
        }
        else if (method.equalsIgnoreCase("forum_add")) {
            forum_add(request, response);
            //转到新增讨论区操作
        }
        else if (method.equalsIgnoreCase("forum_update")) {
            forum_update(request, response);
            //转到更新讨论区操作
        }
        else if (method.equalsIgnoreCase("forum_select")) {
            forum_select(request, response);
            //转到获取讨论区列表操作
        }
        else if (method.equalsIgnoreCase("forum_view")) {
            forum_view(request, response);
            //转到获取某一个讨论区信息操作
        }
        else if (method.equalsIgnoreCase("forum_edit")) {
            forum_edit(request, response);
            //转到获取某一个讨论区信息输入到编辑页面
        }

    }

    
     //删除讨论区
     //1，获得要删除的讨论区编号forum_id
     //2，调用ForumHandle的delete方法，删除编号为forum_id的讨论区
     //3，如果出现异常，将异常信息保存到request对象中
     //4，如果操作成功，返回成功页面
     //5，如果操作失败，返回主页面，并提示出错信息
     
    private void forum_delete(HttpServletRequest request,
                              HttpServletResponse response) {
        //获得要删除的讨论区编号forum_id
        String forum_id = (String) request.getParameter("forum_id");
        boolean isSuccess = false;
        try {
            //调用ForumHandle的delete方法，删除编号为forum_id的讨论区
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

   
     //创建讨论区
     //1，获得要增加的讨论区的名字、顺序号、描述
     //2，判断是否为重复提交，如果为重复提交，直接返回主页面；否则继续执行
     //3，调用ForumHandle的insert方法创建讨论区
     //4，如果出现异常，将异常信息保存到request对象中
     //5，如果操作成功，返回主页面
     //6，如果操作失败，返回forum_add.jsp，并提示出错信息
     
    private void forum_add(HttpServletRequest request,
                           HttpServletResponse response) {
        //获得要增加的讨论区的名字、顺序号、描述
        String name = (String) request.getParameter("name");
        String sort = (String) request.getParameter("sort");
        String description = (String) request.getParameter("description");
        boolean isSuccess = false;
        String forward = null;
        //判断是否为重复提交，如果为重复提交，直接返回主页面；否则继续执行
        if (isRedo(request, "forum_add")) {
            forward = "index.jsp";
        }
        else {
            try {
                //将请求参数封装成业务对象
                forum forum = new forum();
                forum.setName(name);
                int seq = Integer.parseInt(sort);
                forum.setSort(seq);
                forum.setDescription(description);
                //调用业务逻辑创建讨论区
                forumhandle.insert(forum);
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                //将错误信息保存在request对象中，界面展示会用到
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

    
     //获取讨论区列表
     //1，调用业务逻辑获取讨论区列表
     //2，将讨论区列表保存在request对象中
     //3，如果出现异常，将异常信息保存到request对象中
     //4，返回主页面
     
    private void forum_select(HttpServletRequest request,
                              HttpServletResponse response) {
        boolean isSuccess = false;
        //判断处理成功与否
        String forward = null;
        try {
            request.setAttribute("forums", forumhandle.select());
            //调用业务逻辑获取讨论区列表，并保存到request对象中
            isSuccess = true;
            //处理成功
        }
        catch (Exception es) {
            es.printStackTrace();
            isSuccess = false;
            //处理失败
            request.setAttribute("forum_error", es.getMessage());
            //将失败信息保存到request中
        }
        if (isSuccess) {
            request.removeAttribute("forum_error");
            //如果处理成功，从request对象中删除错误信息
        }
        forward = "forums.jsp";
        forward(request, response, forward);
        //转发请求，流程转到主页面
    }

    
     //获取某一个讨论区的详细信息
    
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

    
     //获取某一个讨论区的详细信息,并输出到编辑页面
     
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

    
     //修改讨论区信息
     //1，获得要修改的讨论区编号forum_id以及变更后的讨论区信息：名字、顺序号、描述
     //2，判断是否为重复提交，如果为重复提交，直接返回成功页面；否则继续执行
     //3，调用ForumHandle的update方法更新讨论区
     //4，如果出现异常，将异常信息保存到request对象中
     //5，如果操作成功，返回成功页面
     //6，如果操作失败，返回forum_update.jsp，并提示出错信息
     
    private void forum_update(HttpServletRequest request,
                              HttpServletResponse response) {
        //获得要修改的讨论区编号forum_id
        String id = (String) request.getParameter("forum_id");
        //获取变更后的讨论区信息：
        String name = (String) request.getParameter("name");
        String sort = (String) request.getParameter("sort");
        String description = (String) request.getParameter("description");
        boolean isSuccess = false;
        String forward = null;
        //判断是否为重复提交
        if (isRedo(request, "forum_update")) {
            forward = "success.jsp";
        }
        else {
            try {
                //使用修改后的信息封装讨论区对象
                forum forum = new forum();
                forum.setName(name);
                int seq = Integer.parseInt(sort);
                forum.setSort(seq);
                forum.setId(Integer.parseInt(id));
                forum.setDescription(description);
                //调用业务逻辑修改讨论区信息
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

    
     //判断是否为重复提交
     //1，检查Session中是否含有指定名字的属性
     //2，如果Session中没有该属性或者属性为空，证明已被处理过，判断为重复提交
     //3，否则，证明是第一次处理，并将属性从Session中删除。
    
     
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
