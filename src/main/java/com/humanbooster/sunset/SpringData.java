//package com.humanbooster.sunset;
//
//import com.humanbooster.sunset.models.Role;
//import com.humanbooster.sunset.models.User;
//import com.humanbooster.sunset.services.RoleService;
//import com.humanbooster.sunset.services.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//
//
//@SpringBootApplication
//public class SpringData {
//    private final Logger logger = LoggerFactory.getLogger(SpringData.class);
//
//    public static void main(String[] args){
//        SpringApplication.run(SpringData.class);
//    }
//
//    @Bean
//    public CommandLineRunner dataLoader(UserService userService, RoleService roleService){
//        return args -> {
//            Role user = new Role("USER");
//            Role admin = new Role("ADMIN");
//            Role frere = new Role("FRERE");
//            Role soeur = new Role("SOEUR");
//            Role cousin = new Role("COUSIN");
//
//            if (userService.findByEmail("admin@admin.fr").getEmail().isEmpty()){
//
//                logger.info("Creation d'un admin");
//
//                User userAdmin = new User("Admin", "admin", "admin@admin.fr", "France", "Admin123", "Admin123");
//
////                userService.addUser(userAdmin);
//
//            }
//
//        };
//    }
//
//}
