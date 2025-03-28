package com.quizizz.english.quizizz_english.servlet;

import com.google.gson.Gson;
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


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set kiểu dữ liệu trả về là JSON và mã hóa UTF-8
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

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
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    private Map<String, String> createTopic(String title, String imageUrl) {
        Map<String, String> topic = new HashMap<>();
        topic.put("title", title);
        topic.put("image", imageUrl);
        return topic;
    }
}
