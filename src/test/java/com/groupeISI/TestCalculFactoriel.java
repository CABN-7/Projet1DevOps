package com.groupeISI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculFactoriel {

    @Test
    public void testFactorielZero() {
        assertEquals(1, CalculFactoriel.CalculerFactoriel(0), "Factoriel de 0 doit être 1");
    }

    @Test
    public void testFactorielUn() {
        assertEquals(1, CalculFactoriel.CalculerFactoriel(1), "Factoriel de 1 doit être 1");
    }

    @Test
    public void testFactorielCinq() {
        assertEquals(120, CalculFactoriel.CalculerFactoriel(5), "Factoriel de 5 doit être 120");
    }

    @Test
    public void testFactorielNombreNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculFactoriel.CalculerFactoriel(-1);
        });
        assertEquals("Le nombre doit etre positif ou nul", exception.getMessage());
    }
}
