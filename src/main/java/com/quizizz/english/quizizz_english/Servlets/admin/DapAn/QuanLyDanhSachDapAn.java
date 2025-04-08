package com.quizizz.english.quizizz_english.Servlets.admin.DapAn;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.repositoryImpl.*;
import com.quizizz.english.quizizz_english.service.*;
import com.quizizz.english.quizizz_english.serviceImpl.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDanhSachDapAn extends HttpServlet {
    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;
    private ICapDoService capDoService;
    private ICauHoiService cauHoiService;
    private IDapAnService dapAnService;
    private Gson gson = new Gson();
    @Override
    public void init(ServletConfig config) throws ServletException {
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
        capDoService = new CapDoServiceImpl(new CapDoRepositoryImpl());
        cauHoiService = new CauHoiServiceImpl(new CauHoiRepositoryImpl());
        dapAnService = new DapAnServiceImpl(new DapAnRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idCauHoi = request.getParameter("idCauHoi");
            // Tạo danh sách chủ đề dưới dạng List<Map>
            List<DapAn> dapAns = new ArrayList<>();
            dapAns = dapAnService.getDapAnById(Integer.parseInt(idCauHoi));
            request.setAttribute("baiTap", baiTap); // Gửi danh sách sang JSP
            request.setAttribute("currentPage", "QuanLyDanhSachCauHoi");
            request.setAttribute("idBaiTap", idCauHoi);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layouts/layout.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
