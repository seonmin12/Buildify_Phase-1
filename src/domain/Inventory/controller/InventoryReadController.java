package domain.Inventory.controller;

import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

public interface InventoryReadController {
    WarehouseDto ReadOneProductName();


    List<WarehouseDto> ReadAll();

}
