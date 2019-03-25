;*******************************************************************************
; PRACTICA 3 APARTADO A DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************

; DEFINICION DEL SEGMENTO DE CODIGO

_TEXT SEGMENT BYTE PUBLIC  'CODE'
    ASSUME CS:_TEXT

PUBLIC _decodeBarCode
_decodeBarCode PROC FAR

  PUSH BP ;GUARDAMOS EL CONTENIDO DE BP PARA LUEGO RECOGERLO

	MOV BP, SP
	INC BP
	MOV CX, 21 ;13+2+4+2 = DISTANCIA HASTA LA ULTIMA POSICION DE LA PILA

CONTROLDIGIT:
	MOV AL, [BP]
	MOV DX, BP
	ADD BP, CX
	MOV [BP], AL
	MOV BP, DX
	INC BP

PRODUCTCODE:
  MOV DL, 1 ;UTILIZAREMOS ESTE REGISTRO PARA IR MULTIPLICANDO POR EL DIGITO
  MOV CL, 10
  MOV AH, 0
  MOV BX, 0 ;GUARDAMOS EL VALOR DEL CODIGO DE PRODUCTO
  MOV SI, 0 ;CONTADOR QUE INDICA CUANTAS VECES SE HA REPETIDO EL BUCLE1
  PUSH BP
BUCLE1: ;BUCLE PARA CALCULAR EL NÚMERO DEL CODIGO DE PRODUCTO
	MOV AL, [BP]
  SUB AL, 48
  MUL DX
  ADD BX, AX
  MOV AX, DX
  MUL CL
  MOV DX, AX
	INC BP
  CMP SI, 4
  JGE GUARDADOPC
  INC SI
  JMP BUCLE1

GUARDADOPC:
  POP BP
  MOV [BP+16], BX

COMPANYCODE:
  ADD BP, 5
  MOV DL, 1 ;UTILIZAREMOS ESTE REGISTRO PARA IR MULTIPLICANDO POR EL DIGITO
  MOV CL, 10
  MOV AH, 0
  MOV BX, 0
  MOV SI, 0
  PUSH BP
BUCLE2: ;BUCLE PARA CALCULAR EL NÚMERO DEL CODIGO DE PRODUCTO
  MOV AL, [BP]
  SUB AL, 48
  MUL DX
  ADD BX, AX
  MOV AX, DX
  MUL CL
  MOV DX, AX
  INC BP
  CMP SI, 3
  JGE GUARDADOCC
  INC SI
  JMP BUCLE2

GUARDADOCC:
  POP BP
  MOV [BP+9], BX

COUNTRY CODE
  ADD BP, 4
  MOV DL, 1 ;UTILIZAREMOS ESTE REGISTRO PARA IR MULTIPLICANDO POR EL DIGITO
  MOV CL, 10
  MOV AH, 0
  MOV BX, 0
  MOV SI, 0
  PUSH BP
BUCLE3: ;BUCLE PARA CALCULAR EL NÚMERO DEL CODIGO DE PRODUCTO
  MOV AL, [BP]
  SUB AL, 48
  MUL DX
  ADD BX, AX
  MOV AX, DX
  MUL CL
  MOV DX, AX
  INC BP
  CMP SI, 2
  JGE GUARDADOCOUNTRYC
  INC SI
  JMP BUCLE3

GUARDADOCOUNTRYC:
  POP BP
  MOV [BP+3], BX

  POP BP ;RECOGEMOS EL CONTENIDO DE BP

_decodeBarCode ENDP
_TEXT ENDS
END