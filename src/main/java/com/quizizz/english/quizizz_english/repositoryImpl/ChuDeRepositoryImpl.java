package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.ChuDe;
import com.quizizz.english.quizizz_english.repository.IChuDeRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChuDeRepositoryImpl implements IChuDeRepository {
    @Override
    public boolean insert(ChuDe item) {
        String sql = "INSERT INTO ChuDe(tenChuDe, moTa, hinhAnh)" + "VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenChuDe());
            stmt.setString(2, item.getMoTa());
            stmt.setString(3, item.getHinhAnh());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ChuDe item) {
        String sql = "UPDATE ChuDe SET tenChuDe = ?, moTa = ?, hinhAnh = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenChuDe());
            stmt.setString(2, item.getMoTa());
            stmt.setString(3, item.getHinhAnh());
            stmt.setInt(4, item.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int idChuDe) {
        String sql = "DELETE FROM ChuDe WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idChuDe);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ChuDe> getAll() {
        List<ChuDe> listChuDe = new ArrayList<>();
        String sql = "SELECT * FROM ChuDe";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listChuDe.add(mapResultSetToChuDe(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listChuDe;
    }

    @Override
    public ChuDe getById(int id) {
        String sql = "SELECT * FROM ChuDe WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToChuDe(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ChuDe mapResultSetToChuDe(ResultSet rs) throws SQLException {
        return new ChuDe(
                //Theo thứ tự param trong contructor 4 tham số
                rs.getInt("id"),
                rs.getString("tenChuDe"),
                rs.getString("moTa"),
                rs.getString("hinhAnh")
        );
    }
}
