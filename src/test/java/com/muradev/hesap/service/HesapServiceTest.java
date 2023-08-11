package com.muradev.hesap.service;

import com.muradev.hesap.TestSupport;
import com.muradev.hesap.dto.CreateHesaprequest;
import com.muradev.hesap.dto.converter.HesapDtoConverter;
import com.muradev.hesap.dto.dto.HesapDto;
import com.muradev.hesap.dto.dto.HesapMusteriDto;
import com.muradev.hesap.dto.dto.IslemDto;
import com.muradev.hesap.exception.MusteriNotFoundException;
import com.muradev.hesap.model.Hesap;
import com.muradev.hesap.model.Islem;
import com.muradev.hesap.model.IslemTuru;
import com.muradev.hesap.model.Musteri;
import com.muradev.hesap.repository.HesapRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.util.TestContextSpringFactoriesUtils;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

public class HesapServiceTest extends TestSupport {

    @Mock
    private HesapRepo repo;
    @Mock
    private MusteriService musteriService;
    @InjectMocks
    private HesapDtoConverter converter;
    @Mock
    private HesapService hesapService;

    private final Musteri musteri = generateCustomer();
    private final HesapMusteriDto musteriDto = new HesapMusteriDto("musteri-id", "adi", "soyadi");

    @Before
   public void setUp() {
        repo = mock(HesapRepo.class);
        musteriService = mock(MusteriService.class);
        converter = mock(HesapDtoConverter.class);
        Clock clock = mock(Clock.class);
        hesapService = new HesapService(repo, musteriService, converter, clock);

        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    public void testCreateHesap_whenMusteriIdExistsAndInitialCreditMoreThanZero_shouldCreateHesapWithIslem() {
        CreateHesaprequest hesaprequest = generateCreateAccountRequest(100);
        Hesap hesap = generateHesap(100);
        Islem islem = new Islem(null, IslemTuru.INITIAL, hesaprequest.getIlkCredi(), getLocalDateTime(), hesap);
        hesap.getIslem().add(islem);

        IslemDto islemDto = new IslemDto("", IslemTuru.INITIAL, new BigDecimal(100), getLocalDateTime());
        HesapDto expected = new HesapDto("account-id", new BigDecimal(100), getLocalDateTime(), musteriDto, Set.of(islemDto));

        when(musteriService.findMusteriById("musteri-id")).thenReturn(musteri);
        when(repo.save(hesap)).thenReturn(hesap);

        when(converter.convertHesap(hesap)).thenReturn(expected);

        HesapDto result = hesapService.createHesap(hesaprequest);

        assertEquals(result, expected);
    }


    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditIsZero_shouldCreateAccountWithoutTransaction() {
        CreateHesaprequest request = generateCreateAccountRequest(0);

        Hesap hesap = generateHesap(0);
        HesapDto expected = new HesapDto("hesap-id", BigDecimal.ZERO, getLocalDateTime(), musteriDto, Set.of());

        when(musteriService.findMusteriById("musteri-id")).thenReturn(musteri);
        when(repo.save(hesap)).thenReturn(hesap);
        when(converter.convertHesap(hesap)).thenReturn(expected);

        HesapDto result = hesapService.createHesap(request);

        assertEquals(result, expected);
    }

    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {
        CreateHesaprequest request = generateCreateAccountRequest(0);

        when(musteriService.findMusteriById("musteri-id")).thenThrow(new MusteriNotFoundException("test-exception"));

        assertThrows(MusteriNotFoundException.class,
                () -> hesapService.createHesap(request));

        verify(musteriService).findMusteriById(request.getMusteriId());
        verifyNoInteractions(repo);
        verifyNoInteractions(converter);
    }

    private Hesap generateHesap(int balance) {
        return new Hesap("", new BigDecimal(balance), getLocalDateTime(), musteri, new HashSet<>());
    }
}