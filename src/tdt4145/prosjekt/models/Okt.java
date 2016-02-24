package tdt4145.prosjekt.models;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 24.02.16.
 */
public class Okt {

    private StringProperty navn, notat;
    private Property<LocalDate> dato;
    private Property<LocalTime> start, slutt;
    private IntegerProperty form, prestasjon;

    public Okt(String navn, LocalDate dato, LocalTime start, LocalTime slutt, int form, int prestasjon, String notat) {
        this.navn = new SimpleStringProperty(navn);
        this.notat = new SimpleStringProperty(notat);
        this.dato = new ObjectPropertyBase<LocalDate>(dato) {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "dato";
            }
        };
        this.start = new ObjectPropertyBase<LocalTime>(start) {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "start";
            }
        };
        this.slutt = new ObjectPropertyBase<LocalTime>(slutt) {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "slutt";
            }
        };
        this.form = new SimpleIntegerProperty(form);
        this.prestasjon = new SimpleIntegerProperty(prestasjon);

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

    public String getNotat() {
        return notat.get();
    }

    public StringProperty notatProperty() {
        return notat;
    }

    public void setNotat(String notat) {
        this.notat.set(notat);
    }

    public LocalDate getDato() {
        return dato.getValue();
    }

    public Property<LocalDate> datoProperty() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato.setValue(dato);
    }

    public LocalTime getStart() {
        return start.getValue();
    }

    public Property<LocalTime> startProperty() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start.setValue(start);
    }

    public LocalTime getSlutt() {
        return slutt.getValue();
    }

    public Property<LocalTime> sluttProperty() {
        return slutt;
    }

    public void setSlutt(LocalTime slutt) {
        this.slutt.setValue(slutt);
    }

    public int getForm() {
        return form.get();
    }

    public IntegerProperty formProperty() {
        return form;
    }

    public void setForm(int form) {
        this.form.set(form);
    }

    public int getPrestasjon() {
        return prestasjon.get();
    }

    public IntegerProperty prestasjonProperty() {
        return prestasjon;
    }

    public void setPrestasjon(int prestasjon) {
        this.prestasjon.set(prestasjon);
    }
}
