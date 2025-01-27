package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("qwe", 123)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("qwer", 1234)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("qwert", 12345)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("qwerty", 123456)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      List<User> userList = new ArrayList<>();
      userList.add(userService.getUsersBySeriesAndModel(123, "qwe"));
      userList.add(userService.getUsersBySeriesAndModel(1234, "qwer"));
      userList.add(userService.getUsersBySeriesAndModel(12345, "qwert"));
      userList.add(userService.getUsersBySeriesAndModel(123456, "qwerty"));

      for (User user : userList) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
