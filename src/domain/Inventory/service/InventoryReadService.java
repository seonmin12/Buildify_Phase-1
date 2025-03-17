package domain.Inventory.service;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;

public interface InventoryReadService {
    List<InventoryDto> ReadAll();
    List<InventoryDto> ReadByProductName(String productName);
    List<InventoryDto> ReadByClientID(String clientID);
    List<InventoryDto> ReadByCategory(String category);
}
