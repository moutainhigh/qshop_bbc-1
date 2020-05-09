package co.lq.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * @author billy
 * @date 2019-5-22
 */
@Data
public class LogErrorDTO implements Serializable {

    private Long      id;

    private String    username;

    private String    description;

    private String    method;

    private String    params;

    private String    browser;

    private String    requestIp;

    private String    address;

    private Timestamp createTime;
}
