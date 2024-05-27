package kz.spotilab.alikhandroz.mapper;

import javax.annotation.processing.Generated;
import kz.spotilab.alikhandroz.domain.UserEntity;
import kz.spotilab.alikhandroz.dto.ReadUserDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-27T19:55:50+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ReadUserDto readUserDtoToUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String email = null;
        String imageUrl = null;

        firstName = userEntity.getFirstName();
        lastName = userEntity.getLastName();
        email = userEntity.getEmail();
        imageUrl = userEntity.getImageUrl();

        ReadUserDto readUserDto = new ReadUserDto( firstName, lastName, email, imageUrl );

        return readUserDto;
    }
}
