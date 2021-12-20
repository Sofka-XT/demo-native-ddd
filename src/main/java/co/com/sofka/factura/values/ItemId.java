package co.com.sofka.factura.values;

import co.com.sofka.generico.Id;

public class ItemId extends Id {
    private ItemId(String value){
        super(value);
    }

    public ItemId(){

    }

    public static ItemId of(String value){
        return new ItemId(value);
    }
}
