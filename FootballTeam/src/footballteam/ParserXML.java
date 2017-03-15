package footballteam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javafx.scene.control.Alert;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * ParserXML
 * Klasa zawierajaca zmienne oraz metody odpwiadajace
 * za wczytanie danych do bazy danych za pomoca zdefiniowanego w tej klasie 
 * specjalnego narzedzia, jakim jest parser plikow XML.
 * @author maciejpiotrowski
 */
public class ParserXML {
    public static int result=0;
    /**
     * run
     * Metoda parsujaca plik xml, przetwarzajaca pozyskane dane
     * @param filePath Sciezka pliku xml.
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public static void run(File filePath) throws FileNotFoundException, XMLStreamException {
        // tworzenie parsera
        XMLInputFactory iFactory = XMLInputFactory.newInstance();
        InputStream xmlFile = new FileInputStream(filePath);
        XMLStreamReader parser = iFactory.createXMLStreamReader(xmlFile);

        // Arraylisty tagow i odpowiadajacych im wartosci
        ArrayList<String> tags = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();

        // dopoki masz nastepny element ...
        while (parser.hasNext()) {
            // jesli jest to ... , wowczas ...
            switch (parser.next()) {
                // Start Element //
                case XMLStreamConstants.START_ELEMENT:
                    // tagi 'glowne' //

                    if (parser.getLocalName().equals("Team")) {
                        tags.add("Team");
                        values.add("Team");
                    }
                    if (parser.getLocalName().equals("Players")) {
                        tags.add("Players");
                        values.add("Players");
                    }
                    if (parser.getLocalName().equals("Coach")) {
                        tags.add("Coach");
                        values.add("Coach");
                    }
                    if (parser.getLocalName().equals("Stadiums")) {
                        tags.add("Stadiums");
                        values.add("Stadiums");
                    }
                    if (parser.getLocalName().equals("Successes")) {
                        tags.add("Successes");
                        values.add("Successes");
                    }
                    if (parser.getLocalName().equals("Type_Of_Success")) {
                        tags.add("Type_Of_Success");
                        values.add("Type_Of_Success");
                    }
                    
                    // 'podtagi' //
                    // Team //
                    if (parser.getLocalName().equals("Id_t")) {
                        tags.add("Id_team");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("Team_Name")) {
                        tags.add("Team_Name");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Established")) {
                        tags.add("Established");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("Colors")) {
                        tags.add("Colors");
                        values.add("'" + parser.getElementText() + "'");;
                    }
                    if (parser.getLocalName().equals("TNationality")) {
                        tags.add("Country");
                        values.add("'" + parser.getElementText() + "'");;
                    }
                    
                    // Players //
                    if (parser.getLocalName().equals("Id_player")) {
                        tags.add("Id_player");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("PName")) {
                        tags.add("Name");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("PSurname")) {
                        tags.add("Surname");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("PDate_Of_Birth")) {
                        tags.add("Date_Of_Birth");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Height")) {
                        tags.add("Height");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("Weight")) {
                        tags.add("Weight");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("PNationality")) {
                        tags.add("Nationality");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Position")) {
                        tags.add("Position");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Number")) {
                        tags.add("Number");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("Date_Debut")) {
                        tags.add("Date_Debut");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Id_tp")) {
                        tags.add("Id_team");
                        values.add(parser.getElementText());
                    }
                    
                    // Coach //
                    if (parser.getLocalName().equals("Id_tc")) {
                        tags.add("Id_team");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("CName")) {
                        tags.add("Name");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("CSurname")) {
                        tags.add("Surname");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("CDate_Of_Birth")) {
                        tags.add("Date_Of_Birth");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("CNationality")) {
                        tags.add("Nationality");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    
                    // Stadiums //
                    if (parser.getLocalName().equals("Id_ts")) {
                        tags.add("Id_team");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("SName")) {
                        tags.add("Name");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Year_Of_Construction")) {
                        tags.add("Year_Of_Construction");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("Capacity_Stands")) {
                        tags.add("Capacity_Stands");
                        values.add(parser.getElementText());
                    }
                    
                    // Successes //
                    if (parser.getLocalName().equals("Id_success")) {
                        tags.add("Id_success");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("Season")) {
                        tags.add("Season");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Id_tss")) {
                        tags.add("Id_team");
                        values.add(parser.getElementText());
                    }
                    
                    // Type_Of_Success //
                    if (parser.getLocalName().equals("Id_type_success")) {
                        tags.add("Id_type_success");
                        values.add(parser.getElementText());
                    }
                    if (parser.getLocalName().equals("Success")) {
                        tags.add("Success");
                        values.add("'" + parser.getElementText() + "'");
                    }
                    if (parser.getLocalName().equals("Id_s")) {
                        tags.add("Id_success");
                        values.add(parser.getElementText());
                    }
                    break;
                // End Element //
                case XMLStreamConstants.END_ELEMENT:

                    if (parser.getLocalName().equals("Team")
                            || parser.getLocalName().equals("Players")
                            || parser.getLocalName().equals("Coach")
                            || parser.getLocalName().equals("Stadiums")
                            || parser.getLocalName().equals("Successes")
                            || parser.getLocalName().equals("Type_Of_Success")) {
                        String zapytania = "INSERT INTO ";

                        if (values.get(0).equals("Team")) {

                            zapytania += values.get(0);
                            zapytania += " (" + tags.get(1) + ", " + tags.get(2) + ", " + tags.get(3) + ", " + tags.get(4) + ", " + tags.get(5) + ") VALUES (";
                            zapytania += values.get(1) + ", " + values.get(2) + ", " + values.get(3) + ", " + values.get(4) + ", " + values.get(5) + ");";

                        }
                        if (values.get(0).equals("Players")) {

                            zapytania += values.get(0);
                            zapytania += " (" + tags.get(1) + ", " + tags.get(2) + ", " + tags.get(3) + ", " + tags.get(4) + ", " + tags.get(5) + ", " + tags.get(6) + ", " + tags.get(7) + ", " + tags.get(8) + ", " + tags.get(9) + ", " + tags.get(10) + ", " + tags.get(11) + ") VALUES (";
                            zapytania += values.get(1) + ", " + values.get(2) + ", " + values.get(3) + ", " + values.get(4) + ", " + values.get(5) + ", " + values.get(6) + ", " + values.get(7) + ", " + values.get(8) + ", " + values.get(9) + ", " + values.get(10) + ", " + values.get(11) + ");";

                        }
                        
                        if (values.get(0).equals("Coach")) {

                            zapytania += values.get(0);
                            zapytania += " (" + tags.get(1) + ", " + tags.get(2) + ", " + tags.get(3) + ", " + tags.get(4) + ", " + tags.get(5) + ") VALUES (";
                            zapytania += values.get(1) + ", " + values.get(2) + ", " + values.get(3) + ", " + values.get(4) + ", " + values.get(5) + ");";

                        }
                        
                        if (values.get(0).equals("Stadiums")) {

                            zapytania += values.get(0);
                            zapytania += " (" + tags.get(1) + ", " + tags.get(2) + ", " + tags.get(3) + ", " + tags.get(4) + ") VALUES (";
                            zapytania += values.get(1) + ", " + values.get(2) + ", " + values.get(3) + ", " + values.get(4) + ");";

                        }
                        
                        if (values.get(0).equals("Successes") || values.get(0).equals("Type_Of_Success")) {

                            zapytania += values.get(0);
                            zapytania += " (" + tags.get(1) + "," + tags.get(2) + "," + tags.get(3) + ") VALUES (";
                            zapytania += values.get(1) + "," + values.get(2) + ", " + values.get(3) + ");";

                        }

                        //System.out.println(zapytania);

                        execute(zapytania);
                        tags.clear();
                        values.clear();
                        //System.out.println();
                    }
                    break;
                // End Document //
                case XMLStreamConstants.END_DOCUMENT:
                    //System.out.println("Koniec");
                    break;
            }
        }
    }
    
    /**
     * execute()
     * Metoda umozliwiajaca wprowadzenie wczytanych danych z pliku do bazy danych
     * @param query 
     */
    public static void execute(String query) {
        Connection connection = null;
        Statement statement = null;

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
            connection = DriverManager.getConnection("jdbc:sqlite:FootballTeam.db");
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil blad podczas proby nawiazania polaczenia z baza danych!");
            alert.showAndWait();
        }

        StringTokenizer st = new StringTokenizer(query, " ");
        String firstWord = st.nextToken().toUpperCase();

        if (firstWord.equals("INSERT")) {
            // wykonywanie zapytania INSERT do bazy
            try {
                int wynikUpdate = statement.executeUpdate(query);
                result += wynikUpdate;
                
            } catch (SQLException ex) {
                result = 0;
            }
        }
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {;}   
        
    }
    
    /**
     * results()
     * Metoda przechowujaca informacje na temat wykonywania operacji 
     * importu danych do bazy
     */
    public static void results(){
        System.out.println(result);
        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Pomyslnie zaimportowano dane do bazy!\nLiczba zmodyfikowanych rekordow: "+result);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wystapil problem podczas importu danych!\n"
                    + "Baza moze zawierać juz takie dane!");
            alert.showAndWait();
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Informacja");
            alert2.setHeaderText(null);
            alert2.setContentText("Nie zmodyfikowano zadnego rekordu");
            alert2.showAndWait();
        }
    }
}
