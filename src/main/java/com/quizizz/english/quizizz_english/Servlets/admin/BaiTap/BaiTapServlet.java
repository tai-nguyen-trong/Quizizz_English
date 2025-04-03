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

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet({"/QuanLyDanhSachBaiTap","/CapNhatBaiTap","/XoaBaiTap"})
public class BaiTapServlet extends HttpServlet {
    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;
    private ICapDoService capDoService;
    private Gson gson = new Gson(); // Khởi tạo Gson

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain;charset=UTF-8");

            // Nhận dữ liệu từ AJAX
            String tenBaiTap = request.getParameter("tenBaiTap");
            double thoiGianLamBai = Double.parseDouble(request.getParameter("thoiGianLamBai"));
            int idChuDe = Integer.parseInt(request.getParameter("idChuDe"));
            int idCapDo = Integer.parseInt(request.getParameter("idCapDo"));
            BaiTap baiTap = new BaiTap(tenBaiTap,thoiGianLamBai,idChuDe,idCapDo);
            boolean isSuccess = baiTapService.addBaiTap(baiTap);
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
            BaiTap baiTap = gson.fromJson(requestBody.toString(), BaiTap.class);

            // Gọi Service để cập nhật dữ liệu
            boolean isUpdated = baiTapService.updateBaiTap(baiTap);

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
        String idParam = request.getParameter("id");
        try {
            int id = Integer.parseInt(idParam);
            boolean isDeleted = baiTapService.deleteBaiTap(id);

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
    @Override
    public void destroy() {
        System.out.println("🔹 Servlet đang bị hủy...");
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
