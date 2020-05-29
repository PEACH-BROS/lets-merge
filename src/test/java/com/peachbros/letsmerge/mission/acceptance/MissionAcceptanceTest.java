package com.peachbros.letsmerge.mission.acceptance;

import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MissionAcceptanceTest {
    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2020, 5, 5, 0, 0, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2020, 5, 12, 0, 0, 0);
    public static final String MISSION_NAME = "체스 미션";
    @LocalServerPort
    int port;

    public static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void missionsTest() {
        MissionCreateRequest missionCreateRequest1 = new MissionCreateRequest(MISSION_NAME, START_DATE_TIME, END_DATE_TIME);
        MissionCreateRequest missionCreateRequest2 = new MissionCreateRequest(MISSION_NAME, START_DATE_TIME, END_DATE_TIME);

        addMission(missionCreateRequest1);
        addMission(missionCreateRequest2);

        MissionsResponse missionsResponse = showMissions();

        assertThat(missionsResponse.getMissions()).hasSize(2);


    }

    private void addMission(MissionCreateRequest missionCreateRequest) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(missionCreateRequest)
                .when()
                .post("/admin/missions")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .log().all();
    }

    private MissionsResponse showMissions() {
        StandardResponse<MissionsResponse> response = given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/admin/missions")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().all()
                .extract().as(new TypeRef<StandardResponse<MissionsResponse>>() {
                });
        return response.getData();
    }
}
