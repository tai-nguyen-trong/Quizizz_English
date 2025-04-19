package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repositoryImpl.LichSuLamBaiRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.NguoiDungRepositoryImpl;
import com.quizizz.english.quizizz_english.service.ILichSuLamBaiService;
import com.quizizz.english.quizizz_english.service.INguoiDungService;
import com.quizizz.english.quizizz_english.serviceImpl.LichSuLamBaiServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.NguoiDungServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/LichSuLamBai")
public class LichSuLamBaiServlet extends HttpServlet {
    private ILichSuLamBaiService lichSuLamBaiService;
    private INguoiDungService nguoiDungService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        lichSuLamBaiService = new LichSuLamBaiServiceImpl(new LichSuLamBaiRepositoryImpl());
        nguoiDungService = new NguoiDungServiceImpl(new NguoiDungRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idUser = req.getParameter("idUser");

        List<LichSuLamBaiDTO> lichSuLamBais = lichSuLamBaiService.getLichSuLamBaiByIdUser(Integer.parseInt(idUser));
        req.setAttribute("lichSuLamBais", lichSuLamBais);
        for (LichSuLamBaiDTO lichSuLamBaiDTO : lichSuLamBais) {
            System.out.println("idLichSu: " +  lichSuLamBaiDTO.getId() + "idBaiTap"  + lichSuLamBaiDTO.getIdBaiTap());
        }

        //Lấy tất cả lịch sử làm bài của user
        req.setAttribute("currentPage", "LichSuLamBai");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(req, resp);
    }
}
