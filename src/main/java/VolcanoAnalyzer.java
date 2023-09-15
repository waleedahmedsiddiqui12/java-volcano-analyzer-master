import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class VolcanoAnalyzer {
    private List<Volcano> volcanos;

    public void loadVolcanoes(Optional<String> pathOpt) throws IOException, URISyntaxException {
        try{
            String path = pathOpt.orElse("volcano.json");
            URL url = this.getClass().getClassLoader().getResource(path);
            String jsonString = new String(Files.readAllBytes(Paths.get(url.toURI())));
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            volcanos = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, Volcano.class));
        } catch(Exception e){
            throw(e);
        }
    }

    public Integer numbVolcanoes(){
        return volcanos.size();
    }

    //add methods here to meet the requirements in README.md

    ////Return the volcanoes that erupted in the 1980s.
    public List<Volcano> eruptedInEighties()
    {

        return this.volcanos.stream().filter(x -> x.getYear() >= 1980).filter(y -> y.getYear() <= 1989).collect(Collectors.toList());

        //return volcanos.stream().filter(x -> x.getYear() >= 1980);
        
    }
    //// Return an array of the names of volcanoes that had an eruption with a Volcanic Explosivity Index (VEI) of 6 or higher.
    public String[] highVEI(){

       List<String> name= this.volcanos.stream().filter(x -> x.getVEI() >=6).map(p -> p.getName()).collect(Collectors.toList());
       String[] size=new String[name.size()];
       return name.toArray(size);

    }

    ////  Return the eruption with the highest number of recorded deaths.
    public Volcano mostDeadly()
    {
        return this.volcanos.stream().filter(a -> !a.getDEATHS().isEmpty()).max(Comparator.comparingInt(y->Integer.parseInt(y.getDEATHS()))).orElse(null);

    }

    ////Return the percentage of eruptions that caused tsunamis.
    public double causedTsunami()
    {
         double totalcount=this.volcanos.size();
         double tsunamicount=this.volcanos.stream().filter(a->a.getTsu().equals("tsu")).count();
         return (tsunamicount / totalcount) * 100;


        //return (this.volcanos.stream().filter(a->a.getTsu().equals("tsu")).count() / this.volcanos.size()) * 100;



    }

    ////Return the most common type of volcano in the set.
    public String mostCommonType()
    {
        return  "";
    }


    ////Return an array of types of volcanoes.

    public  String[] volcanoTypes()
    {

       List<String> valcnotype= this.volcanos.stream().filter(x->!x.getType().isEmpty()).map(p -> p.getType()).distinct().collect(Collectors.toList());
       String[] size=new String[valcnotype.size()];
       return valcnotype.toArray(size);


    }



}
