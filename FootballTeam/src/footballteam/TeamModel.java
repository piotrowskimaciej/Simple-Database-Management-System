package footballteam;

/**
 * TeamModel
 * Klasa przechowujaca model tabeli Druzyna
 * @author maciejpiotrowski
 */
public class TeamModel {
    
    public int Id_team;
    public String Team_Name;
    public int Established;
    public String Colors;
    public String Country;
    
    /**
     * Konstruktor modelu tabeli Druzyna inicjalizujacy zaimplementowane pola
     * @param Id_team
     * @param Team_Name
     * @param Established
     * @param Colors
     * @param Country 
     */
    public TeamModel(int Id_team, String Team_Name, int Established, String Colors, String Country) {
        this.Id_team = Id_team;
        this.Team_Name = Team_Name;
        this.Established = Established;
        this.Colors = Colors;
        this.Country = Country;
    }
    
    /**
     * Sekcja geterow do modelu tabeli Druzyna
     * @return 
     */
    public int getId_team() {
        return Id_team;
    }

    public String getTeam_Name() {
        return Team_Name;
    }

    public int getEstablished() {
        return Established;
    }

    public String getColors() {
        return Colors;
    }

    public String getCountry() {
        return Country;
    }
    
    /**
     * Sekcja seterow do modelu tabeli Druzyna
     */
    public void setId_team(int Id_team) {
        this.Id_team = Id_team;
    }

    public void setTeam_Name(String Team_Name) {
        this.Team_Name = Team_Name;
    }

    public void setEstablished(int Established) {
        this.Established = Established;
    }

    public void setColors(String Colors) {
        this.Colors = Colors;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }
}
