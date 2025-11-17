import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class TomcatRunner {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setHostname("localhost");
        tomcat.setPort(8080);
        tomcat.setBaseDir("tomcat-base-dir");

        Host host = tomcat.getHost();
        String webAppLocation = new File("src/main/webapp/").getAbsolutePath();

        Context context = tomcat.addWebapp(host, "/myapp", webAppLocation);

        WebappLoader webappLoader = new WebappLoader();
        webappLoader.setDelegate(true);
        context.setLoader(webappLoader);

        File classesDir = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(
                resources,
                "/WEB-INF/classes",
                classesDir.getAbsolutePath(),
                "/"
        ));
        context.setResources(resources);

        tomcat.getConnector();

        tomcat.start();

        tomcat.getServer().await();


    }

}