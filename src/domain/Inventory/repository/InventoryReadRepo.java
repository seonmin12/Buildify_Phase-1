package domain.Inventory.repository;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

public interface InventoryReadRepo {
    Optional<List<InventoryDto>> ReadAll();
    List<InventoryDto> ReadByProductName(String productName);
    List <InventoryDto> ReadByClientID(String clientID);
    List<InventoryDto> ReadByCategory(String category);
}
