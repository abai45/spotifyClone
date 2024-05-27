package kz.spotilab.alikhandroz.resources;

import jakarta.servlet.http.HttpServletRequest;
import kz.spotilab.alikhandroz.dto.ReadUserDto;
import kz.spotilab.alikhandroz.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api")
public class AuthResource {

    private final UserService userService;
    private final ClientRegistration registration;

    public AuthResource(UserService userService, ClientRegistrationRepository registration) {
        this.userService = userService;
        this.registration = registration.findByRegistrationId("okta");
    }

    @GetMapping("/get-authenticated-user")
    public ResponseEntity<ReadUserDto> getAuthenticationUser(@AuthenticationPrincipal OAuth2User user) {
        if(user==null) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        } else {
            userService.syncWithIdp(user);
            ReadUserDto userFromAuthentication = userService.getAuthenticatedUserFromSecurityContext();
            return ResponseEntity.ok().body(userFromAuthentication);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        var issuerUri = registration.getProviderDetails().getIssuerUri();
        var originUrl = request.getHeader(HttpHeaders.ORIGIN);
        Object[] params = {issuerUri, registration.getClientId(), originUrl};
        var logoutUrl = MessageFormat.format("{0}v2/logout?clientId={1}&returnTo={2}", params);
        request.getSession().invalidate();
        return ResponseEntity.ok().body(Map.of("logoutUrl", logoutUrl));
    }
}
