package com.ocr.sylvain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterTest {
    private String joueur1 = "Joueur 1";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void Given_Warrior_When_AskWarcraftIsRun_Then_ReturnWarrior() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        Character joueur1 = new Character();
        String comp = joueur1.askWarcraft();
        assertTrue(comp.equals("Guerrier"));
    }

    @Test
    public void Given_Rodeur_When_AskWarcraftIsRun_Then_ReturnRodeur() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        Character joueur1 = new Character();
        String comp = joueur1.askWarcraft();
        assertTrue(comp.equals("Rodeur"));
    }

    @Test
    public void Given_Mage_When_AskWarcraftIsRun_Then_ReturnMage() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        Character joueur1 = new Character();
        String comp = joueur1.askWarcraft();
        assertTrue(comp.equals("Mage"));
    }

    @Test
    public void Given_BadValue_When_AskWarcraftIsRun_Then_ReAsk() {
        System.setIn(new ByteArrayInputStream("4\n1\n".getBytes()));
        Character joueur1 = new Character();
        String comp = joueur1.askWarcraft();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de classe parmi les choix proposés", output [1]);
        assertEquals("Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur; 3 : Mage)", output[2]);
    }

    @Test
    public void Given_Text_When_AskWarcraftIsRun_Then_ReAsk() {
        System.setIn(new ByteArrayInputStream("tete\n1\n".getBytes()));
        Character joueur1 = new Character();
        String comp = joueur1.askWarcraft();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de classe parmi les choix proposés", output [1]);
        assertEquals("Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur; 3 : Mage)", output[2]);
    }

    @Test
    public void Given_GuerrierLevel88Strenght88Agility0Intelligence0InStandardInput_When_AskStatsIsRun_Then_DisplayGuerrierSentence() {
        System.setIn(new ByteArrayInputStream("1\n88\n88\n0\n0".getBytes()));
        Character joueur = new Character(joueur1);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Woarg je suis le " + joueur.getWarcraft() + " " + joueur1 + " niveau " + joueur.getLevel() + " je possède " + joueur.getVitality() + " de vitalité, " + joueur.getStrenght() + " de force, " + joueur.getAgility() + " d'agilité et " + joueur.getIntelligence() + " d'intelligence !", output[6]);
    }

    @Test
    public void Given_RodeurLevel56Strenght0Agility56Intelligence0InStandardInput_When_AskStatsIsRun_Then_DisplayRodeurSentence() {
        System.setIn(new ByteArrayInputStream("2\n56\n0\n56\n0\n".getBytes()));
        Character joueur = new Character(joueur1);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Shhh je suis le " + joueur.getWarcraft() + " " + joueur1 + " niveau " + joueur.getLevel() + " je possède " + joueur.getVitality() + " de vitalité, " + joueur.getStrenght() + " de force, " + joueur.getAgility() + " d'agilité et " + joueur.getIntelligence() + " d'intelligence !", output[6]);
    }

    @Test
    public void Given_MageLevel24Strenght0Agility0Intelligence24InStandardInput_When_AskStatsIsRun_Then_DisplayMageSentence() {
        System.setIn(new ByteArrayInputStream("3\n24\n0\n0\n24\n".getBytes()));
        Character joueur = new Character(joueur1);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Abracadabra je suis le " + joueur.getWarcraft() + " " + joueur1 + " niveau " + joueur.getLevel() + " je possède " + joueur.getVitality() + " de vitalité, " + joueur.getStrenght() + " de force, " + joueur.getAgility() + " d'agilité et " + joueur.getIntelligence() + " d'intelligence !", output[6]);
    }

    @Test
    public void Given_BadValueMultipleTimeInStandardInput_When_CharacterIsRun_Then_DisplayErrorSentenceAndReAsk() {
        System.setIn(new ByteArrayInputStream("4\n1\n101\n88\n100\n88\n20\n0\n0\n".getBytes()));
        Character joueur = new Character(joueur1);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de classe parmi les choix proposés", output[2]);
        assertEquals("Le niveau doit être compris entre 1 et 100", output[5]);
        assertEquals("La valeur de la Force doit être comprise entre 0 et 88", output[8]);
        assertEquals("La valeur de l'Agilité doit être comprise entre 0 et 0", output[11]);
        assertEquals("Woarg je suis le " + joueur.getWarcraft() + " " + joueur1 + " niveau " + joueur.getLevel() + " je possède " + joueur.getVitality() + " de vitalité, " + joueur.getStrenght() + " de force, " + joueur.getAgility() + " d'agilité et " + joueur.getIntelligence() + " d'intelligence !", output[14]);
    }

    @Test
    public void Given_TextResponses_When_AskingForWarcraft_Then_DisplayErrorSentence() {
        System.setIn(new ByteArrayInputStream("texte\n1\n1\n1\n0\n0".getBytes()));
        Character joueur = new Character(joueur1);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de classe parmi les choix proposés", output[2]);
    }
}