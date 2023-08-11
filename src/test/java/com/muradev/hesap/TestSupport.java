package com.muradev.hesap;

import com.muradev.hesap.dto.CreateHesaprequest;
import com.muradev.hesap.model.Musteri;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

public class TestSupport {

    public static final String CUSTOMER_API_ENDPOINT = "/v1/musteri/";
    public static final String ACCOUNT_API_ENDPOINT = "/v1/hesap/";

    public Instant getCurrentInstant() {
        String instantExpected = "2021-06-15T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public Musteri generateCustomer() {
        return new Musteri("musteri-id", "musteri-adi", "musteri-soyadi", Set.of());
    }

    public CreateHesaprequest generateCreateAccountRequest(int initialCredit) {
        return generateCreateAccountRequest("customer-id", initialCredit);
    }

    public CreateHesaprequest generateCreateAccountRequest(String customerId, int initialCredit) {
        return new CreateHesaprequest(customerId, new BigDecimal(initialCredit));
    }
}
