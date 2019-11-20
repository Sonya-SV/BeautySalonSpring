package com.training.salon.controller;

import com.training.salon.entity.Master;
import com.training.salon.entity.User;
import com.training.salon.service.CommentService;
import com.training.salon.service.MasterService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CommentController {
    private final CommentService commentService;
    private  final MasterService masterService;

    public CommentController(CommentService commentService, MasterService masterService) {
        this.commentService = commentService;
        this.masterService = masterService;
    }

    @GetMapping("/user/comment")
    public String getMasters(Model model,
                             @RequestParam Long masterId,
                             @RequestParam String comment,
                             @AuthenticationPrincipal User user) {

        Optional<Master> master =masterService.findById(masterId);
        if(master.isPresent()) {
            commentService.createComment(comment, master.get(), user);
        }else
            model.addAttribute("error","Cant save comment");
        return "redirect:/user/master/"+masterId;
    }
}
