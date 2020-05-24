package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.service.MissionService;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MissionControllerTest {
    @MockBean
    private MissionService missionService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @DisplayName("admin 페이지 접근 시 초기화면")
    @Test
    void showMissionList() throws Exception {
        List<MissionResponse> responses = Arrays.asList(mockMissionResponse(), mockMissionResponse());
        when(missionService.showMissions()).thenReturn(responses);

        this.mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private MissionResponse mockMissionResponse() {
        return MissionResponse.of(new Mission("미션제목", LocalDateTime.of(2020, 5, 5, 0, 0, 0),
                LocalDateTime.of(2020, 5, 10, 0, 0, 0)));
    }
}