package com.quizizz.english.quizizz_english.Servlets.admin.ChuDe;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.ChuDe;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet({"/QuanLyDanhSachChuDe","/ThemChuDe"})
public class ChuDeServlet extends HttpServlet {
    private IChuDeService chuDeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Tạo danh sách chủ đề dưới dạng List<Map>
            List<ChuDe> chuDes = new ArrayList<>();
            chuDes = chuDeService.getAllChuDe();
            request.setAttribute("chuDes", chuDes);
            request.setAttribute("currentPage", "QuanLyDanhSachChuDe");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layouts/layout.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain;charset=UTF-8");
            // Nhận dữ liệu từ AJAX
            String tenChuDe = request.getParameter("tenChuDe");
            String moTa = request.getParameter("moTa");
            String hinhAnh = request.getParameter("hinhAnh");
            ChuDe chuDe = new ChuDe(tenChuDe,moTa,hinhAnh);
            boolean isSuccess = chuDeService.addChuDe(chuDe);
            // Trả về phản hồi
            if (isSuccess) {
                response.getWriter().write("Thêm bài tập thành công!");
            } else {
                response.getWriter().write("Thêm bài tập thất bại!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
    private Map<String, String> createTopic(String title, String imageUrl) {
        Map<String, String> topic = new HashMap<>();
        topic.put("title", title);
        topic.put("image", imageUrl);
        return topic;
    }
}
