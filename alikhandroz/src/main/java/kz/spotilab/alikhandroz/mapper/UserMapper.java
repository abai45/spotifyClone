package kz.spotilab.alikhandroz.mapper;

import kz.spotilab.alikhandroz.dto.ReadUserDto;
import kz.spotilab.alikhandroz.domain.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ReadUserDto readUserDtoToUser(UserEntity userEntity);
}
