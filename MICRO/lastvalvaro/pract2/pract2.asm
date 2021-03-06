;******************************************************************************* 
; PRACTICA 2 DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************

; DEFINICION DEL SEGMENTO DE DATOS 

DATOS SEGMENT 

		
	MATRIZ DB 3, 47, 130, 201, 93, 47, 193, 161, 212
	ADET DB  "  |A|=	",'$'
	MATRIZ_LEC	DB	40 DUP(0)
	SOLUCION	DB	4 DUP (0)
	SOL_ASCII	DB  14 DUP(0)		
	CLR_PANT 	DB 	1BH,"[2","J$"
	PREG_MODE 	DB 	1BH,"[10;1fElige el modo de empleo del programa $"
	OPC_1		DB	1BH,"[11;1f1)Calcular el determinante con valores por defecto $"
	OPC_2		DB	1BH,"[12;1f2)Calcular el determinante con valores introducidos por teclado $  "
	ESCOGIDO	DB 	3 DUP (0)
	TEXTO_OPC_2	DB	1BH,"[13;1f2)Introduce los nueve numeros del 0 al 255 separados por espacios $  "
	IGUAL	DB "  = $"
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
	
	;FIN DE LAS INICIALIZACIONES
	
	;COMIENZO DEL PROGRAMA 
	MOV AH,9	; BORRA LA PANTALLA
	MOV DX, OFFSET CLR_PANT
	INT 21H
	
	MOV DX, OFFSET PREG_MODE ;ESCRIBE "Elige ...
	INT 21H
	
	MOV DX, OFFSET OPC_1 ;ESCRIBE "1)Calcular ...
	INT 21H
	
	MOV DX, OFFSET OPC_2 ;ESCRIBE "2)Calcular ...
	INT 21H
	
	MOV AH,0AH			;ALMACENA LA OPCION
	MOV DX, OFFSET ESCOGIDO
	
	MOV ESCOGIDO[0], 2		;MAXIMO NUMERO DE CARACTERES
	INT 21H
	
	MOV AH, 00H	;CONVIERTE EL NUMERO LEIDO EN ASCII EN INT
	MOV AL, ESCOGIDO[2]
	SUB AL, 48
	
	CMP AX, 1 ;SI SE HA ESCOGIDO 1 SALTA A LA RUTINA OPCION1
	JE OPCION1
	
	CMP AX, 2 ;SI SE HA ESCOGIDO 2 SALTA A LA RUTINA OPCION2
	JE OPCION2 

OPCION1:
	MOV AH,9
	CALL CALCULAR_MATRIZ
	JMP IMPRESION
	
OPCION2:
	MOV AH,9
	MOV DX, OFFSET TEXTO_OPC_2	;MUESTRA "Introduce...
	INT 21H	
	
	MOV AH,0AH	;ALMACENA LOS NUMEROS EN ASCII
	MOV DX, OFFSET MATRIZ_LEC
	
	
	CALL CALCULAR_MATRIZ
	JMP IMPRESION
	

	
