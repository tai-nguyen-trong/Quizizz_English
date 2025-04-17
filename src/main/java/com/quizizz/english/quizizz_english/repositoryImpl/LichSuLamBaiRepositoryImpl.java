package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
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
//    @Override
//    public void insert(LichSuLamBai item) {
//        String sql = "INSERT INTO LichSuLamBai(diem, idNguoiDung, idBaiTap, idChuDe)" + "VALUES(?, ?, ?, ?)";
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setDouble(1, item.getDiem());
//            stmt.setInt(2, item.getIdNguoiDung());
//            stmt.setInt(3, item.getIdBaiTap());
//            stmt.setInt(4, item.getIdChuDe());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(LichSuLamBai item) {
//        String sql = "UPDATE LichSuLamBai SET tenDapAn = ?, dapAnDung = ?, idCauHoi = ? WHERE id = ?";
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setDouble(1, item.getDiem());
//            stmt.setInt(2, item.getIdNguoiDung());
//            stmt.setInt(3, item.getIdBaiTap());
//            stmt.setInt(4, item.getIdChuDe());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void delete(LichSuLamBai item) {
//        String sql = "DELETE FROM LichSuLamBai WHERE id = ?";
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, item.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
    @Override
    public List<LichSuLamBaiDTO> getAllLichSuLamBai() {
        List<LichSuLamBaiDTO> lichSuLamBais = new ArrayList<>();
//        String sql = "SELECT * FROM LichSuLamBai";

        String sql = "SELECT bt.id, bt.maBaiTap, bt.tenBaiTap, bt.thoiGianLamBai, \n" +
                "               bt.idCapDo,bt.idChuDe,cd.tenCapDo, chude.tenChuDe\n" +
                "        FROM BaiTap bt\n" +
                "        JOIN CapDo cd ON bt.idCapDo = cd.id\n" +
                "        JOIN ChuDe chude ON bt.idChuDe = chude.id";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LichSuLamBaiDTO lichSuLamBai = new LichSuLamBaiDTO(
                        rs.getInt("id"),
                        rs.getDouble("diem"),
                        rs.getDouble("thoiGianLamBai"),
                        rs.getString("tenBaiTap"),
                        rs.getString("tenChuDe"),
                        rs.getString("tenCapDo"),
                        rs.getInt("idNguoiDung"),
                        rs.getInt("idBaiTap"),
                        rs.getInt("idChuDe")
                );
                lichSuLamBais.add(lichSuLamBai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lichSuLamBais;
    }
//
//    @Override
//    public LichSuLamBai getById(int id) {
//        String sql = "SELECT * FROM LichSuLamBai WHERE id = ?";
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return mapResultSetToLichSuLamBai(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private LichSuLamBai mapResultSetToLichSuLamBai(ResultSet rs) throws SQLException {
//        return new LichSuLamBai(
//                //Theo thứ tự param trong contructor 5 tham số
//                rs.getInt("id"),
//                rs.getDouble("diem"),
//                rs.getInt("idNguoiDung"),
//                rs.getInt("idBaiTap"),
//                rs.getInt("idChuDe")
//        );
//    }
}
