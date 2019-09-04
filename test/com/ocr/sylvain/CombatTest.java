package com.ocr.sylvain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CombatTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Character joueur1 = new Character ("Joueur 1", "Guerrier", 100, 100, 0, 0);
    Character joueur2 = new Character ("Joueur 2", "Guerrier", 100, 100, 0, 0);


    @Test
    public void Given_BasicAttack_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        Combat combat = new Combat(joueur1, joueur2);
        Combat.startCombat(joueur1, joueur2);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 1 utilise Coup d'Épée et inflige 100 dommages", output[1]);
    }
}