START ENDP
; COMIENZO DEL PROCEDIMIENTO (CALCULAR_MATRIZ)
CALCULAR_MATRIZ PROC	;SE CALCULA MEDIANTE LA REGLA DE LAPLACE
	MOV BX, 3	;FILA 2
	MOV SI, 1	;COLUMNA 2
	MOV AL, MATRIZ[BX][SI]	;GUARDAMOS DATO FILA 2 COLUMNA 2
	
	MOV BX, 6	;FILA 3
	MOV SI, 2	;COLUMNA 3
	IMUL MATRIZ[BX][SI]	;MULTIPLICAMOS CON SIGNO EL DATO DE FILA 2 COLUMNA 2 POR AH
	PUSH AX
	
	MOV	SI, 1	;COLUMNA 2
	MOV AL, MATRIZ[BX][SI]	;GUARDAMOS DATO FILA 3 COLUMNA 2 EN AH
	
	MOV BX, 3	;FILA 2
	MOV SI, 2	;COLUMNA 3
	IMUL MATRIZ[BX][SI]	;MULTIPLICAMOS CON SIGNO EL DATO DE FILA 2 COLUMNA 3 POR AH
	
	POP DX
	SUB DX, AX
	
	MOV BX, 0	;FILA 1
	MOV SI, 0	;COLUMNA 1
	MOV AH, MATRIZ[BX][SI]
	MOV CL, 08h
	SAR AX, CL
	
	IMUL DX	;CALCULAMOS EL PRIMER TERMINO DE LAPLACE
	
	PUSH DX	;GUARDAMOS LA PARTE ALTA DEL NUMERO
	PUSH AX	;GUARDAMOS LA PARTE BAJA DEL NUMERO
	
	
	MOV BX, 3	;FILA 2
	MOV SI, 0	;COLUMNA 1
	MOV AL, MATRIZ[BX][SI]	;GUARDAMOS DATO FILA 2 COLUMNA 1
	
	MOV BX, 6	;FILA 3
	MOV SI, 2	;COLUMNA 3
	IMUL MATRIZ[BX][SI]	;MULTIPLICAMOS CON SIGNO EL DATO DE FILA 2 COLUMNA 2 POR AH
	PUSH AX
	
	MOV	SI, 0	;COLUMNA 1
	MOV AL, MATRIZ[BX][SI]	;GUARDAMOS DATO FILA 3 COLUMNA 1 EN AH
	
	MOV BX, 3	;FILA 2
	MOV SI, 2	;COLUMNA 3
	IMUL MATRIZ[BX][SI]	;MULTIPLICAMOS CON SIGNO EL DATO DE FILA 2 COLUMNA 3 POR AH
	
	POP DX
	SUB DX, AX
	
	MOV BX, 0	;FILA 1
	MOV SI, 1	;COLUMNA 2
	MOV AH, MATRIZ[BX][SI]
	MOV CL, 08h
	SAR AX, CL 
	
	IMUL DX	;CALCULAMOS EL SEGUNDO TERMINO DE LAPLACE
	
	PUSH DX	;GUARDAMOS LA PARTE ALTA DEL NUMERO
	PUSH AX	;GUARDAMOS LA PARTE BAJA DEL NUMERO
	
	
	MOV BX, 3	;FILA 2
	MOV SI, 0	;COLUMNA 1
	MOV AL, MATRIZ[BX][SI]	;GUARDAMOS DATO FILA 2 COLUMNA 1
	
	MOV BX, 6	;FILA 3
	MOV SI, 1	;COLUMNA 2
	IMUL MATRIZ[BX][SI]	;MULTIPLICAMOS CON SIGNO EL DATO DE FILA 3 COLUMNA 2 POR AH
	PUSH AX
	
	MOV	SI, 0	;COLUMNA 1
	MOV AL, MATRIZ[BX][SI]	;GUARDAMOS DATO FILA 3 COLUMNA 1 EN AH
	
	MOV BX, 3	;FILA 2
	MOV SI, 1	;COLUMNA 2
	IMUL MATRIZ[BX][SI]	;MULTIPLICAMOS CON SIGNO EL DATO DE FILA 2 COLUMNA 2 POR AH
	
	POP DX
	SUB DX, AX
	
	MOV BX, 0	;FILA 1
	MOV SI, 2	;COLUMNA 3
	MOV AH, MATRIZ[BX][SI]
	MOV CL, 08h
	SAR AX, CL
	
	IMUL DX	;CALCULAMOS EL TERCER TERMINO DE LAPLACE
	
	
	POP CX	;PARTE BAJA DEL SEGUNDO TERMINO
	POP BX	;PARTE ALTA DEL SEGUNDO TERMINO
	SUB AX, CX	;RESTAMOS LA PARTE BAJA DEL SEGUNDO TERMINO AL TERCERO
	SBB DX, BX	;RESTAMOS LA PARTE PARTE ALTA DEL SEGUNDO TERMINO AL TERCERO + CF
	
	POP CX	;PARTE BAJA DEL PRIMER TERMINO
	POP BX	;PARTE ALTA DEL PRIMER TERMINO
	
	ADD CX, AX	;SUMAMOS LA PARTE BAJA DE SEGUNDO TERMINO + TERCER TERMINO AL PRIMER TERMINO
	ADC BX, DX	;SUMAMOS LA PARTE ALTA DE SEGUNDO TERMINO + TERCER TERMINO + CF AL PRIMER TERMINO
	
	MOV SOLUCION[0], BH	;PARTE ALTA DE LA PARTE ALTA
	MOV SOLUCION[1], BL	;PARTE BAJA DE LA PARTE ALTA
	MOV SOLUCION[2], CH	;PARTE ALTA DE LA PARTE BAJA
	MOV SOLUCION[3], CL	;PARTE BAJA DE LA PARTE BAJA
	
	MOV DI, 0 ;INICIALIZAMOS EL INDICE DE ESCRITURA A CERO
	CMP BH, 80h
	JB DET ;SALTA SI NO ES NEGATIVO
	
	;PASAMOS EL NUMERO SI ES NEGATIVO A SU VALOR ABSOLUTO
	MOV AX, 0FFFFh ;PARTE ALTA
	MOV DX, 0FFFFh ;PARTE BAJA
	SUB AX, CX 
	SBB DX, BX
	ADD AX, 1
	ADC DX, 0
	
	MOV SOLUCION[0], DH	;PARTE ALTA DE LA PARTE ALTA
	MOV SOLUCION[1], DL	;PARTE BAJA DE LA PARTE ALTA
	MOV SOLUCION[2], AH	;PARTE ALTA DE LA PARTE BAJA
	MOV SOLUCION[3], AL	;PARTE BAJA DE LA PARTE BAJA
	
	MOV SOL_ASCII[DI], 45
	INC DI
	
	;INICIALIZAMOS SI A 0, VAMOS A HACER UN BUCLE PARA ALMACENAR EN UNA CADENA EL DETERMINANTE
