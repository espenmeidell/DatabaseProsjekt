package tdt4145.prosjekt.models;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by agnetedjupvik on 01.03.2016.
 */
public class Mal {

    private IntegerProperty id, mal;
    private Property<LocalDate> dato;

    public Mal(int id, int mal, LocalDate dato) {
        this.id = new SimpleIntegerProperty(id);
        this.mal = new SimpleIntegerProperty(mal);

        this.dato = new ObjectPropertyBase<LocalDate>(dato) {
            @Override
            public Object getBean() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }
        };
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public int getMal() {
        return mal.get();
    }

    public IntegerProperty malProperty() {
        return mal;
    }

    public void setMal(int mal) {
        this.mal.set(mal);
    }
}
