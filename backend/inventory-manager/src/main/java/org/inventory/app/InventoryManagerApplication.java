package org.inventory.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagerApplication.class, args);

        Student student = new Student("test id","test-name");
        System.out.println(student.getId());
        System.out.println(student.getName());
    }

}
