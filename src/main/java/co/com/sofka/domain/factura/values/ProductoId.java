package co.com.sofka.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class ProductoId extends Identity {
    private ProductoId(String value){
        super(value);
    }

    public static ProductoId of(String value){
        return new ProductoId(value);
    }
}
