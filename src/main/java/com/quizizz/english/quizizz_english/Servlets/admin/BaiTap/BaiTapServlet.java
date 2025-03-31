package com.quizizz.english.quizizz_english.Servlets.admin.BaiTap;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IBaiTapService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/BaiTap")
public class BaiTapServlet extends HttpServlet {
    private IBaiTapService baiTapService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Tạo danh sách chủ đề dưới dạng List<Map>
        List<BaiTapDTO> baitaps = new LinkedList<BaiTapDTO>();
        baitaps = baiTapService.getAllBaiTap();
        /*baitaps.add(createTopic(1,"Bài tập 1", "BT0001","Động vật","Dễ","10 phút"));
        baitaps.add(createTopic(2,"Bài tập 2", "BT0002","Động vật","Dễ","10 phút"));
        baitaps.add(createTopic(3,"Bài tập 3", "BT0003","Động vật","Dễ","10 phút"));
        baitaps.add(createTopic(4,"Bài tập 4", "BT0004","Động vật","Dễ","10 phút"));
        baitaps.add(createTopic(5,"Bài tập 5", "BT0005","Động vật","Dễ","10 phút"));*/

        // Convert danh sách thành JSON
        String json = new Gson().toJson(baitaps);

        // Gửi JSON về client
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
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
}
