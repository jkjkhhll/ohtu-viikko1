package ohtuesimerkki;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    public void pelaajanHakuOnnistuu() {
	Player pelaaja = stats.search("Lem");
        assertEquals(pelaaja.getName(), "Lemieux");
    }

    @Test
    public void palauttaaNullJosPelaajaaEiLoydy() {
	Player pelaaja = stats.search("???");
	assertEquals(pelaaja, null);
    }

    @Test
    public void palauttaaTiiminOikein() {
	List<String> EDM = Arrays.asList("Semenko", "Kurri", "Gretzky");
	List<Player> ps = stats.team("EDM");
	for (Player p : ps) {
	    assertEquals(true, EDM.contains(p.getName()));
	}
    }

    @Test
    public void pistePorssiToimii() {
	List<Player> top = stats.topScorers(1);
	assertEquals(top.get(0).getName(), "Gretzky");
    } 

}
