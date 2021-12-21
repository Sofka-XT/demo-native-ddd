package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.factura.commands.AgregarItemCommand;
import co.com.sofka.domain.factura.events.FacturaCreada;
import co.com.sofka.domain.factura.events.ItemAgregado;
import co.com.sofka.domain.factura.values.*;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AgregarItemUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void agregarItem() {
        FacturaId facturaId = FacturaId.of("xxxxx");
        ProductoId productoId = ProductoId.of("fffff");
        Nombre nombre = new Nombre("Cuaderno");
        Cantidad cantidad = new Cantidad(20D);
        Precio precio = new Precio(30000D);
        var command = new AgregarItemCommand(facturaId, productoId, nombre, cantidad, precio);
        var usecase = new AgregarItemUseCase();

        when(repository.getEventsBy("xxxxx")).thenReturn(events());
        usecase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ItemAgregado) events.get(0);

        Assertions.assertEquals(20D, event.getCantidad().value());
        Assertions.assertEquals("Cuaderno", event.getNombre().value());
        Assertions.assertEquals(30000D, event.getPrecio().value());
        Mockito.verify(repository).getEventsBy("xxxxx");

    }

    @Test
    void agregarItem_errorPorCantidades() {
        FacturaId facturaId = FacturaId.of("xxxxx");
        ProductoId productoId = ProductoId.of("fffff");
        Nombre nombre = new Nombre("Cuaderno");
        Cantidad cantidad = new Cantidad(20D);
        Precio precio = new Precio(30000D);
        var command = new AgregarItemCommand(facturaId, productoId, nombre, cantidad, precio);
        var usecase = new AgregarItemUseCase();

        when(repository.getEventsBy("xxxxx")).thenReturn(fullItemsEvents());
        usecase.addRepository(repository);

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(facturaId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        });


    }

    private List<DomainEvent> events() {
        return List.of(new FacturaCreada(
                ClienteId.of("xxxxx"),
                new Fecha(new Date())
        ));
    }

    private List<DomainEvent> fullItemsEvents() {

        return List.of(new FacturaCreada(
                        ClienteId.of("xxxxx"),
                        new Fecha(new Date())
                ),
                new ItemAgregado(
                        ItemId.of("1"),
                        ProductoId.of("2"),
                        new Nombre("a"),
                        new Cantidad(1D),
                        new Precio(2D)
                ),
                new ItemAgregado(
                        ItemId.of("2"),
                        ProductoId.of("2"),
                        new Nombre("a"),
                        new Cantidad(1D),
                        new Precio(2D)
                ),
                new ItemAgregado(
                        ItemId.of("3"),
                        ProductoId.of("2"),
                        new Nombre("a"),
                        new Cantidad(1D),
                        new Precio(2D)
                ),
                new ItemAgregado(
                        ItemId.of("4"),
                        ProductoId.of("2"),
                        new Nombre("a"),
                        new Cantidad(1D),
                        new Precio(2D)
                ),
                new ItemAgregado(
                        ItemId.of("5"),
                        ProductoId.of("2"),
                        new Nombre("a"),
                        new Cantidad(1D),
                        new Precio(2D)
                )
        );
    }

}