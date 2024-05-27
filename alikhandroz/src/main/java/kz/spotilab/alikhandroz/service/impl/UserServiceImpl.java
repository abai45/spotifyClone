package kz.spotilab.alikhandroz.service.impl;

import kz.spotilab.alikhandroz.domain.UserEntity;
import kz.spotilab.alikhandroz.dto.ReadUserDto;
import kz.spotilab.alikhandroz.mapper.UserMapper;
import kz.spotilab.alikhandroz.repository.UserRepository;
import kz.spotilab.alikhandroz.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void syncWithIdp(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        UserEntity user = mapOauth2AttributesToUser(attributes);
        Optional<UserEntity> existingUser = userRepository.findOneByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (attributes.get("updated_at") != null) {
                Instant dbLastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
                Instant idpModifiedDate;
                if(attributes.get("updated_at") instanceof Instant) {
                    idpModifiedDate = (Instant) attributes.get("updated_at");
                } else {
                    idpModifiedDate = Instant.ofEpochSecond((Integer) attributes.get("updated_at"));
                }
                if(idpModifiedDate.isAfter(dbLastModifiedDate)) {
                    updateUser(user);
                }
            }
        } else {
            userRepository.saveAndFlush(user);
        }
    }


    @Override
    public ReadUserDto getAuthenticatedUserFromSecurityContext() {
        OAuth2User principal = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = mapOauth2AttributesToUser(principal.getAttributes());
        return userMapper.readUserDtoToUser(user);
    }


    private void updateUser(UserEntity user) {
        Optional<UserEntity> userToUpdateOpt = userRepository.findOneByEmail(user.getEmail());
        if(userToUpdateOpt.isPresent()) {
            var userToUpdate = userToUpdateOpt.get();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setImageUrl(user.getImageUrl());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setFirstName(user.getFirstName());
            userRepository.saveAndFlush(userToUpdate);
        }
    }

    private UserEntity mapOauth2AttributesToUser(Map<String, Object> attributes) {
        var user = new UserEntity();
        String sub = String.valueOf(attributes.get("sub"));
        String username = null;
        if(attributes.get("preferred_username") != null) {
            username = ((String) attributes.get("preferred_username")).toLowerCase();
        }
        if(attributes.get("given_name") != null) {
            user.setFirstName((String) attributes.get("given_name"));
        } else if (attributes.get("name") != null) {
            user.setFirstName((String) attributes.get("name"));
        }
        if(attributes.get("family_name") != null) {
            user.setLastName((String) attributes.get("family_name"));
        }
        if(attributes.get("email") != null) {
            user.setEmail((String) attributes.get("email"));
        } else if(sub.contains("|") && (username != null && username.contains("@"))) {
            user.setEmail(username);
        } else {
            user.setEmail(sub);
        }
        if(attributes.get("picture") != null) {
            user.setImageUrl((String) attributes.get("picture"));
        }
        return user;
    }
}
