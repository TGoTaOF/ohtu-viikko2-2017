package ohtu.verkkokauppa;

public class Kauppa {

    public VarastoInterface varasto;
    public PankkiInterface pankki;
    public Ostoskori ostoskori;
    public KirjanpitoInterface kirjanpito;
    public ViitegeneraattoriInterface viitegeneraattori;
    public String kaupanTili;

    public Kauppa(VarastoInterface v, PankkiInterface p, ViitegeneraattoriInterface g, KirjanpitoInterface k) {
        varasto = v;
        pankki = p;
        viitegeneraattori = g;
        kirjanpito = k;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
