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

    //�û���¼�ж�
    private void user_login(HttpServletRequest request,
                            HttpServletResponse response) {
        String userName = request.getParameter("username");//��ȡ����
        String passWord = request.getParameter("password");//��ȡ����
        System.out.println(userName);
        System.out.println(passWord);
        userhandle userlog=new userhandle();
       
        boolean isSuccess = false;
        
        user user = userlog.userLogin(userName, passWord);//�����¶��󣬺˶��û��������룬�����Ƿ��¼ͨ��
       
        
        if (user!=null) 
        {
        	//����ɹ�����ת����ʼҳ��
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
        	//��������ڵ�¼ҳ��

        	 request.setAttribute("login_error", "�û��������벻ƥ��");
			try {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} 
			catch (Throwable t) {
				getServletContext().log(t.getMessage());
			}
        }
       
    }

    //����һ�����û�����ؿ���
    private void user_add(HttpServletRequest request,
                          HttpServletResponse response) 
    {
        String userName =  request.getParameter("username");//��ȡ����
        System.out.println(userName);
        String passWord = request.getParameter("password");//��ȡ����
        String Email = request.getParameter("email");//��ȡ�����ַ
        String nickname = request.getParameter("nickname");//��ȡ�ǳ�
        user user = new user(userName, passWord, Email, nickname);//�����¶���
        boolean isSuccess = false;
        String forward = null;
        if (isRedo(request, "user_add")) 
        {
        	//�����ɹ��������ҳ��
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

    //�û���Ϣ����
    private void user_update(HttpServletRequest request,
                             HttpServletResponse response) {
        user user1 = (user) request.getSession().getAttribute("user");
        String email = (String) request.getParameter("email");//��ȡ�û�ԭ��Ϣ
        String nickname = (String) request.getParameter("nickname");
        String passWord = (String) request.getParameter("password");
        user user = new user();//�����¶���
        user.setUsername(user1.getUsername());//���������û��������Ϣ
        user.setDegree(user1.getDegree());
        user.setPassword(passWord);
        user.setNickname(nickname);
        user.setEmail(email);

        boolean isSuccess = false;
        String forward = null;
        if (isRedo(request, "user_update")) {
        	//���óɹ��Ժ���ת����Ӧҳ��
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

    //���û����ǰ����ťʱ�Ŀ���
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

    //���ƺ���
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
