/**
 * @author Sapir Caduri 
 * 
 * 29/07/14
 * at TOM 
 * tomisrael.org
 * 
 */

package mcplib.general;

public interface ICommands {

		final int ROTATION_DEGREE = 10;
		

		// --------------------------------------------------------------
		// 					The Multi-Arm Functionality  
		// --------------------------------------------------------------
		
		/**
		 * 	Move the arm to a folded position. 
		 *  Use this function at the very start and before shutting down,
		 */
		void setInAFoldedPosition();

		/**
		 * Close the gripper
		 */
		void gripperClose();
		
		/**
		 * Open the gripper
		 */
		void gripperOpen();

		/**
		 * Move the gripper upwards by a constant ROTATION_DEGREE 
		 */
		void gripperMoveUp();

		/**
		 * Move the gripper downwards by a constant ROTATION_DEGREE
		 */
		void gripperMoveDown();

		/**
		 * Move the gripper base to the right
		 */
		void gripperMoveRight();

		/**
		 * Move the gripper base to the left
		 */
		void gripperMoveLeft();

		
		/**
		 * Rotate the gripper to the right by a constant ROTATION_DEGREE
		 */
		void gripperRotateRight();  
		
		
		/**
		 * Rotate the gripper to the left by a constant ROTATION_DEGREE
		 */
		void gripperRotateLeft();

		/**
		 * Rotate the gripper to the right by a given angle
		 * @param angle
		 */
		void gripperRotateRight(int angle);

		/**
		 * Rotate the gripper to the left by a given angle
		 * @param angle
		 */
		void gripperRotateLeft(int angle);

		/** 
		 * Rotate arm's base to the left by a constant ROTATION_DEGREE
		 */
		void armRotateLeft();
		
		/**
		 * Rotate arm's base to the right by a constant ROTATION_DEGREE
		 */
		void armRotateRight();

		
		/**
		 * Rotate arm's base to the left by a given angle
		 * @param angle
		 */
		void armRotateLeft(int angle);

		/**
		 * Rotate arm's base to the right by a  given angle
		 * @param angle
		 */
		void armRotateRight(int angle);

		
		/**
		 * Move the arm up by a constant ROTATION_DEGREE
		 */
		void armUp();
		
		
		/**
		 * Move the arm down by a constant ROTATION_DEGREE
		 */
		void armDown();
		
		/**
		 * 
		 * Move the arm up by a given angle
		 * @param angle
		 */
		void armUp(int angle);
		
		/**
		 * 
		 * Move the arm down by a given angle
		 * @param angle
		 */
		void armDown(int angle);


		// --------------------------------------------------------------
		// 					The Camera-Arm Functionality  
		// --------------------------------------------------------------
		
		
		/**
		 * Turns the camera On
		 */
		void turnCameraOn();

		/**
		 * Shut down the camera
		 */
		void turnCameraOff();

		/**
		 * Zoom in by 1
		 */
		void ZoomIn();

		/**
		 * Zoom in by 1
		 */
		void ZoomOut();

		/**
		 * Take a picture!
		 */
		void snap();

		// Video mode
//		void videoModeStart();
//		void videoModeStop();
//		void startVideoRecording();
//		void StopVideoRecording();
//		void photoPreview();

		// --------------------------------------------------------------
		// 					The Melody player functionality
		// --------------------------------------------------------------
		
		
		/**
		 * playMidiBuffer 
		 * 
		 * @param buffer - the midi stream
		 * 
		 * TODO: Should we limit the size? Find out what's  the maximum allowed 
		 */
		void  playMidiBuffer(short[] buffer);


		enum CommandType {
			setInAFoldedPosition,
			gripperClose,
			gripperOpen,
			gripperMoveUp,
			gripperMoveDown,
			gripperMoveRight,
			gripperMoveLeft,
			gripperRotateRight,  
			gripperRotateLeft,
			gripperRotateRight_byAngle,
			gripperRotateLeft_byAngle,
			armRotateLeft,
			armRotateRight,
			armRotateLeft_byAngle,
			armRotateRight_byAngle,
			armUp,
			armDown,
			armUp_angle,
			armDown_angle,
			turnCameraOn,
			turnCameraOff,
			ZoomIn,
			ZoomOut,
			snap,
			playMidiBuffer
		}

}
