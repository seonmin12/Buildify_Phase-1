package domain.Inbound.controller;

import common.ValidCheck;
import domain.Inbound.service.InboundInsertService;
import dto.InboundDto;

import java.util.Date;
import java.util.Scanner;

public class InboundInsertControllerImp implements InboundInsertController{
    Scanner sc = new Scanner(System.in);

    private final InboundInsertService insertService;
    private final ValidCheck validCheck = new ValidCheck();

    public InboundInsertControllerImp(InboundInsertService insertService) {
        this.insertService = insertService;
    }

    @Override
    public InboundDto insert() {
        InboundDto inboundDto = new InboundDto();
        System.out.println("입고번호");
        inboundDto.setInbound_number(sc.nextLine());
        System.out.println("상품아이디");
        inboundDto.setProd_id(sc.nextLine());
        System.out.println("회원아이디");
        inboundDto.setClient_id(sc.nextLine());
        System.out.println("수량");
        inboundDto.setQuantity(sc.nextInt());
        sc.nextLine();
        inboundDto.setInbound_status(0);
        inboundDto.setReq_inbound_day(new Date());
        System.out.println("창고아이디");
        inboundDto.setWare_id(sc.nextLine());
        return insertService.insert(inboundDto);
    }

    @Override
    public void insertrun() {
        InboundDto inboundDto = insert();

        System.out.println("인바운드 인설트 성공");

    }

    private int inputNum(){
        String str;
        int input = 0;
        while (true){
            str = sc.nextLine();
            if (str.matches(validCheck.NUMBER_REGEX)){
                input = Integer.parseInt(str);
                break;
            }
            System.out.println("숫자입력해라");
        }
        return input;
    }

    private String inputString(){
        String name;

            System.out.printf("인바운드넘버입력");
            name = sc.nextLine().trim();
        return name;
    }
}
