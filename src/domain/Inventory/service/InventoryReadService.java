package domain.Inventory.service;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;

public interface InventoryReadService {
    List<InventoryDto> ReadAll();
    InventoryDto ReadOneProductName(String productName);
    InventoryDto ReadByClientID(String clientID);
    List<InventoryDto> ReadByCategory(String category);
}
