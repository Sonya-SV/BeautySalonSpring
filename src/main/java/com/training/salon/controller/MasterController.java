package com.training.salon.controller;

import com.training.salon.entity.Master;
import com.training.salon.entity.User;
import com.training.salon.service.CommentService;
import com.training.salon.service.MasterService;
import com.training.salon.service.ProcedureService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class MasterController {

    private final MasterService masterService;
    private final ProcedureService procedureService;
    private final CommentService commentService;

    public MasterController(MasterService masterService, ProcedureService procedureService, CommentService commentService) {
        this.masterService = masterService;
        this.procedureService = procedureService;
        this.commentService = commentService;
    }

    @GetMapping("/masterlist")
    public String getMasters(Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 8) Pageable pageable) {

        model.addAttribute("page", masterService.findAll(pageable));
        model.addAttribute("url", "/user/masterlist");
        return "/user/masterlist";
    }

    @GetMapping("/master/{master}")
    public String getMaster(@PathVariable Master master,
                            @AuthenticationPrincipal User user,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "success", required = false) String success,
                            Model model) {

        model.addAttribute("successSend", success);
        model.addAttribute("error", error);

        Optional<Master> mstr = masterService.findById(master.getId());
        mstr.ifPresent(value -> model.addAttribute("master", value));
        model.addAttribute("procedures", procedureService.findAllProceduresByMasterId(master.getId()));
        if (user.isAdmin()) model.addAttribute("comments", commentService.findAllCommentsByMasterId(master.getId()));
        return "/user/master";
    }

}
