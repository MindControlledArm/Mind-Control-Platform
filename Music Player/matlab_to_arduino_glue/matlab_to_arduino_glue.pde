/* Use brainwaves to control a servo
   Creates a server that listens on the given socket.
   Matlab connects and sends in single integers (no syncing necessary)
   which are sent out on serial. TOM_SERVO_SIMPLE then sends these to a servo.

July 1, 2014
Ronit Slyper
at TOM Makeathon
*/
import processing.net.*; 
import processing.serial.*;

Server myServer;
Serial mySerial;

int dataIn;

void setup() {
  // open a little window
  size(400, 400);
  background(120);
 
  // List all the available serial ports:
  println(Serial.list());
 
  // find the arduino by name
  int arduino_ind = 0;
  for (int i = 0; i < Serial.list().length; i++) {
    if (Serial.list()[i].contains("usbmodem")) arduino_ind = i;
  }
 
  // Open the port you are using at the rate you want:
  println("Connecting to " + Serial.list()[arduino_ind]);
  mySerial = new Serial(this, Serial.list()[arduino_ind], 9600);
 
  // Start the socket server
  myServer = new Server(this, 2003);
}

void draw() {
  // clear the window
  background(120);
  
  // read the message from the socket
  Client myClient = myServer.available();
  if (myClient==null) {
    //println("No client");
    return;
  }
  
  
  if (myClient.available() > 0) { // TODO replace with message length
      // read the data in
      dataIn = myClient.read(); // etc.
  
      // debug output
      println("Got data " + dataIn);
      
     // send the message out on serial
     if (dataIn != 10)
       mySerial.write(dataIn); //    Writes bytes, chars, ints, bytes[], Strings to the serial port 
  }
}
