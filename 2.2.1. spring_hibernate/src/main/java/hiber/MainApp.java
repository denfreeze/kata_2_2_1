package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("BMW", 7);
        Car car2 = new Car("Ford", 3);
        Car car3 = new Car("Honda", 6);

        User user1 = new User("Alex", "Freeman", "alfr@gmail.com");
        User user2 = new User("John", "Noxvill", "jnox@gmail.com");
        User user3 = new User("Patrick", "Star", "past@gmail.com");

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + (user.getCar() != null ? user.getCar().toString() : "null"));
            System.out.println();
        }

        context.close();
    }
}
