package domain.AccountManagement.User.controller;

import common.ValidCheck;
import domain.AccountManagement.User.service.ProductService;
import dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductControllerImpl implements ProductController {

    private final ValidCheck validCheck;
    private final ProductService productService;

    public ProductControllerImpl(ValidCheck validCheck, ProductService productService) {
        this.validCheck = validCheck;
        this.productService = productService;
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
        return productService.registerProduct(productDto);
    }

    @Override
    public boolean getAllProduct() {
        List<ProductDto> productDtoList = productService.getAllProduct();

        if (productDtoList.isEmpty()) {
            System.out.println("상품 목록이 없습니다.");
            return false;
        } else {
            // 가격 포맷 (천 단위 콤마 추가)
            NumberFormat priceFormat = NumberFormat.getInstance(Locale.KOREA);

            // 테이블 헤더 출력
            System.out.printf("%-12s │ %-10s │ %-20s │ %-12s │ %-10s │ %-12s │ %-10s %n",
                    "상품 ID", "브랜드", "상품명", "가격", "바코드", "카테고리", "사이즈");
            System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────");

            // 테이블 데이터 출력
            for (ProductDto product : productDtoList) {
                System.out.printf("%-12s │ %-10s │ %-20s │ %12s원 │ %-10d │ %-12s │ %10.2f %n",
                        product.getProd_id(),
                        product.getBrand(),
                        product.getProd_name(),
                        priceFormat.format(product.getProd_price()),  // 가격 천 단위 쉼표 추가
                        product.getProd_code(),
                        product.getProd_category(),
                        product.getProd_size());
            }

            return true;
        }
    }
}
