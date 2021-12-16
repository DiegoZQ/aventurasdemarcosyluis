package game;

import animantia.*;
import interfaces.*;
import items.ItemEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static animantia.PlayerAttackType.MARTILLO;
import static animantia.PlayerAttackType.SALTO;
import static items.ItemEnum.HONEYSYRUP;
import static items.ItemEnum.REDMUSHROOM;

/**
 * GameController is the most important class in this game project. It uses all others classes
 * to perform a battle between Players and Enemies.
 *
 * @author Diego Zuniga.
 */
public class GameController {

    /**
     * List Of Goombas and Spinies.
     */
    private List<AttackableByLuis> listOfGSs;

    private List<Boo> listOfBoos;

    private int turn;

    private int phaseTurn;

    /**
     * Actual game phase.
     */
    private Phase phase;

    /**
     * Phases in the game.
     */
    private Phases phases;

    private String winner = "Nobody";

    private Round round;

    private Rounds rounds;

    /**
     * Move reader
     */
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private PrintStream out = System.out;

    /**
     * Move chosen by players or script.
     */
    private String move;

    /**
     * Int number that determines the number of times the player has passed.
     * If it's over 3, the game ends.
     */
    private int losePoints;

    /**
     * Creates a GameController, initializing lists, setting phases, rounds and turns.
     */
    public GameController() {
        listOfGSs = new ArrayList<>();
        listOfBoos = new ArrayList<>();
        turn = 0;
        phaseTurn = 0;
        losePoints = 0;
        setChest();
        setPhases(new Phases());
        setRounds(new Rounds());
        setRound(rounds.getNextRound());
    }

    /**
     * Sets the phase and configures the controller.
     *
     * @param phase Phase to set.
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    /**
     * Sets the phases and configures the controller.
     *
     * @param phases Phases to set.
     */
    public void setPhases(Phases phases){
        this.phases = phases;
        phases.setController(this);
    }

    /**
     * Sets the round and configures the controller.
     *
     * @param round Round to set.
     */
    public void setRound(Round round){
        this.round = round;
        round.setController(this);
        round.playRound();
    }

    /**
     * Gets the actual round number.
     *
     * @return the actual round number.
     */
    public int getRound(){
        return rounds.getRoundIndex()+1;
    }

    /**
     * Sets the rounds and configures the controller.
     *
     * @param rounds Rounds to set.
     */
    public void setRounds(Rounds rounds){
        this.rounds = rounds;
    }

    /**
     * Gets the turn.
     *
     * @return the turn.
     */
    public int getTurn(){
        return turn;
    }
    /**
     * Gets the phase turn.
     *
     * @return the phase turn.
     */
    public int getPhaseTurn(){
        return phaseTurn;
    }

    /**
     * Generates a Marcos.
     *
     * @return Marcos generated.
     */
    public Marcos generateMarcos() {
        return Marcos.getInstance();
    }

    /**
     * Generates a Luis.
     *
     * @return Luis generated.
     */
    public Luis generateLuis() {
        return Luis.getInstance();
    }

    /**
     * Reset Marcos and Luis back to level 1 and its base stats.
     * It also resets the Players' inventory.
     */
    public void resetPlayers(){
        Marcos.reset();
        Luis.reset();
        AbstractPlayer.resetChest();
    }

    /**
     * Erases all remaining enemies in the round.
     */
    public void eraseEnemies(){
        listOfGSs = new ArrayList<>();
        listOfBoos = new ArrayList<>();

    }

    /**
     * Level up all both players.
     */
    public void levelUpPlayers(){
        Marcos.getInstance().levelUp();
        Luis.getInstance().levelUp();
    }

    /**
     * Generates a Goomba.
     *
     * @return Goomba generated.
     */
    public Goomba generateGoomba() {
        return new Goomba();
    }

    /**
     * Generates a Spiny.
     *
     * @return Spiny generated.
     */
    public Spiny generateSpiny() {
        return new Spiny();
    }

    /**
     * Generates a Boo.
     *
     * @return Boo generated.
     */
    public Boo generateBoo() {
        return new Boo();
    }

