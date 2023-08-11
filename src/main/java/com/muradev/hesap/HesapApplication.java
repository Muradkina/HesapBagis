package com.muradev.hesap;

import com.muradev.hesap.model.Hesap;
import com.muradev.hesap.model.Musteri;
import com.muradev.hesap.repository.MusteriRepo;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import kotlin.collections.SetsKt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class HesapApplication implements CommandLineRunner {

    public HesapApplication(MusteriRepo musteriRepo) {
        this.musteriRepo = musteriRepo;
    }

    private final MusteriRepo musteriRepo;


    public static void main(String[] args) {
        SpringApplication.run(HesapApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version){
        return new OpenAPI()
                .info(new Info()
                        .title("Hesap API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Hesap API Licence")));
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Override
    public void run(String... args) throws Exception {
        Musteri musteri=musteriRepo.save(new Musteri("Murad","KINA"));
        Musteri musteri3=musteriRepo.save(new Musteri("Salih","İMİK"));
        Musteri musteri1= musteriRepo.save(new Musteri("Ebru","EYÜBOĞLU"));
        Musteri musteri2=musteriRepo.save(new Musteri("Bahadırhan","KELEŞ"));

        System.out.println(musteri);
        System.out.println(musteri1);
        System.out.println(musteri2);
        System.out.println(musteri3);


    }
}
