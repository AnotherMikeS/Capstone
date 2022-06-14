package learn.capstone.controllers;

import java.util.List;

import learn.capstone.models.AppUser;
import learn.capstone.domain.Result;
import learn.capstone.domain.AppUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/theater/appUser")

public class AppUserController {
    private final AppUserService service;

    public AppUserController(AppUserService service){this.service = service;}

    @GetMapping
    public ResponseEntity<List<AppUser>> findAll(){
        List<AppUser> users = service.findAll();

        if(users == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{appUserId}")
    public ResponseEntity<AppUser> findById(@PathVariable int appUserId) {
        AppUser user = service.findById(appUserId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> findByUsername(@PathVariable String username) {
        AppUser user = service.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody AppUser user) {
        Result<AppUser> result = service.add(user);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{appUserId}")
    public ResponseEntity<Object> update(@PathVariable int appUserId, @RequestBody AppUser user) {
        if (appUserId != user.getAppUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<AppUser> result = service.update(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{appUserId}")
    public ResponseEntity<Object> deleteById(@PathVariable int appUserId) {
        AppUser user = service.findById(appUserId);
        Result<AppUser> result = service.deleteById(appUserId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
