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
    public void insert(DapAn item) {
        String sql = "INSERT INTO DapAn(tenDapAn, dapAnDung, idCauHoi)" + "VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenDapAn());
            stmt.setBoolean(2, item.getDapAnDung());
            stmt.setInt(3, item.getIdCauHoi());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DapAn item) {
        String sql = "UPDATE DapAn SET tenDapAn = ?, dapAnDung = ?, idCauHoi = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenDapAn());
            stmt.setBoolean(2, item.getDapAnDung());
            stmt.setInt(3, item.getIdCauHoi());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DapAn item) {
        String sql = "DELETE FROM DapAn WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DapAn> getAll() {
        List<DapAn> listDapAn = new ArrayList<>();
        String sql = "SELECT * FROM DapAn";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listDapAn.add(mapResultSetToDapAn(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDapAn;
    }

    @Override
    public DapAn getById(int id) {
        String sql = "SELECT * FROM DapAn WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDapAn(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
