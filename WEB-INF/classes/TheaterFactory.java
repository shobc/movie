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
        prop.load(new FileInputStream("C:/sotugyouseisaku/WEB-INF/classes/seat.properties"));
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

    public static String getAisle(String key){

        Properties prop = new Properties();
        String aisles = "";

        try{
        prop.load(new FileInputStream("C:/sotugyouseisaku/WEB-INF/classes/aisle.properties"));
        String aisle = prop.getProperty(key);
        System.out.println("key"+key);
        aisles = aisle;

        }catch(FileNotFoundException e){
        throw new RuntimeException(e.getMessage(),e);
        }catch(IOException e){
        throw new RuntimeException(e.getMessage(),e);
        }

        return aisles;

    }
    public static void main(String[] args) {
        String a = TheaterFactory.getSeats("1");
        System.out.println("retrun"+a);
    }
}
