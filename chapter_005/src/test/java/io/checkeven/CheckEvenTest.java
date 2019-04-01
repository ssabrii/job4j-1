package io.checkeven;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckEvenTest {
    private final CheckEven checkEven = new CheckEven();

    @Test
    public void whenNotEven() {
        assertFalse(checkEven.isNumber(new ByteArrayInputStream("12345678912345678912345678".getBytes())));
    }

    @Test
    public void whenEven() {
        assertTrue(checkEven.isNumber(new ByteArrayInputStream("23456789123456789123456787111".getBytes())));
    }

    @Test
    public void whenStringNotEven() {
        assertFalse(checkEven.isNumber(new ByteArrayInputStream("223456789мама_мыла__88886789".getBytes())));
    }

    @Test
    public void whenIsNumberFallByEmptyBuffer() {
        assertFalse(checkEven.isNumber(new ByteArrayInputStream(new byte[]{})));
    }

    @Test(expected = FileNotFoundException.class)
    public void whenIsNumberFallByEmptyStream() throws FileNotFoundException {
        checkEven.isNumber(new FileInputStream(new File("ss/25")));

    }

}