package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Controller {

    @Autowired
    private PersonRepository personRepository;
    private List<Person> persons = new ArrayList<>();
    private int id = 0;

    public Person findPerson(String name){
        List<Person> persons = (List<Person>) personRepository.findAll();
        for(Person person : persons){
            if(person.getName().equals(name)){
                return person;
            }
        }

        return null;
    }

    public Person addPerson(String name, String sexo){
        Person person = new Person();
        person.setName(name);
        person.setSexo(sexo);
        id++;
        person.setId(id);
        personRepository.save(person);
        return person;
    }

    public void removePerson(String name){
        Person person = findPerson(name);
        personRepository.delete(person);
    }

    public Person editPerson(String name, String sexo){
        Person person = findPerson(name);
        person.setSexo(sexo);
        personRepository.save(person);
        return person;
<<<<<<< HEAD
=======
    }

    public List<Person> listAll(){
        return (List<Person>)personRepository.findAll();
>>>>>>> 0df9cca3bfd9c5b2ff6a2401c635071b03efd00e
    }

    public List<Person> listAll(){
        return (List<Person>)personRepository.findAll();
    }
}