package com.ocr.sylvain;

public class Main {

    public static void main(String[] args) {
        Character joueur1 = new Character("Joueur 1");
        Character joueur2 = new Character("Joueur 2");
        Combat combat = new Combat(joueur1, joueur2);
        combat.startCombat(joueur1, joueur2);
    }
}
