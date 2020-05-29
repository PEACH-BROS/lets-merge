package com.peachbros.letsmerge.mission.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peachbros.letsmerge.mission.service.MissionService;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .build();
    }

    @Test
    public void addMission() throws Exception {
        MissionCreateRequest request = new MissionCreateRequest("미션 제목", LocalDateTime.of(2020, 5, 5, 0, 0, 0),
                LocalDateTime.of(2020, 5, 10, 0, 0, 0));

        String data = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/admin/missions")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }


}