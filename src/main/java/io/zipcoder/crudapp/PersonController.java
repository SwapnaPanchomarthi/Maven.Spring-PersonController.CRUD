package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PersonController

{
   PersonRepository personRepository;


    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository=personRepository;
    }


    @PostMapping("/people")
        public ResponseEntity<Person> createPerson(@RequestBody Person p){
        personRepository.save(p);
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
//
//        return p;
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id){

        return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/people")
   public ResponseEntity<List<Person>> getPersonList(){
        return new ResponseEntity<>((List<Person>)personRepository.findAll(), HttpStatus.OK);
   }

    @PutMapping("/people/{id}")
     public  ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person p){
          Person person=  personRepository.findOne(id);
          person.setFirstName(p.getFirstName());
        //person.setFirstName(fname);
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void DeletePerson(@PathVariable int id){
            personRepository.delete(id);
       // return new ResponseEntity<>(personRepository.delete(id), HttpStatus.OK);

    }
}
