package co.com.sofka.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public final class Descuento implements ValueObject<Descuento.Properties> {
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

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String razon() {
                return razon;
            }

            @Override
            public Double descuento() {
                return valor;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descuento descuento = (Descuento) o;
        return Objects.equals(razon, descuento.razon) && Objects.equals(valor, descuento.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(razon, valor);
    }

    public interface  Properties {
        String razon();
        Double descuento();
    }
}
