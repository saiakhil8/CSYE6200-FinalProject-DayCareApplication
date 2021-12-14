package edu.neu.csye6200;

import edu.neu.csye6200.controllers.ApplicationController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DayCareApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(DayCareApplication.class).headless(false).run(args);
//        ThreadManager.getInstance().getMainThreadExecutor().execute(()->{
//            context.getBean(MainFrameController.class);
//        });

        context.getBean(ApplicationController.class);
    }

}
