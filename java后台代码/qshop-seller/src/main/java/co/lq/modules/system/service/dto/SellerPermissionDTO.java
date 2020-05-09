package co.lq.modules.system.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

/**
 * @author billy
 * @date 2018-12-03
 */
@Data
public class SellerPermissionDTO implements Serializable {

    private Long                      id;

    private String                    name;

    private Long                      pid;

    private String                    alias;

    private Timestamp                 createTime;

    private List<SellerPermissionDTO> children;

    @Override
    public String toString() {
        return "Permission{" + "id=" + id + ", name='" + name + '\'' + ", pid=" + pid + ", alias='" + alias + '\''
                + ", createTime=" + createTime + '}';
    }
}
