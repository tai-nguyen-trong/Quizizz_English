package com.quizizz.english.quizizz_english.Servlets.admin.BaiTap;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.CapDoRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IBaiTapService;
import com.quizizz.english.quizizz_english.service.ICapDoService;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.CapDoServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet({"/DanhSachBaiTap"})
public class DanhSachBaiTapServlet extends HttpServlet {
    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;
    private ICapDoService capDoService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
        capDoService = new CapDoServiceImpl(new CapDoRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<BaiTapDTO> baitaps = new LinkedList<BaiTapDTO>();
        baitaps = baiTapService.getAllBaiTap();
        // Convert danh sách thành JSON
        String json = new Gson().toJson(baitaps);

        // Gửi JSON về client
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
