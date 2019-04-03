;*******************************************************************************
; PRACTICA 4 APARTADO A DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************


codigo SEGMENT
	ASSUME cs : codigo
	ORG 256
inicio: jmp instalador

; Variables globales
tabla DB 234 ;DIGITO DE CONTROL QUE COMPROBARÁ SI EL PRORGRAMA ESTÁ INSTALADO
; Rutina de servicio a la interrupción
rsi PROC FAR
	CMP AH, 10H
	JZ OPCION_10H
	CMP AH, 11H
	JZ OPCION_11H

FIN:
	iret

OPCION_10H:
	CALL CODIFICADOR
	
OPCION_11H:
	CALL DECODIFICADOR

rsi ENDP

;FUNCION QUE CODIFICA EL CODIGO DE ASCII A POLIBIO
CODIFICADOR PROC

CODIFICADOR ENDP

;FUNCION QUE DECODIFICA EL CODIGO DE ASCII A POLIBIO
DECODIFICADOR PROC

DECODIFICADOR ENDP

instalador PROC
	mov ax, 0
	mov es, ax
	mov ax, OFFSET rsi
	mov bx, cs
	cli
	mov es:[ 57h*4 ], ax
	mov es:[ 57h*4+2 ], bx
	sti
	mov
	dx, OFFSET instalador 
	int 27h ;  Acaba y deja residente
			;  PSP, variables y rutina rsi.
instalador ENDP

desinstalar_57h PROC	; Desinstala RSI de INT 40h
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
desinstalar_57h 
ENDP

codigo ENDS
END inicio