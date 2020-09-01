package com.peachbros.letsmerge.mission.model.web;

import com.peachbros.letsmerge.mission.model.service.AdminMissionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    private final AdminMissionService adminMissionService;

    public AdminPageController(AdminMissionService adminMissionService) {
        this.adminMissionService = adminMissionService;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String adminIndex(Model model) {
        model.addAttribute(adminMissionService.showMissions());
        return "admin/index";
    }
}
