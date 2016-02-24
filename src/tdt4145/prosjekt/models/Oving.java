package tdt4145.prosjekt.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 24.02.16.
 */
public class Oving {
    private StringProperty navnProperty, beskrivelsePropery, enhetProperty;
    private IntegerProperty belastningProperty, repetisjonerProperty, settProperty;

    public Oving(String navn, String beskrivelse, int belastning, int repetisjoner, int sett, String enhet) {
        this.navnProperty = new SimpleStringProperty(navn);
        this.beskrivelsePropery = new SimpleStringProperty(beskrivelse);
        this.enhetProperty = new SimpleStringProperty(enhet);
        this.belastningProperty = new SimpleIntegerProperty(belastning);
        this.repetisjonerProperty = new SimpleIntegerProperty(repetisjoner);
        this.settProperty = new SimpleIntegerProperty(sett);
    }



    public String getNavnProperty() {
        return navnProperty.get();
    }

    public StringProperty navnPropertyProperty() {
        return navnProperty;
    }

    public void setNavnProperty(String navnProperty) {
        this.navnProperty.set(navnProperty);
    }

    public String getBeskrivelsePropery() {
        return beskrivelsePropery.get();
    }

    public StringProperty beskrivelseProperyProperty() {
        return beskrivelsePropery;
    }

    public void setBeskrivelsePropery(String beskrivelsePropery) {
        this.beskrivelsePropery.set(beskrivelsePropery);
    }

    public String getEnhetProperty() {
        return enhetProperty.get();
    }

    public StringProperty enhetPropertyProperty() {
        return enhetProperty;
    }

    public void setEnhetProperty(String enhetProperty) {
        this.enhetProperty.set(enhetProperty);
    }

    public int getBelastningProperty() {
        return belastningProperty.get();
    }

    public IntegerProperty belastningPropertyProperty() {
        return belastningProperty;
    }

    public void setBelastningProperty(int belastningProperty) {
        this.belastningProperty.set(belastningProperty);
    }

    public int getRepetisjonerProperty() {
        return repetisjonerProperty.get();
    }

    public IntegerProperty repetisjonerPropertyProperty() {
        return repetisjonerProperty;
    }

    public void setRepetisjonerProperty(int repetisjonerProperty) {
        this.repetisjonerProperty.set(repetisjonerProperty);
    }

    public int getSettProperty() {
        return settProperty.get();
    }

    public IntegerProperty settPropertyProperty() {
        return settProperty;
    }

    public void setSettProperty(int settProperty) {
        this.settProperty.set(settProperty);
    }
}
