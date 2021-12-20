package co.com.sofka.factura.values;

import co.com.sofka.generico.Id;

public final class FacturaId extends Id {

    private FacturaId(String value){
        super(value);
    }

    public FacturaId(){

    }

    public static FacturaId of(String value){
        return new FacturaId(value);
    }

}
