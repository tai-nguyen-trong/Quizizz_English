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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/ThongTinCaNhan", "/CapNhatThongTinCaNhan"})
public class ThongTinCaNhanServlet extends HttpServlet {
    private INguoiDungService nguoiDungService;

    @Override
    public void init() throws ServletException {
        nguoiDungService = new NguoiDungServiceImpl(new NguoiDungRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            resp.sendRedirect(req.getContextPath() + "/layouts/login.jsp");
//            return;
//        }
//
//        req.getRequestDispatcher("/ThongTinCaNhan.jsp").forward(req, resp);
        req.setAttribute("currentPage", "ThongTinCaNhan");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String hoVaTen = req.getParameter("hoVaTen");
        int tuoi = Integer.parseInt(req.getParameter("tuoi"));
        String email = req.getParameter("email");
        String matKhau = req.getParameter("matKhau");

//        String matKhauCu = req.getParameter("matKhauCu");
//        String matKhauMoi = req.getParameter("matKhauMoi");
//        String xacNhanMatKhauMoi = req.getParameter("xacNhanMatKhauMoi");

        String soDienThoai = req.getParameter("soDienThoai");

        NguoiDung nguoiDung = new NguoiDung(id, hoVaTen, tuoi, email, matKhau, soDienThoai);
//        if (!nguoiDung.getMatKhau().equals(matKhauCu)) {
//            req.setAttribute("error", "Mật khẩu cũ không đúng!");
//        }
//        if (!matKhauMoi.equals(xacNhanMatKhauMoi)) {
//            req.setAttribute("error", "Xác nhận mật khẩu không đúng!");
//        }
        boolean capNhatThanhCong = nguoiDungService.updateNguoiDung(nguoiDung);
        System.out.println(capNhatThanhCong + " CapNhatThongTinCaNhanServlet");
            // Lấy lại thông tin mới từ DB
            NguoiDung nguoiDungMoi = nguoiDungService.getNguoiDungById(id);
            // Cập nhật lại session
            HttpSession session = req.getSession();
            session.setAttribute("user", nguoiDungMoi);
            System.out.println(nguoiDung.getHoVaTen() + "Trong session CapNhatThongTinCaNhanServlet");
//            resp.sendRedirect(req.getContextPath() + "/ThongTinCaNhan");
            req.setAttribute("currentPage", "ThongTinCaNhan");
            req.getRequestDispatcher("/layouts/layoutUser.jsp").forward(req, resp);

    }
}
