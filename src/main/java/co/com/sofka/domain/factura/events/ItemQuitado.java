package co.com.sofka.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.factura.values.ItemId;

public class ItemQuitado extends DomainEvent {
    private final ItemId itemId;

    public ItemQuitado(ItemId itemId) {
        super("sofka.factura.itemquitado");
        this.itemId = itemId;
    }

    public ItemId getItemId() {
        return itemId;
    }
}
