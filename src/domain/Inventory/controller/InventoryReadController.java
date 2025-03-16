package domain.Inventory.controller;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

public interface InventoryReadController {
    InventoryDto ReadOneProductName();


    List<InventoryDto> ReadAll();

    InventoryDto ReadByClientID();

    List<InventoryDto> ReadByCategory();

}
