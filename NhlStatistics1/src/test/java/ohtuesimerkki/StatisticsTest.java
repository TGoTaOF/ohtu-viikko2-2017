/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mxsampsa
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void testSearch() {
        assertEquals(String.format("%-20s","Kurri") + " " + "EDM" + " " + "37" + " + " 
                + "53" + " = " + "90", stats.search("Kurri").toString());
        assertNull(stats.search("OskariOlematon"));
    }

    @Test
    public void testTeam() {
        assertEquals("Gretzky", stats.team("EDM").get(2).getName());
    }

    @Test
    public void testTopScorers() {
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }
    
}
