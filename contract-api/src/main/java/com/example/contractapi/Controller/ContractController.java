package com.example.contractapi.Controller;

import com.example.contractapi.Service.ContractService;
import com.example.contractcore.Domain.File;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/test")
    public void test(){
        contractService.test();
    }

    @GetMapping("/enver")
    public List<File> getEnver(){
        contractService.getEnvers3();
       return null;
    }

}
