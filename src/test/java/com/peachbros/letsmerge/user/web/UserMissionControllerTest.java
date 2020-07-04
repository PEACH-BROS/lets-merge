package com.peachbros.letsmerge.user.web;

import com.peachbros.letsmerge.config.auth.SecurityConfig;
import com.peachbros.letsmerge.user.service.UserMissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserMissionController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
class UserMissionControllerTest {
    @MockBean
    private UserMissionService userMissionService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @DisplayName("사용자의 미션 신청")
    @Test
    void assignMission() throws Exception {
        doNothing().when(userMissionService).assignMission(anyLong(), anyLong());

        this.mockMvc.perform(post("/api/v1/users/1/missions/1"))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(userMissionService).assignMission(anyLong(), anyLong());
    }

    @DisplayName("사용자의 미션 취소")
    @Test
    void cancelMission() throws Exception {
        doNothing().when(userMissionService).cancelMission(anyLong(), anyLong());

        this.mockMvc.perform(delete("/api/v1/users/1/missions/1"))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(userMissionService).cancelMission(anyLong(), anyLong());
    }

    @Test
    void getAssignedMissions() {
    }

    @Test
    void getAssignableMissions() {
    }
}