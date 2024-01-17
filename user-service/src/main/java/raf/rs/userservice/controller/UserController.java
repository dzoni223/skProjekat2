package raf.rs.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.rs.userservice.dto.*;
import raf.rs.userservice.security.CheckSecurity;
import raf.rs.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //list
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<List<ClientDto>> getClients() {

        return new ResponseEntity<>(userService.findAllClients(), HttpStatus.OK);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody TokenRequestDto tokenRequestDto) {

        TokenResponseDto responseDto = userService.login(tokenRequestDto);

        if(responseDto == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        else
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //register client
    @PostMapping("/register/client")
    public ResponseEntity<ClientDto> registerClient(@RequestBody ClientCreateDto clientCreateDto) {

        return new ResponseEntity<>(userService.addClient(clientCreateDto), HttpStatus.CREATED);
    }

    //register manager
    @PostMapping("/register/manager")
    public ResponseEntity<ManagerDto> registerManager(@RequestBody ManagerCreateDto managerCreateDto) {

        return new ResponseEntity<>(userService.addManager(managerCreateDto), HttpStatus.CREATED);
    }

    //edit profile
    @PutMapping
    public ResponseEntity<UserDto> update(@RequestHeader("Authorization") String authorization,
                                          @RequestBody UserUpdateDto userUpdateDto) {

        Integer id = userService.getUserIdFromJwt(authorization.split(" ")[1]);
        UserDto userDto = userService.update(id.longValue(), userUpdateDto);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //forbid login
    @PutMapping("/ban/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<?> ban(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization) {

        userService.banUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //enable login
    @PutMapping("/unban/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<?> unban(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization) {

        userService.unbanUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rank/{id}")
    public ResponseEntity<DiscountDto> getRank(@PathVariable("id") Long id) {

        DiscountDto discountDto = userService.getRank(id);

        if(discountDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(discountDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDto> increment(@PathVariable("id") Long id, @RequestBody IncrementDaysDto incrementDaysDto) {

        userService.increment(id, incrementDaysDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
