package co.com.sofka.factura.values;

import java.io.Serializable;
import java.util.Objects;

public final class Precio implements Serializable {
    private final Double valor;

    public Precio(Double valor) {
        this.valor = Objects.requireNonNull(valor, "El valor no puede ser null");
        if(0>=this.valor){
            throw new IllegalArgumentException("No puede se un valor negativo o cero");
        }
    }

    public Precio aplicarDescuento(Descuento descuento){
        return new Precio(this.valor - this.valor * descuento.getValor());
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Precio precio = (Precio) o;
        return Objects.equals(valor, precio.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
