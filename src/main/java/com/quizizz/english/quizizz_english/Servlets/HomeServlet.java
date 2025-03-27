package com.quizizz.english.quizizz_english.Servlets;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet({"/","/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        //String path = req.getParameter("currentPage");
        try {
            switch (action) {
                case "/denDanhSachBaiTap":
                    danhSachBaiTap(req, resp);
                    break;
                default:
                    DieuHuongChuyenTrang(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            danhSachChuDe(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Map<String, String> createTopic(String title, String imageUrl) {
        Map<String, String> topic = new HashMap<>();
        topic.put("title", title);
        topic.put("image", imageUrl);
        return topic;
    }
    private void danhSachChuDe(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
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
    protected void danhSachBaiTap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title"); // Lấy title từ request
        req.setAttribute("topicTitle", title); // Đưa vào request attribute
        req.getRequestDispatcher("new.jsp").forward(req, resp);
    }
    protected void DieuHuongChuyenTrang(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy tham số "page" từ URL
            String path = req.getParameter("currentPage");
            String currentPage = path == null ? "home" : path;
            // Kiểm tra file JSP có tồn tại không (tránh lỗi 404)
            String pagePath = "/views/" + currentPage + ".jsp";
            if (getServletContext().getResource(pagePath) == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found!");
                return;
            }
            // Chuyển trang đến pagelayout.jsp và truyền tham số "currentPage"
            req.setAttribute("currentPage", currentPage);
            req.getRequestDispatcher("/layouts/layout.jsp").forward(req, resp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
