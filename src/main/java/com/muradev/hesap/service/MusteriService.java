package com.muradev.hesap.service;

import com.muradev.hesap.dto.dto.MusteriDto;
import com.muradev.hesap.dto.converter.MusteriDtoConverter;
import com.muradev.hesap.exception.MusteriNotFoundException;
import com.muradev.hesap.model.Musteri;
import com.muradev.hesap.repository.MusteriRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusteriService {

    private final MusteriRepo repo;
    private final MusteriDtoConverter converter;

    public MusteriService(MusteriRepo repo, MusteriDtoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    protected Musteri findMusteriById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new MusteriNotFoundException("Müşteri kimliğe göre bulunamadı: " + id));
    }

    public MusteriDto getAllMusteriById(String musteriId) {
        return converter.convertToMusteriDto(findMusteriById(musteriId));
    }
    public List<MusteriDto> getAllMusteriler() {

        return repo.findAll()
                .stream()
                .map(converter::convertToMusteriDto)
                .collect(Collectors.toList());
    }

}
