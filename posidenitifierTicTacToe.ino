// this is for the y ultrasonic sensor
int trigPinY=13;
int echoPinY=12;
int ydist[9] = {9,8,8,11,10,9,13,12,12};
int xdist[9] = {12,11,9,12,10,8,11,9,8};
int altydist[9] = {10,8,8,11,10,10,13,12,11};
int altxdist[9] = {13,11,10,12,9,8,12,10,8};

// this is the configuration for the RGB lamp
int red = 3;
int green = 5;
int blue = 6;

int prevred = 0;
int prevgreen = 0;
int prevblue = 0;

// this is for the x ultrasonic sensor
int trigPinX = 11;
int echoPinX = 10;

int count = 0;


void setup() {
  pinMode(trigPinY,OUTPUT);
  pinMode(echoPinY,INPUT);
  pinMode(trigPinX,OUTPUT);
  pinMode(echoPinX,INPUT);  

  pinMode(red, OUTPUT);
  pinMode(green, OUTPUT);
  pinMode(blue, OUTPUT);
  Serial.begin(115200);
  // put your setup code here, to run once:
}

void loop() {
    if(Serial.available() != 0){
       String cmd = Serial.readStringUntil('\r');

       String rval = cmd.substring(4,8);
       String gval = cmd.substring(9,12);
       String bval = cmd.substring(13);
       
       String sect = getfirst(cmd);
       int R = getnum(rval);
       int G = getnum(gval);
       int B = getnum(bval);

       Serial.println(sect + " " + String(R) + "," + String(G) + "," + String(B));

       if(sect == "gwin"){
          fade(R,G,B,red,green,blue,3,5);
          fadeout(R,G,B, red, green, blue);              
       }
       else if(sect == "fwin"){        
          fade(R,G,B,red,green,blue,10,2);
          fadeout(R,G,B, red, green, blue);         
       }
       else if(sect == "turn"){
         if(prevred == 0 && prevblue == 0 && prevgreen == 0){
           fadein(R,G,B,red, green, blue);
         }
         else{
           fadeout(prevred, prevgreen, prevblue, red, green, blue);
           delay(100);
           fadein(R,G,B, red, green, blue);      
         }
         analogWrite(red, R);   
         analogWrite(green, G);
         analogWrite(blue, B);
       }

       prevred = R;
       prevgreen = G;
       prevblue = B;
    }

    count = 0;
    int ydistance = Ultrasonic(trigPinY, echoPinY);
    int xdistance = Ultrasonic(trigPinX, echoPinX);
    //Serial.println("Y: " + String(ydistance) + "X: " + String(xdistance));
    for(int i = 0; i < 9; i++){
      PrintPos(i,xdistance,ydistance);
    }
    if(count == 0)
    {
      Serial.println("0");
    }
  // put your main code here, to run repeatedly:
}

int Ultrasonic(int trig, int echo){
  digitalWrite(trig,LOW);
  delayMicroseconds(10);
  digitalWrite(trig,HIGH);
  delayMicroseconds(10);
  digitalWrite(trig,LOW);
  int pingTravelTime = pulseIn(echo,HIGH);
  delay(25);
  int distance = pingTravelTime/2 * 0.034;
  return distance;
}

void PrintPos(int pos, int x, int y){
    if((y == ydist[pos] ||  y == altydist[pos]) && (x == xdist[pos] ||  x == altxdist[pos])){
      Serial.println(String(pos + 1));
      count = count + 1;
    }
}

String getfirst(String thingie){
  String sect = thingie.substring(0,4);
  return sect;
}

int getnum(String thingie){
  String truethingie = "";

  for(int i = 0; i < thingie.length(); i++){
    if(thingie[i] != '0'){
       truethingie = truethingie + String(thingie[i]);
    }
  }

  int thing = truethingie.toInt();
  return thing;
}

void fade(int Rv, int Gv, int Bv, int redv, int greenv, int bluev, int repeats, int del)
{
   int RO = Rv;
   int GO = Gv;
   int BO = Bv;
      
   for(int i = 0; i < repeats; i++)
   {
     Rv = RO;
     Gv = GO;
     Bv = BO;

     bool fadeddown = false;
     bool fadedup = false;
     while(fadeddown == false){
          if(Rv > 0){
             Rv = Rv - 1;
          }
          if(Gv > 0){
             Gv = Gv - 1;          
          }
          if(Bv > 0){
             Bv = Bv - 1;     
          }

          if(Rv == 0 && Bv == 0 && Gv == 0){
            fadeddown = true;
          }

          analogWrite(redv, Rv);
          analogWrite(greenv, Gv);
          analogWrite(bluev, Bv);
          delay(del);
      }
      delay(90);
      while(fadedup == false){
          if(Rv < RO){
             Rv = Rv + 1;
          }
          if(Gv < GO){
             Gv = Gv + 1;
            
          }
          if(Bv < BO){
             Bv = Bv + 1;    
          }

          if(Rv == RO && Bv == BO && Gv == GO){
            fadedup = true;
          }

          analogWrite(redv, Rv);
          analogWrite(greenv, Gv);
          analogWrite(bluev, Bv);
          delay(del);
      }
   }
}

void fadein(int Rv, int Gv, int Bv, int redv, int greenv, int bluev)
{
   int RO = Rv;
   int GO = Gv;
   int BO = Bv;

   int repeats = 1;
      
   for(int i = 0; i < repeats; i++)
   {
     Rv = 0;
     Gv = 0;
     Bv = 0;

     bool fadedup = false;
     while(fadedup == false){
          if(Rv < RO){
             Rv = Rv + 1;
          }
          if(Gv < GO){
             Gv = Gv + 1;
            
          }
          if(Bv < BO){
             Bv = Bv + 1;    
          }

          if(Rv == RO && Bv == BO && Gv == GO){
            fadedup = true;
          }

          analogWrite(redv, Rv);
          analogWrite(greenv, Gv);
          analogWrite(bluev, Bv);
          delay(5);
      }
   }
}

void fadeout(int Rv, int Gv, int Bv, int redv, int greenv, int bluev)
{
   int RO = Rv;
   int GO = Gv;
   int BO = Bv;

   int repeats = 1;
      
   for(int i = 0; i < repeats; i++)
   {
     Rv = RO;
     Gv = GO;
     Bv = BO;

     bool fadeddown = false;
     bool fadedup = false;
     while(fadeddown == false){
          if(Rv > 0){
             Rv = Rv - 1;
          }
          if(Gv > 0){
             Gv = Gv - 1;          
          }
          if(Bv > 0){
             Bv = Bv - 1;     
          }

          if(Rv == 0 && Bv == 0 && Gv == 0){
            fadeddown = true;
          }

          analogWrite(redv, Rv);
          analogWrite(greenv, Gv);
          analogWrite(bluev, Bv);
          delay(5);
      }
   }
}




