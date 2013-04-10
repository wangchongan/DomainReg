package com.jinmibao.common.util;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUrlInformation extends HttpServlet implements Servlet {

    private static final long serialVersionUID = 3932718344318657793L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 
        // Getting servlet request URL 
        // 
        String url = request.getRequestURL().toString();

        // 
        // Getting servlet request query string. 
        // 
        String queryString = request.getQueryString();

        // 
        // Getting request information without the hostname. 
        // 
        String uri = request.getRequestURI();

        // 
        // Below we extract information about the request object path 
        // information. We extract the protocol user, server and and its  
        // servlet path, path info and the query string information. If we  
        // combaine all the information below we'll get someting equals to  
        // the request.getRequestURL(). 
        // 
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int portNumber = request.getServerPort();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();

        String query = request.getQueryString();

    }
}
