package mediaDB.domain_logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryTest {
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        addressRepository = new AddressRepository();
    }

    @Test
    void nextAddressFirst() {
        assertEquals(0, Integer.parseInt(addressRepository.nextAddress()));
    }

    @Test
    void nextAddress2000() {
        for (int i = 0; i < 2000; i++) {
            addressRepository.nextAddress();
        }
        assertEquals(2000, Integer.parseInt(addressRepository.nextAddress()));
    }

//    void testGetter(Class clazz, String memberName, Object expected){
//        Class.callFunction(clazz,"set"+memberName,expected),Object actual = Class.callFunction(clazz,"get"+memberName);Assert.assertEquals(expected,actual);
//    }
}