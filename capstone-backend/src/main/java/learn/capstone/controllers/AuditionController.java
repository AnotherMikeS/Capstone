package learn.capstone.controllers;

import learn.capstone.domain.AuditionService;
import learn.capstone.domain.Result;
import learn.capstone.models.Audition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://cors-anywhere.herokuapp.com/sncapstoneui-env.eba-duvmn8qw.us-east-2.elasticbeanstalk.com")
@RequestMapping("/api/theater/audition")

public class AuditionController {

    private final AuditionService service;

    public AuditionController(AuditionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Audition>> findAll() {
        List<Audition> auditions = service.findAll();
        if (auditions == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(auditions);
    }

    @GetMapping("/{auditionId}")
    public ResponseEntity<Audition> findById(@PathVariable int auditionId) {
        Audition audition = service.findById(auditionId);
        if (audition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(audition);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Audition audition) {
        Result<Audition> result = service.add(audition);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{auditionId}")
    public ResponseEntity<Object> update(@PathVariable int auditionId, @RequestBody Audition audition) {
        if (auditionId != audition.getAuditionId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Audition> result = service.update(audition);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{auditionId}")
    public ResponseEntity<Object> deleteById(@PathVariable int auditionId) {
        Audition audition = service.findById(auditionId);
        Result<Audition> result = service.deleteById(auditionId);
        if (audition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
