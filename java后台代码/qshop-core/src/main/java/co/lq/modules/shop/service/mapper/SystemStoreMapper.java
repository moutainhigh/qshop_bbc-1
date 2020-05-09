package co.lq.modules.shop.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import co.lq.base.BaseMapper;
import co.lq.modules.shop.domain.SystemStore;
import co.lq.modules.shop.service.dto.SystemStoreDto;

/**
 * @author billy
 * @date 2020-03-03
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemStoreMapper extends BaseMapper<SystemStoreDto, SystemStore> {

}
