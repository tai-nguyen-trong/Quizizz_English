package com.quizizz.english.quizizz_english.Servlets.admin.BaiTap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.dto.DataTableResponse;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.CapDoRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IBaiTapService;
import com.quizizz.english.quizizz_english.service.ICapDoService;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.CapDoServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet({"/DanhSachBaiTap"})
public class DanhSachBaiTapServlet extends HttpServlet {
    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;
    private ICapDoService capDoService;
    private Gson gson = new Gson();

    @Override
    public void init(ServletConfig config) throws ServletException {
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
        capDoService = new CapDoServiceImpl(new CapDoRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int idCapDo = Integer.parseInt(request.getParameter("idCapDo"));
        int idChuDe = Integer.parseInt(request.getParameter("idChuDe"));
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
        List<BaiTapDTO> baitaps = baiTapService.getAllBaiTap(start, length, searchValue, sortColumn, sortDirection,idCapDo,idChuDe);
        int totalRecords = baiTapService.getTotalRecords();

        // Tạo đối tượng DataTableResponse để trả về
        DataTableResponse<BaiTapDTO> dataTableResponse = new DataTableResponse<>(
                draw, totalRecords, totalRecords, baitaps
        );
        // Trả về JSON
        response.getWriter().write(gson.toJson(dataTableResponse));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
