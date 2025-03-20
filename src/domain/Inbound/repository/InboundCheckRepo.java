package domain.Inbound.repository;

import dto.InboundDto;

import java.util.List;

public interface InboundCheckRepo {

    /**
     * 관리자 입고요청 전체확인
     * @return
     */
    List<InboundDto> allCheckRead();

    /**
     * 관리자 입고요청 전체확인
     */
    void allCheckUpdate();

    /**
     * 관리자 입고요청 전체반려
     */
    void allCheckReturn();

    /**
     * 관리자 입고요청 업체별확인
     * @param ci
     * @return
     */
    List<InboundDto> clientCheckRead(String ci);

    /**
     * 관리자 입고요청 업체별승인
     * @param ci
     */
    void clientCheckUpdate(String ci);

    /**
     * 관리자 입고확인 업체별반려
     * @param ci
     */
    void clientCheckReturn(String ci);

    /**
     * 관리자 입고확인 개별승인
     * @param ci
     */

    void inbound_number_check_update(String ci);

    /**
     * 관리자 입고확인 개별반려
     * @param ci
     */

    void inbound_number_check_return(String ci);
}
