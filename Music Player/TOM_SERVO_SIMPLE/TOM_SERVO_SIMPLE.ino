/* Reads data from serial and sends it as servo commands

July 1, 2014
Ronit Slyper
at TOM Makeathon
*/
#include <Servo.h>

Servo servo2;

void setup() {
   Serial.begin(9600);
   servo2.attach(10);
   servo2.write(0);
}

void loop() {
   if (Serial.available() > 0) {
     int val = Serial.read();
     servo2.write(val);
   }
}
