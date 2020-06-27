package com.peachbros.letsmerge.user.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peachbros.letsmerge.config.auth.SecurityConfig;
import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.service.UserService;
import com.peachbros.letsmerge.user.service.dto.UserUpdateRequest;
import com.peachbros.letsmerge.user.service.dto.UsersResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    private void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @DisplayName("User 조회")
    @Test
    void showUsers() throws Exception {
        List<User> users = Arrays.asList(mockUser(), mockUser(), mockUser());

        when(userService.showUsers()).thenReturn(UsersResponse.of(users));

        MvcResult mvcResult = this.mockMvc.perform(get("/admin/api/v1/users")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        StandardResponse<UsersResponse> usersResponse = objectMapper.
                readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<StandardResponse<UsersResponse>>() {
                });
        assertThat(usersResponse.getData().getUsersResponse()).hasSize(users.size());
    }

    @DisplayName("User patch")
    @Test
    void updateUser() throws Exception {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("NEW_EMAIL", "NEW_PICTURE");

        doNothing().when(userService).updateUser(anyLong(), any());

        int userId = 1;
        this.mockMvc.perform(patch("/admin/api/v1/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(userService).updateUser(anyLong(), any());
    }

    @DisplayName("User 삭제")
    @Test
    void deleteUser() throws Exception {
        doNothing().when(userService).deleteUser(any());

        int userId = 1;
        this.mockMvc.perform(delete("/admin/api/v1/users/" + userId))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(userService).deleteUser(any());
    }

    private User mockUser() {
        return new User("name", "email@gmail.com", User.NO_PICTURE, Role.USER);
    }
}