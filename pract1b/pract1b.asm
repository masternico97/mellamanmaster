;_______________________________________________________________
; DEFINICION DEL SEGMENTO DE DATOS
DATOS SEGMENT

	CONTADOR DB 00H
	TOME DW 0CAFEH
	TABLA100 DB 100 DUP(0)
	ERROR1 DB "Atencion: Entrada de datos incorrecta."
	
DATOS ENDS
;_______________________________________________________________
; DEFINICION DEL SEGMENTO DE PILA
PILA SEGMENT STACK "STACK"
	DB 40H DUP (0)
PILA ENDS
;_______________________________________________________________
; DEFINICION DEL SEGMENTO DE CODIGO
CODE SEGMENT
ASSUME CS:CODE,DS:DATOS,SS:PILA
; COMIENZO DEL PROCEDIMIENTO PRINCIPAL (START)
START PROC FAR
;INICIALIZACION DE LOS REGISTROS DE SEGMENTO
	MOV AX,DATOS
	MOV DS,AX	;FIN DE LAS INICIALIZACIONES
	
	MOV AH, ERROR1[2]
	MOV TABLA100[63H], AH
	
	MOV BX, 23H
	MOV AX, TOME
	MOV CX, 100

BUCLE:
	MOV TABLA100[BX], AL
	MOV TABLA100[BX+1], AH
	INC BX
	INC BX
	CMP CX, BX
	JNE BUCLE
	
	MOV BX, TOME
	MOV CONTADOR, BH
	
	MOV AX,4C00H			; FIN DE PROGRAMA Y VUELTA AL DOS
	INT 21H
START ENDP
;FIN DEL SEGMENTO DE CODIGO
CODE ENDS
;FIN DE PROGRAMA INDICANDO DONDE COMIENZA LA EJECUCION
END START

