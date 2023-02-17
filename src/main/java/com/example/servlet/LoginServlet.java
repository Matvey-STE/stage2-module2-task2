package com.example.servlet;

import com.example.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            response.sendRedirect("/user/hello.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        List<String> listOfNames =  Users.getInstance().getUsers();
        if((login.equals(listOfNames.get(0)) || login.equals(listOfNames.get(1))) && password.equals("")){
            response.sendRedirect("/user/hello.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", "success");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
