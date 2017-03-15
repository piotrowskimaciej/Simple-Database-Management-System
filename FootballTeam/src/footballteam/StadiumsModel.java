package footballteam;

/**
 * StadiumsModel
 * Klasa przechowujaca model tabeli Stadiony
 * @author maciejpiotrowski
 */
public class StadiumsModel {
    public int Id_team;
    public String Name;
    public int Year_Of_Construction;
    public int Capacity_Stands;
    
    /**
     * Konstruktor modelu Stadiony Trener inicjalizujacy zaimplementowane pola
     * @param Id_team
     * @param Name
     * @param Year_Of_Construction
     * @param Capacity_Stands 
     */
    public StadiumsModel(int Id_team, String Name, int Year_Of_Construction, int Capacity_Stands) {
        this.Id_team = Id_team;
        this.Name = Name;
        this.Year_Of_Construction = Year_Of_Construction;
        this.Capacity_Stands = Capacity_Stands;
    }
    
    /**
     * Sekcja geterow do modelu tabeli Stadiony
     * @return 
     */
    public int getId_team() {
        return Id_team;
    }

    public String getName() {
        return Name;
    }

    public int getYear_Of_Construction() {
        return Year_Of_Construction;
    }

    public int getCapacity_Stands() {
        return Capacity_Stands;
    }

    public void setId_team(int Id_team) {
        this.Id_team = Id_team;
    }
    
    /**
     * Sekcja seterow do modelu tabeli Stadiony
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setYear_Of_Construction(int Year_Of_Construction) {
        this.Year_Of_Construction = Year_Of_Construction;
    }

    public void setCapacity_Stands(int Capacity_Stands) {
        this.Capacity_Stands = Capacity_Stands;
    }
}
