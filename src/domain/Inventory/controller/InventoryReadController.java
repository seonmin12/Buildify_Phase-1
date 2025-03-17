package domain.Inventory.controller;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

public interface InventoryReadController {
    List<InventoryDto> ReadByProductName();


    List<InventoryDto> ReadAll();

    List<InventoryDto> ReadByClientID();

    List<InventoryDto> ReadByCategory();

}
