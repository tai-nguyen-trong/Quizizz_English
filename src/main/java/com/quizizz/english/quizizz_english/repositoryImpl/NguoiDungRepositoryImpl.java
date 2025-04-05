package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repository.INguoiDungRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NguoiDungRepositoryImpl implements INguoiDungRepository {
    @Override
    public void insert(NguoiDung item) {
        String checkEmailSQL = "SELECT COUNT(*) FROM NguoiDung WHERE email = ?";
        String insertSQL = "INSERT INTO NguoiDung (hoVaTen, tuoi, email, matKhau, soDienThoai, vaiTro) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkEmailSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {

            checkStmt.setString(1, item.getEmail());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Email đã tồn tại!");
            }

            String hashedPassword = BCrypt.hashpw(item.getMatKhau(), BCrypt.gensalt());

            insertStmt.setString(1, item.getHoVaTen());
            insertStmt.setInt(2, item.getTuoi());
            insertStmt.setString(3, item.getEmail());
            insertStmt.setString(4, hashedPassword);
            insertStmt.setString(5, item.getSoDienThoai());
            insertStmt.setString(6, item.getVaiTro());

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm người dùng thành công!");
            } else {
                System.out.println("Thêm người dùng thất bại!");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Lỗi thêm người dùng", e);
            System.out.println("Lỗi hệ thống!");
        }
    }

    @Override
    public void update(NguoiDung item) {
        String sql = "UPDATE NguoiDung SET hoVaTen = ?, tuoi = ?, email = ?, matKhau = ?, soDienThoai = ?, vaiTro = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getHoVaTen());
            stmt.setInt(2, item.getTuoi());
            stmt.setString(3, item.getEmail());
            stmt.setString(4, item.getMatKhau());
            stmt.setString(5, item.getSoDienThoai());
            stmt.setString(6, item.getVaiTro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(NguoiDung item) {
        String sql = "DELETE FROM NguoiDung WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NguoiDung> getAll() {
        List<NguoiDung> listNguoiDung = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listNguoiDung.add(mapResultSetToNguoiDung(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNguoiDung;
    }

    @Override
    public NguoiDung getById(int id) {
        String sql = "SELECT * FROM NguoiDung WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToNguoiDung(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private NguoiDung mapResultSetToNguoiDung(ResultSet rs) throws SQLException {
        return new NguoiDung(
                rs.getInt("id"),
                rs.getString("hoVaTen"),
                rs.getInt("tuoi"),
                rs.getString("email"),
                rs.getString("matKhau"),
                rs.getString("soDienThoai"),
                rs.getString("vaiTro")
        );
    }

    @Override
    public void dangKy(NguoiDung nguoiDung) {
        String checkEmailSQL = "SELECT COUNT(*) FROM NguoiDung WHERE email = ?";
        String insertSQL = "INSERT INTO NguoiDung (hoVaTen, tuoi, email, matKhau, soDienThoai, vaiTro) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkEmailSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {

            checkStmt.setString(1, nguoiDung.getEmail());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Email đã tồn tại!");
            }

            String hashedPassword = BCrypt.hashpw(nguoiDung.getMatKhau(), BCrypt.gensalt());

            insertStmt.setString(1, nguoiDung.getHoVaTen());
            insertStmt.setInt(2, nguoiDung.getTuoi());
            insertStmt.setString(3, nguoiDung.getEmail());
            insertStmt.setString(4, hashedPassword);
            insertStmt.setString(5, nguoiDung.getSoDienThoai());
            insertStmt.setString(6, "NguoiDung");

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Đăng ký thành công!");
            } else {
                System.out.println("Đăng ký thất bại!");
            }

        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Lỗi đăng ký người dùng", e);
            System.out.println("Lỗi hệ thống!");
        }
    }

    @Override
    public NguoiDung dangNhap(String email, String matKhau) {
        String sql = "SELECT * FROM NguoiDung WHERE email = ?";
        NguoiDung nguoiDung = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String hashedPasswordFromDB = rs.getString("matKhau");

                    if (BCrypt.checkpw(matKhau, hashedPasswordFromDB)) {
                        nguoiDung = new NguoiDung(
                                rs.getString("hoVaTen"),
                                rs.getInt("tuoi"),
                                rs.getString("email"),
                                rs.getString("soDienThoai")
                        );
                        System.out.println("Đăng nhập thành công: " + nguoiDung.getHoVaTen());
                    } else {
                        System.out.println("Sai mật khẩu.");
                    }
                } else {
                    System.out.println("Không tìm thấy tài khoản với email này.");
                }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Sai thông tin đăng nhập", e);
        }
        return nguoiDung;
    }
}
