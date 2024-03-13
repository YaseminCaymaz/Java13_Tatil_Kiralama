package com.yasemin.mapper;

import com.yasemin.dto.request.HotelRequestDto;
import com.yasemin.entity.Hotel;
import com.yasemin.entity.HotelProperties;
import com.yasemin.entity.HotelType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    Hotel fromHotelRequestDto(final HotelRequestDto dto);
    HotelType fromTypeIdAnHotelId(final String typeId, final String hotelId);
    HotelProperties fromPropertiesIdAnHotelId(final String propertiesId, final String hotelId);
}
