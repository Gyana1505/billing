package billing.backend.controller;

import billing.backend.io.UserReq;
import billing.backend.io.UserRes;
import billing.backend.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {
    private final UserServiceImpl userServiceimpl;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRes registerUser(@RequestBody UserReq userReq){
        try {
            return userServiceimpl.createUser(userReq);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to create");
        }
    }
    @GetMapping("/user")
    public List<UserRes> readUsers(){
        return userServiceimpl.readUser();
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id){
        try{
            userServiceimpl.deleteUser(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found");
        }
    }

}
