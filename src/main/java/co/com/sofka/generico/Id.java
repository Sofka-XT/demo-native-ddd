package co.com.sofka.generico;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Id implements Serializable {
    private final String value;

    public Id(){
        this(UUID.randomUUID().toString());
    }


    public Id(String value){
        this.value = Objects.requireNonNull(value, "El valor es requerido");
        if(this.value.isBlank()){
            throw new IllegalArgumentException("El valor no puede estar vacio");
        }
    }


    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id facturaId = (Id) o;
        return Objects.equals(value, facturaId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
