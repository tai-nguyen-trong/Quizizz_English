package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.model.ChuDe;
import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.CauHoiRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IBaiTapService;
import com.quizizz.english.quizizz_english.service.ICauHoiService;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.CauHoiServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LamBaiTap")
public class LamBaiTapServlet extends HttpServlet {
    private ICauHoiService cauHoiService;
    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;

    @Override
    public void init() throws ServletException {
        cauHoiService = new CauHoiServiceImpl(new CauHoiRepositoryImpl());
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idBaiTap = Integer.parseInt(req.getParameter("idBaiTap"));

        BaiTap baiTap = baiTapService.getBaiTapById(idBaiTap);
        req.setAttribute("baiTap", baiTap);
        // Gán luôn chủ đề
        ChuDe chuDe = chuDeService.getChuDeById(baiTap.getIdChuDe());
        baiTap.setChuDe(chuDe);

        List<CauHoi> cauHois = cauHoiService.getCauHoiVaDapAnTheoBaiTap(idBaiTap);

        req.setAttribute("cauHois", cauHois);
        req.setAttribute("currentPage", "LamBaiTap");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(req, resp);
    }
}
