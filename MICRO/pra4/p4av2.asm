;*******************************************************************************
; PRACTICA 4 APARTADO A VERSION 2, CON  LOS CAMBIOS DE LA APARTADO 3
; SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************


codigo SEGMENT
	ASSUME cs : codigo
	ORG 256
inicio: jmp instalador

; Variables globales
	CLR_PANT 	  DB 1BH,"[2","J$"
	INSTALADO 	  DB 1BH,"[10;1fEl driver esta instalado $"
	DESINSTALADO  DB 1BH,"[10;1fEl driver no esta instalado $"
	GRUPO		  DB 1BH,"[11;1fPareja numero  10$ "
	NOMBRES		  DB 1BH,"[12;1fRealizado por Alvaro Sanchez y Nicolas Serrano $"
	USO			  DB 1BH,"[13;1fEjecute el programa con /I para instalar y con /D para desisntalar $"
	INSTALANDO 	  DB 1BH,"[10;1fEl driver se esta instalando $"
	DESINSTALANDO DB 1BH,"[10;1fEl driver se esta desinstalando $"
	VECTOR_INT DD 0
	CONTADOR_1CH DB 0

; Rutina de servicio a la interrupción
rsi PROC FAR
	PUSH AX BX DX SI

	;CALL INSTALADOR_1CH

	CMP AH, 10H
	JZ OPCION_10H
	CMP AH, 11H
	JZ OPCION_11H

OPCION_10H:
	CALL CODIFICADOR
	JMP FIN

OPCION_11H:
	CALL DECODIFICADOR

FIN:
	;CALL DESINSTALADOR_1CH
	POP SI DX BX AX
	iret
rsi ENDP

CONTADOR PROC NEAR

	PUSH AX

	MOV AL, CONTADOR_1CH
	CMP AL, 18 ; SI ES IGUAL A 18, HA TERMINADO UN CICLO(1 SEGUNDO) Y LO RESETEAMOS
	JNE INCR
	MOV AL, 0

INCR:
	INC AL ;INCREMENTAMOS EL CONTADOR SIEMPRE QUE LO EJECUTAMOS
	MOV CONTADOR_1CH, AL

	POP AX
	IRET

CONTADOR ENDP

INSTALADOR_1CH PROC NEAR

	MOV AX, 0
	MOV ES, AX
	MOV AX, ES:[ 1Ch*4 ]
	MOV BX, ES:[ 1Ch*4+2 ]
	MOV WORD PTR VECTOR_INT[0], AX
	MOV WORD PTR VECTOR_INT[2], BX

	mov ax, 0
	mov es, ax
	mov ax, OFFSET CONTADOR
	mov bx, cs

	push ax
	in al, 21h ;Guardamos el estado inicial
	mov dl, al
	or al, 00000001b
	out 21h, al ;Desactivamos las interrupciones poniendo el bit a 1 de PIC-0
	pop ax

	mov es:[ 1Ch*4 ], ax
	mov es:[ 1Ch*4+2 ], bx

	mov al, dl
	out 21h, al ;Reseteamos el valor del primer bit de la mascara

	mov dx, OFFSET INSTALADOR_1CH

	RET ;Al no querer acabar el programa no acabamos su ejecucion con int

INSTALADOR_1CH ENDP

DESINSTALADOR_1CH PROC NEAR

	push ax bx cx ds es

	mov cx, 0
	mov ds, cx	;  Segmento de vectores interrupción
	mov es, ds:[ 1Ch*4+2 ]      ;  Lee segmento de RSI
	mov bx, es:[ 2Ch ]  ;  Lee segmento de entorno del PSP de RSI

	mov ah, 49h
	int 21h	;  Libera segmento de RSI (es)
	mov es, bx
	int 21h	;  Libera segmento de variables de entorno de RSI

	;  Pone a su valor original el vector de interrupción 1Ch
	in al, 21h ;Guardamos el estado inicial
	push ax
	or al, 00000001b
	out 21h, al ;Desactivamos las interrupciones poniendo el bit a 1 de PIC-0

	mov ax, WORD PTR VECTOR_INT[0]
	mov bx, WORD PTR VECTOR_INT[2]
	mov ds:[ 1Ch*4 ], ax
	mov ds:[ 1Ch*4+2 ], bx

	pop ax
	out 21h, al ;Reseteamos el valor del primer bit de la mascara

	pop es ds cx bx ax

	RET

