package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryReadRepo;
import dto.WarehouseDto;
import exception.InventoryException;
import exception.NotFoundException;

import java.util.List;

public class InventoryReadServiceImp implements InventoryReadService {
    private InventoryReadRepo inventoryReadRepo;
    public InventoryReadServiceImp(InventoryReadRepo inventoryReadRepo){
        this.inventoryReadRepo = inventoryReadRepo;
    }
    @Override
    public List<WarehouseDto> ReadAll() {
        try {
            return inventoryReadRepo.ReadAll().orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.DB_INVENTORY_READ_ALL_ERROR);
        }


    }

    @Override
    public WarehouseDto ReadOneProductName(String productName) {
        try {
            return inventoryReadRepo.ReadOneProductName(productName).orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }
    }
}
