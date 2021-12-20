package co.com.sofka.factura.values;

import java.io.Serializable;
import java.util.Objects;

public class Cantidad implements Serializable {

    private final Double valor;

    public Cantidad(Double valor) {
        this.valor = Objects.requireNonNull(valor);
    }

    public Double getValor() {
        return valor;
    }
}
