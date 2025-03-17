package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryDeleteRepo;
import domain.Inventory.repository.InventoryUpdateRepo;
import dto.InventoryDto;
import exception.InventoryException;
import exception.NotFoundException;

public class InventoryDeleteServiceImp implements InventoryDeleteService {

    private InventoryDeleteRepo inventoryDeleteRepo;
    public InventoryDeleteServiceImp(InventoryDeleteRepo inventoryDeleteRepo) {
        this.inventoryDeleteRepo = inventoryDeleteRepo;
    }

    @Override
    public InventoryDto deleteInventory(String prodID, String clientID, String wareID) {
        try {
            return inventoryDeleteRepo.deleteInventory(prodID,clientID,wareID).orElseThrow(()->new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }
    }
}
