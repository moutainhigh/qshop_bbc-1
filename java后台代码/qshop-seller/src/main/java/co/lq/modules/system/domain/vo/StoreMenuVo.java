package co.lq.modules.system.domain.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 构建前端路由时用到
 *
 * @author billy
 * @date 2018-12-20
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StoreMenuVo implements Serializable {

    private String            name;

    private String            path;

    private Boolean           hidden;

    private String            redirect;

    private String            component;

    private Boolean           alwaysShow;

    private StoreMenuMetaVo   meta;

    private List<StoreMenuVo> children;
}
