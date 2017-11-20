package ru.ant.chunked4j.sample;

import ru.ant.chunked4j.ChunkService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class AppContextListener implements ServletContextListener {

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        ChunkService.getInstance().registerStreamReaderFactory(new FileWriterFactory());
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

}
