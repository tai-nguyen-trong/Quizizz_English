package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
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
    public boolean addBaiTap(BaiTap baiTap) {
        //String sqlBaiTap = "SELECT * FROM BaiTap ORDER BY id DESC LIMIT 1";
        String insertSQL = "INSERT INTO baitap(maBaiTap, tenBaiTap, thoiGianLamBai, idChuDe, idCapDo)" + "VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                insertStmt.setString(1, baiTap.getMaBaiTap());
                insertStmt.setString(2, baiTap.getTenBaiTap());
                insertStmt.setDouble(3, baiTap.getThoiGianLamBai());
                insertStmt.setInt(4, baiTap.getIdChuDe());
                insertStmt.setInt(5, baiTap.getIdCapDo());
                if(insertStmt.executeUpdate() > 0){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBaiTap(BaiTap item) {

        String sql = "UPDATE baitap SET tenBaiTap = ?, thoiGianLamBai = ?, idChuDe = ?, idCapDo = ?  WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            //stmt.setString(1, item.getMaBaiTap());
            stmt.setString(1, item.getTenBaiTap());
            stmt.setDouble(2, item.getThoiGianLamBai());
            stmt.setInt(3, item.getIdChuDe());
            stmt.setInt(4, item.getIdCapDo());
            stmt.setInt(5, item.getId()); // Truyền ID vào WHERE clause
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public boolean deleteBaiTap(int id) {
        String sql = "DELETE FROM BaiTap WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public BaiTap getBaiTapMoiNhat() {
        String sql = "SELECT * FROM BaiTap ORDER BY id DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBaiTap(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BaiTapDTO> getAllBaiTap() {
        List<BaiTapDTO> danhSachBaiTap = new ArrayList<>();
        String sql = "SELECT bt.id, bt.maBaiTap, bt.tenBaiTap, bt.thoiGianLamBai, \n" +
                "               bt.idCapDo,bt.idChuDe,cd.tenCapDo, chude.tenChuDe\n" +
                "        FROM BaiTap bt\n" +
                "        JOIN CapDo cd ON bt.idCapDo = cd.id\n" +
                "        JOIN ChuDe chude ON bt.idChuDe = chude.id";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                BaiTapDTO baiTap = new BaiTapDTO(
                        rs.getInt("id"),
                        rs.getString("maBaiTap"),
                        rs.getString("tenBaiTap"),
                        rs.getObject("thoiGianLamBai") != null ? rs.getDouble("thoiGianLamBai") : null,
                        rs.getString("tenCapDo"),
                        rs.getString("tenChuDe"),
                        rs.getInt("idChuDe"),
                        rs.getInt("idCapDo")
                );
                danhSachBaiTap.add(baiTap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachBaiTap;
    }

    @Override
    public BaiTap getBaiTapById(int id) {
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
