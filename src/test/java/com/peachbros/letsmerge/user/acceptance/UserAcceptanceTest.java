package com.peachbros.letsmerge.user.acceptance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import com.peachbros.letsmerge.user.service.dto.UserResponse;
import com.peachbros.letsmerge.user.service.dto.UserUpdateRequest;
import com.peachbros.letsmerge.user.service.dto.UsersResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAcceptanceTest {
    private static final String TEST_USER_NAME_1 = "TEST_USER_NAME";
    private static final String TEST_USER_EMAIL_1 = "TEST_USER@gmail.com";
    private static final String NO_PICURE = "NO_PICURE";
    private static final String TEST_USER_NAME_2 = "TEST_USER_NAME_2";
    private static final String TEST_USER_EMAIL_2 = "TEST_USER2@gmail.com";
    private static final String UPDATED_TEST_USER_NAME = "UPDATED_TEST_USERNAME";
    private static final String UPDATED_TEST_USER_EMAIL = "UPDATED_TEST_USER_EMAIL";
    private static final String ANOTHER_PICTURE = "ANOTHER_PICTURE";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    private void deleteAll() {
        userRepository.deleteAll();
    }

    @DisplayName("User 인수테스트")
    @Test
    @WithMockUser(roles = "ADMIN")
    void usersTest() throws Exception {
        /*
        우린 Github 로그인을 쓰고있는데 user를 Create할 이유가 있을까 ?
        지금 로직 : GithubLogin 성공시 => 자동으로 UserReposiory에 해당하는 유저가 생성, 수정 된다.
        */
        userRepository.save(new User(TEST_USER_NAME_1, TEST_USER_EMAIL_1, NO_PICURE, Role.USER));
        userRepository.save(new User(TEST_USER_NAME_2, TEST_USER_EMAIL_2, NO_PICURE, Role.USER));

        // 사용자 조회
        UsersResponse usersResponse = showUsers();
        assertThat(usersResponse.getUsersResponse()).hasSize(2);

        UserResponse userResponse = usersResponse.getUsersResponse().get(0);
        // 사용자 정보 Update
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(
                UPDATED_TEST_USER_EMAIL,
                ANOTHER_PICTURE);

        updateUser(userResponse.getId(), userUpdateRequest);
        usersResponse = showUsers();
        UserResponse updatedUserResponse = usersResponse.getUsersResponse().get(0);
        assertAll(
                () -> assertEquals(updatedUserResponse.getEmail(), UPDATED_TEST_USER_EMAIL),
                () -> assertEquals(updatedUserResponse.getPicture(), ANOTHER_PICTURE)
        );

        // 사용자 삭제
        deleteUser(userResponse.getId());
        usersResponse = showUsers();
        assertThat(usersResponse.getUsersResponse()).hasSize(1);
    }

    private UsersResponse showUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/admin/api/v1/users")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        StandardResponse<UsersResponse> usersResponseStandardResponse
                = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<StandardResponse<UsersResponse>>() {
        });

        return usersResponseStandardResponse.getData();
    }

    private void updateUser(Long userId, UserUpdateRequest userUpdateRequest) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/admin/api/v1/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsString(userUpdateRequest)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    private void deleteUser(Long userId) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/api/v1/users/" + userId))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
