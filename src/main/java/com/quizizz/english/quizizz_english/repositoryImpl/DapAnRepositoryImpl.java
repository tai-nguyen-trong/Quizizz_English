package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.repository.IDapAnRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DapAnRepositoryImpl implements IDapAnRepository {
    @Override
    public boolean insert(DapAn item) {
        String sql = "INSERT INTO DapAn(tenDapAn, dapAnDung, idCauHoi)" + "VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenDapAn());
            stmt.setBoolean(2, item.getDapAnDung());
            stmt.setInt(3, item.getIdCauHoi());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(DapAn item) {
        String sql = "UPDATE DapAn SET tenDapAn = ?, dapAnDung = ?, idCauHoi = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenDapAn());
            stmt.setBoolean(2, item.getDapAnDung());
            stmt.setInt(3, item.getIdCauHoi());
            stmt.setInt(4, item.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int idDapAn) {
        String sql = "DELETE FROM DapAn WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDapAn);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<DapAn> getAllCauHoiByIdBaiTap(Integer idCauHoi) {
        List<DapAn> dapAnList = new ArrayList<>();
        String sql = "SELECT * FROM DapAn WHERE idCauHoi = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCauHoi);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DapAn dapAn = mapResultSetToDapAn(rs);
                dapAnList.add(dapAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dapAnList;
    }

    private DapAn mapResultSetToDapAn(ResultSet rs) throws SQLException {
        return new DapAn(
                //Theo thứ tự param trong contructor 4 tham số
                rs.getInt("id"),
                rs.getString("tenDapAn"),
                rs.getBoolean("dapAnDung"),
                rs.getInt("idCauHoi")
        );
    }


}
