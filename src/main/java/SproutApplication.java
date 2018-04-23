import com.momoc.sprout.configuration.ConfigContext;
import com.momoc.sprout.configuration.exception.ConfigNotFoundException;
import org.apache.log4j.Logger;

public class SproutApplication {

    private static final Logger logger = Logger.getLogger("Application");
    public static void main(String... args) throws ConfigNotFoundException {
        ConfigContext configContext = new ConfigContext();
        logger.info(configContext.getJdbcDriver());

    }
}
