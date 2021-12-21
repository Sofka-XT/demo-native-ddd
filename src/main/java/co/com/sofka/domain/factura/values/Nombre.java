package co.com.sofka.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Nombre implements ValueObject<String> {
    private final String cuaderno;

    public Nombre(String cuaderno) {

        this.cuaderno = Objects.requireNonNull(cuaderno, "El nombre no debe ser nulo");
        //TODO: hacer mas validaciones
    }

    @Override
    public String value() {
        return cuaderno;
    }
}
