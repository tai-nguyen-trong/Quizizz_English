package com.quizizz.english.quizizz_english.Servlets;

import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repositoryImpl.NguoiDungRepositoryImpl;
import com.quizizz.english.quizizz_english.service.INguoiDungService;
import com.quizizz.english.quizizz_english.serviceImpl.NguoiDungServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private INguoiDungService nguoiDungService;

    @Override
    public void init() throws ServletException {
        nguoiDungService = new NguoiDungServiceImpl(new NguoiDungRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hoVaTen = req.getParameter("hoVaTen");
        int tuoi = Integer.parseInt(req.getParameter("tuoi"));
        String email = req.getParameter("email");
        String matKhau = req.getParameter("matKhau");
        String xacNhanMatKhau = req.getParameter("xacNhanMatKhau");
        String soDienThoai = req.getParameter("soDienThoai");

        if (!matKhau.equals(xacNhanMatKhau)) {
            req.setAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        NguoiDung nguoiDung = new NguoiDung(hoVaTen, tuoi, email, matKhau, soDienThoai);
        nguoiDungService.dangKy(nguoiDung);

        if (nguoiDung != null) {
            req.setAttribute("success", "Đăng ký thành công!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Đăng ký thất bại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
