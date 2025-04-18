package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

import com.quizizz.english.quizizz_english.dto.KetQuaDTO;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.LichSuLamBaiRepositoryImpl;
import com.quizizz.english.quizizz_english.service.ILichSuLamBaiService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.LichSuLamBaiServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/KetQua")
public class KetQuaServlet extends HttpServlet {
    private ILichSuLamBaiService lichSuLamBaiService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        lichSuLamBaiService = new LichSuLamBaiServiceImpl(new LichSuLamBaiRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idLichSu = request.getParameter("idLichSuLamBaiTap");
        String idBaiTap = request.getParameter("idBaiTap");
        // Lấy dữ liệu từ session
        List<KetQuaDTO> ketquas = lichSuLamBaiService.getKetQuaLamBai(Integer.parseInt(idLichSu), Integer.parseInt(idBaiTap));
        // Gửi tới trang JSP hiển thị kết quả
        request.setAttribute("ketQuas", ketquas);
        request.setAttribute("currentPage", "KetQua");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(request, response);
    }
}
