package domain.Inventory.service;

import dto.WarehouseDto;

import java.util.List;

public interface InventoryReadService {
    List<WarehouseDto> ReadAll();
    WarehouseDto ReadOneProductName(String productName);
}
