package com;


import com.entity.entities.Almond;
import com.entity.entities.User;
import com.logic.AlmondService;
import com.logic.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Order(1)
@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    UserService userService;

    @Autowired
    AlmondService almondService;

    @Override
    public void run(String... args) throws Exception {
        if (userService.getUser("demo@gmail.com") == null) {
            User user = new User();
            user.setEmail("demo@gmail.com");
            user.setName("demo@gmail.com");
            user.setPassword("demo@gmail.com");
            userService.createUser(user);
        }
        if (almondService.getAlmonds().size() < 16) {
            Boolean deleted = almondService.deleteAllAlmonds();
            if (deleted) {
                List<Almond> almonds = new ArrayList<Almond>();
                almonds.add(0, new Almond("0", List.of(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "tardy nonpareil"));
                almonds.add(1, new Almond("1", List.of(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "fra gulio grade"));
                almonds.add(2, new Almond("2", List.of(0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "atocha"));
                almonds.add(3, new Almond("3", List.of(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "picantil"));
                almonds.add(4, new Almond("4", List.of(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "pri morsky"));
                almonds.add(5, new Almond("5", List.of(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "peerless"));
                almonds.add(6, new Almond("6", List.of(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0), "moncaio"));
                almonds.add(7, new Almond("7", List.of(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0), "marta"));
                almonds.add(8, new Almond("8", List.of(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0), "ferralise"));
                almonds.add(9, new Almond("9", List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0), "garrigaes"));
                almonds.add(10, new Almond("10", List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0), "masbovera"));
                almonds.add(11, new Almond("11", List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0), "ai"));
                almonds.add(12, new Almond("12", List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0), "planeta"));
                almonds.add(13, new Almond("13", List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0), "texas"));
                almonds.add(14, new Almond("14", List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0), "philys"));
                almonds.add(15, new Almond("15", List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), "desmayo lagueto"));

                almondService.initDataTable(almonds);
            }
        }
    }


}
