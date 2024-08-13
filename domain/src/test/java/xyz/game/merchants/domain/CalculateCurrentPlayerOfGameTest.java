package xyz.game.merchants.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculateCurrentPlayerOfGameTest {
    Player jan = new Player("Jan");
    Player lena = new Player("Lena");
    Player patrick = new Player("Patrick");

    Player[] players = new Player[]{jan, lena, patrick}; 
    
    @Test
    public void shouldGetCurrentPlayerOnFirstForwardPass(){
        assertEquals(Game.calculateCurrentPlayer(0, players), jan);
        assertEquals(Game.calculateCurrentPlayer(1, players), lena);
        assertEquals(Game.calculateCurrentPlayer(2, players), patrick);
    }

    @Test
    public void shouldGetCurrentPlayerOnFirstBackwardPass(){
        assertEquals(Game.calculateCurrentPlayer(3, players), patrick);
        assertEquals(Game.calculateCurrentPlayer(4, players), lena);
        assertEquals(Game.calculateCurrentPlayer(5, players), jan);
    }

    @Test
    public void shouldGetCurrentPlayerOnSecondForwardPass(){
        assertEquals(Game.calculateCurrentPlayer(6, players), jan);
        assertEquals(Game.calculateCurrentPlayer(7, players), lena);
        assertEquals(Game.calculateCurrentPlayer(8, players), patrick);
    }

    @Test
    public void shouldGetCurrentPlayerOnThirdForwardPass(){
        assertEquals(Game.calculateCurrentPlayer(9, players), jan);
        assertEquals(Game.calculateCurrentPlayer(10, players), lena);
        assertEquals(Game.calculateCurrentPlayer(11, players), patrick);
    }
    
}
