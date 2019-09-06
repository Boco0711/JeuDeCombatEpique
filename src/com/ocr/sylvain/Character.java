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

    /**
     * Display a question to get the Warcraft of the character
     *
     * @return String warcraft
     */
    public String askWarcraft() {
        System.out.println("Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur; 3 : Mage)");
        boolean responseIsGood = false;
        int choix = 0;
        while (!responseIsGood || choix < 1 || choix > 3) {
            try {
                choix = sc.nextInt();
                responseIsGood = true;
                if (choix < 1 || choix > 3) {
                    System.out.println("Vous n'avez pas choisi de classe parmi les choix proposés");
                    System.out.println("Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur; 3 : Mage)");
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Vous n'avez pas choisi de classe parmi les choix proposés");
                System.out.println("Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur; 3 : Mage)");
                responseIsGood = false;
            }
        }
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
        System.out.println("Niveau du personnage ?");
        int level = 0;
        boolean responseIsGood = false;
        while (!responseIsGood || level < 1 || level > 100) {
            try {
                level = sc.nextInt();
                responseIsGood = true;
                if (level < 1 || level > 100) {
                    System.out.println("Le niveau doit être compris entre 1 et 100 inclus");
                    level = sc.nextInt();
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Le niveau doit être compris entre 1 et 100 inclus");
                System.out.println("Niveau du personnage ?");
            }
        }
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
        System.out.println("Force du personnage ?");
        int strenght = -1;
        boolean responseIsGood = false;
        while (!responseIsGood || strenght < 0 || strenght > level) {
            try {
                strenght = sc.nextInt();
                responseIsGood = true;
                if (strenght < 0 || strenght > level) {
                    System.out.println("La valeur de la Force doit être compris entre 0 et " + level);
                    System.out.println("Force du personnage ? (0 - " + level + ")");
                    strenght = sc.nextInt();
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("La valeur de la Force doit être compris entre 0 et " + level);
                System.out.println("Force du personnage ? (0 - " + level + ")");
            }
        }
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
        System.out.println("Agilité du personnage ?");
        int agility = -1;
        boolean responseIsGood = false;
        while (!responseIsGood || agility < 0 || agility > (level - strenght)) {
            try {
                agility = sc.nextInt();
                responseIsGood = true;
                if (agility < 0 || agility > (level - strenght)) {
                    System.out.println("La valeur de l'Agilité doit être comprise entre 0 et " + (level - strenght));
                    System.out.println("Agilité du personnage ? (0 - " + (level - strenght) + ")");
                    agility = sc.nextInt();
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("La valeur de l'Agilité doit être comprise entre 0 et " + (level - strenght));
                System.out.println("Agilité du personnage ? (0 - " + (level - strenght) + ")");
            }
        }
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
        System.out.println("Intelligence du personnage ?");
        int intelligence = -1;
        boolean responseIsGood = false;
        while (!responseIsGood || intelligence < 0 || intelligence > (level - (strenght + agility))) {
            try {
                intelligence = sc.nextInt();
                responseIsGood = true;
                if (agility < 0 || agility > (level - strenght)) {
                    System.out.println("La valeur de l'Intelligence doit être comprise entre 0 et " + (level - (strenght + intelligence)));
                    System.out.println("Intelligence du personnage ? (0 - " + (level - (strenght + intelligence)) + ")");
                    level = sc.nextInt();
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("La valeur de l'Intelligence doit être comprise entre 0 et " + (level - (strenght + intelligence)));
                System.out.println("Intelligence du personnage ? (0 - " + (level - (strenght + intelligence)) + ")");
            }
        }
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
