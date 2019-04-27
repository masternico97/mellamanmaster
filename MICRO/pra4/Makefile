all: clean pract4a.com pract4b.exe pract4av2.com pract4c.exe

pract4a.com: p4a.obj
	tlink /t p4a

pract4av2.com: p4av2.obj
	tlink /t p4av2

pract4b.exe: p4b.obj
	tlink /v p4b

pract4c.exe: p4c.obj
	tlink /v p4c

p4a.obj: p4a.asm
	tasm /zi p4a.asm

p4av2.obj: p4av2.asm
	tasm /zi p4av2.asm

p4b.obj: p4b.asm
  tasm /zi p4b.asm

p4c.obj: p4c.asm
  tasm /zi p4c.asm

clean:
	del *.obj
	del *.exe
	del *.com