DET:
	MOV SI, 10
	MOV BH, 0 
	MOV CX, 10000
	MOV BL, 100
	MOV DI, 0 ;INDICA SI SE HA ENCONTRADO EL PRIMER DIGITO

INI_BL:
	MOV AH,	SOLUCION[0]
	MOV AL, SOLUCION[1]
	MOV DH,	SOLUCION[2]
	MOV DL, SOLUCION[3]
	
ASCIIDET:
	DIV CX
	DIV BL
	CMP BH, 1 
	JE GUARDAR
	CMP AL, 0
	JE DIVISION10
	
GUARDAR:
	MOV BH, 1
	MOV CL, AH	;GUARDAMOS EN CL EL RESTO, QUE SERA EL PROXIMO NUMERO A DIVIDIR
	ADD	AL, 48	;ANADIMOS 48 PARA OBTENER EL ASCII DEL NUMERO EN CUESTION
	MOV SOL_ASCII[DI], AL
	INC DI
	CMP CL, 1
	JE RETORNO
	
DIVISION10:	;PASA AL SIGUIENTE DIGITO DEL NUMERO QUE SE ESTA IMPRIMIENDO CAMBIANDO EL VALOR DEL DIVISOR
	CMP CL, 1
	JE RETORNO
	CMP BL, 1
	JE CONTINUAC
	MOV AL, BL
	MOV BL, 10
	MOV AH, 0
	DIV BL		;DIVIDE ENTRE 10
	MOV BL, AL
	CMP BH, 1
	JNE INI_BL
	MOV AL, CL
	MOV AH, 0
	JMP ASCIIDET

CONTINUAC:
	MOV AX, CX	
	DIV SI 		;DIVIDE ENTRE 10
	MOV CX, AX
	CMP BH, 1
	JNE INI_BL
	JMP ASCIIDET

RETORNO:
	MOV SOL_ASCII[DI], '$'

	RET
CALCULAR_MATRIZ ENDP



;COMIENZO DE LA IMPRESION DEL DETERMINANTE Y SU RESULTADO
IMPRESION:
	MOV CH, 0 ;BANDERA QUE INDICA SI HEMOS COLOCADO EL SIGNO MENOS
	MOV SI, 0 ;INDICE PARA SEGUIR LA MATRIZ
	MOV DI, 3 ;VARIABLE QUE USAREMOS EN EL BUCLE PARA SABER CUANDO ACABA UNA LINEA
	
	MOV AH,9	;BORRA LA PANTALLA
	MOV DX, OFFSET CLR_PANT
	INT 21H
	MOV AH, 2H	
	MOV DL, 9 ;TABULACION
	INT 21H
	
RECMAT:
	CMP SI, 0
	JE BARRA
	JMP FINLINEA
	
BARRA:
	MOV DL, 124	
	MOV AH, 2H	;IMPRIMIMOS UNA BARRA
	INT 21H
	
INIB:
	MOV DL, 32	
	MOV AH, 2H	;IMPRIMIMOS UN ESPACIO
	INT 21H
	MOV BL, 100
	MOV BH, 0	

INI:
	MOV AX, 0
	MOV AL, MATRIZ[SI]	
	CMP AL, 127	 ;COMPROBAMOS SI ES NEGATIVO
	JA IMPGUION
	
ASCII:
	DIV BL
	CMP BH, 1
	JE NUM
	CMP AL, 0
	JE DIV10
	
