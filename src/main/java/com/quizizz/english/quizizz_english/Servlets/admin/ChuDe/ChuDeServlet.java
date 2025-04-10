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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet({"/QuanLyDanhSachChuDe","/ThemChuDe","/XoaChuDe","/CapNhatChuDe"})
public class ChuDeServlet extends HttpServlet {
    private IChuDeService chuDeService;
    private Gson gson = new Gson();

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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc JSON từ request body
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();
        try {
            // Chuyển đổi JSON thành đối tượng Java
            ChuDe chuDe = gson.fromJson(requestBody.toString(), ChuDe.class);

            // Gọi Service để cập nhật dữ liệu
            boolean isUpdated = chuDeService.updateChuDe(chuDe);

            // Trả về kết quả
            if (isUpdated) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(gson.toJson("Cập nhật thành công!"));
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(gson.toJson("Cập nhật thất bại!"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idChuDe = request.getParameter("idChuDe");
        try {
            int id = Integer.parseInt(idChuDe);
            boolean isDeleted = chuDeService.deleteChuDe(id);

            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Xóa thành công!");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Xóa thất bại!");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID không hợp lệ!");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Lỗi khi xóa bài tập: " + e.getMessage());
        }
    }
    private Map<String, String> createTopic(String title, String imageUrl) {
        Map<String, String> topic = new HashMap<>();
        topic.put("title", title);
        topic.put("image", imageUrl);
        return topic;
    }
}
