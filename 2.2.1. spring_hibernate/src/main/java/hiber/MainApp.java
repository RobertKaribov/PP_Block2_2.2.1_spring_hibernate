package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("Car1", 111);
      Car car2 = new Car("Car2", 222);
      Car car3 = new Car("Car3", 333);
      Car car4 = new Car("Car4", 444);
      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car3).setUser(user2));
      userService.add(user3.setCar(car2).setUser(user3));

      int i = 1;
      for (User user : userService.listUsers()){
         System.out.println(i++ + ") " + user + " " + user.getCar());
         System.out.println(".....");
      }
      for (int j = 0; j < 2; j++) {
         System.out.println();
      }
      try {
         System.out.println(userService.carsForUsers("Car1", 111));
         System.out.println();
         System.out.println(userService.carsForUsers("Car3", 333));
         System.out.println();
         System.out.println(userService.carsForUsers("Car4", 444));
         System.out.println();
      } catch (NoResultException s) {
         System.out.println("User имеющий данную car не найден");
      }
      context.close();
   }
}
