package com.muradev.hesap.service;

import com.muradev.hesap.TestSupport;
import com.muradev.hesap.dto.converter.MusteriDtoConverter;
import com.muradev.hesap.dto.dto.MusteriDto;
import com.muradev.hesap.exception.MusteriNotFoundException;
import com.muradev.hesap.model.Musteri;
import com.muradev.hesap.repository.MusteriRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MusteriServiceTest extends TestSupport {
    @Mock
    private MusteriService musteriService;
    @Mock
    private MusteriRepo musteriRepo;
    @InjectMocks
    private MusteriDtoConverter musteriDtoConverter;

    @Before
    public void setup() {
        musteriRepo = mock(MusteriRepo.class);
        musteriDtoConverter = mock(MusteriDtoConverter.class);
        musteriService = new MusteriService(musteriRepo, musteriDtoConverter);
    }

    @Test
    public void testFindByMusteriId_whenMusteriIdExists_thenSholdReturnMusteri() {
        Musteri musteri = new Musteri("id", "adi", "soyadi", Set.of());
        Mockito.when(musteriRepo.findById("id")).thenReturn(Optional.of(musteri));

        Musteri sonuc = musteriService.findMusteriById("id");
        assertEquals(sonuc, musteri);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){

        Mockito.when(musteriRepo.findById("id")).thenReturn(Optional.empty());

        assertThrows(MusteriNotFoundException.class, () -> musteriService.findMusteriById("id"));

    }

    @Test
    public void testgetMusteriById_whenMusteriIdExists_sholdReturnMusteri() {
        Musteri musteri = generateCustomer();
        MusteriDto musteriDto = new MusteriDto("musteri-id", "adi", "soyadi", Set.of());

        Mockito.when(musteriRepo.findById("musteri-id")).thenReturn(Optional.of(musteri));
        Mockito.when(musteriDtoConverter.convertToMusteriDto(musteri)).thenReturn(musteriDto);

        MusteriDto result = musteriService.getAllMusteriById("musteri-id");

        assertEquals(result,
                musteriDto);
    }
    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        Mockito.when(musteriRepo.findById("id")).thenReturn(Optional.empty());

        assertThrows(MusteriNotFoundException.class,
                () -> musteriService.getAllMusteriById("id"));

        Mockito.verifyNoInteractions(musteriDtoConverter);
    }
}

