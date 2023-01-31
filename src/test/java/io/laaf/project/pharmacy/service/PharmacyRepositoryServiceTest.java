package io.laaf.project.pharmacy.service;

import io.laaf.project.pharmacy.entity.Pharmacy;
import io.laaf.project.pharmacy.repository.PharmacyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PharmacyRepositoryServiceTest {

    @Autowired
    private PharmacyRepositoryService pharmacyRepositoryService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @BeforeEach
    void setup() {
        pharmacyRepository.deleteAll();
    }

    @Test
    void dirtyCheckingSuccess() {
        // given
        String inputAddress = "서울 특별시 성북구 종암동";
        String modifiedAddress = "서울 광진구 중곡동";
        String name = "은혜 약국";

        Pharmacy pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build();

        // when
        Pharmacy entity = pharmacyRepository.save(pharmacy);
        pharmacyRepositoryService.updateAddress(entity.getId(), modifiedAddress);

        List<Pharmacy> result = pharmacyRepository.findAll();

        // then
        assertThat(result.get(0).getPharmacyAddress()).isEqualTo(modifiedAddress);
    }

}