package com.ocr.sylvain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.ocr.sylvain.Combat.*;
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

    Character joueur1 = new Character("Joueur 1", "Guerrier", 100, 100, 0, 0);
    Character joueur2 = new Character("Joueur 2", "Rodeur", 100, 0, 100, 0);
    Character joueur3 = new Character("Joueur 3", "Mage", 100, 0, 0, 100);


    @Test
    public void Given_WarriorBasicAttack_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("1\n1\n1\n1\n1\n1\n1\n1\n1\n".getBytes()));
        Combat combat = new Combat(joueur1, joueur2);
        combat.startCombat(joueur1, joueur2);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 1 utilise Coup d'Épée et inflige 100 dommages", output[1]);
        assertEquals("Joueur 2 utilise Coup d'Épée et inflige 100 dommages", output[]);
        assertEquals("Joueur 1 utilise Coup d'Épée et inflige 100 dommages", output[1]);
        assertEquals("Joueur 2 utilise Coup d'Épée et inflige 100 dommages", output[1]);
        assertEquals("Joueur 1 utilise Coup d'Épée et inflige 100 dommages", output[1]);
        assertEquals("Joueur 2 utilise Coup d'Épée et inflige 100 dommages", output[1]);
        assertEquals("Joueur 1 utilise Coup d'Épée et inflige 100 dommages", output[1]);
        assertEquals("Joueur 2 utilise Coup d'Épée et inflige 100 dommages", output[1]);
    }

    @Test
    public void Given_WarriorSpecialAttack_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        Combat combat = new Combat(joueur1, joueur2);
        combat.startCombat(joueur1, joueur2);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 1 utilise Coup de Rage et inflige 200 dommages", output[1]);
    }

    @Test
    public void Given_RodeurBasicAttack_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        Combat combat = new Combat(joueur2, joueur3);
        combat.startCombat(joueur2, joueur3);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 2 utilise Tir à l'Arc et inflige 100 dommages", output[1]);
    }

    @Test
    public void Given_RodeurSpecialAttack_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        Combat combat = new Combat(joueur2, joueur3);
        combat.startCombat(joueur2, joueur3);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 2 utilise Concentration et gagne 50 d'agilité", output[1]);
    }

    @Test
    public void Given_MageBasicAttack_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        Combat combat = new Combat(joueur3, joueur2);
        combat.startCombat(joueur3, joueur2);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 3 utilise Boule de Feu et inflige 100 dommages", output[1]);
    }

    @Test
    public void Given_MageSpecialAttack_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        joueur3.receiveDamage(300);
        Combat combat = new Combat(joueur3, joueur2);
        combat.startCombat(joueur3, joueur2);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 3 utilise Soin et gagne 200 en vitalité", output[1]);
    }

    @Test
    public void Given_MageSpecialAttackAtFullHP_When_CombatIsRun_Then_DisplaySentence() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        Combat combat = new Combat(joueur3, joueur2);
        combat.startCombat(joueur3, joueur2);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 3 utilise Soin et gagne 0 en vitalité", output[1]);
    }

    @Test
    public void Given_BadValue_When_CombatIsRun_Then_DisplayErrorSentence() {
        System.setIn(new ByteArrayInputStream("4\n2\n".getBytes()));
        Combat combat = new Combat(joueur1, joueur2);
        combat.startCombat(joueur1, joueur2);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi une action valide", output[1]);
        assertEquals("Joueur 1 utilise Coup de Rage et inflige 200 dommages", output[3]);
    }



}