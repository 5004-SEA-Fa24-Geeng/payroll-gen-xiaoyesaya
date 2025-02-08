package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayStubTest {
    private PayStub payStub;

    @BeforeEach
    void setUp() {
        payStub = new PayStub("Kelly", 1000.00, 226.50, 12000.00, 2700.00);
    }

    @Test
    void getPay() {
        assertEquals(1000.00, payStub.getPay(), 0.01);
    }

    @Test
    void getTaxesPaid() {
        assertEquals(226.50, payStub.getTaxesPaid(), 0.01);
    }

    @Test
    void toCSV() {
        String expectedCSV = "Kelly,1000.00,226.50,12000.00,2700.00";
        assertEquals(expectedCSV, payStub.toCSV());
    }
}