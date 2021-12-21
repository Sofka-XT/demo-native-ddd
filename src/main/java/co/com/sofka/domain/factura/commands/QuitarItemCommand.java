package co.com.sofka.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.factura.values.FacturaId;
import co.com.sofka.domain.factura.values.ItemId;

public class QuitarItemCommand extends Command {

    private final FacturaId facturaId;
    private final ItemId itemId;

    public QuitarItemCommand(FacturaId facturaId, ItemId itemId){
        this.facturaId = facturaId;

        this.itemId = itemId;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }
}
