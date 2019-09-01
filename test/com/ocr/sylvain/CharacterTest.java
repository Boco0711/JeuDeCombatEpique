package com.ocr.sylvain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    String joueur1 = "Joueur 1";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Character joueur = new Character(joueur1);

    @Test
    public void Given_GuerrierLevel88Strenght88Agility0Intelligence0InStandardInput_When_AskStatsIsRun_Then_DisplayGuerrierSentence() {
        System.setIn(new ByteArrayInputStream("1\n88\n88\n0\n0\n".getBytes()));
        joueur = new Character(joueur1);
        joueur.askStats();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Woarg je suis le Guerrier " + joueur1 + " niveau 88 je possède 440 de vitalité, 88 de force, 0 d'agilité et 0 d'intelligence !\n", output[6]);
    }

    @Test
    public void Given_RodeurLevel56Strenght0Agility56Intelligence0InStandardInput_When_AskStatsIsRun_Then_DisplayRodeurSentence() {
        System.setIn(new ByteArrayInputStream("2\n56\n0\n56\n0\n".getBytes()));
        joueur = new Character(joueur1);
        joueur.askStats();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Shhh je suis le Rodeur " + joueur1 + " niveau 56 je possède 280 de vitalité, 0 de force, 56 d'agilité et 0 d'intelligence !\n", output[6]);
    }

    @Test
    public void Given_MageLevel24Strenght0Agility0Intelligence24InStandardInput_When_AskStatsIsRun_Then_DisplayMageSentence() {
        System.setIn(new ByteArrayInputStream("3\n24\n0\n0\n24\n".getBytes()));
        joueur = new Character(joueur1);
        joueur.askStats();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Abracadabra je suis le Mage " + joueur1 + " niveau 24 je possède 120 de vitalité, 0 de force, 0 d'agilité et 24 d'intelligence !\n", output[6]);
    }

    @Test
    public void Given_BadValueMultipleTimeInStandardInput_When_AskStatsIsRun_Then_ReAskWithErrorSentence() {
        System.setIn(new ByteArrayInputStream("4\n3\n101\n100\n0\n0\n101\n100\n".getBytes()));
        joueur = new Character(joueur1);
        joueur.askStats();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de classe parmi les choix proposés\n", output[2]);
        assertEquals("Le niveau maximum est compris entre 1 et 100 veuillez entrer une valeur comprise entre 1 et 100\n", output[4]);
        assertEquals("Le montant maximum des stats du personnage ne peut exceder son niveau\n", output[7]);
        assertEquals("Abracadabra je suis le Mage " + joueur1 + " niveau 100 je possède 500 de vitalité, 0 de force, 0 d'agilité et 100 d'intelligence !\n", output[8]);
    }

}