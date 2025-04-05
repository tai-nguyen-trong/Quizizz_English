package com.quizizz.english.quizizz_english.Servlets.admin.ChuDe;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.model.ChuDe;
import jakarta.servlet.RequestDispatcher;
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


@WebServlet("/QuanLyDanhSachChuDe")
public class ChuDeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("currentPage", "QuanLyDanhSachChuDe");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layouts/layout.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        // Set kiểu dữ liệu trả về là JSON và mã hóa UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Tạo danh sách chủ đề dưới dạng List<Map>
        List<Map<String, String>> topics = new ArrayList<>();

        topics.add(createTopic("Động vật", "https://media.wired.com/photos/593261cab8eb31692072f129/master/w_1920,c_limit/85120553.jpg"));
        topics.add(createTopic("Thực vật", "https://www.cactusoutlet.com/cdn/shop/files/20231020_CactiProduct2ndShoot_KathleenDreierPhotography_KMDP0583-Edit_2048x.jpg"));
        topics.add(createTopic("Thể thao", "https://ieltsxuanphi.edu.vn/wp-content/uploads/2021/06/sports-New-Brunswick.jpg"));
        topics.add(createTopic("Công nghệ", "https://sgs.upm.edu.my/summer-uploads/20230608140907blobid0.jpg"));
        topics.add(createTopic("Chính trị", "https://m.media-amazon.com/images/I/81wZUou4F7L._AC_UF894,1000_QL80_.jpg"));
        topics.add(createTopic("Du lịch", "https://smartcom.vn/blog/wp-content/uploads/2024/03/2_1.jpg"));

        // Convert danh sách thành JSON
        String json = new Gson().toJson(topics);

        // Gửi JSON về client
        response.getWriter().write(json);
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
