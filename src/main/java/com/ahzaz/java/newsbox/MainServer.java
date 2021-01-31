package com.ahzaz.java.newsbox;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

/**
 * @author Ahzaz
 */
public class MainServer {

    @Autowired
    Properties properties;

    public MainServer() {
        WEB_APP = System.getenv("WEBAPP_PATH");
//        System.out.println(WEB_APP);
    }

    private static final String CONTEXT_PATH = "/";
    private static String WEB_APP = "";

    private int port;
    private int maxThread;
    private Server server;

    public void startServer() throws Exception {

        port = Integer.valueOf(System.getProperty("port", "8080"));
        maxThread = Integer.valueOf(System.getProperty("maxThread", "100"));

        server = new Server(createThreadPool());

        server.addConnector(createHTTPConnector());


        HandlerList handlers = new HandlerList();

//        ResourceHandler resourceHandler = new ResourceHandler();
//        resourceHandler.setResourceBase(AppUtil.getResourcesDirectory());

//        ContextHandler ctxHandler = new ContextHandler();
//        ctxHandler.setHandler(resourceHandler);
//        ctxHandler.setContextPath("/uploads");
//        handlers.addHandler(ctxHandler);

        WebAppContext webapp = new WebAppContext();
        webapp.setResourceBase(WEB_APP);
        webapp.setContextPath(CONTEXT_PATH);
        handlers.addHandler(webapp);

        server.setHandler(handlers);
        server.start();
        server.join();
    }

    /**
     * If you look a the QueuedThreadPool implementation,
     * Default: maxThread=200, minThread=8, initialQueueCapicity=8
     *
     * @return
     */
    private ThreadPool createThreadPool() {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMaxThreads(maxThread);
        return threadPool;
    }

    private Connector createHTTPConnector() {
        ServerConnector http = new ServerConnector(server);
        http.setPort(port);
        http.setIdleTimeout(30000);
//        http.setHost("localhost");
        return http;
    }

    public static void main(String[] args) throws Exception {
        new MainServer().startServer();
    }
}
