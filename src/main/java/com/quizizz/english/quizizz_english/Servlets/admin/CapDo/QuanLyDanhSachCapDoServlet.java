package com.quizizz.english.quizizz_english.Servlets.admin.CapDo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quizizz.english.quizizz_english.model.CapDo;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.repositoryImpl.CapDoRepositoryImpl;
import com.quizizz.english.quizizz_english.service.ICapDoService;
import com.quizizz.english.quizizz_english.serviceImpl.CapDoServiceImpl;
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
@WebServlet({"/QuanLyDanhSachCapDo","/XoaCapDo","/ThemCapDo"})
public class QuanLyDanhSachCapDoServlet extends HttpServlet {

    private ICapDoService capDoService;
    private Gson gson = new Gson();
    @Override
    public void init(ServletConfig config) throws ServletException {
        capDoService = new CapDoServiceImpl(new CapDoRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CapDo> capDos = new ArrayList<>();
            capDos = capDoService.getAllCapDo();
            request.setAttribute("capDos", capDos);
            request.setAttribute("currentPage", "QuanLyDanhSachCapDo");
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
            String tenCapDo = request.getParameter("tenCapDo");
            // Parse chuỗi JSON thành List<DapAnDTO>
            Gson gson = new Gson();
            CapDo capdo = new CapDo(tenCapDo);
            boolean isSuccess = capDoService.addCapDo(capdo);
            if (isSuccess) {
                response.setStatus(HttpServletResponse.SC_OK); // 200
            }else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 200
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCapDo = request.getParameter("idCapDo");
        try {
            int id = Integer.parseInt(idCapDo);
            boolean isDeleted = capDoService.deleteCapDo(id);

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
