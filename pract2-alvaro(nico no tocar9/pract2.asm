;******************************************************************************* 
; PRACTICA 2 DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************

; DEFINICION DEL SEGMENTO DE DATOS 

DATOS SEGMENT 

	MATRIZ DB 23, 4, 5, 3, 11, 18, 10, 6 , 9


DATOS ENDS 


; DEFINICION DEL SEGMENTO DE PILA 

PILA SEGMENT STACK "STACK" 
    DB   40H DUP (0) 
PILA ENDS 


; DEFINICION DEL SEGMENTO EXTRA 

EXTRA SEGMENT 
EXTRA ENDS 


; DEFINICION DEL SEGMENTO DE CODIGO 

CODE    SEGMENT 
    ASSUME CS:CODE, DS:DATOS, ES: EXTRA, SS:PILA 

; COMIENZO DEL PROCEDIMIENTO PRINCIPAL 

START PROC 
    ;INICIALIZA LOS REGISTROS DE SEGMENTO CON SUS VALORES 
    MOV AX, DATOS 
    MOV DS, AX 

    MOV AX, PILA 
    MOV SS, AX 

    MOV AX, EXTRA 
    MOV ES, AX 


	MOV SI, 0
	MOV BL, 100
	MOV CX, 0
	MOV DL, 0	;;BANDERA QUE INDICA SI SE HA ENCONTRADO LA CIFRA 
				;;MAS SIGNIFICATIVA
	
	
ASCII:
	MOV AX, MATRIZ[SI]
	DIV BL
	CMP DL, 0
	JE COMP
	MOV AH,9			
	MOV DX, OFFSET AL+48
	INT 21H
	ADD CX, AL

COMP:
	CMP AL, 0
	JE DIV10	
	
DIV10:
	MOV AL, BL
	DIV 10
	MOV BL, AL
	JMP ASCII
	


 
 
    ; FIN DEL PROGRAMA 
    MOV AX, 4C00H 
    INT 21H 

START ENDP 
; FIN DEL SEGMENTO DE CODIGO 
CODE ENDS 
; FIN DEL PROGRAMA INDICANDO DONDE COMIENZA LA EJECUCION 
END START 
