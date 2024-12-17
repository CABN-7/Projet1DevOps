package com.groupeISI;


public class CalculFactoriel {

    public static long CalculerFactoriel(int n) {
        long fact;
        if (n < 0) {
            throw new IllegalArgumentException("Le nombre doit etre positif ou nul");
        } else {
            fact = 1;
            for (int i = 1; i <= n; i++) {
                fact *= i;
            }
        }
        return fact;
    }
}
