package com.groupeISI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String rep;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir un nombre : ");
            int n = sc.nextInt();
            sc.nextLine();

            long fact = CalculFactoriel.CalculerFactoriel(n);
            System.out.println("Le factoriel de " + n + " est : " + fact);

            System.out.println("Taper Q pour quitter : ");
            rep = sc.nextLine();
        } while (!(rep.equalsIgnoreCase("Q")));
    }
}