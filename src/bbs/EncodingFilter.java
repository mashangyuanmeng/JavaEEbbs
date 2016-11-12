package bbs;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


 //×Ö·û±àÂëfilter

public class EncodingFilter extends HttpServlet implements Filter{
    private FilterConfig filterConfig;
    private String encoding = null;
    protected boolean ignore = true;

    public void init(FilterConfig filterConfig) throws ServletException{
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null){
            this.ignore = true;
        } else if (value.equalsIgnoreCase("true")) {
            this.ignore = true;
        } else if (value.equalsIgnoreCase("yes")) {
            this.ignore = true;
        } else {
            this.ignore = false;
        }
    }

    //Process the request/response pair
    public void doFilter(ServletRequest request,ServletResponse response,
                         FilterChain filterChain){
        try{
            if (ignore || (request.getCharacterEncoding() == null)) {
                 String encoding = selectEncoding(request);
                 if (encoding != null)
                     request.setCharacterEncoding(encoding);
             }
            filterChain.doFilter(request,response);
        } catch(Exception sx){
            sx.getMessage();
        }

    }

    private String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }

    //Çå³ý±àÂë
    public void destroy(){
        encoding = null;
        filterConfig = null;
    }
}
