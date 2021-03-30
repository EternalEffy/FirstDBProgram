import java.util.Random;

public class Generator {

    private String name,age,score,level;
    private final String alphabet="AaBbCcDdEeFfGgHhJjKkIiLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";


    public String generateAge(){
        age = String.valueOf(new Random().nextInt(50));
        return age;
    }

    public String generateScore(){
        score = String.valueOf(new Random().nextInt(999));
        return score;
    }

    public String generateName(){
        Random r = new Random();
        StringBuilder builder = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            builder.append(alphabet.charAt(r.nextInt(51)));
        }
        return name=builder.toString();
    }

    public String generateLevel(){
        Random r = new Random();
        level = String.valueOf(r.nextInt(100));
        return level;
    }

    public User genUser(){
        return new User(generateName(),generateAge(),generateScore(),generateLevel());
    }

}
