package domain.Inbound.repository;

import domain.Inbound.controller.InboundSearchController;
import dto.InboundDto;

import java.sql.SQLException;

public interface InboundInsertRepo {
    void insert(InboundDto inboundDto) throws SQLException;
}
