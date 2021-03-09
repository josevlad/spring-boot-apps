package ar.com.ada.online.second.yourpiratemovies.controller.demo.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/admin", "/admin/"})
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({ "ping", "ping/"})
    public ResponseEntity testAdminRole() {
        Map<String, String> body = new HashMap<>();
        body.put("admin", "pong");
        return ResponseEntity.ok(body);
    }
}
