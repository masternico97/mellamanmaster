;******************************************************************************* 
; PRACTICA 2 DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************

; DEFINICION DEL SEGMENTO DE DATOS 

DATOS SEGMENT 

		
	MATRIZ DB 10, 22, 211, 0, 11, 7, 244, 32, 12
	ADET DB  "  |A|=	",'$'
	IGUAL	DB "  = $"
	MATRIZ_LEC	DB	42 DUP(0)
	SOLUCION	DB	4 DUP (0)
	SOL_ASCII	DB  14 DUP(0)		
	CLR_PANT 	DB 	1BH,"[2","J$"
	PREG_MODE 	DB 	1BH,"[10;1fElige el modo de empleo del programa $"
	OPC_1		DB	1BH,"[11;1f1)Calcular el determinante con valores por defecto $"
	OPC_2		DB	1BH,"[12;1f2)Calcular el determinante con valores introducidos por teclado $"
	TEXTO_OPC_2	DB	1BH,"[14;1fIntroduce los nueve numeros del 0 al 255 separados por espacios, y con un espacio tras el ultimo numero: $"
	ESCOGIDO	DB 	3 DUP (0)
	ERR_CADENA	DB  1BH,"[13;1fEl numero introducido es erroneo, pruebe de nuevo con 1 o 2.$"
	NUM_LEIDOS	DB  0
	
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

	; FIN DEL PROGRAMA SI EL NUMERO INTRODUCIDO NO ES CORRECTO
    MOV AX, 4C00H 
    INT 21H 
	

OPCION1:
	MOV AH,9
	CALL CALCULAR_MATRIZ
	JMP IMPRESION
	
OPCION2:
	CALL MATRIZ_TECLADO
	CALL CALCULAR_MATRIZ
	JMP IMPRESION
	
START ENDP

; COMIENZO DEL PROCEDIMIENTO (MATRIZ_TECLADO)
MATRIZ_TECLADO PROC	;RECIBE UNA MATRIZ POR TECLADO CON LOS NUMEROS ENTRE ESPACIOS(ASCII = 32)
	
	MOV AH,9
	MOV DX, OFFSET TEXTO_OPC_2	;MUESTRA "Introduce...
	INT 21H	
	
	MOV MATRIZ_LEC[0], 39		
	
	MOV AH,0AH	;ALMACENA LOS NUMEROS EN ASCII
	MOV DX, 0
	MOV DX, OFFSET MATRIZ_LEC
	INT 21H	

	;COMENZAMOS A DIVIDIR LA CADENA, PASANDOLA DE ASCII A HEX NUMERO POR NUMERO
	MOV SI, 2
	MOV DI, 0 
	
INI_LEC:	
	MOV AL, 0 ;10 ELEVADO AL NUMERO DE DIGITOS DEL NUMERO, DESPUES EN NUM_LEC ALMACENAMOS EL DIGITO
	MOV BX, SI ;GUARDAMOS POR QUE POSICION EMPIEZA EL DIGITO
	MOV CL, 0 ;BANDERA QUE INDICA QUE EL NUMERO ES NEGATIVO(1)
	MOV DH, 10
	MOV DL, 0
	
CUENTA_NUM_DIG:
	CMP MATRIZ_LEC[SI], 32
	JE INIC_DI
	CMP MATRIZ_LEC[SI], 45
	JNE CONTINUACION
	INC SI
	JMP CUENTA_NUM_DIG
	
CONTINUACION:
	CMP AL, 0
	JNE CAMBIO10
	ADD AL, 1
	INC SI
	JMP CUENTA_NUM_DIG
CAMBIO10:
	MUL DH
	INC SI
	JMP CUENTA_NUM_DIG
	
	
INIC_DI:	;GUARDAMOS POR DONDE EMPIEZA EL SIGUIENTE NUMERO
	PUSH SI
	MOV DL, 0
	MOV SI, BX
	MOV BH, 0
	MOV BL, AL
