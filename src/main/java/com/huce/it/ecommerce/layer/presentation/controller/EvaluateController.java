package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.EvaluateDto;
import com.huce.it.ecommerce.layer.application.domain.service.IEvaluateService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huce/evaluate")
public class EvaluateController {
    private final IEvaluateService iEvaluateService;

    public EvaluateController(IEvaluateService iEvaluateService) {
        this.iEvaluateService = iEvaluateService;
    }

    @PostMapping("create")
    public Response createEvaluate(@RequestBody EvaluateDto evaluateDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iEvaluateService.create(evaluateDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @DeleteMapping("delete")
    public Response delete(@RequestParam(name = "id")Integer id) {
        try {
            iEvaluateService.delete(id);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("get-page")
    public Response getPageEvaluate(@RequestParam(name = "page", required = false, defaultValue ="0")Integer page,
                                    @RequestParam(name = "limit",required = false,defaultValue = "10")Integer limit,
                                    @RequestParam(name = "prod_id")Integer prodId) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iEvaluateService.getPage(page, limit, prodId));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

}
