package com.example.chess360.dao;

import com.example.chess360.vo.*;

import java.util.ArrayList;

public abstract class Dao {

    private static int current_id_user = 1;
    private static int current_id_tournament = 1;
    private static int current_id_game = 1;

    // Checks whether the arraylists have been initialized:
    private static boolean initialized = false;

    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Player> players = new ArrayList<>();
    private static final ArrayList<Club> clubs = new ArrayList<>();
    private static final ArrayList<Organizer> organizers = new ArrayList<>();
    private static final ArrayList<Tournament> tournaments = new ArrayList<>();
    private static final ArrayList<Game> games = new ArrayList<>();
    private static final ArrayList<TournamentRecord> tournamentRecords = new ArrayList<>();

    // Users
    public static void addUser(User newUser){
        users.add(newUser);
    }

    public static ArrayList<User> getUsers(){
        return(users);
    }

    public static int getUser(User user){
        return(users.indexOf(user));
    }

    // Players
    public static void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }

    public static ArrayList<Player> getPlayers(){
        return(players);
    }

    public static int getPlayerIndex(Player player){
        return(players.indexOf(player));
    }

    public static Player getPlayer(String name){

        Player newPlayer = new Player(name);
        int index = Dao.getPlayerIndex(newPlayer);

        Player myPlayer = null;

        if (index != -1){

            myPlayer = Dao.getPlayers().get(index);
        }

        return myPlayer;
    }

    // Clubs
    public static void addClub(Club newClub){
        clubs.add(newClub);
    }

    public static ArrayList<Club> getClubs(){
        return(clubs);
    }

    public static int getClub(Club club){
        return(clubs.indexOf(club));
    }

    // Organizers
    public static void addOrganizer(Organizer newOrganizer){
        organizers.add(newOrganizer);
    }

    public static ArrayList<Organizer> getOrganizers(){
        return(organizers);
    }

    public static int getOrganizer(Organizer organizer){
        return(organizers.indexOf(organizer));
    }

    // Tournaments
    public static void addTournament(Tournament newTournament){
        tournaments.add(newTournament);
    }

    public static ArrayList<Tournament> getTournaments(){
        return(tournaments);
    }

    public static int getTournament(Tournament tournament){
        return(tournaments.indexOf(tournament));
    }

    // Games
    public static void addGame(Game newGame){
        games.add(newGame);
    }

    public static ArrayList<Game> getGames(){
        return(games);
    }

    public static int getGame(Game game){
        return(games.indexOf(game));
    }

    // Tournament records
    public static void addTournamentRecord(TournamentRecord newTournamentRecord){
        tournamentRecords.add(newTournamentRecord);
    }

    public static ArrayList<TournamentRecord> getTournamentRecords(){
        return(tournamentRecords);
    }

    public static int getTournamentRecord(TournamentRecord tournamentRecord){
        return(tournamentRecords.indexOf(tournamentRecord));
    }

    public static int generateID_User(){
        Dao.current_id_user++;
        return Dao.current_id_user -1;
    }

    public static int generateID_Tournament(){
        Dao.current_id_tournament++;
        return Dao.current_id_tournament -1;
    }

    public static int generateID_Game(){
        Dao.current_id_game++;
        return Dao.current_id_game -1;
    }

    /********************************************************************************************/

    public static void initialize(){

        if (!Dao.initialized){

            Dao.initialized = true;

            // Add several users:
            Player myPlayer = new Player("Martín", "Mato Búa", "martincheckmate",
                    "martinmb.dam@gmail.com", 2000, "whvw");
            Club myClub = new Club("Club de Ajedrez Maracena", "camaracena", "Granada", "686626393",
                    "caMaracena@gmail.com", "Whvw4567&");
            Organizer myOrganizer = new Organizer("Abanca", "abanca", "Santiago de Compostela",
                    "986180760", "abanca@gmail.com", "Whvw4567&");

            Dao.addPlayer(myPlayer);
            Dao.addClub(myClub);
            Dao.addOrganizer(myOrganizer);

            Dao.addUser(myPlayer);
            Dao.addUser(myClub);
            Dao.addUser(myOrganizer);

            // Usuario para hacer pruebas:
            Player test = new Player("test","test test", "test","fakeMail@test.com", 1500,"whvw");
            Dao.addUser(test);
            Dao.addPlayer(test);
        }
    }

    public static boolean isPlayer(String user){
        return Dao.getPlayerIndex(new Player(user)) != -1;
    }

    public static boolean isClub(String user){
        return Dao.getClub(new Club(user)) != -1;
    }

    public static boolean isOrganizer(String user){
        return Dao.getOrganizer(new Organizer(user)) != -1;
    }

}
