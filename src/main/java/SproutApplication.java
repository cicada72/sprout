import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import com.momoc.sprout.common.FileScanner;
import com.momoc.sprout.configuration.exception.ConfigNotFoundException;
import org.apache.log4j.Logger;

import java.io.File;

public class SproutApplication {

    private static final Logger logger = Logger.getLogger("Application");
    public static void main(String... args) throws ConfigNotFoundException, IllegalPackageNameException {

        File f = FileScanner.findTargetFileByName("/Users/chengmoran/dev/github/sprout/target/classes",
                "classes");
        logger.info(f.getAbsolutePath());
    }
}
