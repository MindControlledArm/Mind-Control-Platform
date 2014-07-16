import mcplib.general.ICommands;


public class CommandsManager implements ICommands {

	@Override
	public void setInAFoldedPosition() {

		System.out.println("Moving the arm to the init position");
	}

	@Override
	public void gripperClose() {
		System.out.println("Closing the gripper");
		
	}

	@Override
	public void gripperOpen() {
		System.out.println("Opening the gripper");
	}

	@Override
	public void gripperRotateRight(int angle) {
		System.out.println("Rotating gripper "+ angle + " degrees.");
	}

	@Override
	public void playMidiBuffer(short[] buffer) {
		System.out.println("Midi stream received:");

		for (int i = 0; i < buffer.length; i++) {
			System.out.print(buffer[i] + " , ");
		}
	}

	@Override
	public void gripperMoveUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gripperMoveDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gripperMoveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gripperMoveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gripperRotateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gripperRotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gripperRotateLeft(int angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armRotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armRotateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armRotateLeft(int angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armRotateRight(int angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armUp(int angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armDown(int angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnCameraOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnCameraOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ZoomIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ZoomOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void snap() {
		// TODO Auto-generated method stub
		
	}
}
