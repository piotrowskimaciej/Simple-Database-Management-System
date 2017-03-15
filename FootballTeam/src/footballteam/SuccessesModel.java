package footballteam;

/**
 * SuccessesModel
 * Klasa przechowujaca model tabeli Sukcesy
 * @author maciejpiotrowski
 */
public class SuccessesModel {
    public int Id_success;
    public String Team_Name;
    public String Season;
    public String Success;
    public int Id_type_success;
    
    /**
     * Konstruktor modelu tabeli Sukcesy inicjalizujacy zaimplementowane pola
     * @param Id_success
     * @param Team_Name
     * @param Season
     * @param Success
     * @param Id_type_success 
     */
    public SuccessesModel(int Id_success, String Team_Name, String Season, String Success, int Id_type_success) {
        this.Id_success = Id_success;
        this.Team_Name = Team_Name;
        this.Season = Season;
        this.Success = Success;
        this.Id_type_success = Id_type_success;
    }
    
    /**
     * Sekcja geterow do modelu tabeli Sukcesy
     * @return 
     */
    public int getId_success() {
        return Id_success;
    }

    public String getTeam_Name() {
        return Team_Name;
    }

    public String getSeason() {
        return Season;
    }

    public String getSuccess() {
        return Success;
    }

    public int getId_type_success() {
        return Id_type_success;
    }
    
    /**
     * Sekcja seterow do modelu tabeli Sukcesy
     */
    public void setId_success(int Id_success) {
        this.Id_success = Id_success;
    }

    public void setTeam_Name(String Team_Name) {
        this.Team_Name = Team_Name;
    }

    public void setSeason(String Season) {
        this.Season = Season;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public void setId_type_success(int Id_type_success) {
        this.Id_type_success = Id_type_success;
    }
}
