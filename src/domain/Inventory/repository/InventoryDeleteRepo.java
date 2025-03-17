package domain.Inventory.repository;

import dto.InventoryDto;
import exception.InventoryException;

import java.util.Optional;

public interface InventoryDeleteRepo {
    Optional<InventoryDto> deleteInventory(String prodID, String clientID, String wareID);
}
