package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.dto.KetQuaDTO;
import com.quizizz.english.quizizz_english.model.DapAnNguoiDung;
import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;
import com.quizizz.english.quizizz_english.repository.IDapAnNguoiDungRepository;
import com.quizizz.english.quizizz_english.repository.IDapAnRepository;
import com.quizizz.english.quizizz_english.repository.ILichSuLamBaiRepository;
import com.quizizz.english.quizizz_english.repositoryImpl.DapAnNguoiDungRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.DapAnRepositoryImpl;
import com.quizizz.english.quizizz_english.repositoryImpl.LichSuLamBaiRepositoryImpl;
import com.quizizz.english.quizizz_english.service.IDapAnNguoiDungService;
import com.quizizz.english.quizizz_english.service.IDapAnService;
import com.quizizz.english.quizizz_english.service.ILichSuLamBaiService;

import java.util.List;
import java.util.Map;

public class LichSuLamBaiServiceImpl implements ILichSuLamBaiService {
    private ILichSuLamBaiRepository lichSuLamBaiRepository;
    private IDapAnNguoiDungRepository dapAnNguoiDungRepository = new DapAnNguoiDungRepositoryImpl();
    private  IDapAnRepository dapAnRepository = new DapAnRepositoryImpl()   ;

    public LichSuLamBaiServiceImpl(ILichSuLamBaiRepository lichSuLamBaiRepository, IDapAnNguoiDungRepository dapAnNguoiDungRepository, IDapAnRepository dapAnRepository) {
        this.lichSuLamBaiRepository = lichSuLamBaiRepository;
        this.dapAnNguoiDungRepository = dapAnNguoiDungRepository;
        this.dapAnRepository = dapAnRepository;
    }

    public LichSuLamBaiServiceImpl(ILichSuLamBaiRepository lichSuLamBaiRepository) {
        this.lichSuLamBaiRepository = lichSuLamBaiRepository;
    }

    @Override
    public int addLichSuLamBai(int idNguoiDung, int idBaiTap, int idChuDe, Map<Integer, Integer> cauHoiVaDapAn) {
        int tongCau = cauHoiVaDapAn.size();
        int soCauDung = 0;
        for (Map.Entry<Integer, Integer> entry : cauHoiVaDapAn.entrySet()) {
            int idCauHoi = entry.getKey();
            int idDapAnChon = entry.getValue();

            int idDapAnDung = dapAnRepository.getDapAnDungIdByCauHoi(idCauHoi);
            if (idDapAnDung == idDapAnChon) {
                soCauDung++;
            }
        }
        double diem = ((double) soCauDung / tongCau) * 10;

        // Lưu lịch sử làm bài
        LichSuLamBai lsb = new LichSuLamBai(idNguoiDung, idBaiTap, idChuDe, diem);
        int idLichSu = lichSuLamBaiRepository.insert(lsb);
        // Lưu chi tiết từng câu trả lời
        for (Map.Entry<Integer, Integer> entry : cauHoiVaDapAn.entrySet()) {
            int idCauHoi = entry.getKey();
            int idDapAnChon = entry.getValue();
            int idDapAnDung = dapAnRepository.getDapAnDungIdByCauHoi(idCauHoi);
            boolean cauDung = idDapAnChon == idDapAnDung;
            DapAnNguoiDung dapAnNguoiDung = new DapAnNguoiDung(idLichSu, cauDung, idCauHoi, idDapAnChon, idBaiTap);

            dapAnNguoiDungRepository.insert(dapAnNguoiDung);
        }
        return idLichSu;
    }

    @Override
    public void updateLichSuLamBai(LichSuLamBai item) {
        lichSuLamBaiRepository.update(item);
    }

    @Override
    public void deleteLichSuLamBai(LichSuLamBai item) {
        lichSuLamBaiRepository.delete(item.getId());
    }

    @Override
    public List<LichSuLamBai> getAllLichSuLamBai() {
        return lichSuLamBaiRepository.getAll();
    }

    @Override
    public LichSuLamBai getLichSuLamBaiById(int id) {
        return lichSuLamBaiRepository.getById(id);
    }

    @Override
    public List<KetQuaDTO> getKetQuaLamBai(int idLichSu,int idBaiTap) {
        return lichSuLamBaiRepository.getKetQuaLamBai(idLichSu, idBaiTap);
    }
//
//    @Override
//    public LichSuLamBai getLichSuLamBaiById(int id) {
//        return lichSuLamBaiRepository.getById(id);
//    }
}
