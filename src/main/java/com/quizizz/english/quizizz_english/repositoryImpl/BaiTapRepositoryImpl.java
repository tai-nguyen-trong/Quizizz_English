package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.repository.IBaiTapRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaiTapRepositoryImpl implements IBaiTapRepository {
    @Override
    public void insert(BaiTap item) {
        String sql = "INSERT INTO Book(maBaiTap, tenBaiTap, thoiGianLamBai, idChuDe, idCapDo)" + "VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getMaBaiTap());
            stmt.setString(2, item.getTenBaiTap());
            stmt.setDouble(3, item.getThoiGianLamBai());
            stmt.setInt(4, item.getIdChuDe());
            stmt.setInt(5, item.getIdCapDo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BaiTap item) {
        String sql = "UPDATE Book SET maBaiTap = ?, tenBaiTap = ?, thoiGianLamBai = ?, idChuDe = ?, idCapDo = ?  WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getMaBaiTap());
            stmt.setString(2, item.getTenBaiTap());
            stmt.setDouble(3, item.getThoiGianLamBai());
            stmt.setInt(4, item.getIdChuDe());
            stmt.setInt(5, item.getIdCapDo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(BaiTap item) {
        String sql = "DELETE FROM BaiTap WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BaiTap> getAll() {
        List<BaiTap> listBaiTap = new ArrayList<>();
        String sql = "SELECT * FROM BaiTap";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listBaiTap.add(mapResultSetToBaiTap(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBaiTap;
    }

    @Override
    public BaiTap getById(int id) {
        String sql = "SELECT * FROM BaiTap WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBaiTap(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BaiTap mapResultSetToBaiTap(ResultSet rs) throws SQLException {
        return new BaiTap(
                //Theo thứ tự param trong contructor 6 tham số
                rs.getInt("id"),
                rs.getString("maBaiTap"),
                rs.getString("tenBaiTap"),
                rs.getDouble("thoiGianLamBai"),
                rs.getInt("idChuDe"),
                rs.getInt("idCapDo")
        );
    }
}