DESINSTALADOR_1CH ENDP

CODIFICADOR PROC NEAR

  PUSH DX
  MOV AH,2H
  MOV DL, 10 ;ESCRIBE UN SALTO DE LINEA
  INT 21H
  POP DX

	XOR SI,SI
  XOR CX, CX
	MOV BX, DX
BUCLE:
	MOV AL, [BX][SI]
	CMP AL, 13 ;SI DETECTAMOS UN SALTO DE CARRO ACABAMOS
	JE FIN_COD
  CMP AL, 32
  JE ESPACIO
	CMP AL, 48 ;SI ES MAS PEQUEÑO QUE EL 0 ES UN CARACTER NO PERMITIDO
	JB INCREMENTO
	CMP AL, 90
	JA INCREMENTO ;SI ES MAS GRANDE QUE LA Z ES UN CARACTER NO PERMITIDO
	CMP AL, 32
	JE INCREMENTO
	CMP AL, 48 ;SI ES CERO IMPRIME 66
	JNE NUM_Y_LET
	MOV AH, 2H
	;CALL BUCLE_CONTADOR
	MOV DL, 54 ;IMPRIME UN 6
	INT 21H
	;CALL BUCLE_CONTADOR
	MOV DL, 54 ;IMPRIME UN 6
	INT 21H

INCREMENTO: ;INCREMENTA EL INDICE DE LECTURA Y REGRESA AL BUCLE PRINCIPAL
	INC SI
	JMP BUCLE

ESPACIO:
	MOV AH,2H	; IMPRIMIMOS UNO DE LOS NUMEROS DE LA PARTE SUPERIOR
	MOV DL, 32 ;IMPRIME UN ESPACIO
	;CALL BUCLE_CONTADOR
	INT 21H
	JMP INCREMENTO

FIN_COD:
  RET

NUM_Y_LET:
	CMP AL, 57 ;ES UN NUMERO
	JBE IMP_NUM
	CMP AL, 65 ;ES UNA LETRA
	JAE IMP_LET
	JMP INCREMENTO

IMP_NUM:
	SUB AL, 48
	XOR AH, AH
	MOV CL, 6
	DIV CL
	ADD AL, 49
	CMP AH, 0
	JNE IMP_NUM_CONT
	MOV AH, 6
	DEC AL
IMP_NUM_CONT:
	ADD AH, 48
	MOV CL, AH
	MOV AH,2H
	;CALL BUCLE_CONTADOR
	MOV DL, AL ;IMPRIME LA FILA
	INT 21H
	;CALL BUCLE_CONTADOR
	MOV DL, CL ;IMPRIME LA COLUMNA
	INT 21H
	JMP INCREMENTO

IMP_LET:
	SUB AL, 65
	XOR AH, AH
	ADD AX, 10
	MOV CL, 6
	DIV CL
	ADD AL, 49
	CMP AH, 0
	JNE IMP_LET_CONT
	MOV AH, 6
	DEC AL
IMP_LET_CONT:
	ADD AH, 48
	MOV CL, AH
	MOV AH,2H
	MOV DL, AL ;IMPRIME LA FILA
	;CALL BUCLE_CONTADOR
	INT 21H
	MOV DL, CL ;IMPRIME LA COLUMNA
	;CALL BUCLE_CONTADOR
	INT 21H
	JMP INCREMENTO

CODIFICADOR ENDP

