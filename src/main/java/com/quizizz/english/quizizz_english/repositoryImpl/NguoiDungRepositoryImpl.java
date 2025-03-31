package com.quizizz.english.quizizz_english.repositoryImpl;
import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repository.INguoiDungRepository;
import com.quizizz.english.quizizz_english.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungRepositoryImpl implements INguoiDungRepository {
    @Override
    public void insert(NguoiDung item) {
        String sql = "INSERT INTO NguoiDung(hoVaTen, tuoi, email, matKhau, soDienThoai, vaiTro)" + "VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getHoVaTen());
            stmt.setInt(2, item.getTuoi());
            stmt.setString(3, item.getEmail());
            stmt.setString(4, item.getMatKhau());
            stmt.setString(5, item.getSoDienThoai());
            stmt.setString(6, item.getVaiTro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(NguoiDung item) {
        String sql = "UPDATE NguoiDung SET hoVaTen = ?, tuoi = ?, email = ?, matKhau = ?, soDienThoai = ?, vaiTro = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getHoVaTen());
            stmt.setInt(2, item.getTuoi());
            stmt.setString(3, item.getEmail());
            stmt.setString(4, item.getMatKhau());
            stmt.setString(5, item.getSoDienThoai());
            stmt.setString(6, item.getVaiTro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(NguoiDung item) {
        String sql = "DELETE FROM NguoiDung WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NguoiDung> getAll() {
        List<NguoiDung> listNguoiDung = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listNguoiDung.add(mapResultSetToNguoiDung(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNguoiDung;
    }

    @Override
    public NguoiDung getById(int id) {
        String sql = "SELECT * FROM NguoiDung WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToNguoiDung(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private NguoiDung mapResultSetToNguoiDung(ResultSet rs) throws SQLException {
        return new NguoiDung(
                //Theo thứ tự param trong contructor 7 tham số
                rs.getInt("id"),
                rs.getString("hoVaTen"),
                rs.getInt("tuoi"),
                rs.getString("email"),
                rs.getString("matKhau"),
                rs.getString("soDienThoai"),
                rs.getString("vaiTro")
        );
    }
}
