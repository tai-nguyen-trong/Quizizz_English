package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quizizz.english.quizizz_english.dto.KetQuaDTO;
import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repositoryImpl.BaiTapRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.CapDoRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.ChuDeRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.LichSuLamBaiRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IBaiTapService;
import com.quizizz.english.quizizz_english.service.ICapDoService;
import com.quizizz.english.quizizz_english.service.IChuDeService;
import com.quizizz.english.quizizz_english.service.ILichSuLamBaiService;
import com.quizizz.english.quizizz_english.serviceImpl.BaiTapServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.CapDoServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.ChuDeServiceImpl;
import com.quizizz.english.quizizz_english.serviceImpl.LichSuLamBaiServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/NopBaiTap")
public class NopBaiTapServlet extends HttpServlet {
    private IBaiTapService baiTapService;
    private IChuDeService chuDeService;
    private ILichSuLamBaiService lichSuLamBaiService;
    private Gson gson = new Gson(); // Khởi tạo Gson
    @Override
    public void init(ServletConfig config) throws ServletException {
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
        lichSuLamBaiService = new LichSuLamBaiServiceImpl(new LichSuLamBaiRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("user");
        int idBaiTap = Integer.parseInt(req.getParameter("idBaiTap"));
        int idChuDe = Integer.parseInt(req.getParameter("idChuDe"));
        String danhSachDapAnJson = req.getParameter("danhSachDapAn");
        Gson gson = new Gson();
        Type type = new TypeToken<Map<Integer, Integer>>(){}.getType();
        // danh sách người dùng chọn
        Map<Integer, Integer> cauHoiVaDapAn = gson.fromJson(danhSachDapAnJson, type);


        int idLichSu = lichSuLamBaiService.addLichSuLamBai(nguoiDung.getId(), idBaiTap, idChuDe, cauHoiVaDapAn);
        PrintWriter out = resp.getWriter();
        out.print("{\"idLichSuLamBaiTap\": " + idLichSu + ", \"idBaiTap\": " + idBaiTap + "}");
        out.flush();
    }
}
