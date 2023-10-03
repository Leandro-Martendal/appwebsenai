package com.example.appwebsenai;

import com.example.appwebsenai.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonControllerTestOperation {

    @Test
    public void testSaveCompletePerson(){
        Person p = new Person();
        p.setId(1);
        Assertions.assertEquals(1, p.getId());

    }

}
