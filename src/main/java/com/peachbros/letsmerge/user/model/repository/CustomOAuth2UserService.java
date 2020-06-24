package com.peachbros.letsmerge.user.model.repository;

import com.peachbros.letsmerge.config.auth.dto.OAuthAttributes;
import com.peachbros.letsmerge.user.model.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        /*todo : 이걸 static하게 변경해도 되는지 궁금, Super 클래스의 loadUser를 구현한 DefaultOAuth2UserService()의 loadUser를 가져다
                사용한 뒤 필요한 커스텀 코드를 덧붙였는데 이렇게 한 이점은 ? */
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String userNameAttributeName = extractAttributeName(userRequest);

        OAuthAttributes attributes = OAuthAttributes.of(userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private String extractAttributeName(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByName(attributes.getName())
                .map(entity -> entity.update(attributes.getEmail(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
