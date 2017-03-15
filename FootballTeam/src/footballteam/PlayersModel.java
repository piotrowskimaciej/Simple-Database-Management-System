package footballteam;

/**
 * PlayersModel
 * Klasa przechowujaca model tabeli Zawodncy
 * @author maciejpiotrowski
 */
public class PlayersModel {
    public int Id_player;
    public String Team_Name;
    public String Name;
    public String Surname;
    public String Date_Of_Birth;
    public int Height;
    public int Weight;
    public String Nationality;
    public String Position;
    public int Number;
    public String Date_Debut;
    public int Id_team;
    
    /**
     * Konstruktor modelu tabeli Zawodnicy inicjalizujacy zaimplementowane pola
     * @param Id_player
     * @param Team_Name
     * @param Name
     * @param Surname
     * @param Date_Of_Birth
     * @param Height
     * @param Weight
     * @param Nationality
     * @param Position
     * @param Number
     * @param Date_Debut
     * @param Id_team 
     */
    public PlayersModel(int Id_player, String Team_Name, String Name, String Surname, String Date_Of_Birth, int Height, int Weight, String Nationality, String Position, int Number, String Date_Debut, int Id_team) {
        this.Id_player = Id_player;
        this.Team_Name = Team_Name;
        this.Name = Name;
        this.Surname = Surname;
        this.Date_Of_Birth = Date_Of_Birth;
        this.Height = Height;
        this.Weight = Weight;
        this.Nationality = Nationality;
        this.Position = Position;
        this.Number = Number;
        this.Date_Debut = Date_Debut;
        this.Id_team = Id_team;
    }
    
    /**
     * Sekcja geterow do modelu tabeli Zawodnicy
     * @return 
     */
    public int getId_player() {
        return Id_player;
    }

    public String getTeam_Name() {
        return Team_Name;
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

    public int getHeight() {
        return Height;
    }

    public int getWeight() {
        return Weight;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getPosition() {
        return Position;
    }

    public int getNumber() {
        return Number;
    }

    public String getDate_Debut() {
        return Date_Debut;
    }

    public int getId_team() {
        return Id_team;
    }

    /**
     * Sekcja seterow do modelu tabeli Zawodnicy
     */
    public void setId_player(int Id_player) {
        this.Id_player = Id_player;
    }

    public void setTeam_Name(String Team_Name) {
        this.Team_Name = Team_Name;
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

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setWeight(int Weight) {
        this.Weight = Weight;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public void setDate_Debut(String Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public void setId_team(int Id_team) {
        this.Id_team = Id_team;
    }
}
