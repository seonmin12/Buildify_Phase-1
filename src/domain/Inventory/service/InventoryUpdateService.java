package domain.Inventory.service;

import dto.InventoryDto;

public interface InventoryUpdateService {
    InventoryDto updateInventory(String prodID, String clientID, String wareID, int newQuantity);
}
