package springBeans;

import org.assertj.core.api.Assert;
import org.example.model.Person;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class prototypeBeanTest {

    private static final String NAME = "John Smith";
    private static final String NAME_OTHER = "Anna Jones";

    @Test
    public void givenPrototypeScope_whenSetNames_thenDifferentNames() {
        AbstractApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("scopes.xml");

        Person personPrototypeA = (Person) applicationContext.getBean("personPrototype");
        Person personPrototypeB = (Person) applicationContext.getBean("personPrototype");

        personPrototypeA.setName(NAME);
        personPrototypeB.setName(NAME_OTHER);

        assertEquals(NAME, personPrototypeA.getName());
        assertEquals(NAME_OTHER, personPrototypeB.getName());

        applicationContext.close();
    }

    @Test
    public void givenSingletonScope_whenSetNames_thenDifferentNames() {
        AbstractApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("scopes.xml");

        Person personPrototypeA = (Person) applicationContext.getBean("personSingleton");
        Person personPrototypeB = (Person) applicationContext.getBean("personSingleton");

        personPrototypeA.setName(NAME);
        personPrototypeB.setName(NAME_OTHER);

        System.out.println("Prototype A" + personPrototypeA);
        System.out.println("Prototype B" + personPrototypeB);

        assertEquals(NAME_OTHER, personPrototypeA.getName());
        assertEquals(NAME_OTHER, personPrototypeB.getName());

        applicationContext.close();
    }
}
