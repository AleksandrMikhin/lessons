package lesson26;


import java.util.Arrays;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//public class Log4jLoggedProgram {
//    //Log4j
//
//    //FATAL
//    //ERROR
//    //WARN
//    //INFO
//
//    private static final Logger LOGGER = Logger.getLogger(Log4jLoggedProgram.class);
//
//    public static void main(String[] args) {
//
//        LOGGER.info("Started with args: " + Arrays.toString(args));
//        try {
//            int res = 3/0;
//        }catch (Exception e){
//            LOGGER.error("Fatal error: " + e.getMessage());
//        }
//    }
//}


//slf4j
class Slf4jLoggedProgram {
    //
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jLoggedProgram.class);


    public static void main(String[] args) {

        LOGGER.info("Started with args: " + Arrays.toString(args));
        try {
            int res = 3/0;
        }catch (Exception e){
            LOGGER.error("Fatal error: " + e.getMessage());
        }
    }
}

// дз - посмотреть фассад slf4j