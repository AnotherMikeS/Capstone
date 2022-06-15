package learn.capstone.controllers;


import learn.capstone.domain.AuditioneeService;
import learn.capstone.domain.Result;
import learn.capstone.models.Audition;
import learn.capstone.models.Auditionee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/theater/auditionee")

public class AuditioneeController {

    private final AuditioneeService service;

    public AuditioneeController(AuditioneeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Auditionee>> findAll() {
        List<Auditionee> auditionees = service.findAll();
        if (auditionees == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(auditionees);
    }

    @GetMapping("/{auditioneeId}")
    public ResponseEntity<Auditionee> findById(@PathVariable int auditioneeId) {
        Auditionee auditionee = service.findById(auditioneeId);
        if (auditionee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(auditionee);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Auditionee auditionee) {
        Result<Auditionee> result = service.add(auditionee);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{auditioneeId}")
    public ResponseEntity<Object> update(@PathVariable int auditioneeId, @RequestBody Auditionee auditionee) {
        if (auditioneeId != auditionee.getAuditioneeId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Auditionee> result = service.update(auditionee);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{auditioneeId}")
    public ResponseEntity<Object> deleteById(@PathVariable int auditioneeId) {
        Auditionee auditionee = service.findById(auditioneeId);
        Result<Auditionee> result = service.deleteById(auditioneeId);
        if (auditionee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

}
