package mediaDB;

import mediaDB.domain_logic.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryTest {
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        addressRepository = new AddressRepository();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void nextAddressZero() {
        assertEquals(0, addressRepository.nextAddress());
    }

    @Test
    void nextAddressThreehundred() {
        for (int i = 0; i < 300; i++) {
            addressRepository.nextAddress();
        }
        assertEquals(300, addressRepository.nextAddress());
    }
}