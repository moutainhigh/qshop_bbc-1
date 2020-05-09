package co.lq.modules.shop.service.dto;

import co.lq.annotation.Query;
import lombok.Data;

/**
 * @author billy
 * @date 2020-03-27
 */
@Data
public class CmsSubjectCommentQueryCriteria {

    @Query
    private Long    id;

    @Query
    private Integer deleted;
}
