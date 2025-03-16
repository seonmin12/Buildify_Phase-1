package domain.Inventory.repository;

import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

public interface InventoryReadRepo {
    Optional<List<WarehouseDto>> ReadAll();
    Optional<WarehouseDto> ReadOneProductName(String productName);
}
