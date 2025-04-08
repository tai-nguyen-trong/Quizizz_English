package com.quizizz.english.quizizz_english.Servlets.admin.CauHoi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.repositoryImpl.*;
import com.quizizz.english.quizizz_english.service.*;
import com.quizizz.english.quizizz_english.serviceImpl.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet({"/ThemCauHoi","/XoaCauHoi","/SuaCauHoi"})
public class ThemXoaSuaCauHoiServlet extends HttpServlet {

    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;
    private ICapDoService capDoService;
    private ICauHoiService cauHoiService;
    private IDapAnService dapAnService;
    private Gson gson = new Gson();
    @Override
    public void init(ServletConfig config) throws ServletException {
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
        capDoService = new CapDoServiceImpl(new CapDoRepositoryImpl());
        cauHoiService = new CauHoiServiceImpl(new CauHoiRepositoryImpl());
        dapAnService = new DapAnServiceImpl(new DapAnRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idBaiTap = request.getParameter("idBaiTap");
            // Tạo danh sách chủ đề dưới dạng List<Map>
            BaiTap baiTap = new BaiTap();
            baiTap = baiTapService.getBaiTapById(Integer.parseInt(idBaiTap));
            request.setAttribute("baiTap", baiTap); // Gửi danh sách sang JSP
            request.setAttribute("currentPage", "QuanLyDanhSachCauHoi");
            request.setAttribute("idBaiTap", idBaiTap);
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
            String tenCauHoi = request.getParameter("tenCauHoi");
            int idBaiTap = Integer.parseInt(request.getParameter("idBaiTap"));
            String danhSachDapAnJson = request.getParameter("danhSachDapAn");
            // Parse chuỗi JSON thành List<DapAnDTO>
            Gson gson = new Gson();
            List<DapAn> danhSachDapAn = gson.fromJson(danhSachDapAnJson, new TypeToken<List<DapAn>>(){}.getType());
            CauHoi cauHoi = new CauHoi(tenCauHoi, idBaiTap);
            int idCauHoi = cauHoiService.addCauHoi(cauHoi);
            if(idCauHoi >= 0){
                // Thành công
                response.setStatus(HttpServletResponse.SC_OK); // 200
                response.getWriter().write("Thêm câu hỏi thành công!");
                for (DapAn dapAn : danhSachDapAn) {
                    try {
                        dapAn.setIdCauHoi(idCauHoi);
                        dapAnService.addDapAn(dapAn);
                        response.setStatus(HttpServletResponse.SC_OK); // 200
                        response.getWriter().write("Thêm đáp aán thành công!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
                        response.getWriter().write("Đã xảy ra lỗi máy chủ.");
                    }
                }
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
}
