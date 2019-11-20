package com.training.salon.controller;

import com.training.salon.entity.Category;
import com.training.salon.service.CategoryService;
import com.training.salon.service.MasterService;
import com.training.salon.service.ProcedureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
                                @PathVariable Category category) {
        model.addAttribute("masters", masterService.getMastersByCategory(category.getId()));
        model.addAttribute("procedures", procedureService.findAllProceduresByCategory(category.getId()));
        return "/user/procedures";
    }
}
