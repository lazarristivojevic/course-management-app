package main;

import java.util.List;
import crud.KursPolaznikCrud;
import model.LR56323Polaznik;

public class Glavni {
    public static void main(String[] args) {

        KursPolaznikCrud crud = new KursPolaznikCrud();

        long kursId = 2;

        System.out.println("Prikaz polaznika za kurs ID: " + kursId);

        List<LR56323Polaznik> polaznici = crud.findPolazniciZaKurs(kursId);

        if (polaznici == null || polaznici.isEmpty()) {
            System.out.println("Nema polaznika za dati kurs (ID: " + kursId + ")");
        } else {
            System.out.println("Polaznici koji pohadjaju kurs:");
            for (LR56323Polaznik p : polaznici) {
                System.out.println("- " + p.getIme() + " " + p.getPrezime());
            }
        }
    }
}
