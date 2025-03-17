package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryUpdateRepo;
import dto.InventoryDto;
import exception.InventoryException;
import exception.NotFoundException;

public class InventoryUpdateServiceImp implements InventoryUpdateService {

    private InventoryUpdateRepo inventoryUpdateRepo;
    public InventoryUpdateServiceImp(InventoryUpdateRepo inventoryUpdateRepo) {
        this.inventoryUpdateRepo = inventoryUpdateRepo;
    }

    @Override
    public InventoryDto updateInventory(String prodID, String clientID, String wareID, int newQuantity) {
        try {
            return inventoryUpdateRepo.updateQuantity(prodID,clientID,wareID,newQuantity).orElseThrow(()->new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }
    }
}
