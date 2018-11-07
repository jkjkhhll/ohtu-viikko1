package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;
    static final int TESTIVARASTON_KOKO = 10;

    @Before
    public void setUp() {
        varasto = new Varasto(TESTIVARASTON_KOKO);
    }


    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void virheJosVirheellinenTilavuusLuotaessa() {
		Varasto ilman_alkusaldoa = new Varasto(-1);
        assertEquals(0, ilman_alkusaldoa.getTilavuus(), vertailuTarkkuus);
        Varasto alkusaldolla = new Varasto(-1, 1);
        assertEquals(0, alkusaldolla.getTilavuus(), vertailuTarkkuus);
	}

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        Varasto alkusaldolla = new Varasto(10, 1);
        assertEquals(10, alkusaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoMuuttuuNollaksi() { 
        Varasto v = new Varasto(10, -1);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void oikeaAlkusaldo() { 
        Varasto v = new Varasto(10, 5);
        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylimaarainenAlkusaldoHukataan() { 
        Varasto v = new Varasto(10, 11);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() { 
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylimaarainenSaldoHukataan() { 
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoEiMuutaSaldoa() { 
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otettaessaYliSaldonVarastoNollautuu() { 
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysToimii() { 
        varasto.lisaaVarastoon(5);
        assertEquals(varasto.toString(), "saldo = 5.0, vielä tilaa 5.0");
    }

}