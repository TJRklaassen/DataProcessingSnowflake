package POJOs;

import java.util.ArrayList;
import java.util.Date;

public class Reiziger {

    private int reizigerID;
    private String voorl;
    private String tussenV;
    private String achterNaam;
    private Date gbDatum;
    private ArrayList<OVChipkaart> ovChipkaarten;

    public Reiziger(int rID, String vl, String tv, String an, Date gbDatum, ArrayList<OVChipkaart> ovChipkaarten) {
        this.reizigerID = rID;
        this.voorl = vl;
        this.tussenV = tv;
        this.achterNaam = an;
        this.gbDatum = gbDatum;
        this.ovChipkaarten = ovChipkaarten;
    }

    public int getReizigerID() { return reizigerID; }
    public void setReizigerID(int ID) { this.reizigerID = ID; }

    public String getVoorl() { return voorl; }
    public void setVoorl( String voorl ) { this.voorl = voorl; }

    public String getTussenV() { return tussenV; }
    public void setTussenV( String tussenV ) { this.tussenV = tussenV; }

    public String getAchterNaam() { return achterNaam; }
    public void setAchterNaam( String naam) { this.achterNaam = naam; }

    public Date getGbDatum() { return gbDatum; }
    public void setGbDatum( Date gbDatum ) { this.gbDatum = gbDatum; }

    public ArrayList<OVChipkaart> getOvChipkaarten() { return ovChipkaarten; }
    public void setOvChipkaarten(ArrayList<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }
}
