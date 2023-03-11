package com.example.contractapi.Controller;

import com.example.contractapi.Service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/test")
    public void test(){
        contractService.test();
    }

    @GetMapping("/enver")
    public void getEnver(){
        contractService.getEnvers();
    }

}
