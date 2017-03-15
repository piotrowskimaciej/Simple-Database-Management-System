package footballteam;

/**
 * CoachModel
 * Klasa przechowujaca model tabeli Trener
 * @author maciejpiotrowski
 */
public class CoachModel {
    public int Id_team;
    public String Name;
    public String Surname;
    public String Date_Of_Birth;
    public String Nationality;
    
    /**
     * Konstruktor modelu tabeli Trener inicjalizujacy zaimplementowane pola
     * @param Id_team
     * @param Name
     * @param Surname
     * @param Date_Of_Birth
     * @param Nationality 
     */
    public CoachModel(int Id_team, String Name, String Surname, String Date_Of_Birth, String Nationality) {
        this.Id_team = Id_team;
        this.Name = Name;
        this.Surname = Surname;
        this.Date_Of_Birth = Date_Of_Birth;
        this.Nationality = Nationality;
    }
    
    /**
     * Sekcja geterow do modelu tabeli Trener
     * @return 
     */
    public int getId_team() {
        return Id_team;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getDate_Of_Birth() {
        return Date_Of_Birth;
    }

    public String getNationality() {
        return Nationality;
    }
    
    /**
     * Sekcja seterow do modelu tabeli Trener
     */
    public void setId_team(int Id_team) {
        this.Id_team = Id_team;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public void setDate_Of_Birth(String Date_Of_Birth) {
        this.Date_Of_Birth = Date_Of_Birth;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }
}
