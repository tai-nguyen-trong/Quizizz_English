package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;
import com.quizizz.english.quizizz_english.repository.ILichSuLamBaiRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LichSuLamBaiRepositoryImpl implements ILichSuLamBaiRepository {
    @Override
    public int insert(LichSuLamBai item) {
        String sql = "INSERT INTO LichSuLamBai(diem, idNguoiDung, idBaiTap, idChuDe)" + "VALUES(?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, item.getDiem());
            stmt.setInt(2, item.getIdNguoiDung());
            stmt.setInt(3, item.getIdBaiTap());
            stmt.setInt(4, item.getIdChuDe());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean update(LichSuLamBai item) {
        String sql = "UPDATE LichSuLamBai SET tenDapAn = ?, dapAnDung = ?, idCauHoi = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, item.getDiem());
            stmt.setInt(2, item.getIdNguoiDung());
            stmt.setInt(3, item.getIdBaiTap());
            stmt.setInt(4, item.getIdChuDe());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int idLichSuLamBai) {
        String sql = "DELETE FROM LichSuLamBai WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLichSuLamBai);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LichSuLamBai> getAll() {
        List<LichSuLamBai> listLichSuLamBai = new ArrayList<>();
        String sql = "SELECT * FROM LichSuLamBai";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listLichSuLamBai.add(mapResultSetToLichSuLamBai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLichSuLamBai;
    }

    @Override
    public LichSuLamBai getById(int id) {
        String sql = "SELECT * FROM LichSuLamBai WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToLichSuLamBai(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LichSuLamBai mapResultSetToLichSuLamBai(ResultSet rs) throws SQLException {
        return new LichSuLamBai(
                //Theo thứ tự param trong contructor 5 tham số
                rs.getInt("id"),
                rs.getDouble("diem"),
                rs.getInt("idNguoiDung"),
                rs.getInt("idBaiTap"),
                rs.getInt("idChuDe")
        );
    }
}
