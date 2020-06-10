package com.peachbros.letsmerge.mission.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peachbros.letsmerge.core.ErrorCode;
import com.peachbros.letsmerge.core.dto.ErrorResponse;
import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.core.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.service.MissionService;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionUpdateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.List;

import static com.peachbros.letsmerge.mission.acceptance.MissionAcceptanceTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MissionControllerTest {
    @MockBean
    private MissionService missionService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void addMission() throws Exception {
        MissionCreateRequest request = new MissionCreateRequest(MISSION_NAME, START_DATE_TIME, DUE_DATE_TIME);
        Mission mission = request.toMission();
        String missionCreateRequestData = objectMapper.writeValueAsString(request);
        when(missionService.addMission(any())).thenReturn(MissionResponse.of(mission));

        this.mockMvc.perform(post("/admin/missions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(missionCreateRequestData))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo(print());
    }

    @Test
    void showMissions() throws Exception {
        List<Mission> missions = Arrays.asList(mockMission(), mockMission(), mockMission());

        when(missionService.showMissions()).thenReturn(MissionsResponse.of(missions));

        MvcResult mvcResult = this.mockMvc.perform(get("/admin/missions")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        StandardResponse<MissionsResponse> missionsResponse = objectMapper.
                readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<StandardResponse<MissionsResponse>>() {
                });
        assertThat(missionsResponse.getData().getMissions()).hasSize(missions.size());
    }

    @Test
    void updateMission() throws Exception {
        MissionUpdateRequest missionUpdateRequest = new MissionUpdateRequest(1L, "NEW " + MISSION_NAME, START_DATE_TIME, DUE_DATE_TIME);
        String missionUpdateRequestData = objectMapper.writeValueAsString(missionUpdateRequest);

        doNothing().when(missionService).updateMission(missionUpdateRequest);

        this.mockMvc.perform(patch("/admin/missions/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(missionUpdateRequestData))
                .andExpect(status().isOk())
                .andDo(print());

        verify(missionService).updateMission(missionUpdateRequest);

    }

    @Test
    void deleteMission() throws Exception {
        doNothing().when(missionService).deleteMission(any());

        this.mockMvc.perform(delete("/admin/missions/1"))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(missionService).deleteMission(any());
    }

    @DisplayName("미션 삭제 예외상황 : 삭제할 미션이 없는 경우")
    @Test
    void deleteMissionException1() throws Exception {
        doThrow(NoSuchValueException.class).when(missionService).deleteMission(any());

        MvcResult mvcResult = this.mockMvc.perform(delete("/admin/missions/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();

        StandardResponse<ErrorResponse> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<StandardResponse<ErrorResponse>>() {
                });

        verify(missionService).deleteMission(any());
        assertThat(response.getData().getCode()).isEqualTo(ErrorCode.UNEXPECTED_EXCEPTION.getValue());
    }

    private Mission mockMission() {
        return new Mission(MISSION_NAME, START_DATE_TIME, DUE_DATE_TIME);
    }
}