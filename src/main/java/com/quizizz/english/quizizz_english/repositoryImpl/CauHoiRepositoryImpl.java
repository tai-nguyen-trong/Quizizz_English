package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.repository.ICauHoiRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CauHoiRepositoryImpl implements ICauHoiRepository {
    @Override
    public void insert(CauHoi item) {
        String sql = "INSERT INTO CauHoi(maCauHoi, tenCauHoi, idBaiTap)" + "VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getMaCauHoi());
            stmt.setString(2, item.getTenCauHoi());
            stmt.setInt(3, item.getIdBaiTap());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CauHoi item) {
        String sql = "UPDATE CauHoi SET maCauHoi = ?, tenCauHoi = ?, idBaiTap = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getMaCauHoi());
            stmt.setString(2, item.getTenCauHoi());
            stmt.setInt(3, item.getIdBaiTap());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CauHoi item) {
        String sql = "DELETE FROM CauHoi WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CauHoi> getAll() {
        List<CauHoi> listBaiTap = new ArrayList<>();
        String sql = "SELECT * FROM CauHoi";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listBaiTap.add(mapResultSetToCauHoi(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBaiTap;
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
