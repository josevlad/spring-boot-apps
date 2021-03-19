package ar.com.ada.second.springboottests.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    // 2 endpoint /test/one y /test/two => GET => OK => {"status":"ok"}

    @GetMapping({ "/one", "/one/"})
    public ResponseEntity testOne() {
        Map<String, String> body = new HashMap<>();
        body.put("status", "ok");
        return ResponseEntity.ok(body);
    }

    @GetMapping({ "/two", "/two/"})
    public ResponseEntity testTwo() {
        return ResponseEntity.ok(null);
    }
}
