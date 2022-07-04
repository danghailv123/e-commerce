package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.AccountDto;
import com.huce.it.ecommerce.layer.application.domain.service.IAccountService;
import com.huce.it.ecommerce.unitity.exception.ClientException;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huce/account")
public class AccountController {
    private final IAccountService iAccountService;

    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @GetMapping("/get-list")
    public Response getListAccount(@RequestParam(name = "limit", defaultValue = "255", required = false) Integer limit,
                                   @RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iAccountService.getAccounts(limit, page));

        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }


    @PostMapping("/user/create")
    public Response createAccount( @RequestBody AccountDto accountDto) {
        try {
            iAccountService.createAccount(accountDto);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }


    /*
    body{
        "email":"admin@gmail.com",
        "password":"admin"
    }
     */

    @PostMapping("/user/change-password")
    public Response changePassword( @RequestBody AccountDto accountDto) {
        try {
            iAccountService.changePasswordAccount(accountDto);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }


    /*
    body{
        "user":1232132,
        "status":1
    }
    */
    @PostMapping("/change-status")
    public Response changeStatus( @RequestBody AccountDto accountDto) {
        try {
            iAccountService.activeAccount(accountDto);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/find-account")
    public Response getAccount(@RequestParam(name = "email", required = true) String email) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iAccountService.getAccountByEmail(email));

        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/user/get")
    public Response getAccountById(@RequestParam(name = "id", required = true) Integer id) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iAccountService.getAccountById(id));

        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("/user/update")
    public void updateAccount( @RequestBody AccountDto accountDto) {
        try {
            iAccountService.updateAccount(accountDto);

        } catch (Exception exception) {
        }
    }

    @GetMapping("/get-es")
    public Response getListAccountEs(@RequestParam(name = "limit", defaultValue = "255", required = false) Integer limit,
                                   @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                     @RequestParam(name = "keyword",required = false)String keyword) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iAccountService.getPage(limit, page , keyword));

        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }
}
