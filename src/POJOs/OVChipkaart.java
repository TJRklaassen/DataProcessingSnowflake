package POJOs;

import java.util.Date;

public class OVChipkaart {

    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private Reiziger eigenaar;

    public OVChipkaart( int kaartNummer, Date geldigTot, int klasse, double saldo, Reiziger eigenaar) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.eigenaar = eigenaar;
    }

    public int getKaartNummer() { return kaartNummer; }
    public void setKaartNummer(int kaartNummer) { this.kaartNummer = kaartNummer;}

    public Date getGeldigTot() { return geldigTot; }
    public void setGeldigTot( Date geldigTot ) { this.geldigTot = geldigTot; }

    public int getKlasse() { return klasse; }
    public void setKlasse( int klasse ) { this.klasse = klasse; }

    public double getSaldo() { return saldo; }
    public void setSaldo( Double saldo ) { this.saldo = saldo; }

    public Reiziger getEigenaar() { return eigenaar; }
    public void setEigenaar( Reiziger eigenaar ) { this.eigenaar = eigenaar; }
}
