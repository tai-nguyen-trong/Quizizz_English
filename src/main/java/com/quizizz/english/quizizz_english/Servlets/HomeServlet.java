package com.quizizz.english.quizizz_english.Servlets;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.model.ChuDe;
import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


@WebServlet({"/","/home"})
public class HomeServlet extends HttpServlet {
    private IChuDeService chuDeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Tạo danh sách chủ đề dưới dạng List<Map>
            RequestDispatcher dispatcher = null;
            List<ChuDe> chuDes = new ArrayList<>();
            chuDes = chuDeService.getAllChuDe();
            request.setAttribute("chuDes", chuDes); // Gửi danh sách sang JSP
            HttpSession session = request.getSession(false); // không tạo mới
            boolean isLoggedIn = false;

            if (session != null && session.getAttribute("isLoggedIn") != null) {
                isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
            }
            if(isLoggedIn){
                request.setAttribute("currentPage", "home");
                NguoiDung nguoiDung = (NguoiDung) session.getAttribute("user");
                if(nguoiDung != null) {
                    if(Objects.equals(nguoiDung.getVaiTro(), "GiaoVien")){
                        dispatcher = request.getRequestDispatcher("/layouts/layout.jsp");
                    }else if (Objects.equals(nguoiDung.getVaiTro(), "NguoiDung")){
                        dispatcher = request.getRequestDispatcher("/layouts/layoutUser.jsp");
                    }
                }
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                } else {
                    // Trường hợp vai trò không xác định → chuyển về login hoặc báo lỗi
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            }else {
                dispatcher  = request.getRequestDispatcher("/layouts/layoutUser.jsp");
                dispatcher.forward(request, response);
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
        List<ChuDe> chuDes = new ArrayList<>();
        chuDes = chuDeService.getAllChuDe();
        // Convert danh sách thành JSON
        String json = new Gson().toJson(chuDes);

        // Gửi JSON về client
        response.getWriter().write(json);
    }
    protected void DieuHuongChuyenTrang(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy tham số "page" từ URL
            String path = req.getParameter("currentPage");
            String currentPage = path == null ? "home" : path;
            // Kiểm tra file JSP có tồn tại không (tránh lỗi 404)
            String pagePath = "/views/" + currentPage + ".jsp";
            if (req.getServletContext().getResource(pagePath) == null) {
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
