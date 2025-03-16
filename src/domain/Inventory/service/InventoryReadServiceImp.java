package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryReadRepo;
import dto.InventoryDto;
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
    public List<InventoryDto> ReadAll() {
        try {
            return inventoryReadRepo.ReadAll().orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.DB_INVENTORY_READ_ALL_ERROR);
        }


    }

    @Override
    public InventoryDto ReadOneProductName(String productName) {
        try {
            return inventoryReadRepo.ReadOneProductName(productName).orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }
    }

    @Override
    public InventoryDto ReadByClientID(String clientID) {
        try {
            return inventoryReadRepo.ReadByClientID(clientID).orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }
    }

    @Override
    public List<InventoryDto> ReadByCategory(String category) {
        List<InventoryDto> inventoryList = inventoryReadRepo.ReadByCategory(category);

        if (inventoryList == null || inventoryList.isEmpty()) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }

        return inventoryList;
    }
}
