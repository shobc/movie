import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class NotSelectable{
    public static String getNotSelectableSeats(String key){

        Properties prop = new Properties();
        String notSelectableSeats = "";
        
        try{
        prop.load(new FileInputStream("C:/sotugyouseisaku/WEB-INF/classes/notSelectableSeats.properties"));
        String notSelectableSeat = prop.getProperty(key);
        notSelectableSeats = notSelectableSeat;

        }catch(FileNotFoundException e){
        throw new RuntimeException(e.getMessage(),e);
        }catch(IOException e){
        throw new RuntimeException(e.getMessage(),e);
        }

        return notSelectableSeats;

    }
    public static void main(String[] args) {
        String a = NotSelectable.getNotSelectableSeats("2");
        System.out.println("retrun"+a);
    }
}
