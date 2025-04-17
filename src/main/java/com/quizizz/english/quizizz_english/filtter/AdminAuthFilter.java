package com.quizizz.english.quizizz_english.filtter;

import com.quizizz.english.quizizz_english.model.NguoiDung;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AdminAuthFilter implements Filter {
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/login", "/login.jsp", "/register", "/register.jsp", "/home", "/", "/assets"
    );

    /*private static final List<String> ADMIN_PATHS = Arrays.asList(

    );*/

    private static final List<String> GIAOVIEN_PATHS = Arrays.asList(
            "/QuanLyDanhSachBaiTap", "/QuanLyDanhSachCapDo","/QuanLyDanhSachCauHoi","/QuanLyDanhSachChuDe"
    );

    private static final List<String> NGUOIDUNG_PATHS = Arrays.asList(

    );
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        // Nếu là trang public thì cho qua
        if (PUBLIC_PATHS.contains(path)) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Chưa đăng nhập
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        // Đã login, kiểm tra vai trò
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("user");
        String vaiTro = nguoiDung.getVaiTro();
        if (GIAOVIEN_PATHS.contains(path) && !"GiaoVien".equals(vaiTro)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/access-denied.jsp");
            return;
        }
        /*if (NGUOIDUNG_PATHS.contains(path) && !"NguoiDung".equals(vaiTro)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied.jsp");
            return;
        }*/
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên khi filter bị hủy
    }
}
