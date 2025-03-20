package domain.Inbound.service;

import dto.InboundDto;

import java.util.List;

public interface InboundCheckService {

    /**
     * 관리자 입고요청 전체확인
     * @return
     */
    List<InboundDto> allCheckRead();

    /**
     * 관리자 입고요청 전체승인
     */
    void allCheckUpdate();

    /**
     * 관리자 입고요청 전체반려
     */
    void allCheckReturn();

    /**
     * 관리자 입고요청 업체별 확인
     * @param ci
     * @return
     */
    List<InboundDto> clientCheckRead(String ci);

    /**
     * 관리자 입고요청 업체별 승인
     * @param ci
     */
   void clientCheckUpdate(String ci);

    /**
     * 관리자 입고요청 업체별 반려
     * @param ci
     */
    void clientCheckReturn(String ci);


    /**
     * 관리자 입고요청 개별 승인
     * @param ci
     */
   void numCheckUpdate(String ci);

    /**
     * 관리자 입고요청 개별 반려
     * @param ci
     */
    void numCheckReturn(String ci);





}