;FUNCION QUE DECODIFICA EL CODIGO DE ASCII A POLIBIO
DECODIFICADOR PROC NEAR

  PUSH DX
  MOV AH,2H
  MOV DL, 10 ;ESCRIBE UN SALTO DE LINEA
  INT 21H
  POP DX

	XOR SI,SI
  XOR CX, CX
	MOV BX, DX
BUCLE_D:
	MOV AX, WORD PTR [BX][SI]
	CMP AH, 13 ;SI DETECTAMOS UN SALTO DE CARRO ACABAMOS
	JE FIN_COD_D
  CMP AL, 13 ;SI DETECTAMOS UN SALTO DE CARRO ACABAMOS
  JE FIN_COD_D
	CMP AL, 32
	JNE VALIDO
	INC SI
  MOV AH,2H	; IMPRIMIMOS UNO DE LOS NUMEROS DE LA PARTE SUPERIOR
  MOV DL, 32 ;IMPRIME UN ESPACIO
	;CALL BUCLE_CONTADOR
  INT 21H
	JMP BUCLE_D

VALIDO: ;COMPRUEBA QUE SE NOS HA DADO UNA FILA Y COLUMNA VALIDA
	CMP AL, 49
	JB INCREMENTO_D
	CMP AL, 54
	JA INCREMENTO_D
	CMP AH, 49
	JB INCREMENTO_D
	CMP AH, 54
	JA INCREMENTO_D

	;IMPRIMIMOS EL CASO ESPECIAL DEL CERO
	CMP AL, 54
	JNE COMPRUEBA
	CMP AH, 54
	JNE COMPRUEBA
  MOV AH,2H
	MOV DL, 48 ;IMPRIME UN CERO
	;CALL BUCLE_CONTADOR
	INT 21H
  JMP INCREMENTO_D

COMPRUEBA: ;COMPRUEBA SI ES UN NUMERO O UNA LETRA
	CMP AL, 50
	JE LINEA2
	JB IMP_NUM_D
	JMP IMP_LET_D

LINEA2: ;SI ESTA EN LA LINEA 2 SALTARA AQUI Y COMPROBARA SI ES NUMERO O LETRA
	CMP AH, 52
	JB IMP_NUM_D
	JMP IMP_LET_D

FIN_COD_D:
	RET

IMP_NUM_D:
	MOV DL, 6
	SUB AL, 49
	SUB AH, 48
	MOV CL, AH
	XOR AH, AH
	MUL DL
	ADD AL, CL
	ADD AL, 48
	MOV AH,2H
	MOV DL, AL ;IMPRIME EL NUMERO
	;CALL BUCLE_CONTADOR
	INT 21H
	JMP INCREMENTO_D

IMP_LET_D:
	MOV DL, 6
	SUB AL, 49
	SUB AH, 48
	MOV CL, AH
	XOR AH, AH
	MUL DL
	ADD AL, CL
	ADD AL, 55
	MOV AH,2H
	MOV DL, AL ;IMPRIME LA LETRA
	;CALL BUCLE_CONTADOR
	INT 21H

INCREMENTO_D:
	ADD SI, 2
	JMP BUCLE_D

DECODIFICADOR ENDP

CONTADOR_BUCLE PROC NEAR

BUCLE_CONTADOR: ;COMPRUEBA EL CONTADOR Y NO CONTINUA HASTA QUE SEA 18, IMPRIMIENDO UN CARACTER PR SEGUNDO
	CMP CONTADOR_1CH, 18
	JNE BUCLE_CONTADOR
	RET

CONTADOR_BUCLE ENDP

INSTALADOR PROC
	MOV AH,9	; BORRA LA PANTALLA
	MOV DX, OFFSET CLR_PANT
	INT 21H
	MOV DL, ES:[80h] ;LEEMOS EL NUMERO DE CARACTERES QUE SE PASAN POR ARGUMENTO
	MOV DH, 0
	MOV SI, DX
LEER_PSP:
	CMP SI, 0
	JE SIN_PARAMETROS
	MOV DL, ES:[SI]+81h	;LEEMOS LOS CARACTERES DE ENTRADA DE DERECHA A IZQUIERDA
	CMP DL, '/'
	JE MODO
	DEC SI
	JNE LEER_PSP

