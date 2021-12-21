package co.com.sofka.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Cantidad implements ValueObject<Double> {

    private final Double valor;

    public Cantidad(Double valor) {
        this.valor = Objects.requireNonNull(valor);
    }

    @Override
    public Double value() {
        return valor;
    }
}
