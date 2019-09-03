package com.ocr.sylvain;

import java.util.Scanner;


public class Character {
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

    private String warcraft;
    private int level;
    private int vitality;
    private int strenght;
    private int agility;
    private int intelligence;

    Scanner sc = new Scanner(System.in);

    public Character(String joueur) {
        System.out.println("Création du personnage du " + joueur);
        this.warcraft = askWarcraft();
        this.level = askLevel();
        this.vitality = this.level * 5;
        this.strenght = askStrenght(this.level);
        this.agility = askAgility(this.level, this.strenght);
        this.intelligence = askIntelligence(this.level, this.strenght, this.agility);
        characterApperance(joueur);
    }

    public String askWarcraft() {
        System.out.println("Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur; 3 : Mage)");
        int choix = sc.nextInt();
        while (choix < 1 || choix > 3) {
            System.out.println("Vous n'avez pas choisi de classe parmi les choix proposés");
            choix = sc.nextInt();
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

    private int askLevel() {
        System.out.println("Niveau du personnage ?");
        int level = sc.nextInt();
        while (level < 1 || level > 100) {
            System.out.println("Le niveau doit être compris entre 1 et 100 inclus");
            level = sc.nextInt();
        }
        return level;
    }

    private int askStrenght(int level) {
        System.out.println("Force du personnage ?");
        int strenght = sc.nextInt();
        while (strenght < 0 || strenght > level) {
            System.out.println("La valeur des vos statistiques Force, Agilité et Intelligence cumulé ne peut excéder le niveau du personnage");
            strenght = sc.nextInt();
        }
        return strenght;
    }

    private int askAgility(int level, int strenght) {
        System.out.println("Agilité du personnage ?");
        int agility = sc.nextInt();
        while (agility < 0 || agility > (level - strenght)) {
            System.out.println("La valeur des vos statistiques Force, Agilité et Intelligence cumulé ne peut excéder le niveau du personnage");
            agility = sc.nextInt();
        }
        return agility;
    }

    private int askIntelligence(int level, int strenght, int agility) {
        System.out.println("Intelligence du personnage ?");
        int intelligence = sc.nextInt();
        while (intelligence < 0 || intelligence > (level - (strenght + agility))) {
            System.out.println("La valeur des vos statistiques Force, Agilité et Intelligence cumulé ne peut excéder le niveau du personnage");
            intelligence = sc.nextInt();
        }
        return intelligence;
    }

    private void characterApperance(String joueur) {
        if (this.warcraft.equals("Guerrier"))
            System.out.println("Woarg je suis le Guerrier " + joueur + " niveau " + this.level + " je possède " + this.vitality + " de vitalité, " + this.strenght + " de force, " + this.agility + " d'agilité et " + this.intelligence + " d'intelligence !");
        else if (this.warcraft.equals("Rodeur"))
            System.out.println("Shhh je suis le Rodeur " + joueur + " niveau " + this.level + " je possède " + this.vitality + " de vitalité, " + this.strenght + " de force, " + this.agility + " d'agilité et " + this.intelligence + " d'intelligence !");
        else if (this.warcraft.equals("Mage"))
            System.out.println("Abracadabra je suis le Mage " + joueur + " niveau " + this.level + " je possède " + this.vitality + " de vitalité, " + this.strenght + " de force, " + this.agility + " d'agilité et " + this.intelligence + " d'intelligence !");
    }
}
