package com.quizizz.english.quizizz_english.Servlets.admin.DapAn;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.model.BaiTap;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/DanhSachDapAn","/XoaDapAn"})
public class QuanLyDanhSachDapAn extends HttpServlet {
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
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String idCauHoi = request.getParameter("idCauHoi");
            // Tạo danh sách chủ đề dưới dạng List<Map>
            List<DapAn> dapAns = new ArrayList<>();
            dapAns = dapAnService.getAllCauHoiByIdBaiTap(Integer.parseInt(idCauHoi));
            Gson gson = new Gson();
            String json = gson.toJson(dapAns);
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCauHoi = request.getParameter("idCauHoi");
        try {
            int id = Integer.parseInt(idCauHoi);
            boolean isDeleted = dapAnService.deleteDapAn(id);

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
}
