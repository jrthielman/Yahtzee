package sample.data;

public class UserInput {

    private static UserInput instance = new UserInput();

    private String naam;

    private UserInput() { }

    public static UserInput getInstance(){
        return instance;
    }

    public void setNaam(String naam){
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }
}
