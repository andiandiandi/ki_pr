package de.fh;

import de.fh.agentI.IAction;
import de.fh.agentI.IAgentActions;
import de.fh.agentI.IAgentState;
import de.fh.connection.ActionEffect;
import de.fh.connection.Percept;
import de.fh.connection.StartInfo;
import de.fh.connection.Vector2;
import de.fh.connection.client.PacmanClientConnector;
import de.fh.connection.pacman.PacmanAction;
import de.fh.connection.pacman.PacmanPercept;
import de.fh.connection.pacman.PacmanStartInfo;
import de.fh.elements.Element;

/**
 * DIESE KLASSE VERÄNDERN SIE BITTE NUR AN DEN GEKENNZEICHNETEN STELLEN
 * wenn die Bonusaufgabe bewertet werden soll.
 */
class MyAgent implements IAgentActions, IAgentState {

    private PacmanAction nextAction = PacmanAction.GO_EAST;
    private PacmanPercept percept;
    private int actionEffect;
    private PacmanStartInfo agentStartInfo;

    public static void main(String[] args) {
        MyAgent ki = new MyAgent();
        // Client connect to the server with given predefined settings
        PacmanClientConnector pacmanClient = new PacmanClientConnector(ki, ki);
        pacmanClient.run();
    }


    /**
     * Diese Funktion wird einmalig aufgerufen und übergibt
     * alle Startinformationen über die Welt
     *
     * @param startInfo Startinfo-Objekt mit alle Startinformationen
     */
    @Override
    public void setStartInfo(StartInfo startInfo) {
        /**
         * In dem Startinfo Objekt befinden sich alle Startinformationen,
         * auf die die "interne Welt", die Wissenbasis aufbaut.
         * Achtung: Die Feldgröße ist im Standard unbekannt also -1
         */
        this.agentStartInfo = (PacmanStartInfo) startInfo;
        //Startinformationen einmal ausgeben
        System.out.println(agentStartInfo.toString());
    }


    /**
     * In dieser Methode kann das Wissen über die Welt (der State, der Zustand)
     * entsprechend der aktuellen Wahrnehmungen anpasst, und die "interne Welt",
     * die Wissensbasis, des Agenten kontinuierlich ausgebaut werden.
     *
     * Wichtig: Diese Methode wird aufgerufen, bevor der Agent handelt, d.h.
     * bevor die chooseAction()-Methode aufgerufen wird...
     *
     * @param percept Aktuelle Wahrnehmung des Agenten
     * @param actionEffect Rückmeldung des Servers auf die letzte PacmanAction
     */
    @Override
    public void updateState(Percept percept, int actionEffect) {
        /**
         * Je nach Sichtbarkeit & Schwierigkeitsgrad (laut Serverkonfiguration)
         * die aktuelle Wahrnehmung des Agenten.
         * Beim Pacman erhalten Sie je nach Level mehr oder weniger Mapinformationen
         */
        this.percept = (PacmanPercept) percept;

        /**
         * Aktuelle Reaktion des Server auf die letzte übermittelte Action
         */
        this.actionEffect = actionEffect;
        


        /**
        * percept.getView() enthält die aktuelle Felderbelegung je nach Level/Sichtweite in einem Array
		*
		* Die Interpretationen der int-Werte können der public Elementen-Klasse
		* entnommen werden
		*
		* Für den Pacman sind folgende Felderbelegungen möglich
        *
		* Element.OUT_OF_RANGE = -1
		* Element.FREE = 0
		* Element.WALL = 1
		* Element.GOLD = DOT = 4
		* Element.AGENT = 5
		* Element.CHERRY = 20
		* Element.AGENT_CHERRY = (AGENT + CHERRY) = 25
		* Element.AGENT_GOLD = (AGENT + GOLD) = 9
		*/

        Vector2 v = this.percept.getPosition();
        if(this.percept.getView()[v.getX()+1][v.getY()]==1 && this.percept.getView()[v.getX()][v.getY()+1]!=1)
        	nextAction = PacmanAction.GO_SOUTH;
         if(this.percept.getView()[v.getX()-1][v.getY()]==1 && this.percept.getView()[v.getX()][v.getY()+1]==1)
        	nextAction = PacmanAction.GO_NORTH;
         if(this.percept.getView()[v.getX()][v.getY()+1]==1 && this.percept.getView()[v.getX()+1][v.getY()]==1)
        	nextAction = PacmanAction.GO_WEST;
         if(this.percept.getView()[v.getX()][v.getY()-1]==1 && this.percept.getView()[v.getX()-1][v.getY()+1]==1)
        	nextAction = PacmanAction.GO_EAST;
        	
        //Beispiel:
        int[][] view = this.percept.getView();
        String view_row = "";
       // System.out.println("viewsize: " + view.length + "*" + view[0].length);
        for (int x = 0; x < view[0].length; x++) {
            for (int y = 0; y < view.length; y++) {
                view_row += (view[y][x] == Element.OUT_OF_RANGE ? "-1" : " " + view[y][x]);
            }
          //  System.out.println(view_row);
            view_row = "";
        }
      //  System.out.println("-------------------------------");

        /**
         * TODO [1]: Erweitern Sie diese updateState-Methode gemäß der Aufgabenstellung
         */

        // Alle möglichen Serverrückmeldungen:
        switch (actionEffect) {
            case ActionEffect.SUCCESS:
                break;
            case ActionEffect.DOT_EATEN:
                break;
            case ActionEffect.INVALID_LOCATION:
                break;
            case ActionEffect.NO_DOT_FOUND:
                break;
            default:
                //Startsituation - aller erster Aufruf!
                break;
        }

    }


    /**
     * Die chooseAction-Methode erweitern Sie so, dass die nächste sinnvolle Aktion,
     * auf Basis der vorhandenen Zustandsinformationen und gegebenen Zielen, ausgeführt wird.
     * Die chooseAction-Methode soll den Agenten so intelligent wie möglich handeln lassen.
     *
     * Zum Beispiel:
     * Wenn die letzte Wahrnehmung ein Haufen von Dots in einem bestimmten Bereich der
     * Pacman-Welt ist, dann wird der Agent sicher eine intelligente Entscheidung treffen
     * können, welches das nächste zu betretende Feld ist
     *
     * @param
     * @return Die nächste Pacman-Action die vom Server ausgeführt werden soll
    */
    @Override
    public IAction chooseAction() {



         /*
		 * TODO [2]: Erweitern Sie diese chooseAction-Methode gemäß der Aufgabenstellung
		 */

        //Beispiel
        if (percept.isDotBelow()) {
            System.out.println("DOT BELOW");
            return PacmanAction.EAT;
        }


        // Alle möglichen Serverrückmeldungen:
        switch (actionEffect) {
            case ActionEffect.SUCCESS:
                break;
            case ActionEffect.DOT_EATEN:
            	break;
            case ActionEffect.INVALID_LOCATION:
            	return nextAction;
            case ActionEffect.NO_DOT_FOUND:
                break;
            default:
                //Startsituation - aller erster Aufruf!
                break;
        }
        return nextAction;
    }
}