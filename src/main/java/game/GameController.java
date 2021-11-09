package game;

import animantia.*;
import interfaces.*;
import items.ItemEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private final List<AttackableByLuis> listOfGSs;

    private final List<Boo> listOfBoos;

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

    /**
     * Creates a GameController initializing lists, setting the phases and turns.
     */
    public GameController() {
        listOfGSs = new ArrayList<>();
        listOfBoos = new ArrayList<>();
        turn = 0;
        phaseTurn = 0;
        setPhase(new MarcosPhase());
        setPhases(new Phases());
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
     * Generates a random Enemy using a random number.
     *
     * @return Random Enemy generated.
     */
    private IEnemy generateRandomEnemy() {
        int randomNumber = new Random().nextInt(3);
        return switch (randomNumber) {
            case 0 -> generateGoomba();
            case 1 -> generateSpiny();
            default -> generateBoo();
        };
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
     * Check if the current phase's turn is the last to phase change.
     * If yes, then changes the phase and set the phase turn to zero;
     * If not, increases the phaseTurn by one.
     * Finally, gets the next turn.
     */
    public void finishTurn() {
        if (notOver()){
            if (phaseTurn == phase.getSize()-1) {
                setPhase(phases.nextPhase());
                phaseTurn = 0;
            }
            else{
                phaseTurn++;
            }
            turn=getNextTurn();
        }
        else{
            System.out.println(getWinner() + " won");
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
     * Adds a specific item to Players' chest.
     *
     * @param anItem Item to add.
     */
    public void addItem(ItemEnum anItem){
        AbstractPlayer.add(anItem);
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
    public List<IEnemy> getListOfEnemies() {
        List<IEnemy> list = new ArrayList<>(listOfGSs);
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
     * Action used by Enemies to attack a specific Player,
     * Not usable by Boo.
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
        this.phase.use(targetIndex, anItem);
        setPhase(new Phase());
    }

    /**
     * Finishes the turn.
     */
    public void pass() {
        this.finishTurn();
    }

    /**
     * Checks if a side won. If there is no alive Enemy, then Players win, if there is no
     * alive Player, the Enemies win.
     */
    public void checkWinner(){
        if (getListOfEnemies().size()==0){
            winner = "Players";
        }
        else if (getListOfPlayers().size()==0){
            winner = "Enemies";
        }
    }

    /**
     * Gets the actual winner.
     *
     * @return the winner.
     */
    public String getWinner(){
        return winner;
    }

    /**
     * Checks if the game is not over yet.
     *
     * @return True if the winner is nobody;
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
}