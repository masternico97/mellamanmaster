;*******************************************************************************
; PRACTICA 4 APARTADO A DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************


codigo SEGMENT
	ASSUME cs : codigo
	ORG 256
inicio: jmp INSTALADOR

; Rutina de servicio a la interrupción
rsi PROC FAR
	CMP AH, 10H
	JZ OPCION_10H
	CMP AH, 11H
	JZ OPCION_11H
	
OPCION_10H:
	CALL CODIFICADOR
	
OPCION_11H:
	CALL DECODIFICADOR

FIN:
	iret
	
rsi ENDP

;FUNCION QUE CODIFICA EL CODIGO DE ASCII A POLIBIO
CODIFICADOR PROC NEAR

CODIFICADOR ENDP

;FUNCION QUE DECODIFICA EL CODIGO DE ASCII A POLIBIO
DECODIFICADOR PROC NEAR

DECODIFICADOR ENDP

INSTALADOR PROC
	MOV SI, ES:[80h]
LEER_PSP:
	MOV DL, ES:[SI]+81h	;Leemos los caracteres de entrada de derecha a izquierda
	CMP DL, '/'
	JE MODO
	dec SI
	JNE LEER_PSP
	
	
MODO:
	MOV DL, ES:[SI]+81h+1	;Leemos los caracter despues de /
	CMP DL, 'D'
	JE INSTALAR
	CMP DL, 'I'
	JE DESINSTALAR
	
DESINSTALAR:
	MOV AX, ES:[ 57h*4 ]
	MOV BX, ES:[ 57h*4+2 ]
	
	ADD AX, BX
	JE FIN_DESINSTALAR	;SI AMBOS SON 0 EL PROGRAMA NO ESTA INSTALADO Y NO SE DEBE DESISNTALAR
	
	CALL DESINSTALAR_57H
	
FIN_DESINSTALAR: 
	INT 21h

INSTALAR:
	MOV AX, ES:[ 57h*4 ]
	MOV BX, ES:[ 57h*4+2 ]
	
	ADD AX, BX
	JNE FIN_INSTALAR	;SI NO SON 0 EL PROGRAMA ESTA INSTALADO Y NO SE DEBE DESISNTALAR

	mov ax, 0
	mov es, ax
	mov ax, OFFSET rsi
	mov bx, cs
	cli
	mov es:[ 57h*4 ], ax
	mov es:[ 57h*4+2 ], bx
	sti
	mov
	dx, OFFSET INSTALADOR 

	int 27h ;  Acaba y deja residente
			;  PSP, variables y rutina rsi.
	ret
INSTALADOR ENDP

DESINSTALAR_57H PROC	; Desinstala RSI de INT 40h
	push ax bx cx ds es

	mov cx, 0
	mov ds, cx	;  Segmento de vectores interrupción
	mov es, ds:[ 57h*4+2 ]      ;  Lee segmento de RSI
	mov bx, es:[ 2Ch ]  ;  Lee segmento de entorno del PSP de RSI
	
	mov ah, 49h 
	int 21h	;  Libera segmento de RSI (es)
	mov es, bx
	int 21h	;  Libera segmento de variables de entorno de RSI

	;  Pone a cero vector de interrupción 40h
	cli
	mov ds:[ 57h*4 ], cx;  cx = 0
	mov ds:[ 57h*4+2 ], cx
	sti

	pop es ds cx bx ax
	ret
DESINSTALAR_57H 
ENDP

codigo ENDS
END inicio