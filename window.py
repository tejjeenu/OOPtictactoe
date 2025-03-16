from tkinter import *
from tkinter.colorchooser import askcolor
from tkinter import messagebox

def isanumbercheck(word):
    if(word.isnumeric() == False):
        messagebox.showerror("Error", "number of games in wrong format")
        return False
    else:
        return True

def samecheck(thing1, thing2, errorreference):
    if(thing1 == thing2):
        messagebox.showerror("Error", "the " + errorreference + " for both players are the same")
        return False
    else:
        return True

def isacharcheck(char, playerreference):
    if(len(char) != 1):
        messagebox.showerror("Error", "the counterpiece for " + playerreference + " is longer than one character")
        return False
    else:
        return True


def enterdetails():
    checks = [False, False, False, False, False, False]

    checks[0] = isacharcheck(counterentry1.get(), "player 1")
    checks[1] = isacharcheck(counterentry2.get(), "player 2")
    checks[2] = isanumbercheck(gamesentry.get())
    checks[3] = samecheck(colorentry1.get(), colorentry2.get(), "colours")
    checks[4] = samecheck(nameentry1.get(), nameentry2.get(), "names")
    checks[5] = samecheck(counterentry1.get(), counterentry2.get(), "counters")

    if(False not in checks):
       a_file = open("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTdetails.txt", "r")

       list_of_lines = a_file.readlines()
       list_of_lines[0] = nameentry1.get() + "," + counterentry1.get() + ",player1\n"
       list_of_lines[1] = nameentry2.get() + "," + counterentry2.get() + ",player2\n"
       list_of_lines[2] = gamesentry.get() + "\n"
       list_of_lines[3] = colorentry1.get() + "\n"
       list_of_lines[4] = colorentry2.get()

       a_file = open("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTdetails.txt", "w")
       a_file.writelines(list_of_lines)
       a_file.close()
       messagebox.showinfo("", "setup succesful!")


def changep1_color():
    colors = askcolor(title="Tkinter Color Chooser")
    colorentry1.delete(0, END)
    colorentry1.insert(0, str(colors[0][0]) + "," + str(colors[0][1]) + "," + str(colors[0][2]))

def changep2_color():
    colors = askcolor(title="Tkinter Color Chooser")
    colorentry2.delete(0, END)
    colorentry2.insert(0, str(colors[0][0]) + "," + str(colors[0][1]) + "," + str(colors[0][2]))

window = Tk()

window.geometry("999x600")
window.configure(bg = "#ffffff")
canvas = Canvas(
    window,
    bg = "#ffffff",
    height = 600,
    width = 999,
    bd = 0,
    highlightthickness = 0,
    relief = "ridge")
canvas.place(x = 0, y = 0)

background_img = PhotoImage(file = f"background.png")
background = canvas.create_image(
    499.5, 300.0,
    image=background_img)

img0 = PhotoImage(file = f"img0.png")
colour2btn = Button(
    image = img0,
    borderwidth = 0,
    highlightthickness = 0,
    command = changep2_color,
    relief = "flat")

colour2btn.place(
    x = 478, y = 195,
    width = 68,
    height = 36)

img1 = PhotoImage(file = f"img1.png")
colour1btn = Button(
    image = img1,
    borderwidth = 0,
    highlightthickness = 0,
    command = changep1_color,
    relief = "flat")

colour1btn.place(
    x = 254, y = 195,
    width = 68,
    height = 36)

img2 = PhotoImage(file = f"img2.png")
cmdbutton = Button(
    image = img2,
    borderwidth = 0,
    highlightthickness = 0,
    command = enterdetails,
    relief = "flat")

cmdbutton.place(
    x = 247, y = 484,
    width = 214,
    height = 65)

entry0_img = PhotoImage(file = f"img_textBox0.png")
entry0_bg = canvas.create_image(
    616.5, 261.0,
    image = entry0_img)

counterentry2 = Entry(
    bd = 0,
    bg = "#ffffff",
    highlightthickness = 0)

counterentry2.place(
    x = 570.0, y = 249,
    width = 93.0,
    height = 22)

entry1_img = PhotoImage(file = f"img_textBox1.png")
entry1_bg = canvas.create_image(
    616.5, 214.0,
    image = entry1_img)

colorentry2 = Entry(
    bd = 0,
    bg = "#ffffff",
    highlightthickness = 0)

colorentry2.place(
    x = 571.0, y = 201,
    width = 91.0,
    height = 24)

entry2_img = PhotoImage(file = f"img_textBox2.png")
entry2_bg = canvas.create_image(
    618.5, 171.0,
    image = entry2_img)

nameentry2 = Entry(
    bd = 0,
    bg = "#ffffff",
    highlightthickness = 0)

nameentry2.place(
    x = 572.0, y = 159,
    width = 93.0,
    height = 22)

entry3_img = PhotoImage(file = f"img_textBox3.png")
entry3_bg = canvas.create_image(
    392.5, 261.0,
    image = entry3_img)

counterentry1 = Entry(
    bd = 0,
    bg = "#ffffff",
    highlightthickness = 0)

counterentry1.place(
    x = 346.0, y = 249,
    width = 93.0,
    height = 22)

entry4_img = PhotoImage(file = f"img_textBox4.png")
entry4_bg = canvas.create_image(
    392.5, 217.5,
    image = entry4_img)

colorentry1 = Entry(
    bd = 0,
    bg = "#ffffff",
    highlightthickness = 0)

colorentry1.place(
    x = 346.5, y = 205,
    width = 92.0,
    height = 23)

entry5_img = PhotoImage(file = f"img_textBox5.png")
entry5_bg = canvas.create_image(
    392.5, 171.0,
    image = entry5_img)

nameentry1 = Entry(
    bd = 0,
    bg = "#ffffff",
    highlightthickness = 0)

nameentry1.place(
    x = 346.0, y = 159,
    width = 93.0,
    height = 22)

entry6_img = PhotoImage(file = f"img_textBox6.png")
entry6_bg = canvas.create_image(
    128.0, 378.5,
    image = entry6_img)

gamesentry = Entry(
    bd = 0,
    bg = "#ffffff",
    highlightthickness = 0)

gamesentry.place(
    x = 61.5, y = 366,
    width = 133.0,
    height = 23)

a_file = open("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTdetails.txt", "r")
list_of_lines = a_file.readlines()

player1gendetails = list_of_lines[0].split(",")
player2gendetails = list_of_lines[1].split(",")
gamedetails = list_of_lines[2].strip()
player1coldetails = list_of_lines[3].strip()
player2coldetails = list_of_lines[4]

nameentry1.insert(0, player1gendetails[0])
nameentry2.insert(0, player2gendetails[0])
counterentry1.insert(0, player1gendetails[1])
counterentry2.insert(0, player2gendetails[1])
gamesentry.insert(0, gamedetails)
colorentry1.insert(0, player1coldetails)
colorentry2.insert(0, player2coldetails)

window.resizable(False, False)
window.mainloop()
