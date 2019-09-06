package com.ocr.sylvain;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Character {

    private String name;
    private String warcraft;
    private int level;
    private int vitality;
    private int strenght;
    private int agility;
    private int intelligence;

    Scanner sc = new Scanner(System.in);

    public Character() {
    }

    public Character(String joueur, String warcraft, int level, int strenght, int agility, int intelligence) {
        this.name = joueur;
        this.warcraft = warcraft;
        this.level = level;
        this.vitality = level * 5;
        this.strenght = strenght;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public Character(String joueur) {
        this.name = joueur;
        System.out.println("Création du personnage du " + joueur);
        this.warcraft = askWarcraft();
        this.level = askLevel();
        this.vitality = this.level * 5;
        this.strenght = askStrenght(this.level);
        this.agility = askAgility(this.level, this.strenght);
        this.intelligence = askIntelligence(this.level, this.strenght, this.agility);
        characterApperance(joueur);
    }

    public int askSomething(String question, String error, int min, int max) {
        System.out.println(question);
        boolean responseIsGood = false;
        int choix = 0;
        while (!responseIsGood || choix < min || choix > max) {
            try {
                choix = sc.nextInt();
                responseIsGood = true;
                if (choix < min || choix > max) {
                    System.out.println(error);
                    System.out.println(question);
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println(error);
                System.out.println(question);
                responseIsGood = false;
            }
        }
        return choix;
    }

    /**
     * Display a question to get the Warcraft of the character
     *
     * @return String warcraft
     */
    public String askWarcraft() {
        String question = "Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur; 3 : Mage)";
        String error = "Vous n'avez pas choisi de classe parmi les choix proposés";
        int min = 1;
        int max = 3;
        int choix = askSomething(question, error, min, max);
        switch (choix) {
            case 1:
                return "Guerrier";
            case 2:
                return "Rodeur";
            case 3:
                return "Mage";
        }
        return null;
    }

    /**
     * Display a question to get the level of Character
     *
     * @return Level of character
     */
    private int askLevel() {
        String question = "Niveau du personnage ?";
        String error = "Le niveau doit être compris entre 1 et 100";
        int min = 0;
        int max = 100;
        int level = askSomething(question, error, min, max);
        return level;
    }

    /**
     * Display a question to get the strenght of the character depending on his level.
     * Strenght cannot be higher then level
     *
     * @param level Level of the character
     * @return the Strenght of the character
     */
    private int askStrenght(int level) {
        String question = "Force du personnage ?";
        String error = "La valeur de la Force doit être comprise entre 0 et " + level;
        int min = 0;
        int strenght = askSomething(question, error, min, level);
        return strenght;
    }

    /**
     * Display a question to get the Agility stat of the character depending on his level and strenght.
     * Agility cannot be higher then level - strenght
     *
     * @param level    Level of the character
     * @param strenght Strenght of the character
     * @return Agility of the character
     */
    private int askAgility(int level, int strenght) {
        String question = "Agilité du personnage ?";
        String error = "La valeur de l'Agilité doit être comprise entre 0 et " + (level - strenght);
        int min = 0;
        int max = level - strenght;
        int agility = askSomething(question, error, min, max);
        return agility;
    }

    /**
     * Display a question to get the Intelligence stat of the character depending on his level and strenght and agility.
     * Intelligence cannot be higher then level - (strenght + agility)
     *
     * @param level    the level of the character
     * @param strenght the strenght of the character
     * @param agility  agility of the character
     * @return intelligence of the character
     */
    private int askIntelligence(int level, int strenght, int agility) {
        String question = "Intelligence du personnage ?";
        String error = "La valeur de l'Intelligence doit être comprise entre 0 et " + (level - (strenght + agility));
        int min = 0;
        int max = level - (strenght + agility);
        int intelligence = askSomething(question, error, min, max);
        return intelligence;
    }

    /**
     * Display a sentence when a character is completed depending on his warcraft
     * The sentence recap : Warcraft, name, level, vitality, strenght, agility, intelligence
     *
     * @param joueur the name of the character.
     */
    private void characterApperance(String joueur) {
        if (this.warcraft.equals("Guerrier"))
            System.out.println("Woarg je suis le Guerrier " + joueur + " niveau " + this.level + " je possède " + this.vitality + " de vitalité, " + this.strenght + " de force, " + this.agility + " d'agilité et " + this.intelligence + " d'intelligence !");
        else if (this.warcraft.equals("Rodeur"))
            System.out.println("Shhh je suis le Rodeur " + joueur + " niveau " + this.level + " je possède " + this.vitality + " de vitalité, " + this.strenght + " de force, " + this.agility + " d'agilité et " + this.intelligence + " d'intelligence !");
        else if (this.warcraft.equals("Mage"))
            System.out.println("Abracadabra je suis le Mage " + joueur + " niveau " + this.level + " je possède " + this.vitality + " de vitalité, " + this.strenght + " de force, " + this.agility + " d'agilité et " + this.intelligence + " d'intelligence !");
    }

    public String getName() {
        return name;
    }

    public String getWarcraft() {
        return warcraft;
    }

    public int getLevel() {
        return level;
    }

    public int getVitality() {
        return vitality;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Display a text when damage are received
     *
     * @param damage the amout of damage received
     */
    public void receiveDamage(int damage) {
        this.vitality = this.vitality - damage;
        if (damage > 1) {
            System.out.println(this.getName() + " perd " + damage + " points de vie");
        } else if (damage == 1)
            System.out.println(this.getName() + " perd " + damage + " point de vie");
    }

    /**
     * Add Agility to the current amount of agility
     * <p>
     * * @param i the amount of agility gained
     */
    public void addAgility(int i) {
        this.agility = agility + i;
    }

    /**
     * Add Health to the current amount of health
     * Health cannot be higher then level * 5
     *
     * @param i the amount of health gained
     */
    public void addHealth(int i) {
        this.vitality = this.vitality + i;
        if (this.vitality > this.level * 5)
            this.vitality = this.level * 5;
    }
}
