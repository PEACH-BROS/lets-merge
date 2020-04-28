package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.mission.dto.MissionSaveRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MissionController {


    @PostMapping
    public void get(@RequestBody @Valid MissionSaveRequest request) {
    }
}
