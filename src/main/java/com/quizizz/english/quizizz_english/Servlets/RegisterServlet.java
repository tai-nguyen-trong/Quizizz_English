package com.quizizz.english.quizizz_english.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/*")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           // Lấy tham số "page" từ URL
           String path = req.getPathInfo();
           String currentPage = (path == null || path.equals("/")) ? "home" : path.substring(1);

           // Kiểm tra file JSP có tồn tại không (tránh lỗi 404)
           String pagePath = "/views/" + currentPage + ".jsp";
           if (getServletContext().getResource(pagePath) == null) {
               resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found!");
               return;
           }

           // Chuyển trang đến pagelayout.jsp và truyền tham số "currentPage"
           req.setAttribute("currentPage", currentPage);
           req.getRequestDispatcher("/layouts/layout.jsp").forward(req, resp);
       }catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
