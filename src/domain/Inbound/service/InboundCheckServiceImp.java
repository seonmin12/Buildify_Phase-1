package domain.Inbound.service;

import domain.Inbound.repository.InboundCheckRepo;
import domain.Inbound.repository.InboundSearchRepo;
import dto.InboundDto;

import java.util.List;

public class InboundCheckServiceImp implements InboundCheckService{

    private final InboundCheckRepo inboundCheckRepo;

    public InboundCheckServiceImp(InboundCheckRepo inboundCheckRepo) {
        this.inboundCheckRepo = inboundCheckRepo;
    }


    @Override
    public List<InboundDto> allCheckRead() {
        return inboundCheckRepo.allCheckRead();

    }

    @Override
    public void allCheckUpdate() {
        inboundCheckRepo.allCheckUpdate();

    }

    @Override
    public void allCheckReturn() {
        inboundCheckRepo.allCheckReturn();

    }

    @Override
    public List<InboundDto> prodCheckRead(String ci) {
        return inboundCheckRepo.prodCheckRead();
    }

    @Override
    public void prodCheckUpdate(String ci) {

    }

    @Override
    public void prodCheckReturn(String ci) {

    }

    @Override
    public List<InboundDto> clientCheckRead(String ci) {
        return inboundCheckRepo.clientCheckRead(ci);
    }

    @Override
    public void clientCheckUpdate(String ci) {

    }

    @Override
    public void clientCheckReturn(String ci) {

    }

}



