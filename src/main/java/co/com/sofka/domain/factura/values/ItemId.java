package co.com.sofka.domain.factura.values;


import co.com.sofka.domain.generic.Identity;

public class ItemId extends Identity {
    private ItemId(String value){
        super(value);
    }

    public ItemId(){

    }

    public static ItemId of(String value){
        return new ItemId(value);
    }
}
