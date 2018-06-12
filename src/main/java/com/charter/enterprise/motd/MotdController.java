package com.charter.enterprise.motd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class MotdController {
    private String currentMotd = "Welcome to Charter.  All systems are nominal.";

    @GetMapping
    public String getMotd() {
        return currentMotd;
    }

    @PutMapping
    public ResponseEntity<?> setMotd(@RequestBody String newMotd) {
        ResponseEntity result;

        try {
            currentMotd = newMotd;
            result = new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            result = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        return result;
    }
}
