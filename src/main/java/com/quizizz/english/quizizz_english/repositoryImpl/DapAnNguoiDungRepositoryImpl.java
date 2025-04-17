package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.DapAnNguoiDung;
import com.quizizz.english.quizizz_english.repository.IDapAnNguoiDungRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DapAnNguoiDungRepositoryImpl implements IDapAnNguoiDungRepository {
    @Override
    public boolean insert(DapAnNguoiDung item) {
        String sql = "INSERT INTO DapAnNguoiDung(cauDung, idBaiTap, idCauHoi, idDapAn,idLichSuLamBai)" + "VALUES(?, ?, ?, ?,?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, item.getCauDung());
            stmt.setInt(2, item.getIdBaiTap());
            stmt.setInt(3, item.getIdCauHoi());
            stmt.setInt(4, item.getIdDapAn());
            stmt.setInt(5, item.getIdLichSuLamBai());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(DapAnNguoiDung item) {
        String sql = "UPDATE DapAnNguoiDung SET cauDung = ?, idBaiTap = ?, idCauHoi = ?, idDapAn = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, item.getCauDung());
            stmt.setInt(2, item.getIdBaiTap());
            stmt.setInt(3, item.getIdCauHoi());
            stmt.setInt(4, item.getIdDapAn());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(int idDapAnNguoiDung) {
        String sql = "DELETE FROM DapAnNguoiDung WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDapAnNguoiDung);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<DapAnNguoiDung> getAll() {
        List<DapAnNguoiDung> listDapAnNguoiDung = new ArrayList<>();
        String sql = "SELECT * FROM DapAnNguoiDung";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listDapAnNguoiDung.add(mapResultSetToDapAnNguoiDung(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDapAnNguoiDung;
    }

    @Override
    public DapAnNguoiDung getById(int id) {
        String sql = "SELECT * FROM DapAnNguoiDung WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDapAnNguoiDung(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DapAnNguoiDung mapResultSetToDapAnNguoiDung(ResultSet rs) throws SQLException {
        return new DapAnNguoiDung(
                //Theo thứ tự param trong contructor 5 tham số
                rs.getInt("id"),
                rs.getBoolean("cauDung"),
                rs.getInt("idBaiTap"),
                rs.getInt("idCauHoi"),
                rs.getInt("idDapAn")
        );
    }
}
