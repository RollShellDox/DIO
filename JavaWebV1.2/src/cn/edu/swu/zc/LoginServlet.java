package cn.edu.swu.zc;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.security.auth.Refreshable;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String code = request.getParameter("code");
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

//        if(code == null || !code.equalsIgnoreCase((String) session.getAttribute(AuthCodeServlet.AUTH_CODE))){
//            response.sendRedirect("./index.html");
//            return;
//        }

        if(user != null && user.equals("homo")){
            if(pass != null && pass.equals("114514")){
                session.setAttribute(AuthFilter.AUTH_STATUS,AuthStatus.LOGIN_SUCCESS);

                //返回一个重定向
//                response.sendRedirect("./main.html");
                response.sendRedirect("./main");

                //返回HTML代码
//                try (Writer writer = response.getWriter()){
//                    writer.write("<html><head></head><body><center><br><br>");
//                    writer.write("<h1 style='color:red'>压力马斯内from"+request.getRemoteAddr()+"</h1><br><br>");
//                    writer.write(this.printParameters(request));
//                    writer.write("<br></br>");
//                    writer.write(this.printHeader(request));
//                    writer.write(("</center></body></html>"));
//                }
                return;
            }
        }



//        response.sendRedirect("./index.html");
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"用户名或密码错误");
        response.setContentType("text/html");
        response.setHeader("Refresh","5;url=./index.html");
        try(Writer writer = response.getWriter()){
            writer.write("<center><h1>用户名或密码错误</h1></center>");
        }
    }
    public String printParameters(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<table style='width:70%' cellpadding='5px' cellspacing='20px'>");
        sb.append("<tr style='background-color:#336699;color:#FFF'><th>Header name</th><th>Header value</th></tr>");
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
//            writer.write(name +" : "+value+"<br>");
            sb.append(String.format("<tr style='background-color:#eee'><td width='200px'>%s</td><td>%s</td></tr>",name,request.getParameter(name)));
        }
        sb.append("</table>");
        return sb.toString();
    }


    public String printHeader(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<table style='width:70%' cellpadding='5px' cellspacing='20px'>");
        sb.append("<tr style='background-color:#336699;color:#FFF'><th>Header name</th><th>Header value</th></tr>");
        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
//            writer.write(name +" : "+value+"<br>");
            sb.append(String.format("<tr style='background-color:#eee'><td width='200px'>%s</td><td>%s</td></tr>",name,request.getHeader(name)));
        }
        sb.append("</table>");
        return sb.toString();
    }
}
