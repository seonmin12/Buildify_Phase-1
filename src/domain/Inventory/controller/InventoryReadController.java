package domain.Inventory.controller;

import dto.WarehouseDto;

public interface InventoryReadController {
    WarehouseDto ReadOne(String id);
    void ReadAll();
}