NUM_LEC:
	CMP MATRIZ_LEC[SI], 45 ;COMPROBAMOS SI ES NEGATIVO(TIENE SIGNO MENOS)
	JNE CONT_NUM_LEC
	MOV CL, 1
	INC SI
CONT_NUM_LEC:
	MOV BH, AL
	MOV AL, MATRIZ_LEC[SI]
	SUB AL, 48
	MUL BL
	ADD DL, AL
	MOV AL, BL
	DIV DH
	MOV BL, AL
	INC SI
	CMP AL, 0
	JNE NUM_LEC
	CMP CL, 1
	JNE ESCRITURA_NUM
	MOV AL, 255
	SUB AL, DL
	INC AL
	MOV DL, AL
	
ESCRITURA_NUM:
	MOV MATRIZ[DI], DL ;GUARDAMOS EN MEMORIA EL NUMERO
	INC DI	
	POP SI
	INC SI
	MOV AL, NUM_LEIDOS
	INC AL
	CMP AL, 9
	JE FIN_F
	MOV NUM_LEIDOS, AL
	JMP INI_LEC

FIN_F:
	
MATRIZ_TECLADO ENDP

	
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
	MOV BH, 0 ;BANDERA PARA INDICAR SI ENCONTRAMOS PRIMER BIT
	MOV CX, 10000
	MOV BL, 10

	;PESE A QUE ALMACENEMOS CUATRO BYTES, EL PROGRAMA SOLO ESTÁ PREPARADO PARA IMPRIMIR NUMEROS DE 2 BYTES E IMPRIMIRÁ DE MANERA INCORRECTA LO MAYORES
INI_BL:
	MOV AH,	SOLUCION[2]
	MOV AL, SOLUCION[3]
	
ASCIIDET:
	DIV CX
	CMP BH, 1 
	JE GUARDAR
	CMP AX, 0
	JE DIVISION10
	
GUARDAR:
	MOV BH, 1
	ADD	AL, 48	;ANADIMOS 48 PARA OBTENER EL ASCII DEL NUMERO EN CUESTION
	MOV SOL_ASCII[DI], AL
	INC DI
	CMP CX, 1
	JE RETORNO ;SE HA TERMINADO DE PASAR A ASCII EL DETERMINANTE
	
DIVISION10:	;PASA AL SIGUIENTE DIGITO DEL NUMERO QUE SE ESTA IMPRIMIENDO CAMBIANDO EL VALOR DEL DIVISOR
	MOV AX, 0
	MOV AX, CX
	MOV CL, BH
	CMP AX, 10000
	JNE CONTINUAC
	MOV AX, 1000
	MOV BH, CL
	MOV CX, AX
	MOV AX, DX ;GUARDAMOS EL RESTO EN AX PARA SEGUIRLO DIVIDIENDO SUCESIVAMENTE
	MOV DX, 0
	CMP BH, 1
	JNE INI_BL
	JMP ASCIIDET
	
CONTINUAC:
	DIV BL 		;DIVIDE ENTRE 10
	MOV BH, CL
	MOV CX, AX
	MOV AX, DX ;GUARDAMOS EL RESTO EN AX PARA SEGUIRLO DIVIDIENDO SUCESIVAMENTE
	MOV DX, 0
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
	CMP BL, 1
	JNE OTRO
	CMP AL, 0
	JNE NUM
	MOV DL, 48	
	MOV AH, 2H	;IMPRIMIMOS UN CERO
	INT 21H
	JMP DIV10
	
OTRO:
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

BUCLE:
	INC SI
	MOV CH, 0
	CMP SI, DI
	JE RECMAT
	JMP INIB
	
ESPACIO:
	MOV DL, 32	
	MOV AH, 2H	;IMPRIMIMOS DOS ESPACIOS
	INT 21H
	JMP CONT
	
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

