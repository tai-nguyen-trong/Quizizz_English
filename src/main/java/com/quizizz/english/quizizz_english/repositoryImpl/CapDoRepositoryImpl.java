package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.CapDo;
import com.quizizz.english.quizizz_english.repository.ICapDoRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapDoRepositoryImpl implements ICapDoRepository {
    @Override
    public void insert(CapDo item) {
        String sql = "INSERT INTO CapDo(tenCapDo)" + "VALUES(?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenCapDo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CapDo item) {
        String sql = "UPDATE CapDo SET tenCapDo = ?  WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTenCapDo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CapDo item) {
        String sql = "DELETE FROM CapDo WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CapDo> getAll() {
        List<CapDo> listCapDo = new ArrayList<>();
        String sql = "SELECT * FROM CapDo";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listCapDo.add(mapResultSetToCapDo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCapDo;
    }

    @Override
    public CapDo getById(int id) {
        String sql = "SELECT * FROM CapDo WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCapDo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CapDo mapResultSetToCapDo(ResultSet rs) throws SQLException {
        return new CapDo(
                //Theo thứ tự param trong contructor 2 tham số
                rs.getInt("id"),
                rs.getString("tenCapDo")
        );
    }
}
