package com.peachbros.letsmerge.mission.acceptance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.core.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionUpdateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MissionAcceptanceTest {
    public static final String MISSION_NAME = "체스 미션";
    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2020, 5, 5, 0, 0, 0);
    public static final LocalDateTime DUE_DATE_TIME = LocalDateTime.of(2020, 5, 12, 0, 0, 0);
    public static final LocalDateTime NEW_DUE_DATE_TIME = LocalDateTime.of(2020, 5, 19, 0, 0, 0);

    private MockMvc mvc;

    @LocalServerPort
    private int port;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUpEach() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    void afterEach() {
        missionRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void missionsTest() throws Exception {
        MissionCreateRequest missionCreateRequest1 = new MissionCreateRequest(MISSION_NAME, "2010-11-25 12:30:00", "2010-11-26 12:30:00");
        MissionCreateRequest missionCreateRequest2 = new MissionCreateRequest(MISSION_NAME, "2010-11-25 12:30:00", "2010-11-26 12:30:00");

//        MissionRequest missionCreateRequest1 = new MissionRequest("sadads", "21321", "@!32");
//        MissionRequest missionCreateRequest2 = new MissionRequest("sadads", "21321", "@!32");

        //미션 추가
        addMission(missionCreateRequest1);
        addMission(missionCreateRequest2);

        //미션 조회
        MissionsResponse missionsResponse = showMissions();
        assertThat(missionsResponse.getMissions()).hasSize(2);

        //미션 수정 (미션의 기한을 늘린다.)
        MissionUpdateRequest missionUpdateRequest = new MissionUpdateRequest(null, null, NEW_DUE_DATE_TIME);
        MissionResponse firstMission = missionsResponse.getMissions().get(0);

        updateMission(firstMission.getId(), missionUpdateRequest);
        MissionsResponse updatedMissionsResponse = showMissions();
        MissionResponse missionResponse = findMissionById(firstMission.getId(), updatedMissionsResponse);

        assertThat(missionResponse.getName()).isNotNull();
        assertThat(missionResponse.getStartDateTime()).isNotNull();
        assertThat(missionResponse.getDueDateTime()).isEqualTo(NEW_DUE_DATE_TIME);

        //미션 삭제
        deleteMission(missionsResponse.getMissions().get(0).getId());
        assertThat(showMissions().getMissions()).hasSize(1);
    }

    private void addMission(MissionCreateRequest missionCreateRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(missionCreateRequest);

        mvc.perform(post("/admin/missions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(s))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    private MissionsResponse showMissions() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/admin/missions")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        StandardResponse<MissionsResponse> response = objectMapper.readValue(contentAsString,
                new TypeReference<StandardResponse<MissionsResponse>>() {
                });
        MissionsResponse data = response.getData();
//        StandardResponse<MissionsResponse> response =
//                objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
//                        objectMapper.getTypeFactory().constructType(StandardResponse.class, MissionResponse.class));
//        StandardResponse<MissionsResponse> response = given()
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .when()
//                .get("/admin/missions")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .log().all()
//                .extract().as(new TypeRef<StandardResponse<MissionsResponse>>() {
////                });
        return data;
    }

    private void updateMission(Long missionId, MissionUpdateRequest missionUpdateRequest) {
//        given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(missionUpdateRequest)
//                .when()
//                .patch("/admin/missions/" + missionId)
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .log().all();
    }

    private void deleteMission(Long missionId) {
//        given()
//                .when()
//                .delete("/admin/missions/" + missionId)
//                .then()
//                .statusCode(HttpStatus.NO_CONTENT.value())
//                .log().all();
    }

    private MissionResponse findMissionById(Long missionId, MissionsResponse missionsResponse) {
        return missionsResponse.getMissions().stream()
                .filter(val -> Objects.equals(val.getId(), missionId))
                .findFirst()
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 미션입니다."));
    }
}
