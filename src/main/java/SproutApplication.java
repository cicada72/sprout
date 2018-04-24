import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import com.momoc.sprout.common.FileScanner;
import com.momoc.sprout.configuration.ConfigContext;
import com.momoc.sprout.configuration.exception.ConfigKeyNotFoundException;
import org.apache.log4j.Logger;

import java.io.File;

public class SproutApplication {

    private static final Logger logger = Logger.getLogger("Application");
    public static void main(String... args) throws ConfigKeyNotFoundException, IllegalPackageNameException {

        ConfigContext configContext = new ConfigContext();

        File f = FileScanner.findTargetFileByPath("/Users/chengmoran/dev/github/sprout/target/classes",
                FileScanner.convertPackageToPath("com.momoc.sprout"));
        logger.info(f.getAbsolutePath());
    }
}
