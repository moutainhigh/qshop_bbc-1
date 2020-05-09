package co.lq.mp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

/**
 * @author billy
 * @date 2019-12-10
 */
@Entity
@Data
@Table(name = "wechat_template")
public class WechatTemplate implements Serializable {

    // 模板id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 模板编号
    @Column(name = "tempkey", nullable = false)
    private String  tempkey;

    // 模板名
    @Column(name = "name", nullable = false)
    private String  name;

    // 回复内容
    @Column(name = "content", nullable = false)
    private String  content;

    // 模板ID
    @Column(name = "tempid")
    private String  tempid;

    // 添加时间
    @Column(name = "add_time", nullable = false)
    private String  addTime;

    // 状态
    @Column(name = "status", nullable = false)
    private Integer status;

    public void copy(WechatTemplate source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
