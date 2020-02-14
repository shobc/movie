import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TheaterFactory{
    public static String getSeats(String key){

        Properties prop = new Properties();
        String seats = "";

        try{
        prop.load(new FileInputStream("C:\\webapps\\movie\\WEB-INF\\classes\\seat.properties"));
        String seat = prop.getProperty(key);
        System.out.println("key"+key);
        seats = seat;

        }catch(FileNotFoundException e){
        throw new RuntimeException(e.getMessage(),e);
        }catch(IOException e){
        throw new RuntimeException(e.getMessage(),e);
        }

        return seats;

    }
    public static void main(String[] args) {
        String a = TheaterFactory.getSeats("1");
        System.out.println("retrun"+a);
    }
}
