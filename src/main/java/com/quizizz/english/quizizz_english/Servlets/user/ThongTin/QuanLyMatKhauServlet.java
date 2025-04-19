package com.quizizz.english.quizizz_english.Servlets.user.ThongTin;

import com.quizizz.english.quizizz_english.model.NguoiDung;

import com.quizizz.english.quizizz_english.repositoryImpl.NguoiDungRepositoryImpl;
import com.quizizz.english.quizizz_english.service.INguoiDungService;
import com.quizizz.english.quizizz_english.serviceImpl.NguoiDungServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/QuanLyMatKhau")
public class QuanLyMatKhauServlet extends HttpServlet {
    private INguoiDungService nguoiDungService;

    @Override
    public void init() throws ServletException {
        nguoiDungService = new NguoiDungServiceImpl(new NguoiDungRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "QuanLyMatKhau");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String matKhauCu = req.getParameter("matKhauCu");
        String matKhauMoi = req.getParameter("matKhauMoi");
        String xacNhanMatKhauMoi = req.getParameter("xacNhanMatKhauMoi");

        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);

        if (!BCrypt.checkpw(matKhauCu, nguoiDung.getMatKhau())) {
            req.setAttribute("error", "Mật khẩu cũ không đúng!");
            System.out.println("Mật khẩu cũ không đúng!");
        } else if (!matKhauMoi.equals(xacNhanMatKhauMoi)) {
            req.setAttribute("error", "Xác nhận mật khẩu không khớp!");
            System.out.println("Xác nhận mật khẩu không khớp!");
        } else {
            String hashedMatKhauMoi = BCrypt.hashpw(matKhauMoi, BCrypt.gensalt());
            nguoiDungService.updateMatKhau(id, hashedMatKhauMoi);
            req.setAttribute("success", "Đổi mật khẩu thành công!");
            System.out.println("Đổi mật khẩu thành công!");
        }

        req.setAttribute("currentPage", "QuanLyMatKhau");
        req.getRequestDispatcher("/layouts/layoutUser.jsp").forward(req, resp);
    }
}
