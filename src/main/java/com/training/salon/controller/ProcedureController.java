package com.training.salon.controller;

import com.training.salon.entity.Category;
import com.training.salon.service.CategoryService;
import com.training.salon.service.MasterService;
import com.training.salon.service.ProcedureService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProcedureController {

    private CategoryService categoryService;
    private ProcedureService procedureService;
    private MasterService masterService;

    public ProcedureController(CategoryService categoryService, ProcedureService procedureService, MasterService masterService) {
        this.categoryService = categoryService;
        this.procedureService = procedureService;
        this.masterService = masterService;
    }


    @GetMapping("/user/categorylist")
    public String getMasters(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "/user/categorylist";
    }

    @GetMapping("/user/procedures/{category}")
    public String getProcedures(Model model,
                                @PathVariable Category category,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 8) Pageable pageable) {
        model.addAttribute("url", "/user/procedures/{category}");
        model.addAttribute("page", masterService.getMastersByCategory(category.getId(), pageable));
        model.addAttribute("procedures", procedureService.findAllProceduresByCategory(category.getId()));
        return "/user/procedures";
    }
}
