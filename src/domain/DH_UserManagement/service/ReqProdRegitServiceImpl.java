package domain.DH_UserManagement.service;
import domain.DH_UserManagement.repository.ReqProdRegitRepository;
import dto.ProductDto;


public class ReqProdRegitServiceImpl implements ReqProdRegitService {

    private final ReqProdRegitRepository reqProdRegitRepository;

    public ReqProdRegitServiceImpl(ReqProdRegitRepository reqProdRegitRepository) {
        this.reqProdRegitRepository = reqProdRegitRepository;
    }

    @Override
    public boolean registerProduct(ProductDto productDto) {
        return reqProdRegitRepository.insertProduct(productDto);
    }
}
