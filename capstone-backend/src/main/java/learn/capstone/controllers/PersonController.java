package learn.capstone.controllers;

import java.util.List;

import learn.capstone.domain.PersonService;
import learn.capstone.models.Person;
import learn.capstone.domain.Result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/theater/person")

public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service){this.service = service;}

    @GetMapping
    public ResponseEntity<List<Person>> findAll(){
        List<Person> users = service.findAll();

        if(users == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findById(@PathVariable int personId) {
        Person user = service.findById(personId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Person person) {
        Result<Person> result = service.add(person);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Object> update(@PathVariable int personId, @RequestBody Person person) {
        if (personId != person.getAppUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Person> result = service.update(person);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Object> deleteById(@PathVariable int personId) {
        Person user = service.findById(personId);
        Result<Person> result = service.deleteById(personId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
