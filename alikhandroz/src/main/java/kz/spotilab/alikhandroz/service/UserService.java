package kz.spotilab.alikhandroz.service;

import kz.spotilab.alikhandroz.dto.ReadUserDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {
    void syncWithIdp(OAuth2User oAuth2User);
    ReadUserDto getAuthenticatedUserFromSecurityContext();
}
