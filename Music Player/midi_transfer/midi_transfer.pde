/* For brain control of music.
 Creates a server that listens on a socket.
   Matlab connects and repeatedly send sends n_bar length vectors
   of integer data in the range [0, 127] (prepended by the sync_charcter)
   These vectors are graphed, and some manipulated combination of them is
   sent out to the midi device on the computer (bus id set in the MidiBus constructor)

July 1, 2014
Ronit Slyper
at TOM Makeathon
*/

import themidibus.*; //Import the library
import processing.net.*; 

final int n_bars = 20;
final int sync_character = 'a';

//Client myClient; 
Server myServer;

int dataIn; 

MidiBus myBus; // The MidiBus
String [] midi_outputs;

int bar_width_spacer = 0;
int bar_width = 100;
int bar_height = 127 * 3;
int screen_height = bar_height + 50;
int screen_width = n_bars * (bar_width+bar_width_spacer) + bar_width_spacer;
color orange = #F58823;

int [] old_data = new int[n_bars]; // previous full frame read in before new_data
int [] new_data = new int[n_bars]; // most recent frame read in

int [] playing_data = new int[n_bars];

int channel = 0;
//int pitch = 64;
int velocity = 127;



void setup() {
  size(screen_width, screen_height);
  background(120);

  // myClient = new Client(this, "127.0.0.1", 2002); 
  myServer = new Server(this, 2002);

  MidiBus.list(); // List all available Midi devices on STDOUT. This will show each device's index and name.
  midi_outputs = MidiBus.availableOutputs();
  
  // Either you can
  //                   Parent In Out
  //                     |    |  |
  //myBus = new MidiBus(this, 0, 1); // Create a new MidiBus using the device index to select the Midi input and output devices respectively.

  // or you can ...
  //                   Parent         In                   Out
  //                     |            |                     |
  //myBus = new MidiBus(this, "IncomingDeviceName", "OutgoingDeviceName"); // Create a new MidiBus using the device names to select the Midi input and output devices respectively.

  // or for testing you could ...
  //                 Parent  In        Out
  //                   |     |          |
  
  myBus = new MidiBus(this, -1, 1); // Create a new MidiBus with no input device and the default Java Sound Synthesizer as the output device.

  for (int i = 0; i < n_bars; i++) playing_data[i] = old_data[i] = new_data[i] = 100;
}


int bar_percent_to_y_coord(float ypercent) {
  return (int)(height - bar_height + (1.0-ypercent)*bar_height);
}

void draw_bar(int barnum, int oldval, int newval, float percent_decayed) {
  int x = barnum*(bar_width+bar_width_spacer)+bar_width_spacer;
  
  // draw the new bar
  fill(orange);
  noStroke();
  rect(x, 
    bar_percent_to_y_coord(lerp(oldval,newval,percent_decayed)/127.0), 
    bar_width,
    lerp(oldval,newval,percent_decayed)/127.0*bar_height);
}

int decay_ms = 1000;
int decay_start_time = 0;

void draw() {
   background(120);

  Client myClient = myServer.available();
  if (myClient==null) {
    println("No client");
  }
  if (myClient != null && myClient.available() >= n_bars+1) {
      dataIn = myClient.read();
      if (dataIn != sync_character) {
        println("Socket re-syncing");
        return;
      }
      print("Reading data from socket: ");
      for (int i = 0; i < n_bars; i++) {
        old_data[i] = new_data[i];
        new_data[i] = myClient.read();
        print(new_data[i] + ", ");
      }
      println("done.");
      decay_start_time = millis();
      
      // off with the old
      for (int i = 0; i < n_bars; i++) {
        myBus.sendNoteOff(channel, playing_data[i], velocity); // Send a Midi nodeOff
      }
      
      // in with the new!
      for (int i = 0; i < n_bars; i++) {
        if (abs(new_data[i] - old_data[i]) > 5) {
          myBus.sendNoteOn(channel, new_data[i], velocity); // Send a Midi noteOn
        }
        playing_data[i] = new_data[i];
      }
   }
 
   int tm = millis();
   
   float percent_decayed = (tm - decay_start_time)/float(decay_ms);
   if (percent_decayed > 1.0) percent_decayed = 1.0;
   
   // draw bars
   for (int i = 0; i < n_bars; i++) {
     draw_bar(i, old_data[i], new_data[i], percent_decayed);
   }

   // temp - draw the midi outputs list
   fill(0);
   for (int i = 0; i < midi_outputs.length; i++) {
     text(midi_outputs[i], 10, i * 30 + 30);
   }

  
  //int number = 0;
  //int value = 90;
  //myBus.sendControllerChange(channel, number, value); // Send a controllerChange
  //delay(2000);
}


void noteOn(int channel, int pitch, int velocity) {
  // Receive a noteOn
  println();
  println("Note On:");
  println("--------");
  println("Channel:"+channel);
  println("Pitch:"+pitch);
  println("Velocity:"+velocity);
}

void noteOff(int channel, int pitch, int velocity) {
  // Receive a noteOff
  println();
  println("Note Off:");
  println("--------");
  println("Channel:"+channel);
  println("Pitch:"+pitch);
  println("Velocity:"+velocity);
}

void controllerChange(int channel, int number, int value) {
  // Receive a controllerChange
  println();
  println("Controller Change:");
  println("--------");
  println("Channel:"+channel);
  println("Number:"+number);
  println("Value:"+value);
}

void delay(int time) {
  int current = millis();
  while (millis () < current+time) Thread.yield();
}
