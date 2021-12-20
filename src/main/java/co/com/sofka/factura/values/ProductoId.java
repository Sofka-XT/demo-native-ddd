package co.com.sofka.factura.values;

import co.com.sofka.generico.Id;

public class ProductoId extends Id {
    private ProductoId(String value){
        super(value);
    }

    public static ProductoId of(String value){
        return new ProductoId(value);
    }
}
