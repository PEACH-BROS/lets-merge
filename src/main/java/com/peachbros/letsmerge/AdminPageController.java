package com.peachbros.letsmerge;

import com.peachbros.letsmerge.mission.service.AdminMissionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    private AdminMissionService adminMissionService;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String adminIndex(Model model) {
        model.addAttribute(adminMissionService.showMissions());
        return "admin/index";
    }
}
