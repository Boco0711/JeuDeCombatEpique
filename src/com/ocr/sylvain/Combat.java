package com.ocr.sylvain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Combat {
    private Character joueur1;
    private Character joueur2;

    Scanner sc = new Scanner(System.in);


    private boolean joueur1IsWinner = false;

    public Combat(Character joueur1, Character joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    /**
     *  Run fight of the 2 player
     * @param joueur1 one of the 2 player
     * @param joueur2 second of the 2 player
     */
    public void startCombat(Character joueur1, Character joueur2) {
        while (joueur1.getVitality() > 0 && joueur2.getVitality() > 0) {
            fight(joueur1, joueur2);
            if (joueur1.getVitality() > 0 && joueur2.getVitality() > 0)
            fight(joueur2, joueur1);
        }
        boolean player1Win = getJoueur1IsWinner();
        if (player1Win == false)
            System.out.println(joueur1.getName() + " a perdu !");
        else if (player1Win == true)
            System.out.println(joueur2.getName() + " a perdu !");
    }

    /**
     *  Ask a question about the fight in standart input, get response and call correct action
     * @param attacker the player that attack
     * @param defender the player that will be attacked
     */
    public void fight(Character attacker, Character defender) {
        int choiceOfAttack = 0;
        boolean responseIsGood = false;
        while (!responseIsGood || choiceOfAttack < 1 || choiceOfAttack > 2) {
            System.out.println(attacker.getName() + " (" + attacker.getVitality() + " vitalité) veuillez choisir votre action (1 : " + getBasicAttack(attacker.getWarcraft()) + ", 2 : " + getSpecialAttack(attacker.getWarcraft()) + ")");
            try {
                choiceOfAttack = sc.nextInt();
                responseIsGood = true;
                if (choiceOfAttack < 1 || choiceOfAttack > 2) {
                    System.out.println("Vous n'avez pas choisi une action valide");
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Vous n'avez pas choisi une action valide");
            }
            if (choiceOfAttack == 1)
                basicActionEffect(attacker, defender);
            else if (choiceOfAttack == 2)
                specialActionEffect(attacker, defender);
        }
    }

    /**
     * Return the name of Basic attack depending on the player's warcraft
     * @param warcraft player's warcraft
     * @return the name of the basic attack
     */
    String getBasicAttack(String warcraft) {
        switch (warcraft) {
            case "Guerrier":
                return ("Coup d'Épée");
            case "Rodeur":
                return ("Tir à l'Arc");
            case "Mage":
                return ("Boule de Feu");
        }
        return null;
    }

    /**
     * Return the name of Special attack depending on the player's warcraft
     * @param warcraft
     * @return the name of special attack
     */
    String getSpecialAttack(String warcraft) {
        switch (warcraft) {
            case "Guerrier":
                return ("Coup de Rage");
            case "Rodeur":
                return ("Concentration");
            case "Mage":
                return ("Soin");
        }
        return null;
    }

    /**
     * Display the effect of a basic attack depending on the attacker's warcraft
     * @param attacker player that used the basic attack
     * @param defender player that will be hit by the basic attack
     */
    private void basicActionEffect(Character attacker, Character defender) {
        switch (attacker.getWarcraft()) {
            case "Guerrier":
                System.out.println(attacker.getName() + " utilise " + getBasicAttack(attacker.getWarcraft()) + " et inflige " + attacker.getStrenght() + " dommages");
                defender.receiveDamage(attacker.getStrenght());
                break;
            case "Rodeur":
                System.out.println(attacker.getName() + " utilise " + getBasicAttack(attacker.getWarcraft()) + " et inflige " + attacker.getAgility() + " dommages");
                defender.receiveDamage(attacker.getAgility());
                break;
            case "Mage":
                System.out.println(attacker.getName() + " utilise " + getBasicAttack(attacker.getWarcraft()) + " et inflige " + attacker.getIntelligence() + " dommages");
                defender.receiveDamage(attacker.getIntelligence());
                break;
        }
        if (defender.getVitality() == 0) {
            if (defender.getName().equals("Joueur 2"))
                setJoueur1IsWinner(true);
            System.out.println(defender.getName() + " est mort");
        }
    }

    /**
     * Display the effect of a special attack depending on the attacker's warcraft
     * @param attacker player that used the special attack
     * @param defender player that may be hit by special attack depending on attacker's warcraft
     */
    private void specialActionEffect(Character attacker, Character defender) {
        switch (attacker.getWarcraft()) {
            case "Guerrier":
                System.out.println(attacker.getName() + " utilise " + getSpecialAttack(attacker.getWarcraft()) + " et inflige " + attacker.getStrenght() * 2 + " dommages");
                defender.receiveDamage(attacker.getStrenght() * 2);
                attacker.receiveDamage(attacker.getStrenght() / 2);
                break;
            case "Rodeur":
                System.out.println(attacker.getName() + " utilise " + getSpecialAttack(attacker.getWarcraft()) + " et gagne " + attacker.getLevel() / 2 + " d'agilité");
                attacker.addAgility(attacker.getLevel() / 2);
                break;
            case "Mage":
                if(attacker.getVitality() <= (attacker.getLevel()*5 - attacker.getIntelligence()*2)) {
                    System.out.println(attacker.getName() + " utilise " + getSpecialAttack(attacker.getWarcraft()) + " et gagne " + attacker.getIntelligence() * 2 + " en vitalité");
                    attacker.addHealth(attacker.getIntelligence() * 2);
                } else {
                    System.out.println(attacker.getName() + " utilise " + getSpecialAttack(attacker.getWarcraft()) + " et gagne " + (attacker.getLevel()*5 - attacker .getVitality() ) + " en vitalité");
                    attacker.addHealth(attacker.getIntelligence() * 2);
                }
                break;
        }
    }
}
