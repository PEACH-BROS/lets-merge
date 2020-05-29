package com.peachbros.letsmerge.mission.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MissionControllerTest {
    @MockBean
    private MissionRepository missionRepository;

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
    public void addMission() throws Exception {
        MissionCreateRequest request = new MissionCreateRequest(MISSION_NAME, START_DATE_TIME, END_DATE_TIME);
        Mission mission = request.toMission();
        String data = objectMapper.writeValueAsString(request);

        when(missionRepository.save(any())).thenReturn(mission);

        this.mockMvc.perform(post("/admin/missions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(data))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void showMissions() throws Exception {
        List<Mission> missions = Arrays.asList(mockMission(), mockMission(), mockMission());

        when(missionRepository.findAll()).thenReturn(missions);

        MvcResult mvcResult = this.mockMvc.perform(get("/admin/missions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        StandardResponse<MissionsResponse> missionsResponse = objectMapper.
                readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<StandardResponse<MissionsResponse>>() {
                });
        assertThat(missionsResponse.getData().getMissions()).hasSize(missions.size());
    }

    private Mission mockMission() {
        return new Mission(MISSION_NAME, START_DATE_TIME, END_DATE_TIME);
    }
}