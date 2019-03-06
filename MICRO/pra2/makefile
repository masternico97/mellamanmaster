all: delete pract2.exe 
delete:
		del pract2.obj
		del pract2.lst
		del pract2.exe
pract2.exe: pract2.obj 
       tlink /v pract2 
pract2.obj: pract2.asm 
       tasm /zi pract2.asm,,pract2.lst
