import time
import serial
arduinoData = serial.Serial('com5', 9600)
time.sleep(1)
while True:
    while(arduinoData.in)
