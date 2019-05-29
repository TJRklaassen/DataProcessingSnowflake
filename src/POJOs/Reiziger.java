package POJOs;

import java.util.ArrayList;
import java.util.Date;

public class Reiziger {

    private int reizigerID;
    private String naam;
    private Date gbDatum;
    private ArrayList<OVChipkaart> ovChipkaarten;

    public Reiziger(int rID, String nm, Date gbDatum, ArrayList<OVChipkaart> ovChipkaarten) {
        this.reizigerID = rID;
        this.naam = nm;
        this.gbDatum = gbDatum;
        this.ovChipkaarten = ovChipkaarten;
    }

    public int getReizigerID() { return reizigerID; }
    public void setReizigerID(int ID) { this.reizigerID = ID; }

    public String getNaam() { return naam; }
    public void setNaam( String naam) { this.naam = naam; }

    public Date getGbDatum() { return gbDatum; }
    public void setGbDatum( Date gbDatum ) { this.gbDatum = gbDatum; }

    public ArrayList<OVChipkaart> getOvChipkaarten() { return ovChipkaarten; }
    public void setOvChipkaarten(ArrayList<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }
}
