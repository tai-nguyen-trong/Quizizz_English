package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.repository.ICauHoiRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CauHoiRepositoryImpl implements ICauHoiRepository {
    @Override
    public int insert(CauHoi item) {
        String sql = "INSERT INTO CauHoi(maCauHoi, tenCauHoi, idBaiTap)" + "VALUES(?, ?, ?)";
        int id = -1;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getMaCauHoi());
            stmt.setString(2, item.getTenCauHoi());
            stmt.setInt(3, item.getIdBaiTap());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1); // lấy id tự tăng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean update(CauHoi item) {
        String sql = "UPDATE CauHoi SET  tenCauHoi = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getTenCauHoi());
            stmt.setInt(2,item.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int idCauHoi) {
        String sql = "DELETE FROM CauHoi WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,idCauHoi);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CauHoiDTO> getAll() {
        List<CauHoiDTO> danhSachCauHoi = new ArrayList<>();
        String sql = "SELECT cauhoi.id, cauhoi.maCauHoi, cauhoi.tenCauHoi, " +
                "cauhoi.idBaiTap, baitap.maBaiTap, baitap.tenBaiTap " +
                "FROM CauHoi cauhoi " +
                "JOIN BaiTap baitap ON cauhoi.idBaiTap = baitap.id";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CauHoiDTO cauhoi = new CauHoiDTO(
                        rs.getInt("id"),
                        rs.getString("maCauHoi"),
                        rs.getString("tenCauHoi"),
                        rs.getInt("idBaiTap"),
                        rs.getString("maBaiTap"),
                        rs.getString("tenBaiTap")
                );
                danhSachCauHoi.add(cauhoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachCauHoi;
    }

    @Override
    public CauHoi getById(int id) {
        String sql = "SELECT * FROM CauHoi WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCauHoi(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CauHoiDTO> getAllByIdBaiTap(Integer idBaiTap) {
        List<CauHoiDTO> danhSachCauHoi = new ArrayList<>();
        String sql = "SELECT cauhoi.id, cauhoi.maCauHoi, cauhoi.tenCauHoi, " +
                "cauhoi.idBaiTap, baitap.maBaiTap, baitap.tenBaiTap " +
                "FROM CauHoi cauhoi " +
                "JOIN BaiTap baitap ON cauhoi.idBaiTap = baitap.id WHERE cauhoi.idBaiTap = ? ";
        // Nếu có truyền idBaiTap thì thêm điều kiện

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
                stmt.setInt(1, idBaiTap);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CauHoiDTO cauhoi = new CauHoiDTO(
                        rs.getInt("id"),
                        rs.getString("maCauHoi"),
                        rs.getString("tenCauHoi"),
                        rs.getInt("idBaiTap"),
                        rs.getString("maBaiTap"),
                        rs.getString("tenBaiTap")
                );
                danhSachCauHoi.add(cauhoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachCauHoi;
    }

    @Override
    public CauHoi getCauHoiMoiNhat() {
        String sql = "SELECT * FROM cauhoi ORDER BY id DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCauHoi(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CauHoi mapResultSetToCauHoi(ResultSet rs) throws SQLException {
        return new CauHoi(
                //Theo thứ tự param trong contructor 4 tham số
                rs.getInt("id"),
                rs.getString("maCauHoi"),
                rs.getString("tenCauHoi"),
                rs.getInt("idBaiTap")
        );
    }
}
