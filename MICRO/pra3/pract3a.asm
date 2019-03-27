;*******************************************************************************
; PRACTICA 3 APARTADO A DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************

;_______________________________________________________________
; DEFINICION DEL SEGMENTO DE CODIGO
_TEXT SEGMENT BYTE PUBLIC 'CODE'
ASSUME CS: _TEXT
; COMIENZO DEL PROCEDIMIENTO (_computeControlDigit)
PUBLIC _computeControlDigit 
_computeControlDigit PROC FAR

	PUSH BP	;GUARDAMOS EN LA PILA TODOS LOS REGISTROS QUE VAMOS A SOBRESCRIBIR
	PUSH BX
	PUSH CX
	PUSH DX
	PUSH SI
	MOV BP, SP
	ADD BP, 14
	LES BX, [BP]

	;OBTENEMOS LOS 12 PRIMEROS DIGITOS DE EL CODIGO EN ASCII PASANDOLOS A DECIMAL Y LOS GUARDAMOS EN LA PILA
	MOV CX, 0
LECTURA:
	MOV DX, ES:[BX]	;LOS CHAR SE EXTRAEN DE 2 EN 2
	SUB DH, 48	;CONVERSION NUMERO EN ASCII A HEX
	SUB DL, 48	;CONVERSION NUMERO EN ASCII A HEX
	PUSH DX	;GUARDAMOS LOS 2 NUMEROS CONVERTIDOS EN LA PILA
	INC BX
	INC CX
	CMP CX, 6
	JL LECTURA

PASO_1:
	MOV BX, 0	;BX ALMACENARA LA SUMA DE LOS DIGITOS IMPARES
	MOV CX, 0	;CX ALMACENARA LA SUMA DE LOS VALORES PARES * 3
	MOV SI, 0	;SI SE UTILIZA COMO CONTADOR DE BUCLE PUES TENEMOS CX UTILIZADO
BUCLE_PASO_1:	;SUMAMOS LOS VALORES DE LOS DIGITOS IMPARES Y SUMAMOS LOS VALORES DE LOS PARES * 3 (POR SEPARADO)
	POP DX
	ADD	BL, DL
	MOV AL, DH
	MOV AH, 3
	MUL AH
	ADD CX, AX
	INC SI
	CMP SI, 6
	JL BUCLE_PASO_1

PASO_2:
	ADD BX, CX

PASO_3y4:
	MOV AX, BX
	MOV DL, 10
	DIV DL	;CALCULAMOS EL PRIMER DIGITO DEL NUMERO OBTENIDO ANTERIORMENTE EXTAYENDO EL COCIENTE DE DIVIDIR ENTRE 10
	MOV AL, 00h
	SUB AL, AH	;HACEMOS 0 - EL COCIENTE DE LA DIVISIÖN
	CMP AL, 0
	JE FIN
	MOV AH, 0FFh	;INTRODUCIMOS FF EN LA PARTE ALTA DEL REGISTRO AX PUES LA SOLUCION SI NO ES 0 ES NEGATIVA
	NEG AX	;LA SOLUCIÓN SE NOS DAN EN NEGATIVO Y LA PASAMOS A POSITIVO

FIN:
	ADD AX, 48	;SE PASA EL NUMERO EN AX A ASCII
	POP SI	;SACAMOS DE LA PILA LOS REGISTROS QUE HABÍAMOS GUARDADO AL PRINCIPIO PARA QUE NO SE SOBRESCRIBIERAN
	POP	DX
	POP CX
	POP BX
	POP BP   
	RET
_computeControlDigit ENDP
_TEXT ENDS
END
