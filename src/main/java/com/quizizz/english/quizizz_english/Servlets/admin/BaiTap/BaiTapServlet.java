package com.quizizz.english.quizizz_english.Servlets.admin.BaiTap;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.CapDo;
import com.quizizz.english.quizizz_english.model.ChuDe;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.CapDoRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IBaiTapService;
import com.quizizz.english.quizizz_english.service.ICapDoService;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.CapDoServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet({"/QuanLyDanhSachBaiTap"})
public class BaiTapServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Tạo danh sách chủ đề dưới dạng List<Map>
            List<ChuDe> chuDes = new ArrayList<>();
            List<CapDo> capDos = new ArrayList<>();
            chuDes = chuDeService.getAllChuDe();
            capDos = capDoService.getAllCapDo();
            request.setAttribute("chuDes", chuDes); // Gửi danh sách sang JSP
            request.setAttribute("capDos", capDos);
            request.setAttribute("currentPage", "QuanLyDanhSachBaiTap");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layouts/layout.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Tạo danh sách chủ đề dưới dạng List<Map>
        List<BaiTapDTO> baitaps = new LinkedList<BaiTapDTO>();
        baitaps = baiTapService.getAllBaiTap();
        // Convert danh sách thành JSON
        String json = new Gson().toJson(baitaps);

        // Gửi JSON về client
        response.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Tạo danh sách chủ đề dưới dạng List<Map>
        List<BaiTapDTO> baitaps = new LinkedList<BaiTapDTO>();
        baitaps = baiTapService.getAllBaiTap();
        // Convert danh sách thành JSON
        String json = new Gson().toJson(baitaps);

        // Gửi JSON về client
        response.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


    private Map<String, String> createTopic(int id, String tenBaiTap, String maBaiTap, String chuDe, String capDo, String thoiGianLamBai) {
        Map<String, String> topic = new HashMap<>();
        topic.put("ID", String.valueOf(id));
        topic.put("TenBaiTap", tenBaiTap);
        topic.put("MaBaiTap", maBaiTap);
        topic.put("ChuDe", chuDe);
        topic.put("CapDo", capDo);
        topic.put("ThoiGianLamBai", thoiGianLamBai);
        return topic;
    }
    private void danhSachChuDe(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Tạo danh sách chủ đề dưới dạng List<Map>
        List<ChuDe> chuDes = new ArrayList<>();
        chuDes = chuDeService.getAllChuDe();
        request.setAttribute("chuDes", chuDes); // Gửi danh sách sang JSP
        request.setAttribute("currentPage", "QuanLyDanhSachBaiTap");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/layouts/layout.jsp");
        dispatcher.forward(request, response);
    }
}
