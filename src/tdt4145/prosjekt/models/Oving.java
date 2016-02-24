package tdt4145.prosjekt.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 24.02.16.
 */
public class Oving {
    private StringProperty navn, beskrivelse, enhet;
    private IntegerProperty belastning, repetisjoner, sett;

    public Oving(String navn, String beskrivelse, int belastning, int repetisjoner, int sett, String enhet) {
        this.navn = new SimpleStringProperty(navn);
        this.beskrivelse = new SimpleStringProperty(beskrivelse);
        this.enhet = new SimpleStringProperty(enhet);
        this.belastning = new SimpleIntegerProperty(belastning);
        this.repetisjoner = new SimpleIntegerProperty(repetisjoner);
        this.sett = new SimpleIntegerProperty(sett);
    }



    public String getNavn() {
        return navn.get();
    }

    public StringProperty navnProperty() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn.set(navn);
    }

    public String getBeskrivelse() {
        return beskrivelse.get();
    }

    public StringProperty beskrivelseProperty() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse.set(beskrivelse);
    }

    public String getEnhet() {
        return enhet.get();
    }

    public StringProperty enhetProperty() {
        return enhet;
    }

    public void setEnhet(String enhet) {
        this.enhet.set(enhet);
    }

    public int getBelastning() {
        return belastning.get();
    }

    public IntegerProperty belastningProperty() {
        return belastning;
    }

    public void setBelastning(int belastning) {
        this.belastning.set(belastning);
    }

    public int getRepetisjoner() {
        return repetisjoner.get();
    }

    public IntegerProperty repetisjonerProperty() {
        return repetisjoner;
    }

    public void setRepetisjoner(int repetisjoner) {
        this.repetisjoner.set(repetisjoner);
    }

    public int getSett() {
        return sett.get();
    }

    public IntegerProperty settProperty() {
        return sett;
    }

    public void setSett(int sett) {
        this.sett.set(sett);
    }
}
