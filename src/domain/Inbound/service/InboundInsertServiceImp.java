package domain.Inbound.service;

import domain.Inbound.repository.InboundInsertRepo;
import domain.Inbound.repository.InboundSearchRepo;
import domain.Inbound.repository.InboundSearchRepoImp;
import dto.InboundDto;
import exception.InboundException;

import java.sql.SQLException;

public class InboundInsertServiceImp  implements InboundInsertService{

    private final InboundInsertRepo inboundInsertRepo;
    private final InboundSearchRepo inboundSearchRepo;

    public InboundInsertServiceImp(InboundInsertRepo inboundInsertRepo, InboundSearchRepo inboundSearchRepo) {
        this.inboundInsertRepo = inboundInsertRepo;
        this.inboundSearchRepo = inboundSearchRepo;
    }

    @Override
    public InboundDto insert(InboundDto inboundDto) throws InboundException {
        try{
            inboundInsertRepo.insert(InboundDto.builder()
                    .inbound_number(inboundDto.getInbound_number())
                    .prod_id(inboundDto.getProd_id())
                    .client_id(inboundDto.getClient_id())
                    .quantity(inboundDto.getQuantity())
                    .inbound_status(inboundDto.getInbound_status())
                    .req_inbound_day(inboundDto.getReq_inbound_day())
                    .ware_id(inboundDto.getWare_id())
                    .build());;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return inboundDto;
    }
}
