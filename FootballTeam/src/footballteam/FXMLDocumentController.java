package footballteam;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.xml.stream.XMLStreamException;

/**
 * FXMLDocumentController
 * Klasa implementujaca interfejs Initializable
 * sluzaca do przechowywania wszystkich
 * elementow zastosowanych przy projektowaniu programu
 * oraz zawierajaca metode initialize(),
 * ktora odpowaiada za obsluge tych elementow
 * @author maciejpiotrowski
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane details;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem connect;
    @FXML
    private MenuItem disconnect;
    @FXML
    private MenuItem infromations;
    @FXML
    private SplitPane team;
    @FXML
    private SplitPane players;
    @FXML
    private SplitPane coach;
    @FXML
    private SplitPane stadiums;
    @FXML
    private SplitPane successes;
    @FXML
    private Button changeTeam;
    @FXML
    private Button changePlayers;
    @FXML
    private Button changeCoach;
    @FXML
    private Button changeStadiums;
    @FXML
    private Button cahangeSuccesses;
    
    @FXML
    private Button proccedTeam;
    @FXML
    private TableView<TeamModel> tableTeam;
    @FXML
    private TableColumn<TeamModel, Integer> tId;
    @FXML
    private TableColumn<TeamModel, String> tTeamName;
    @FXML
    private TableColumn<TeamModel, Integer> tEstablished;
    @FXML
    private TableColumn<TeamModel, String> tColours;
    @FXML
    private TableColumn<TeamModel, String> tNationality;
    
    @FXML
    private Button proccedPlayers;
    @FXML
    private TableView<PlayersModel> tablePlayers;
    @FXML
    private TableColumn<PlayersModel, Integer> pId;
    @FXML
    private TableColumn<PlayersModel, String> pTeamName;
    @FXML
    private TableColumn<PlayersModel, String> pName;
    @FXML
    private TableColumn<PlayersModel, String> pSurname;
    @FXML
    private TableColumn<PlayersModel, String> pDOB;
    @FXML
    private TableColumn<PlayersModel, Integer> pHeight;
    @FXML
    private TableColumn<PlayersModel, Integer> pWeight;
    @FXML
    private TableColumn<PlayersModel, String> pNationality;
    @FXML
    private TableColumn<PlayersModel, String> pPosition;
    @FXML
    private TableColumn<PlayersModel, Integer> pNumber;
    @FXML
    private TableColumn<PlayersModel, String> pDateDebut;
    @FXML
    private TableColumn<PlayersModel, Integer> pIdTeam;
    
    @FXML
    private Button proccedCoach;
    @FXML
    private TableView<CoachModel> tableCoach;
    @FXML
    private TableColumn<CoachModel, Integer> cId;
    @FXML
    private TableColumn<CoachModel, String> cName;
    @FXML
    private TableColumn<CoachModel, String> cSurname;
    @FXML
    private TableColumn<CoachModel, String> cDOB;
    @FXML
    private TableColumn<CoachModel, String> cNationality;
    
    @FXML
    private Button proccedStadiums;
    @FXML
    private TableView<StadiumsModel> tableStadiums;
    @FXML
    private TableColumn<StadiumsModel, Integer> sId;
    @FXML
    private TableColumn<StadiumsModel, String> sName;
    @FXML
    private TableColumn<StadiumsModel, Integer> sYOC;
    @FXML
    private TableColumn<StadiumsModel, Integer> sCapacityStands;
    
    @FXML
    private Button proccedSuccesses;
    @FXML
    private TableView<SuccessesModel> tableSuccesses;
    @FXML
    private TableColumn<SuccessesModel, Integer> ssId;
    @FXML
    private TableColumn<SuccessesModel, String> ssTeamName;
    @FXML
    private TableColumn<SuccessesModel, String> ssSeason;
    @FXML
    private TableColumn<SuccessesModel, String> ssSuccess;
    @FXML
    private TableColumn<SuccessesModel, Integer> ssIdTypeSuccess;
    @FXML
    public TextField searchTeam;
    @FXML
    private Button proccedSearchT;
    @FXML
    private TextField searchSuccess;
    @FXML
    private Button proccedSearchSuccess;
    
    @FXML
    private TextField addSTId;
    @FXML
    private TextField addSeason;
    @FXML
    private TextField addSId;
    @FXML
    private TextField addTOS;
    @FXML
    private Button proccedAddSuccess;
    
    @FXML
    private TextField addTeamName;
    @FXML
    private TextField addColors;
    @FXML
    private TextField addNation;
    @FXML
    private TextField addEstablished;
    @FXML
    private Button proccedAddTeam;
    
    @FXML
    private TextField searchPlayers;
    @FXML
    private Button proccedSearchPlayers;
    
    @FXML
    private TextField addPHeight;
    @FXML
    private TextField addPNationality;
    @FXML
    private TextField addPDOB;
    @FXML
    private TextField addPSurname;
    @FXML
    private TextField addPName;
    @FXML
    private TextField addPPosition;
    @FXML
    private TextField addPNumber;
    @FXML
    private TextField addPDD;
    @FXML
    private TextField addPTId;
    @FXML
    private Button proccedAddPlayer;
    @FXML
    private TextField addPWeight;
    
    @FXML
    private TextField addCTId;
    @FXML
    private TextField addCName;
    @FXML
    private TextField addCSurname;
    @FXML
    private TextField addCDOB;
    @FXML
    private TextField addCNation;
    @FXML
    private Button proccedAddCoach;
    
    @FXML
    private TextField AddSTId;
    @FXML
    private TextField addSName;
    @FXML
    private TextField addSYOC;
    @FXML
    private TextField addSCStands;
    @FXML
    private Button proccedAddStadium;
    
    @FXML
    private TextField updatePTId;
    @FXML
    private TextField updatePName;
    @FXML
    private TextField updatePSurname;
    @FXML
    private Button proccedUpdatePTId;
    @FXML
    private Button proccedUpdatePName;
    @FXML
    private Button proccedUpdatePSurname;
    
    @FXML
    private TextField updatePDOB;
    @FXML
    private TextField updatePHeight;
    @FXML
    private TextField updatePWeight;
    @FXML
    private Button proccedUpdatePWeight;
    @FXML
    private Button proccedUpdatePHeight;
    @FXML
    private Button proccedUpdatePDOB;
    
    @FXML
    private TextField updatePNationality;
    @FXML
    private TextField updatePPosition;
    @FXML
    private TextField updatePNumber;
    @FXML
    private Button proccedUpdatePNation;
    @FXML
    private Button proccedUpdatePPosition;
    @FXML
    private Button proccedUpdatePNumber;
    
    @FXML
    private TextField updatePDD;
    @FXML
    private Button proccedUpdatePDD;
    @FXML
    private TextField selectPId;
    
    @FXML
    private TextField selectTId;
    @FXML
    private Button proccedUpdateTColors;
    @FXML
    private Button proccedUpdateTCountry;
    
    @FXML
    private TextField updateTCountry;
    @FXML
    private TextField updateTColors;
    @FXML
    private Button proccedUpdateTEstablished;
    
    @FXML
    private Button proccedUpdateTName;
    @FXML
    private TextField updateTEstablished;
    @FXML
    private TextField updateTName;
    
    @FXML
    private TextField updateCName;
    @FXML
    private TextField updateCSurname;
    @FXML
    private Button proccedUpdateCName;
    
    @FXML
    private Button proccedUpdateCSurname;
    @FXML
    private TextField updateCDOB;
    @FXML
    private TextField updateCNationality;
    
    @FXML
    private Button proccedUpdateCNationality;
    @FXML
    private Button proccedUpdateCDOB;
    @FXML
    private TextField selectCId;
    
    @FXML
    private TextField selectSId;
    @FXML
    private Button proccedUpdateSYOC;
    @FXML
    private Button proccedUpdateSCStands;
    @FXML
    private TextField updateSCStands;
    @FXML
    private TextField updateSYOC;
    @FXML
    private Button proccedUpdateSName;
    @FXML
    private TextField updateSName;
    
    @FXML
    private Button proccedDeletePlayer;
    @FXML
    private TextField deletePlayer;
    @FXML
    private Button proccedDeleteCoach;
    @FXML
    private TextField deleteCoach;
    @FXML
    private TextField deleteStadium;
    @FXML
    private Button proccedDeleteStadium;
    @FXML
    private TextField deleteSuccess;
    @FXML
    private TextField deleteSuccessIdType;
    @FXML
    private Button procceddeleteSuccess;
    @FXML
    private Button procceddeleteSuccessType;
    @FXML
    private TextField deleteTeam;
    @FXML
    private Button proccedDeleteTeam;
    
    @FXML
    private Button runParser;
    @FXML
    private Button disconnectDB;
    @FXML
    private Button connectDB;
    @FXML
    private Button closeApplication;
    
    /**
     * initialize()
     * @param url
     * @param rb 
     * Metoda inicjalizujaca wszystkie
     * zdarzenia wykonywane w oknie programu
     * za pomoaca przyciskow oraz innych elementow
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disconnect.setDisable(true);
        
        connect.setOnAction(event -> {connect.setDisable(true); disconnect.setDisable(false); connectDB.setVisible(false); disconnectDB.setVisible(true); DataBaseController execute = new DataBaseController();
            execute.connectDataBase();
        });
        
        disconnect.setOnAction(event -> {connect.setDisable(false); disconnect.setDisable(true); connectDB.setVisible(true); disconnectDB.setVisible(false); DataBaseController execute = new DataBaseController();
            execute.disconnectDataBase();
        });
        
        connectDB.setOnAction(event -> {connect.setDisable(true); disconnect.setDisable(false); connectDB.setVisible(false); disconnectDB.setVisible(true); DataBaseController execute = new DataBaseController();
            execute.connectDataBase();
        });
        
        disconnectDB.setOnAction(event -> {connect.setDisable(false); disconnect.setDisable(true); connectDB.setVisible(true); disconnectDB.setVisible(false);DataBaseController execute = new DataBaseController();
            execute.disconnectDataBase();
        });
        
        closeApplication.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.closeApp();
        });
        
        close.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.closeApp();
        });
        
        infromations.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.viewInfo();
        });
        
        runParser.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            try {
                execute.runParserXML();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        proccedTeam.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadTeam();
            tId.setCellValueFactory(new PropertyValueFactory<>("Id_team"));
            tTeamName.setCellValueFactory(new PropertyValueFactory<>("Team_Name"));
            tEstablished.setCellValueFactory(new PropertyValueFactory<>("Established"));
            tColours.setCellValueFactory(new PropertyValueFactory<>("Colors"));
            tNationality.setCellValueFactory(new PropertyValueFactory<>("Country"));
            tableTeam.setItems(DataBaseController.team);
        });
        
        proccedSearchT.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadTeamName(searchTeam.getText());
            tId.setCellValueFactory(new PropertyValueFactory<>("Id_team"));
            tTeamName.setCellValueFactory(new PropertyValueFactory<>("Team_Name"));
            tEstablished.setCellValueFactory(new PropertyValueFactory<>("Established"));
            tColours.setCellValueFactory(new PropertyValueFactory<>("Colors"));
            tNationality.setCellValueFactory(new PropertyValueFactory<>("Country"));
            tableTeam.setItems(DataBaseController.team);
        });
        
        proccedPlayers.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadPlayers();
            pId.setCellValueFactory(new PropertyValueFactory<>("Id_player"));
            pTeamName.setCellValueFactory(new PropertyValueFactory<>("Team_Name"));
            pName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            pSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            pDOB.setCellValueFactory(new PropertyValueFactory<>("Date_Of_Birth"));
            pHeight.setCellValueFactory(new PropertyValueFactory<>("Height"));
            pWeight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
            pNationality.setCellValueFactory(new PropertyValueFactory<>("Nationality"));
            pPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
            pNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
            pDateDebut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
            pIdTeam.setCellValueFactory(new PropertyValueFactory<>("Id_team"));
            tablePlayers.setItems(DataBaseController.players);
        });
        
        proccedSearchPlayers.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadSearchPlayers(searchPlayers.getText());
            pId.setCellValueFactory(new PropertyValueFactory<>("Id_player"));
            pTeamName.setCellValueFactory(new PropertyValueFactory<>("Team_Name"));
            pName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            pSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            pDOB.setCellValueFactory(new PropertyValueFactory<>("Date_Of_Birth"));
            pHeight.setCellValueFactory(new PropertyValueFactory<>("Height"));
            pWeight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
            pNationality.setCellValueFactory(new PropertyValueFactory<>("Nationality"));
            pPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
            pNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
            pDateDebut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
            pIdTeam.setCellValueFactory(new PropertyValueFactory<>("Id_team"));
            tablePlayers.setItems(DataBaseController.players);
        });
        
        proccedCoach.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadCoach();
            cId.setCellValueFactory(new PropertyValueFactory<>("Id_team"));
            cName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            cSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            cDOB.setCellValueFactory(new PropertyValueFactory<>("Date_Of_Birth"));
            cNationality.setCellValueFactory(new PropertyValueFactory<>("Nationality"));
            tableCoach.setItems(DataBaseController.coach);
        });
        
        proccedStadiums.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadStadiums();
            sId.setCellValueFactory(new PropertyValueFactory<>("Id_team"));
            sName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            sYOC.setCellValueFactory(new PropertyValueFactory<>("Year_Of_Construction"));
            sCapacityStands.setCellValueFactory(new PropertyValueFactory<>("Capacity_Stands"));
            tableStadiums.setItems(DataBaseController.stadiums);
        });
        
        proccedSuccesses.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadSuccesses();
            ssId.setCellValueFactory(new PropertyValueFactory<>("Id_success"));
            ssTeamName.setCellValueFactory(new PropertyValueFactory<>("Team_Name"));
            ssSeason.setCellValueFactory(new PropertyValueFactory<>("Season"));
            ssSuccess.setCellValueFactory(new PropertyValueFactory<>("Success"));
            ssIdTypeSuccess.setCellValueFactory(new PropertyValueFactory<>("Id_type_success"));
            tableSuccesses.setItems(DataBaseController.successes);
        });
        
        proccedSearchSuccess.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.loadTeamSuccesses(searchSuccess.getText());
            ssId.setCellValueFactory(new PropertyValueFactory<>("Id_success"));
            ssTeamName.setCellValueFactory(new PropertyValueFactory<>("Team_Name"));
            ssSeason.setCellValueFactory(new PropertyValueFactory<>("Season"));
            ssSuccess.setCellValueFactory(new PropertyValueFactory<>("Success"));
            ssIdTypeSuccess.setCellValueFactory(new PropertyValueFactory<>("Id_type_success"));
            tableSuccesses.setItems(DataBaseController.successes);
        });
        
        proccedAddTeam.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.insertTeam(addTeamName.getText(), addEstablished.getText(), addColors.getText(), addNation.getText());
        });
        
        proccedAddPlayer.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.insertPlayer(addPName.getText(), addPSurname.getText(), addPDOB.getText(), addPHeight.getText(), addPWeight.getText(), addPNationality.getText(), addPPosition.getText(), addPNumber.getText(), addPDD.getText(), addPTId.getText());
        });
        
        proccedAddCoach.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.insertCoach(addCTId.getText(), addCName.getText(), addCSurname.getText(), addCDOB.getText(), addCNation.getText());
        });
        
        proccedAddStadium.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.insertStadium(AddSTId.getText(), addSName.getText(), addSYOC.getText(), addSCStands.getText());
        });
        
        proccedAddSuccess.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.insertSuccess(addSId.getText(), addSeason.getText(), addTOS.getText(), addSTId.getText());
        });
        
        proccedUpdateTName.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateTeamName(updateTName.getText(), selectTId.getText());
        });
        proccedUpdateTEstablished.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateTeamEstablished(updateTEstablished.getText(), selectTId.getText());
        });
        proccedUpdateTColors.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateTeamColors(updateTColors.getText(), selectTId.getText());
        });
        proccedUpdateTCountry.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateTeamCountry(updateTCountry.getText(), selectTId.getText());
        });
        
        proccedUpdatePTId.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerIdTeam(updatePTId.getText(), selectPId.getText());
        });
        proccedUpdatePName.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerName(updatePName.getText(), selectPId.getText());
        });
        proccedUpdatePSurname.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerSurname(updatePSurname.getText(), selectPId.getText());
        });
        proccedUpdatePDOB.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerDOB(updatePDOB.getText(), selectPId.getText());
        });
        proccedUpdatePHeight.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerHeight(updatePHeight.getText(), selectPId.getText());
        });
        proccedUpdatePWeight.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerWeight(updatePWeight.getText(), selectPId.getText());
        });
        proccedUpdatePNation.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerNation(updatePNationality.getText(), selectPId.getText());
        });
        proccedUpdatePPosition.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerPosition(updatePPosition.getText(), selectPId.getText());
        });
        proccedUpdatePNumber.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerNumber(updatePNumber.getText(), selectPId.getText());
        });
        proccedUpdatePDD.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updatePlayerDD(updatePDD.getText(), selectPId.getText());
        });
        
        
        proccedUpdateCName.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateCoachName(updateCName.getText(), selectCId.getText());
        });
        proccedUpdateCSurname.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateCoachSurname(updateCSurname.getText(), selectCId.getText());
        });
        proccedUpdateCDOB.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateCoachDOB(updateCDOB.getText(), selectCId.getText());
        });
        proccedUpdateCNationality.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateCoachNation(updateCNationality.getText(), selectCId.getText());
        });
                
        proccedUpdateSName.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateStadiumName(updateSName.getText(), selectSId.getText());
        });
        proccedUpdateSYOC.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateStadiumYOC(updateSYOC.getText(), selectSId.getText());
        });
        proccedUpdateSCStands.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.updateStadiumCStands(updateSCStands.getText(), selectSId.getText());
        });
        
        proccedDeleteTeam.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.deleteFromTeam(deleteTeam.getText());
        });
        proccedDeletePlayer.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.deleteFromPlayers(deletePlayer.getText());
        });
        proccedDeleteCoach.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.deleteFromCoach(deleteCoach.getText());
        });
        proccedDeleteStadium.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.deleteFromStadiums(deleteStadium.getText());
        });
        procceddeleteSuccess.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.deleteFromSuccess(deleteSuccess.getText());
        });
        procceddeleteSuccessType.setOnAction(event -> {DataBaseController execute = new DataBaseController();
            execute.deleteFromSuccessType(deleteSuccessIdType.getText());
        });
               
        changeTeam.setOnAction(event -> {team.setVisible(true); players.setVisible(false); coach.setVisible(false); stadiums.setVisible(false); successes.setVisible(false);});
        changePlayers.setOnAction(event -> {team.setVisible(false); players.setVisible(true); coach.setVisible(false); stadiums.setVisible(false); successes.setVisible(false);});
        changeCoach.setOnAction(event -> {team.setVisible(false); players.setVisible(false); coach.setVisible(true); stadiums.setVisible(false); successes.setVisible(false);});
        changeStadiums.setOnAction(event -> {team.setVisible(false); players.setVisible(false); coach.setVisible(false); stadiums.setVisible(true); successes.setVisible(false);});
        cahangeSuccesses.setOnAction(event -> {team.setVisible(false); players.setVisible(false); coach.setVisible(false); stadiums.setVisible(false); successes.setVisible(true);});
    }    
}
