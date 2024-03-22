package com.humanbooster.sunset;

import com.humanbooster.sunset.models.Role;
import com.humanbooster.sunset.models.User;
import com.humanbooster.sunset.services.RoleService;
import com.humanbooster.sunset.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class SpringData {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(SpringData.class);
    }

    @Bean
    public CommandLineRunner dataLoader(UserService userService, RoleService roleService){
        return args->{
            Role roleAdmin = roleService.findByName("ADMIN");
            Role roleUser =  roleService.findByName("USER");

            if(roleAdmin == null){
                roleAdmin = new Role("ADMIN");
                roleService.saveRole(roleAdmin);
            }

            if(roleUser == null){
                roleUser = new Role("USER");
                roleService.saveRole(roleUser);
            }

            if (userService.findByEmail("admin@admin.com")==null){
                User user = new User();
                user.setFirstname("Admin");
                user.setLastname("Admin");
                user.setPassword("admin");
                user.setEmail("admin@admin.com");
                user.setStreet_number(26);
                user.setStreet_name("Boulevard des crabes");
                user.setZip_code("63000");
                user.setCountry("FRANCE");

                user.addRole(roleUser);
                user.addRole(roleAdmin);

                userService.saveUser(user);
            }

            if (userService.findByEmail("test@test.com")==null){
                User user2 = new User();
                user2.setFirstname("Test");
                user2.setLastname("Test");
                user2.setPassword("Test1234");
                user2.setEmail("test@test.com");
                user2.setStreet_number(1);
                user2.setStreet_name("Avenue de Lune");
                user2.setZip_code("75500");
                user2.setCountry("FRANCE");

                user2.addRole(roleUser);

                userService.saveUser(user2);
            }

            if (userService.findByEmail("tata@tata.com")==null){
                User user3 = new User();
                user3.setFirstname("Tata");
                user3.setLastname("Tata");
                user3.setPassword("Tata1234");
                user3.setEmail("tata@tata.com");
                user3.setStreet_number(96);
                user3.setStreet_name("Rue soley");
                user3.setZip_code("75500");
                user3.setCountry("ESPAGNE");

                user3.addRole(roleUser);

                userService.saveUser(user3);
            }

            if (userService.findByEmail("toto@toto.com")==null){
                User user4 = new User();
                user4.setFirstname("Toto");
                user4.setLastname("Toto");
                user4.setPassword("Toto1234");
                user4.setEmail("toto@toto.com");
                user4.setStreet_number(9);
                user4.setStreet_name("Impasse des Grues");
                user4.setZip_code("69001");
                user4.setCountry("CHINE");

                user4.addRole(roleUser);

                userService.saveUser(user4);
            }
        };
    }

}