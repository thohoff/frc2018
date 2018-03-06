import javax.swing.JFrame;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ChargerDash extends JFrame{
	
	private NetworkTable table;
	private NetworkTableInstance inst;
	
	public ChargerDash(){
		inst = NetworkTableInstance.getDefault();
	    table = inst.getTable("dashtable");
	    System.out.println(inst.isConnected());
	    inst.startClientTeam(5160);
	    inst.startDSClient();
	}
	
	
	public static void main(String[] args){
		ChargerDash frame = new ChargerDash();
		frame.setSize(1920/2, 600);
		frame.setVisible(true);
	}
}
