package com.wissen.controller;

import com.wissen.dto.ClientDTO;
import com.wissen.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/client")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> saveClient(@RequestBody @NotEmpty(message = "Input client list cannot be empty.") final List<@Valid ClientDTO> clients) {
        log.info("START: Saving clients : {}", clients);
        this.clientService.saveClients(clients);
        log.info("END: Saving clients");
        return ResponseEntity.status(HttpStatus.OK)
                .body("Saved clients successfully");
    }

}
