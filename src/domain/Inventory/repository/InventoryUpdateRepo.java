package domain.Inventory.repository;

import config.DBConnection;
import dto.InventoryDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Optional;

public interface InventoryUpdateRepo {

    Optional<InventoryDto> updateQuantity(String prodID, String clientID, String wareID, int newQuantity);
}