SIN_PARAMETROS:
	MOV AX, 0
	MOV ES, AX
	MOV AX, ES:[ 57h*4 ]
	MOV BX, ES:[ 57h*4+2 ]
	ADD AX, BX	;COMPROBAMOS SI EL VECTOR DE INTERRUPCION ESTÁ INICIALIZADO

	JNE INSTALLED

	MOV AH,9
	MOV DX, OFFSET DESINSTALADO
	INT 21H

CONTINUAR_SIN_PARAMETROS:
	MOV DX, OFFSET GRUPO
	INT 21H

	MOV DX, OFFSET NOMBRES
	INT 21H

	MOV DX, OFFSET USO
	INT 21H

	JMP FIN_INSTALAR

INSTALLED:
	MOV AH,9
	MOV DX, OFFSET INSTALADO
	INT 21H
	JMP CONTINUAR_SIN_PARAMETROS

MODO:
	MOV DL, ES:[SI]+81h+1	;Leemos los caracter despues de /
	CMP DL, 'D'
	JE DESINSTALAR
	CMP DL, 'I'
	JE INSTALAR
	JMP FIN_INSTALAR	;SI NO SE ENCUENTA NI D NI I SE TERMINA LA EJECUCION

DESINSTALAR:
	MOV AX, 0
	MOV ES, AX
	MOV AX, ES:[ 57h*4 ]
	MOV BX, ES:[ 57h*4+2 ]

	ADD AX, BX	;COMPROBAMOS SI EL VECTOR DE INTERRUPCION ESTÁ INICIALIZADO
	JE FIN_INSTALAR	;SI AMBOS SON 0 EL PROGRAMA NO ESTA INSTALADO Y NO SE DEBE DESISNTALAR

	JMP DESINSTALAR_57H

FIN_INSTALAR:
	MOV AX, 4C00H
	INT 21h

INSTALAR:
	MOV AX, 0
	MOV ES, AX
	MOV AX, ES:[ 57h*4 ]
	MOV BX, ES:[ 57h*4+2 ]

	ADD AX, BX ;COMPROBAMOS SI EL VECTOR DE INTERRUPCION ESTÁ INICIALIZADO
	JNE FIN_INSTALAR	;SI NO SON 0 EL PROGRAMA ESTA INSTALADO Y NO SE DEBE DESISNTALAR

	MOV AH,9
	MOV DX, OFFSET INSTALANDO
	INT 21H

	mov ax, 0
	mov es, ax
	mov ax, OFFSET rsi
	mov bx, cs
	cli
	mov es:[ 57h*4 ], ax
	mov es:[ 57h*4+2 ], bx
	sti
	mov dx, OFFSET INSTALADOR

	CALL INSTALADOR_1CH

	int 27h ;  Acaba y deja residente
			;  PSP, variables y rutina rsi.

DESINSTALAR_57H:	; Desinstala RSI de INT 57h
	push ax bx cx ds es

	MOV AH,9
	MOV DX, OFFSET DESINSTALANDO
	INT 21H

	mov cx, 0
	mov ds, cx	;  Segmento de vectores interrupción
	mov es, ds:[ 57h*4+2 ]      ;  Lee segmento de RSI
	mov bx, es:[ 2Ch ]  ;  Lee segmento de entorno del PSP de RSI

	mov ah, 49h
	int 21h	;  Libera segmento de RSI (es)
	mov es, bx
	int 21h	;  Libera segmento de variables de entorno de RSI

	;  Pone a cero vector de interrupción 57h
	cli
	mov ds:[ 57h*4 ], cx;  cx = 0
	mov ds:[ 57h*4+2 ], cx
	sti

	CALL DESINSTALADOR_1CH

	pop es ds cx bx ax

	JMP FIN_INSTALAR

INSTALADOR ENDP

codigo ENDS
END inicio
