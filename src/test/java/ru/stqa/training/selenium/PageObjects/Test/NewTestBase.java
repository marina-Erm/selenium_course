package ru.stqa.training.selenium.PageObjects.Test;

import org.junit.Before;
import ru.stqa.training.selenium.PageObjects.Application.Application;

public class NewTestBase {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }

}
