package org.inventory.app.controller;

import org.inventory.app.entity.User;
import org.inventory.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try{
            User cratedUser = userService.createUser(user);

            return ResponseEntity.status(HttpStatus.OK).body(cratedUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(){
        try{
            List<User> users=userService.getAllUsers();

            if(users.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(users);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
