package footballteam;

import static footballteam.ParserXML.run;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javax.xml.stream.XMLStreamException;
import org.sqlite.SQLiteConfig;

/**
 * DataBaseControllers
 * Klasa przechowujaca wszystkie metody sluzace do zarzadzania baza danych.
 * @author maciejpiotrowski
 */
public class DataBaseController {
    //public static final String DRIVER = "org.sqlite.JDBC";
    //public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
    private static Connection connection;
    private static Statement statement;
    /**
     * ObservableList
     *Kolekcje sluzace do przechowywania danych z bazy po wykonaniu zapytan.
     */
    public static ObservableList<TeamModel> team;
    public static ObservableList<PlayersModel> players;
    public static ObservableList<CoachModel> coach;
    public static ObservableList<StadiumsModel> stadiums;
    public static ObservableList<SuccessesModel> successes;
    
    /**
     * runParserXML()
     * Metoda powodujaza uruchomienie parsera plikow XML 
     * oraz umozliwiajaca wybranie pliku z danymi.
     * @throws FileNotFoundException
     * @throws XMLStreamException 
     */
    public void runParserXML() throws FileNotFoundException, XMLStreamException{
        /*File xmlFile = new File("./src/projekt/data.xml");
        run(xmlFile);*/
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
               run(selectedFile);
        }
        ParserXML.results();        
    }
    
    /**
    * connectDataBase()
    * Metoda pozwalajaca na ustanowienie polaczenie z baza danych.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void connectDataBase(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Nie odnaleziono sterownika do obslugi bazy danych!");
            alert.showAndWait();
        }

        // nawiazanie polaczenia
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection("jdbc:sqlite:FootballTeam.db", config.toProperties());
            statement = connection.createStatement();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Pomyslnie polaczono z baza danych!");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby nawiazania polaczenia z baza danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * disconnectDataBase()
    * Metoda pobierajaca na zakonczenie polaczenia
    * z baza danych.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void disconnectDataBase(){
        try {
            statement.close();
            connection.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Pomyslnie zamknieto polaczenie z baza danych!");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby zamkniecia polaczenia z baza danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * loadTeam()
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadTeam(){
        try {
            team = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Team;");
            
            while(resultSet.next()){
                TeamModel teamModel = new TeamModel(resultSet.getInt("Id_team"),
                    resultSet.getString("Team_Name"), resultSet.getInt("Established"),
                    resultSet.getString("Colors"), resultSet.getString("Country")
                );
                team.add(teamModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * loadTeamName()
    * @param searchText
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadTeamName(String searchText){
        try {
            team = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Team WHERE Team_Name='"+searchText+"';");
            System.out.println(searchText);
            while(resultSet.next()){
                TeamModel teamModel = new TeamModel(resultSet.getInt("Id_team"),
                    resultSet.getString("Team_Name"), resultSet.getInt("Established"),
                    resultSet.getString("Colors"), resultSet.getString("Country")
                );
                team.add(teamModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * loadPlayers()
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadPlayers(){
        try {
            players = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT Id_player, Team_Name, Name, Surname, Date_Of_Birth, Height, Weight, Nationality, Position, Number, Date_Debut, Id_team FROM Team JOIN Players USING(Id_team);");
            
            while(resultSet.next()){
                PlayersModel playersModel = new PlayersModel(
                    resultSet.getInt("Id_player"), resultSet.getString("Team_Name"), resultSet.getString("Name"), 
                    resultSet.getString("Surname"), resultSet.getString("Date_Of_Birth"), 
                    resultSet.getInt("Height"), resultSet.getInt("Weight"), 
                    resultSet.getString("Nationality"), resultSet.getString("Position"), 
                    resultSet.getInt("Number"), resultSet.getString("Date_Debut"),
                    resultSet.getInt("Id_team")    
                );
                players.add(playersModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * loadSearchPlayers()
    * @param searchText
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadSearchPlayers(String searchText){
        try {
            players = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT Id_player, Team_Name, Name, Surname, Date_Of_Birth, Height, Weight, Nationality, Position, Number, Date_Debut, Id_team FROM Team JOIN Players USING(Id_team) WHERE Team_name='"+searchText+"';");
            System.out.println(searchText);
            while(resultSet.next()){
                PlayersModel playersModel = new PlayersModel(
                    resultSet.getInt("Id_player"), resultSet.getString("Team_Name"), resultSet.getString("Name"), 
                    resultSet.getString("Surname"), resultSet.getString("Date_Of_Birth"), 
                    resultSet.getInt("Height"), resultSet.getInt("Weight"), 
                    resultSet.getString("Nationality"), resultSet.getString("Position"), 
                    resultSet.getInt("Number"), resultSet.getString("Date_Debut"),
                    resultSet.getInt("Id_team")    
                );
                players.add(playersModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * loadCoach()
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadCoach(){
        try {
            coach = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Coach;");
            
            while(resultSet.next()){
                CoachModel coachModel = new CoachModel(
                    resultSet.getInt("Id_team"), resultSet.getString("Name"), 
                    resultSet.getString("Surname"), resultSet.getString("Date_Of_Birth"), 
                    resultSet.getString("Nationality")
                );
                coach.add(coachModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * loadStadiums()
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadStadiums(){
        try {
            stadiums = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Stadiums;");
            
            while(resultSet.next()){
                StadiumsModel stadiumsModel = new StadiumsModel(
                    resultSet.getInt("Id_team"), resultSet.getString("Name"), 
                    resultSet.getInt("Year_Of_Construction"), resultSet.getInt("Capacity_Stands")
                );
                stadiums.add(stadiumsModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * loadSuccesses()
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadSuccesses(){
        try {
            successes = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT Id_success, Team_Name, Season, Success, Id_type_success  FROM Team join Successes using(Id_team), Type_Of_Success using(Id_success);");
            
            while(resultSet.next()){
                SuccessesModel successesModel = new SuccessesModel(
                    resultSet.getInt("Id_success"), resultSet.getString("Team_Name"), 
                    resultSet.getString("Season"), resultSet.getString("Success"),
                    resultSet.getInt("Id_type_success")
                );
                successes.add(successesModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * loadTeamSuccesses()
    * @param searchText
    * Metoda pobierajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void loadTeamSuccesses(String searchText){
        try {
            successes = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT Id_success, Team_Name, Season, Success, Id_type_success FROM Team join Successes using(Id_team), Type_Of_Success using(Id_success) WHERE Team_NAME='"+searchText+"';");
            
            while(resultSet.next()){
                SuccessesModel successesModel = new SuccessesModel(
                    resultSet.getInt("Id_success"), resultSet.getString("Team_Name"), 
                    resultSet.getString("Season"), resultSet.getString("Success"),
                    resultSet.getInt("Id_type_success")
                );
                successes.add(successesModel);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby wykonania zapytania do bazy danych! Prosze sprawdzic polaczenie z baza!");
            alert.showAndWait();
        } 
    }
    
    /**
    * insertTeam()
    * @param teamname
    * @param established 
    * @param colors
    * @param nation 
    * Metoda dodajaca informacje do konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void insertTeam(String teamname, String established, String colors, String nation) {
        // wykonywanie zapytan INSERT
        try {
            int wynikUpdate = statement.executeUpdate("INSERT INTO Team VALUES(null, '"+teamname+"', "+established+", '"+colors+"' ,'"+nation+"');");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * insertPlayer()
    * @param name
    * @param surname 
    * @param dob
    * @param height 
    * @param weight
    * @param nation 
    * @param position
    * @param number
    * @param datedebut 
    * @param idteam 
    * Metoda dodajaca informacje do konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void insertPlayer(String name, String surname, String dob, String height, String weight, String nation, String position, String number, String datedebut, String idteam) {
        // wykonywanie zapytan INSERT
        try {
            int wynikUpdate = statement.executeUpdate("INSERT INTO Players VALUES(null, '"+name+"', '"+surname+"', '"+dob+"', '"+height+"', '"+weight+"', '"+nation+"', '"+position+"', '"+number+"', '"+datedebut+"', "+idteam+");");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * insertCoach()
    * @param idteam 
    * @param name
    * @param surname 
    * @param dob
    * @param nation
    * Metoda dodajaca informacje do konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void insertCoach(String idteam, String name, String surname, String dob, String nation) {
        // wykonywanie zapytan INSERT
        try {
            int wynikUpdate = statement.executeUpdate("INSERT INTO Coach VALUES("+idteam+", '"+name+"', '"+surname+"', '"+dob+"', '"+nation+"');");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * insertStadium()
    * @param idteam 
    * @param name
    * @param yoc 
    * @param capacitystands
    * Metoda dodajaca informacje do konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void insertStadium(String idteam, String name, String yoc, String capacitystands) {
        // wykonywanie zapytan INSERT
        try {
            int wynikUpdate = statement.executeUpdate("INSERT INTO Stadiums VALUES("+idteam+", '"+name+"', "+yoc+", "+capacitystands+");");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
        
    /**
    * insertSuccess()
    * @param id 
    * @param season
    * @param typeofsucces 
    * @param idteam
    * Metoda dodajaca informacje do konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void insertSuccess(String id, String season, String typeofsucces, String idteam) {
        // wykonywanie zapytan INSERT
        try {
            int wynikUpdate = statement.executeUpdate("INSERT INTO Successes VALUES("+id+", '"+season+"', "+idteam+"); INSERT INTO Type_Of_Success VALUES(null, '"+typeofsucces+"', "+id+");");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateTeamName()
    * @param tname 
    * @param tid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateTeamName(String tname, String tid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Team SET Team_Name='"+tname+"' WHERE Id_Team="+tid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateTeamEstablished()
    * @param est 
    * @param tid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateTeamEstablished(String est, String tid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Team SET Established="+est+" WHERE Id_Team="+tid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateTeamColors()
    * @param colors 
    * @param tid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateTeamColors(String colors, String tid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Team SET Colors='"+colors+"' WHERE Id_Team="+tid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!"+ex.getMessage());
            alert.showAndWait();
        }
    }
    
    /**
    * updateTeamCountry()
    * @param country 
    * @param tid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateTeamCountry(String country, String tid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Team SET Country='"+country+"' WHERE Id_Team="+tid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerIdTeam()
    * @param idteam 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerIdTeam(String idteam, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Id_team="+idteam+" WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerName()
    * @param name 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerName(String name, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Name='"+name+"' WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerSurname()
    * @param surname 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerSurname(String surname, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Surname='"+surname+"' WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerDOB()
    * @param dob 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerDOB(String dob, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Date_Of_Birth='"+dob+"' WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerHeight()
    * @param height 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerHeight(String height, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Height="+height+" WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerWeight()
    * @param weight 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerWeight(String weight, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Weight="+weight+" WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerNation()
    * @param nation 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerNation(String nation, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Nationality='"+nation+"' WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerPosition()
    * @param position 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerPosition(String position, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Position='"+position+"' WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerNumber()
    * @param number 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerNumber(String number, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Number="+number+" WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updatePlayerDD()
    * @param dd 
    * @param pid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updatePlayerDD(String dd, String pid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Players SET Date_Debut='"+dd+"' WHERE Id_Player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateCoachName()
    * @param name 
    * @param cid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateCoachName(String name, String cid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Coach SET Name='"+name+"' WHERE Id_Team="+cid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateCoachSurname()
    * @param surname 
    * @param cid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateCoachSurname(String surname, String cid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Coach SET Surname='"+surname+"' WHERE Id_Team="+cid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateCoachDOB()
    * @param dob 
    * @param cid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateCoachDOB(String dob, String cid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Coach SET Date_Of_Birth='"+dob+"' WHERE Id_Team="+cid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateCoachNation()
    * @param nation 
    * @param cid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateCoachNation(String nation, String cid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Coach SET Nationality='"+nation+"' WHERE Id_Team="+cid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateStadiumName()
    * @param name 
    * @param sid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateStadiumName(String name, String sid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Stadiums SET Name='"+name+"' WHERE Id_Team="+sid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateStadiumYOC()
    * @param yoc 
    * @param sid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateStadiumYOC(String yoc, String sid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Stadiums SET Year_Of_Construction='"+yoc+"' WHERE Id_Team="+sid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * updateStadiumCStands()
    * @param cstands 
    * @param sid
    * Metoda aktualizujaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void updateStadiumCStands(String cstands, String sid) {
        // wykonywanie zapytan UPDATE
        try {
            int wynikUpdate = statement.executeUpdate("UPDATE Stadiums SET Capacity_Stands='"+cstands+"' WHERE Id_Team="+sid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * deleteFromTeam()
    * @param tid
    * Metoda  usuwajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void deleteFromTeam(String tid) {
        // wykonywanie zapytan DELETE
        try {
            int wynikUpdate = statement.executeUpdate("DELETE FROM Team WHERE Id_Team="+tid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * deleteFromPlayers()
    * @param pid
    * Metoda  usuwajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void deleteFromPlayers(String pid) {
        // wykonywanie zapytan DELETE
        try {
            int wynikUpdate = statement.executeUpdate("DELETE FROM Players WHERE Id_player="+pid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * deleteFromCoach()
    * @param cid
    * Metoda  usuwajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void deleteFromCoach(String cid) {
        // wykonywanie zapytan DELETE
        try {
            int wynikUpdate = statement.executeUpdate("DELETE FROM Coach WHERE Id_Team="+cid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * deleteFromStadiums()
    * @param sid
    * Metoda  usuwajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void deleteFromStadiums(String sid) {
        // wykonywanie zapytan DELETE
        try {
            int wynikUpdate = statement.executeUpdate("DELETE FROM Stadiums WHERE Id_Team="+sid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * deleteFromSuccess()
    * @param ssid
    * Metoda  usuwajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void deleteFromSuccess(String ssid) {
        // wykonywanie zapytan DELETE
        try {
            int wynikUpdate = statement.executeUpdate("DELETE FROM Successes WHERE Id_success="+ssid+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * deleteFromSuccessType()
    * @param ssidtype
    * Metoda  usuwajaca informacje z konkretnej tabeli
    * z bazy danych za pomoca jezyka SQL.
    * Obsługa wyjatkow za pomoca SQL Exception.
    */
    public void deleteFromSuccessType(String ssidtype) {
        // wykonywanie zapytan DELETE
        try {
            int wynikUpdate = statement.executeUpdate("DELETE FROM Type_Of_Success WHERE Id_type_success="+ssidtype+";");
            if (wynikUpdate > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Nie zmodyfikowano zadnego rekordu");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby aktualizacji bazy danych!");
            alert.showAndWait();
        }
    }
    
    /**
    * viewInfo()
    * Metoda pozwalajaca na zamkniecie aplikacji.
    */
    public void viewInfo(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("O Autorze:");
        alert.setHeaderText(null);
        alert.setContentText("Projekt zaliczeniowy z przedmiotu\nProgramowanie Obiektowe II"
                + "\n\n"
                + "Maciej Piotrowski\n"
                + "INFORMATYKA II rok");
        alert.showAndWait();
    }
    
    /**
    * closeApp()
    * Metoda pozwalajaca na zamkniecie aplikacji.
    */
    public void closeApp(){
        System.exit(0);
    }
}
