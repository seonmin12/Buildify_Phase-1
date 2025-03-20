package domain.Inbound.controller;

import common.ValidCheck;
import domain.Inbound.service.InboundInsertService;
import dto.InboundDto;
import dto.ProductDto;
import dto.UserDto;

import java.time.LocalDate;
import java.util.List;

import java.sql.Date;
import java.util.UUID;

import static java.time.LocalTime.now;

public class InboundInsertControllerImp implements InboundInsertController{

    private final InboundInsertService insertService;
    private final ValidCheck validCheck ;


    public InboundInsertControllerImp(InboundInsertService insertService, ValidCheck validCheck) {
        this.insertService = insertService;
        this.validCheck = validCheck;
    }


    /**
     * 회원 입고요청
     * @param inboundDto
     * @return
     */
    @Override
    public InboundDto insert(InboundDto inboundDto) {
        return insertService.insert(inboundDto);
    }

    /**
     * 회원 입고요청 시작
     * @param userDto
     * @return
     */
    @Override
    public boolean insertrun(UserDto userDto) {
        List<ProductDto> productDto = insertService.inboundinsertlist();

        if(productDto == null || productDto.isEmpty()){
            System.out.println("입고가능 상품이 없습니다.");
            return false;
        }

        int index = 1;
        for(ProductDto productDto1 : productDto){

            System.out.printf("번호:%d ID: %s | 브랜드: %s | 제품명: %s | 가격: %,d원 | 코드: %d | 카테고리: %s | 크기: %.2fcm³\n",index++,productDto1.getProd_id(), productDto1.getBrand(), productDto1.getProd_name(),
                    productDto1.getProd_price(), productDto1.getProd_code(), productDto1.getProd_category(),
                   productDto1.getProd_size() );
        }
        System.out.println("입고 요청할 번호를 선택하세요 (취소하려면 0입력)");
        int select = validCheck.inputNumRegex();

        if(select == 0){
            return true;
        }else {
            ProductDto productDto2 = productDto.get(select - 1);

            System.out.print("수량을 입력하세요: ");
            int amount = Integer.parseInt(validCheck.inputAnyString());

            System.out.println("입고 요청 완료");
            System.out.printf("제품ID: %s 브랜드: %-8s 제품명:%s 가격: %,8d 제품코드: %9d 카테고리: %-10s 크기(cm³) %8.2f |\n",
                    productDto2.getProd_id(), productDto2.getBrand(), productDto2.getProd_name(),
                    productDto2.getProd_price(), productDto2.getProd_code(), productDto2.getProd_category(),
                    productDto2.getProd_size());
            System.out.println("입고 수량: " + amount);
            String inbound_num = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            InboundDto inboundDto = new InboundDto();
            inboundDto.setInbound_number(inbound_num);
            inboundDto.setProd_id(productDto2.getProd_id());
            inboundDto.setClient_id(userDto.getClient_id());
            inboundDto.setQuantity(amount);
            inboundDto.setReq_inbound_day(Date.valueOf(LocalDate.now()));
            inboundDto.setWare_id("ware1");
            //System.out.println(inboundDto.getInbound_number());
            insert(inboundDto);
        }
        return false;
    }

}
