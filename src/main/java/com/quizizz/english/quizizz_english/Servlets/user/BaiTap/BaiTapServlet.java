package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BaiTap")
public class BaiTapServlet extends HttpServlet {
    private IChuDeService chuDeService;
    private ICapDoService capDoService;
    private IBaiTapService baiTapService;

    @Override
    public void init() throws ServletException {
        chuDeService = new ChuDeServiceImpl(new ChuDeRepositoryImpl());
        capDoService = new CapDoServiceImpl(new CapDoRepositoryImpl());
        baiTapService = new BaiTapServiceImpl(new BaiTapRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ChuDe> chuDes = chuDeService.getAllChuDe();
        req.setAttribute("chuDes", chuDes);
        List<CapDo> capDos = capDoService.getAllCapDo();
        req.setAttribute("capDos", capDos);

        //Lấy tham số từ URL, idChuDeParam là tham số từ URL để lọc theo chủ đề: /BaiTap?idChuDe=3 → idChuDeParam = "3"
        // . idCapDoParam là tham số để lọc theo cấp độ: /BaiTap?idCapDo=2 → idCapDoParam = "2"
        String idChuDeParam = req.getParameter("idChuDe");
        String idCapDoParam = req.getParameter("idCapDo");

//        int idChuDe = 1; // Mặc định
//        int idCapDo = 1; // Mặc định
        int idChuDe = 0;
        int idCapDo = 0;

        if (idChuDeParam != null && !idChuDeParam.isEmpty()) {
            idChuDe = Integer.parseInt(idChuDeParam);
        }
        if (idCapDoParam != null && !idCapDoParam.isEmpty()) {
            idCapDo = Integer.parseInt(idCapDoParam);
        }
//        if (idChuDeParam != null && !idChuDeParam.isEmpty()) {
//            idChuDe = Integer.parseInt(idChuDeParam);
//        }
//
//        if (idCapDoParam != null && !idCapDoParam.isEmpty()) {
//            idCapDo = Integer.parseInt(idCapDoParam);
//        }

        List<BaiTap> baiTaps;

//        if (idChuDeParam != null && !idChuDeParam.isEmpty()) {
//            idChuDe = Integer.parseInt(idChuDeParam);
////            req.setAttribute("idChuDe", idChuDe);
//
//            if (idCapDoParam != null && !idCapDoParam.isEmpty()) {
//                idCapDo = Integer.parseInt(idCapDoParam);
//                baiTaps = baiTapService.getBaiTapTheoChuDeVaCapDo(idChuDe, idCapDo);
//            } else {
//                baiTaps = baiTapService.getBaiTapTheoChuDe(idChuDe);
//            }
//        } else {
//            if (idCapDoParam != null && !idCapDoParam.isEmpty()) {
//                idCapDo = Integer.parseInt(idCapDoParam);
//            }
//            baiTaps = baiTapService.getBaiTapTheoCapDo(idCapDo);
//        }
        if (idChuDe > 0 && idCapDo > 0) {
            baiTaps = baiTapService.getBaiTapTheoChuDeVaCapDo(idChuDe, idCapDo);
        } else if (idChuDe > 0) {
            baiTaps = baiTapService.getBaiTapTheoChuDe(idChuDe);
        } else if (idCapDo > 0) {
            baiTaps = baiTapService.getBaiTapTheoCapDo(idCapDo);
        } else {
            baiTaps = baiTapService.tatCaBaiTap();
        }
//        if (idChuDeParam != null && !idChuDeParam.isEmpty() && idCapDoParam != null && !idCapDoParam.isEmpty()) {
//            baiTaps = baiTapService.getBaiTapTheoChuDeVaCapDo(idChuDe, idCapDo);
//        } else if (idChuDeParam != null && !idChuDeParam.isEmpty()) {
//            baiTaps = baiTapService.getBaiTapTheoChuDe(idChuDe);
//        } else if (idCapDoParam != null && !idCapDoParam.isEmpty()) {
//            baiTaps = baiTapService.getBaiTapTheoCapDo(idCapDo);
//        } else {
//            baiTaps = baiTapService.tatCaBaiTap();
//        }

        req.setAttribute("idChuDe", idChuDe);
        req.setAttribute("idCapDo", idCapDo);
//        System.out.println("ID Chu De: " + idChuDe);
//        System.out.println("ID Cap Do: " + idCapDo);
        req.setAttribute("baiTaps", baiTaps);
        req.setAttribute("currentPage", "BaiTap");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(req, resp);
    }
}
