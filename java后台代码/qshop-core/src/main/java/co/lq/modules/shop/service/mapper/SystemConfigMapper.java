package co.lq.modules.shop.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import co.lq.mapper.EntityMapper;
import co.lq.modules.shop.domain.SystemConfig;
import co.lq.modules.shop.service.dto.SystemConfigDTO;

/**
 * @author billy
 * @date 2019-10-10
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemConfigMapper extends EntityMapper<SystemConfigDTO, SystemConfig> {

}
