package co.lq.modules.shop.service.mapper;

import co.lq.base.BaseMapper;
import co.lq.modules.shop.domain.HomeAdvertise;
import co.lq.modules.shop.service.dto.HomeAdvertiseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author billy
* @date 2020-03-13
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeAdvertiseMapper extends BaseMapper<HomeAdvertiseDTO, HomeAdvertise> {

}