package domain.DH_UserManagement.controller;

import common.ValidCheck;
import domain.DH_UserManagement.service.ReqProdRegitService;
import dto.ProductDto;

import java.math.BigDecimal;
import java.util.UUID;

public class ReqProdRegitControllerImpl implements ReqProdRegitController {

    private final ValidCheck validCheck;
    private final ReqProdRegitService reqProdRegitService;

    public ReqProdRegitControllerImpl(ValidCheck validCheck, ReqProdRegitService reqProdRegitService) {
        this.validCheck = validCheck;
        this.reqProdRegitService = reqProdRegitService;
    }

    @Override
    public boolean requestProdcutRegist() {
        String prodID = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        System.out.println("브랜드를 입력하세요. (한글 또는 영문, 2~20자)");
        String brand = validCheck.inputStringRegex(validCheck.PRODUCT_BRAND_REGEX);

        System.out.println("상품명을 입력하세요. (한글/영문/숫자, 2~30자)");
        String productName = validCheck.inputStringRegex(validCheck.PRODUCT_NAME_REGEX);

        System.out.println("가격을 입력하세요. (숫자만 입력, 1~10자리)");
        int price = validCheck.inputIntRegex(validCheck.PRODUCT_PRICE_REGEX);

        System.out.println("바코드를 입력하세요. (숫자만 8~13자리, EAN-8 또는 EAN-13 형식)");
        int barcode = validCheck.inputIntRegex(validCheck.PRODUCT_CODE_REGEX);

        System.out.println("카테고리를 입력하세요. (한글 또는 영문, 2~20자)");
        String category = validCheck.inputStringRegex(validCheck.PRODUCT_CATEGORY_REGEX);

        System.out.println("사이즈를 입력하세요. (정수 또는 소수점 이하 2자리까지 가능, 예: 42, 42.5, 100.99)");
        BigDecimal size = validCheck.inputDecimalRegex(validCheck.PRODUCT_SIZE_REGEX);

        ProductDto productDto = new ProductDto(prodID, brand, productName, price, barcode, category, size);
        return reqProdRegitService.registerProduct(productDto);
    }
}
