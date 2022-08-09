package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      userService.setCarToUser(userService.getUser(1), new Car("Hyndai", 123));
      userService.setCarToUser(userService.getUser(2), new Car("Honda", 1234));
      userService.setCarToUser(userService.getUser(3), new Car("BMW", 12345));
      userService.setCarToUser(userService.getUser(4), new Car("Merz", 123456));

      System.out.println(userService.getUserByCar("Hyndai", 123));
      System.out.println(userService.getUserByCar("Honda", 1234));
      System.out.println(userService.getUserByCar("BMW", 12345));
      System.out.println(userService.getUserByCar("Merz", 123456));

//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = " + user.getId());
//         System.out.println("First Name = " + user.getFirstName());
//         System.out.println("Last Name = " + user.getLastName());
//         System.out.println("Email = " + user.getEmail());
//         System.out.println();
//
//      }

      context.close();
   }
}