    /**
     * Adds a Goomba or Spiny to listOfGSs.
     *
     * @param anEnemy Enemy to add.
     */
    public void addEnemy(AttackableByLuis anEnemy){
        listOfGSs.add(anEnemy);
    }

    /**
     * Adds a Boo to listOfBoos.
     *
     * @param anEnemy Enemy to add.
     */
    public void addEnemy(Boo anEnemy){
        listOfBoos.add(anEnemy);
    }

    /**
     * Creates an array of random numbers between 0 and 2.
     *
     * @param numbers Size of array.
     * @return the array with random numbers.
     */
    public List<Integer> generateRandomNumbers(int numbers){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers; i++){
            list.add(new Random().nextInt(2));
        }
        return list;
    }

    /**
     * Adds random enemies to the round using an array of random numbers.
     *
     * @param enemies Array of random numbers.
     */
    public void addEnemies(int enemies){
        List<Integer> list = generateRandomNumbers(enemies);
        for (int i = 0; i < list.size(); i++){
            switch (i) {
                case 0 -> addEnemy(generateGoomba());
                case 1 -> addEnemy(generateSpiny());
                default -> addEnemy(generateBoo());
            }

        }
    }

    /**
     * Gets the next turn.
     *
     * @return the next turn.
     */
    public int getNextTurn(){
        int nextTurn = turn + 1;
        if (nextTurn > getListOfCharacters().size()){
            return 0;
        }
        return (nextTurn) % getListOfCharacters().size();
    }

    /**
     * Check if round isn't over, then checks if the current phase's turn is the last to phase change.
     * If yes, then changes the phase and set the phase turn to zero;
     * If not, increases the phaseTurn by one.
     * Finally, gets the next turn.
     */
    public void finishTurn() {
        if (notOver()){
            if (phaseTurn >= phase.getSize()-1) {
                setPhase(phases.nextPhase());
                phaseTurn = 0;
            }
            else{
                phaseTurn++;
            }
            turn=getNextTurn();
        }
        else{
            setPhase(new Phase());
            out.println(getWinner() + " won the round");
            if (getWinner().equals("Players") && rounds.getRoundIndex()<4){
                setRound(rounds.getNextRound());
            }
            else{
                out.println(getWinner() + " won the game");
            }
        }
    }

    /**
     * Gets the actual Players' chest.
     *
     * @return chest.
     */
    public Consumable[] getChestItems(){
        return AbstractPlayer.getChestItems();
    }

    /**
     * Sets a chest to Players.
     */
    public void setChest(){
        AbstractPlayer.setChest(new Chest());
    }

    /**
     * Adds a specific item amount to Players' chest.
     *
     * @param anItem Specific item.
     * @param quantity Amount to add.
     */
    public void addItem(ItemEnum anItem, int quantity){
        AbstractPlayer.add(anItem,quantity);
    }

    /**
     * Increases the difficulty of the next enemies by one.
     */
    public void increaseDifficulty(){
        AbstractEnemy.increasePower();
    }

    /**
     * Resets the difficulty of the next enemies.
     */
    public void resetDifficulty(){
        AbstractEnemy.resetPower();
    }

    /**
     * Gets the list of all Players and Enemies alive.
     *
     * @return the list of Characters.
     */
    public List<CanMove> getListOfCharacters(){
        List<CanMove> list = new ArrayList<>(getListOfPlayers());
        list.addAll(getListOfEnemies());
        return list;
    }

    /**
     * Gets the list of all Players alive.
     *
     * @return the list of Players.
     */
    public List<IPlayer> getListOfPlayers(){
        List<IPlayer> list = new ArrayList<>();
        if (Marcos.isAlive()){
            list.add(Marcos.getInstance());
        }
        if (Luis.isAlive()){
            list.add(Luis.getInstance());
        }
        return list;
    }

    /**
     * Gets the list of all Enemies alive.
     *
     * @return the list of Enemies.
     */
    public Lista<IEnemy> getListOfEnemies() {
        Lista<IEnemy> list = new Lista<>(listOfGSs);
        list.addAll(listOfBoos);
        return list;
    }

    /**
     * Gets the list of all Goombas and Spinies alive.
     *
     * @return the list of Goombas and Spinies.
     */
    public List<AttackableByLuis> getListOfGSs(){
        return listOfGSs;
    }

    /**
     * Gets the list of all Boos alive.
     *
     * @return the list of Boos.
     */
    public List<Boo> getListOfBoos(){
        return listOfBoos;
    }

    /**
     * Gets the owner of this turn.
     *
     * @return the owner.
     */
    public CanMove getOwner(){
        return getListOfCharacters().get(turn);
    }

    /**
     * Gets the next owner of the turn.
     *
     * @return the next owner.
     */
    public CanMove getNextOwner(){
        return getListOfCharacters().get(getNextTurn());
    }

    /**
     * Action used by Enemies to attack a Random Player,
     * or just Luis if the Enemy is Boo.
     */
    public void enemyTryToAttack(){
        this.phase.attack();
        checkWinner();
        this.finishTurn();
    }

    /**
     * Action used by Enemies to attack a specific Player, not usable by Boo.
     * Mainly used in tests.
     */
    public void enemyTryToAttack(int index){
        this.phase.attack(index);
        checkWinner();
        this.finishTurn();
    }

    /**
     * Action used by Players to attack a specific Enemy
     *
     * @param targetIndex Index of the specific Enemy in the attackable actual Player's list.
     * @param anAttack Attack to perform.
     */
    public void playerTryToAttack(int targetIndex, PlayerAttackType anAttack){
        losePoints = 0;
        this.phase.attack(targetIndex, anAttack);
        checkWinner();
        setPhase(new Phase());
    }

    /**
     * Action used by Players to get and use a specific Item from the Player´s chest on
     * a specific Player.
     *
     * @param targetIndex Index of the specific Player in the Players' list.
     * @param anItem Item to use.
     */
    public void tryToUseAnItem(int targetIndex, ItemEnum anItem){
        losePoints = 0;
        this.phase.use(targetIndex, anItem);
        setPhase(new Phase());
    }

    /**
     * Finishes the turn.
     */
    public void pass() {
        losePoints++;
        checkWinner();
        this.finishTurn();
    }

    /**
     * Checks if a side won. If there is no alive Enemy, then Players win, if there is no
     * alive Player, the Enemies win.
     */
    private void checkWinner(){
        if (getListOfEnemies().size()==0){
            winner = "Players";
        }
        else if (getListOfPlayers().size()==0 || losePoints>=3){
            winner = "Enemies";
        }
    }

    /**
     * Sets the winner to Nobody.
     */
    public void resetWinner(){
        winner = "Nobody";
    }

    /**
     * Gets the actual winner.
     *
     * @return the winner.
     */
    private String getWinner(){
        return winner;
    }

    /**
     * Checks if round is not over yet.
     *
     * @return True if the winner is Nobody;
     *         false otherwise.
     */
    public boolean notOver() {
        return winner.equals("Nobody");
    }

    /**
     * Sets perfect precision for Marcos and Luis.
     */
    public void activePerfectPrecision(){
        Marcos.getInstance().setPerfectPrecision(true);
        Luis.getInstance().setPerfectPrecision(true);
    }

    /**
     * Sets the winner equal to Enemies.
     */
    private void leave(){
        winner = "Enemies";
    }

    /**
     * Asks user for a move choice or use a script to attack, use and item, pass or leave game.
     *
     * @throws IOException Exception thrown by .readLine().
     */
    public void chooseMove() throws IOException {
        out.println("Choose a number: \n 1 to attack - 2 to use an item - 3 to pass - 4 to leave");
        move = in.readLine();

        switch (move) {
            case "1" -> chooseTarget();
            case "2" -> choosePlayer();
            case "3" -> pass();
            case "4" -> leave();
            default -> {
                out.println("Invalid move!");
                chooseMove();
            }
        }
    }

    /**
     * Asks user for choose a target printed as a list with indexes to attack it,
     * or does it with a script.
     *
     * @throws IOException Exception thrown by .readLine().
     */
    private void chooseTarget() throws IOException {
        out.println("Choose a number: " + phase.getListOfTargets().toString());
        move = in.readLine();
        try{
            int target = Integer.parseInt(move);
            if (target<1 || target>phase.getListOfTargets().size()){
                out.println("Invalid number!");
                chooseTarget();
            }
            else{
                chooseAttack(target-1);
            }
        }catch(NumberFormatException e){
            out.println("Invalid number!");
            chooseTarget();

        }
    }

    /**
     * Asks user for choose an attack to perform on a specific target,
     * or does it with a script.
     *
     * @param target Target chosen in chooseTarget method.
     * @throws IOException Exception thrown by .readLine().
     */
    private void chooseAttack(int target) throws IOException {
        out.println("Choose a number: \n 1 to use JUMP attack - 2 to use HAMMER attack - 3 to return");
        move = in.readLine();

        switch (move) {
            case "1" -> {
                try{
                    playerTryToAttack(target, SALTO);
                    finishTurn();
                }catch(AssertionError e){
                    out.println(e.getMessage());
                    chooseAttack(target);
                }
            }
            case "2" -> {
                try{
                    playerTryToAttack(target, MARTILLO);
                    finishTurn();
                }catch(AssertionError e){
                    out.println(e.getMessage());
                    chooseAttack(target);
                }
            }
            case "3" -> chooseMove();
            default -> {
                out.println("Invalid number!");
                chooseAttack(target);
            }
        }
    }

    /**
     * Asks user for choose a player to use an item on him,
     * or does it with a script.
     *
     * @throws IOException Exception thrown by .readLine().
     */
    private void choosePlayer() throws IOException {
        out.println("Choose a number: \n 1 to use on MARCOS - 2 to use on LUIS - 3 to return");
        move = in.readLine();

        switch (move) {
            case "1" -> chooseItem(1);
            case "2" -> chooseItem(2);
            case "3" -> chooseMove();
            default -> {
                out.println("Invalid number!");
                choosePlayer();
            }
        }
    }

    /**
     * Asks user for choose an item to use on a specific player,
     * or does it with a script.
     *
     * @param player Player chosen in choosePlayer method.
     * @throws IOException Exception thrown by .readLine().
     */
    private void chooseItem(int player) throws IOException {
        out.println("Choose a number: " + getChestItems()[0].toString() + getChestItems()[1].toString() +
                " - or type 3 to return");
        move = in.readLine();

        switch (move) {
            case "1" -> {
                try{
                    tryToUseAnItem(player-1, REDMUSHROOM);
                    finishTurn();
                }catch(AssertionError e){
                    out.println(e.getMessage());
                    chooseItem(player);
                }
            }
            case "2" -> {
                try{
                    tryToUseAnItem(player-1, HONEYSYRUP);
                    finishTurn();
                }catch(AssertionError e){
                    out.println(e.getMessage());
                    chooseItem(player);
                }
            }
            case "3" -> chooseMove();
            default -> {
                out.println("Invalid number!");
                chooseItem(player);
            }
        }
    }

    /**
     * Sets the outputStream.
     *
     * @param out outputStream to set.
     */
    public void setOut(PrintStream out){
        this.out = out;
    }

    /**
     * Gets the outputStream.
     *
     * @return the outputStream.
     */
    public PrintStream getOut(){
        return out;
    }

    /**
     * Runs the game until game is over.
     *
     * @throws IOException Exception thrown by .readLine() method located in chooseMove()
     */
    public void runGame() throws IOException {
        while (notOver()){
            if (phase.isPlayer()){
                chooseMove();
            }
            else{
                enemyTryToAttack();
            }
        }
    }

    /**
     * Runs a specific game using a script including all moves done by players.
     * It also sets a NullOutputStream to avoid prints on tests.
     *
     * @param script Script used to run the game.
     * @throws IOException Exception thrown by .readLine() method located in runGame()
     */
    public void runGame(String script) throws IOException {
        in = new BufferedReader(new StringReader(script));
        out = new PrintStream(new NullOutputStream());
        runGame();
    }
}