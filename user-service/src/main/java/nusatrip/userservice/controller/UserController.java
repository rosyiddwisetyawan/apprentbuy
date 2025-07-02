package nusatrip.userservice.controller;

import nusatrip.userservice.model.response.ApiResponse;
import nusatrip.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<ApiResponse> createUser(@RequestParam String name) {
        ApiResponse response = userService.createUser(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable("id") Long userId) {
        ApiResponse response = userService.getUserById(userId);
        if (response.isResult()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse> getUsers(
            @RequestParam(value = "page_num", defaultValue = "1") int pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize) {
        ApiResponse response = userService.getAllUsers(pageNum, pageSize);
        return ResponseEntity.ok(response);
    }
}
