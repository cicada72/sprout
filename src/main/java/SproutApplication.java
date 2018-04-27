import com.momoc.sprout.classloader.BasicClassLoader;
import com.momoc.sprout.classloader.JFileReader;
import com.momoc.sprout.classloader.entity.JFile;
import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import com.momoc.sprout.common.FileScanner;
import com.momoc.sprout.configuration.ConfigContext;
import com.momoc.sprout.configuration.exception.ConfigKeyNotFoundException;
import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class SproutApplication {

    private static final Logger logger = Logger.getLogger("Application");
    public static void main(String... args) throws ConfigKeyNotFoundException, IllegalPackageNameException, ClassNotFoundException {
        BasicClassLoader.getClassLoader().getClassesByPackage("com.momoc.sprout");
        logger.info("============");
    }

}
