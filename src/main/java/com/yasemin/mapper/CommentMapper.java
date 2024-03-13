package com.yasemin.mapper;

import com.yasemin.dto.response.CommentResponseDto;
import com.yasemin.entity.Comments;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentResponseDto fromComment(final Comments comment);
}
