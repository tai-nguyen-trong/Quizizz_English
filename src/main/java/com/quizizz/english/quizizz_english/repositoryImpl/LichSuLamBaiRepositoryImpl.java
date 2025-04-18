package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.dto.KetQuaDTO;
import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;
import com.quizizz.english.quizizz_english.repository.ILichSuLamBaiRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LichSuLamBaiRepositoryImpl implements ILichSuLamBaiRepository {
    @Override
    public int insert(LichSuLamBai item) {
        String sql = "INSERT INTO LichSuLamBai(diem, idNguoiDung, idBaiTap, idChuDe)" + "VALUES(?, ?, ?, ?)";
        int id = -1;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, item.getDiem());
            stmt.setInt(2, item.getIdNguoiDung());
            stmt.setInt(3, item.getIdBaiTap());
            stmt.setInt(4, item.getIdChuDe());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
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

    @Override
    public List<LichSuLamBaiDTO> getAllLichSuLamBai() {
        return List.of();
    }

    @Override
    public List<KetQuaDTO> getKetQuaLamBai(int idLichSu,int idBaiTap) {
        List<KetQuaDTO> list = new ArrayList<>();
        String sql = "SELECT \n" +
                "    ch.id AS idCauHoi,\n" +
                "    ch.tenCauHoi AS tenCauHoi,\n" +
                "    da1.tenDapAn AS dapAnNguoiDungDaChon,\n" +
                "    da2.tenDapAn AS dapAnDung,\n" +
                "    CASE \n" +
                "        WHEN da1.id = da2.id THEN true \n" +
                "        ELSE false \n" +
                "    END AS cauDung,\n" +
                "    ls.diem AS soDiem,\n" +
                "    bt.tenBaiTap AS tenBaiTap,\n" +
                "    t.tenChuDe AS tenChuDe\n" +
                "FROM cauhoi ch\n" +
                "JOIN dapan da2 ON da2.idCauHoi = ch.id AND da2.dapAnDung = true\n" +
                "LEFT JOIN dapannguoidung dan ON dan.idCauHoi = ch.id AND dan.idLichSuLamBai = ?\n" +
                "LEFT JOIN dapan da1 ON dan.idDapAn = da1.id\n" +
                "JOIN baitap bt ON bt.id = ch.idBaiTap  -- Thêm bảng baidap để lấy tên bài tập\n" +
                "JOIN chuDe t ON t.id = bt.idChuDe      -- Thêm bảng chuDe để lấy tên chủ đề\n" +
                "LEFT JOIN lichsulambai ls ON ls.id = dan.idLichSuLamBai -- Giữ lại phần liên kết lịch sử làm bài\n" +
                "WHERE ch.idBaiTap = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLichSu);
            stmt.setInt(2, idBaiTap);
            ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    KetQuaDTO kq = new KetQuaDTO();
                    kq.setIdCauHoi(rs.getInt("idCauHoi"));
                    kq.setTenCauHoi(rs.getString("tenCauHoi"));
                    kq.setDapAnNguoiDungChon(rs.getString("dapAnNguoiDungDaChon"));
                    kq.setCauDung(rs.getBoolean("cauDung"));
                    kq.setDapAnDung(rs.getString("dapAnDung"));
                    kq.setTenBaiTap(rs.getString("tenBaiTap"));
                    kq.setTenChuDe(rs.getString("tenChuDe"));
                    kq.setSoDiem(rs.getDouble("soDiem"));
                    list.add(kq);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
