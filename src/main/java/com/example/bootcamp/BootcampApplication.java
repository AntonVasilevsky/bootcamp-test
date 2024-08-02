package com.example.bootcamp;

import com.example.bootcamp.model.Employee;
import com.example.bootcamp.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootcampApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BootcampApplication.class, args);
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
       /* Employee alice = Employee
                .builder()
                .name("Alice")
                .surname("Wonder")
                .patronymic("Lewisovna")
                .email("bitMushroom@wonderland.com")
                .title("explorer")
                .build();
        Employee bob = Employee
                .builder()
                .name("Bob")
                .surname("Sinclair")
                .patronymic("Ivanovich")
                .email("blabla@wonderland.com")
                .title("singer")
                .build();
        employeeService.add(alice);
        employeeService.add(bob);
        employeeService.findAll().forEach(System.out::println);*/
      //  employeeService.deleteAll();

    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
