package bla.bla.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/people")
public class PersonController {

    private final PersonRepository service;

    @Autowired
    public PersonController(PersonRepository service) {
        this.service = service;
    }

    @GetMapping
    public List<Person> getPeople() {
        return service.findAll();
    }

    @PostMapping
    public Person addPerson (@RequestBody Person person) {
        return service.save(person);
    }
}
