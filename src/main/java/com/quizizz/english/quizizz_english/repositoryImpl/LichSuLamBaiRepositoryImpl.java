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
    public List<KetQuaDTO> getKetQuaLamBai(int idLichSu) {
        List<KetQuaDTO> list = new ArrayList<>();
        String sql = "SELECT dan.idCauHoi, da1.tenDapAn AS dapAnNguoiDungDaChon, " +
                "dan.cauDung, da2.tenDapAn AS dapAnDung " +
                "FROM dapannguoidung dan " +
                "JOIN dapan da1 ON dan.idDapAn = da1.id " +
                "JOIN dapan da2 ON da2.idCauHoi = dan.idCauHoi AND da2.dapAnDung = true " +
                "WHERE dan.idLichSuLamBai = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLichSu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    KetQuaDTO kq = new KetQuaDTO();
                    kq.setIdCauHoi(rs.getInt("idCauHoi"));
                    kq.setDapAnNguoiDungChon(rs.getString("dapAnNguoiDungDaChon"));
                    kq.setCauDung(rs.getBoolean("cauDung"));
                    kq.setDapAnDung(rs.getString("dapAnDung"));
                    list.add(kq);
                }
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
