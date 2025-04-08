package com.quizizz.english.quizizz_english.Servlets.admin.CauHoi;

import com.google.gson.Gson;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.dto.DataTableResponse;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.CapDo;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.model.ChuDe;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.CapDoRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.CauHoiRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IBaiTapService;
import com.quizizz.english.quizizz_english.service.ICapDoService;
import com.quizizz.english.quizizz_english.service.ICauHoiService;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.CapDoServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.CauHoiServiceImpl;
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
import java.util.List;

@WebServlet({"/QuanLyDanhSachCauHoi"})
public class QuanLyDanhSachCauHoi extends HttpServlet {
    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;
    private ICapDoService capDoService;
    private ICauHoiService cauHoiService;
    private Gson gson = new Gson();
    @Override
    public void init(ServletConfig config) throws ServletException {
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
        capDoService = new CapDoServiceImpl(new CapDoRepositoryImpl());
        cauHoiService = new CauHoiServiceImpl(new CauHoiRepositoryImpl());
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String idBaiTap = request.getParameter("idBaiTap");
        // 🔹 Nhận tham số từ DataTable

        int draw = Integer.parseInt(request.getParameter("draw"));
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        String searchValue = request.getParameter("search[value]");
        String sortColumnIndex = request.getParameter("order[0][column]");
        String sortDirection = request.getParameter("order[0][dir]");

        // Lấy tên cột để sắp xếp từ DataTable
        String sortColumn = request.getParameter("columns[" + sortColumnIndex + "][data]");

        // Lấy danh sách có tìm kiếm, sắp xếp, phân trang
        List<CauHoiDTO> cauhois = cauHoiService.getAllCauHoi(start, length, searchValue, sortColumn, sortDirection,idBaiTap);
        int totalRecords = baiTapService.getTotalRecords();

        // Tạo đối tượng DataTableResponse để trả về
        DataTableResponse<CauHoiDTO> dataTableResponse = new DataTableResponse<>(
                draw, totalRecords, totalRecords, cauhois
        );
        // Trả về JSON
        response.getWriter().write(gson.toJson(dataTableResponse));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
