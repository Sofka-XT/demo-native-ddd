package co.com.sofka.factura.values;

import java.io.Serializable;
import java.util.Objects;

public final class Descuento implements Serializable {
    private final String razon;
    private final Double valor;

    public Descuento(String razon, Double valor) {
        this.razon = Objects.requireNonNull(razon, "La razon es requerido");
        this.valor = Objects.requireNonNull(valor, "El valor del descuento es requerido");
        if(this.valor <= 0){
            throw new IllegalArgumentException("El decunto no puede ser cero o negativo");
        }
        if(this.razon.isBlank()){
            throw new IllegalArgumentException("El valor no puede estar vacio");
        }
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descuento descuento = (Descuento) o;
        return Double.compare(descuento.valor, valor) == 0 && Objects.equals(razon, descuento.razon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(razon, valor);
    }
}
