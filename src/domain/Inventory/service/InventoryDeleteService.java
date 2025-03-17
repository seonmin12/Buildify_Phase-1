package domain.Inventory.service;

import dto.InventoryDto;

public interface InventoryDeleteService {
    InventoryDto deleteInventory(String prodID, String clientID, String wareID);
}
