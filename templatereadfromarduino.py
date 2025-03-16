import time
import serial
import threading
import os
arduinodata =serial.Serial('com5', 115200)
time.sleep(1)

def writetofile(datapacket):
    datapacket = str(datapacket)
    #lines = ['Readme', 'How to write text files in Python']
    with open('TTTpos.txt', 'w') as f:
       #for line in lines:
            f.write(datapacket)
            #f.write('\n')

def checktosend():
    f = open("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTsignals.txt", "r")
    message = str(f.read())
    if(message != "-"):
        message = message + '\r'
        arduinodata.write(message.encode())
        time.sleep(1)
        #reset()

def reset():
    with open('C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTsignals.txt', 'w') as f:
       #for line in lines:
            f.write("-")


def sendpos():
    #os.startfile("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TicTacToeinputs.py")
    while True:
        while(arduinodata.inWaiting() == 0):
             pass
        datapacket = arduinodata.readline()
        datapacket = str(datapacket, 'utf-8')
        datapacket = datapacket.strip('\r\n')
        print(datapacket)
        if(datapacket.isdigit()):
            writetofile(datapacket)
        elif(datapacket != " 0,0,0"):
            reset()


def sendled():
    reset()
    while True:
        checktosend()


positions_thread = threading.Thread(target = sendpos) 
positions_thread.start()
light_thread = threading.Thread(target = sendled)
light_thread.start()