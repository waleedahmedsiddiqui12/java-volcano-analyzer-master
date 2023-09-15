import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class Main {
    
    public static void main(String[] args) throws IOException, URISyntaxException {


        VolcanoAnalyzer volcanoAnalyzer=new VolcanoAnalyzer();

         volcanoAnalyzer.loadVolcanoes(Optional.empty());

        System.out.println(); 
    }

}
