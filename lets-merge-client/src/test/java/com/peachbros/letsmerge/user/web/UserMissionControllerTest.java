package com.peachbros.letsmerge.user.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.peachbros.letsmerge.common.dto.StandardResponse;
import com.peachbros.letsmerge.config.auth.SecurityConfig;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.service.dto.MissionsResponse;
import com.peachbros.letsmerge.user.service.UserMissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserMissionController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
class UserMissionControllerTest {
    @MockBean
    private UserMissionService userMissionService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
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
    void getAssignedMissions() throws Exception {
        when(userMissionService.getAssignedMissions(anyLong())).thenReturn(dummyMissionsResponse());
        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/users/1/missions/assigned")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        StandardResponse<MissionsResponse> missionsResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<StandardResponse<MissionsResponse>>() {
        });

        assertThat(missionsResponse.getData().getMissions()).hasSize(2);
        verify(userMissionService).getAssignedMissions(anyLong());
    }

    @Test
    void getAssignableMissions() throws Exception {
        when(userMissionService.getAssignableMissions(anyLong())).thenReturn(dummyMissionsResponse());
        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/users/1/missions/assignable")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        StandardResponse<MissionsResponse> missionsResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<StandardResponse<MissionsResponse>>() {
        });

        assertThat(missionsResponse.getData().getMissions()).hasSize(2);
        verify(userMissionService).getAssignableMissions(anyLong());
    }

    private MissionsResponse dummyMissionsResponse() {
        List<Mission> missions = Arrays.asList(dummyMission(), dummyMission());
        return MissionsResponse.of(missions);
    }

    private Mission dummyMission() {
        return new Mission("미션", LocalDateTime.of(2020, 5, 1, 21, 0), LocalDateTime.of(2020, 5, 8, 21, 0));
    }
}