all: clean pract4a.com pract4b.exe

pract4a.com: p4a.obj
	tlink /t p4a

pract4b.exe: p4b.obj
	tlink /v p4b
	
p4a.obj: p4a.asm 
	tasm /zi p4a.asm
	
p4b.obj: p4b.asm
    tasm /zi p4b.asm
	
clean:
	del *.obj
	del *.exe
	del *.com
	