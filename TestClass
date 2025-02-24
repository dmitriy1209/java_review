@Component
public class TestClass {

    @Autowired(required = false)
    public SuperService superService;

    @Autowired
    public SuperRepository superRepository;

    @Transactional
    public Object getSuperData() {
        String someString = getSuperDataFromRepository();
        return superService.getSuperData() + someString; 
    }

    @Transactional
    public String getSuperDataFromRepository() {
      
        BufferedReader reader;
        String result = "";
      
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;  
            }
        } catch (IOException e) {} 
      
        result = result + superRepository.getSuperString();

        return result;
    }
}

@Component
public class SuperService {
    Object getSuperData() {return null;}
}

@Component
public class SuperRepository {
    Object getSuperString() {return null;}
}
