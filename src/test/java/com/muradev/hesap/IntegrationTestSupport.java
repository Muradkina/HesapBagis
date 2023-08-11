package com.muradev.hesap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.muradev.hesap.dto.converter.MusteriDtoConverter;
import com.muradev.hesap.repository.MusteriRepo;
import com.muradev.hesap.service.HesapService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class IntegrationTestSupport extends TestSupport{

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public MusteriRepo musteriRepo;

    @Autowired
    public HesapService hesapService;

    @Autowired
    public MusteriDtoConverter converter;

    public final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
