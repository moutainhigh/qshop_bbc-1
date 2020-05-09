package co.lq.modules.shop.service.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author billy
 * @date 2020-03-11
 */
@Data
public class BargainVertifyRecordDTO implements Serializable {

    private Long    id;

    private Integer bargainId;

    /** 审核人 */
    private String  vertifyMan;

    private Integer status;

    /** 反馈详情 */
    private String  detail;

    /** 所属店铺 */
    private Long    storeId;
}