NUM:
	MOV BH, 1
	MOV CL, AH	;GUARDAMOS EN CL EL RESTO, QUE SERA EL PROXIMO NUMERO A DIVIDIR
	ADD	AL, 48	;ANADIMOS 48 PARA OBTENER EL ASCII DEL NUMERO EN CUESTION
	MOV DL, AL
	MOV AH, 2H	;IMPRIMIMOS EL DIGITO
	INT 21H
	JMP DIV10	

CAMBIO: 
	MOV AL, CL
	JMP ASCII	 

COMP:
	CMP AL, 0
	JE DIV10	
	JMP NUM
	
DIV10:
	CMP BL, 1
	JE BUCLE
	CMP BH, 0
	JE ESPACIO
	
CONT:	;PASA AL SIGUIENTE DIGITO DEL NUMERO QUE SE ESTA IMPRIMIENDO CAMBIANDO EL VALOR DEL DIVISOR
	MOV AX, 0
	MOV AL, BL
	MOV BL, 10	;DIVIDE ENTRE 10
	DIV BL
	MOV BL, AL
	CMP BH, 1
	JE CAMBIO
	JMP INI
	
ESPACIO:
	MOV DL, 32	
	MOV AH, 2H	;IMPRIMIMOS DOS ESPACIOS
	INT 21H
	JMP CONT

BUCLE:
	INC SI
	MOV CH, 0
	CMP SI, DI
	JE RECMAT
	JMP INIB
	
FINLINEA:	;IMPRIMIR LOS DISTINTOS CAMBIOS DE LINEA
	CMP DI, 3
	JE FINLINEA1
	CMP DI, 6
	JE FINLINEA2
	CMP DI, 9
	JE FINLINEA3

IMPGUION:	;IMPRIME EL SINGO MENOS SI NO SE HA HECHO YA Y SE 
			;MARCA CH = 1 PARA NO REPETIR LA IMPRESION
	CMP CH, 0
	JNE NEGATIVO
	MOV CH, 1
	MOV DL, 45	
	MOV AH, 2H	
	INT 21H ;IMPRIMIMOS UN SIGNO MENOS
	JMP NEGATIVO

FINLINEA1:	;IMPRIME EL FIN DE LA LINEA UNO, Y EL COMIENZO DE LA SIGUIENTE
	ADD DI, 3
	MOV DL, 32	;IMPRIMIMOS UN ESPACIO
	INT 21H
	MOV DL, 124	;IMPRIMIMOS UNA BARRA
	INT 21H
	MOV DL, 10 ;SALTO DE LINEA	
	INT 21H
	MOV AH, 9
	MOV DX, OFFSET ADET	;IMPRIME " |A|=	"
	INT 21H 
	MOV AH, 2H	
	JMP BARRA

FINLINEA2: ;IMPRIME EL FIN DE LA LINEA DOS CON EL RESULTADO DEL DETERMINANTE,
		   ;Y EL COMIENZO DE LA SIGUIENTE
	ADD DI, 3
	MOV DL, 32	;IMPRIMIMOS UN ESPACIO
	INT 21H
	MOV DL, 124	;IMPRIMIMOS UNA BARRA
	INT 21H
	MOV AH, 9
	MOV DX, OFFSET IGUAL ;IMPRIME UN IGUAL
	INT 21H
	MOV DX, OFFSET SOL_ASCII	
	INT 21H ;IMPRESION DEL RESULTADO DEL DETERMINANTE
	MOV AH, 2H	
	MOV DL, 10 ;SALTO DE LINEA	
	INT 21H
	MOV DL, 9 ;TABULACION
	INT 21H
	JMP BARRA

	
FINLINEA3:	;;IMPRIME EL FINAL DE LA LINEA TRES
	MOV DL, 32	;;IMPRIMIMOS UN ESPACIO
	INT 21H
	MOV DL, 124	;;IMPRIMIMOS UNA BARRA
	INT 21H
	MOV DL, 10 ;;SALTO DE LINEA	
	INT 21H

FIN:
     ; FIN DEL PROGRAMA 
    MOV AX, 4C00H 
    INT 21H 
	
NEGATIVO:	;;TRANSFORMA EL NUMERO DE C2 PARA PODER IMPRIMIRLO
	MOV AH, 255
	SUB AH, AL
	INC AH
	MOV AL, AH
	MOV AH, 0
	JMP ASCII


; FIN DEL SEGMENTO DE CODIGO 
CODE ENDS 
; FIN DEL PROGRAMA INDICANDO DONDE COMIENZA LA EJECUCION 
END START 

