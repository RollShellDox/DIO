package cn.edu.swu.zc;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.security.auth.Refreshable;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        if(user != null && user.equals("homo")){
            if(pass != null && pass.equals("114514")){
//                response.sendRedirect("./main.html");
                response.setContentType("text/html");
                try (Writer writer = response.getWriter()){
                    writer.write("<html><head></head><body><center><br><br>");
                    writer.write("<h1 style='color:red'>压力马斯内from"+request.getRemoteAddr()+"</h1><br><br>");
                    writer.write(this.printParameters(request));
                    writer.write("<br></br>");
                    writer.write(this.printHeader(request));
                    writer.write(("</center></body></html>"));
                    return;
                }
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
        sb.append("HTTP Parameter Table<br> <table border=1 style='width:70%' cell-padding='1em'>");
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            String value = request.getParameter(name);
//            writer.write(name +" : "+value+"<br>");
            sb.append(String.format("<tr style='background-color:#eee'><td width='200px'>%s</td><td>%s</td></tr>",name,value));
        }
        sb.append("</table>");
        return sb.toString();
    }


    public String printHeader(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP Header Table<table border=1 style='width:70%' cell-padding='1em'>");
        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            String value = request.getHeader(name);
//            writer.write(name +" : "+value+"<br>");
            sb.append(String.format("<tr style='background-color:#eee'><td width='200px'>%s</td><td>%s</td></tr>",name,value));
        }
        sb.append("</table>");
        return sb.toString();
    }
}
