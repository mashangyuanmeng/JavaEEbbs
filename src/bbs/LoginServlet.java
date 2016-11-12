package bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet
    extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null) {
            return;
        }
        else if (method.equalsIgnoreCase("user_add")) {
            user_add(request, response);
        }
        else if (method.equalsIgnoreCase("user_update")) {
            user_update(request, response);
        }
        else if (method.equalsIgnoreCase("user_login")) {
            user_login(request, response);
        }
    }

    //用户登录判断
    private void user_login(HttpServletRequest request,
                            HttpServletResponse response) {
        String userName = request.getParameter("username");//获取姓名
        String passWord = request.getParameter("password");//获取密码
        System.out.println(userName);
        System.out.println(passWord);
        userhandle userlog=new userhandle();
       
        boolean isSuccess = false;
        
        user user = userlog.userLogin(userName, passWord);//创建新对象，核定用户名和密码，决定是否登录通过
       
        
        if (user!=null) 
        {
        	//如果成功，跳转至起始页面
        	System.out.println("ms1");
            user.setUsername(userName);
            user.setPassword(passWord);
        	request.getSession().setAttribute("user", user);
        	 
        	 System.out.println(user.getUsername());
			try {
				request.getRequestDispatcher("index.jsp").forward(request,response);
			} 
			catch (Throwable t) {
				getServletContext().log(t.getMessage());
			}
        }
        else {
        	//否则继续在登录页面

        	 request.setAttribute("login_error", "用户名或密码不匹配");
			try {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} 
			catch (Throwable t) {
				getServletContext().log(t.getMessage());
			}
        }
       
    }

    //增加一个新用户的相关控制
    private void user_add(HttpServletRequest request,
                          HttpServletResponse response) 
    {
        String userName =  request.getParameter("username");//获取姓名
        System.out.println(userName);
        String passWord = request.getParameter("password");//获取密码
        String Email = request.getParameter("email");//获取邮箱地址
        String nickname = request.getParameter("nickname");//获取昵称
        user user = new user(userName, passWord, Email, nickname);//创建新对象
        boolean isSuccess = false;
        String forward = null;
        if (isRedo(request, "user_add")) 
        {
        	//创建成功后进入主页面
            forward = "index.jsp";
        }
        else 
        {
            try {
            	System.out.println("ms5");
                userhandle.userReg(user);
                
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                request.setAttribute("user_add_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("user_add_error");
                forward = "success.jsp?url=login.jsp";
            }
            else {
                forward = "user_add.jsp";
            }
        }
        forward(request, response, forward);
    }

    //用户信息更新
    private void user_update(HttpServletRequest request,
                             HttpServletResponse response) {
        user user1 = (user) request.getSession().getAttribute("user");
        String email = (String) request.getParameter("email");//获取用户原信息
        String nickname = (String) request.getParameter("nickname");
        String passWord = (String) request.getParameter("password");
        user user = new user();//创建新对象
        user.setUsername(user1.getUsername());//重新设置用户的相关信息
        user.setDegree(user1.getDegree());
        user.setPassword(passWord);
        user.setNickname(nickname);
        user.setEmail(email);

        boolean isSuccess = false;
        String forward = null;
        if (isRedo(request, "user_update")) {
        	//设置成功以后跳转到相应页面
            forward = "success.jsp";
        }
        else {
            try {
                userhandle.userUpdate(user);
                isSuccess = true;
            }
            catch (Exception es) {
                es.printStackTrace();
                isSuccess = false;
                request.setAttribute("user_update_error", es.getMessage());
            }
            if (isSuccess) {
                request.removeAttribute("user_update_error");
                request.getSession().setAttribute("user", user);
                forward = "success.jsp";
            }
            else {
                forward = "user_update.jsp";
            }

        }
        forward(request, response, forward);
    }

    //当用户点击前进按钮时的控制
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

    //控制函数
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
