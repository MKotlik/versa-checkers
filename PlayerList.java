import javax.swing.JList;

public class PlayerList extends JList{

    public PlayerList(){
        //Temporary type for the array list
        //Will be converted to clients in the future
        String[] players = {"test", "temp", "don't kill me"};
        this.setListData(players);
    }
}