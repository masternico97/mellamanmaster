;*******************************************************************************
; PRACTICA 4 APARTADO B DE SISTEMAS BASADOS EN MICROPROCESADORES
; AUTORES: ALVARO SANCHEZ Y NICOLAS SERRANO
; PAREJA 10
;*******************************************************************************

; DEFINICION DEL SEGMENTO DE DATOS

DATOS SEGMENT

  CLR_PANT 	DB 	1BH,"[2","J$"
  NUM_P DB 31H
  FIRST_LET DB 65 ;LETRA A(ASCII)
  LAST_LET DB 91 ;LETRA Z+1(ASCII)
  INI_NUM DB 48 ;NUMERO 0(ASCII)
  FIRST_NUM DB 49 ;NUMERO 1(ASCII)
  LAST_NUM DB 58 ;NUMERO 9+1(ASCII)
  PRUEBA 	DB "ABCD GHIJ KRS TUVW XYZ01236789 $"
  PRUEBA2 	DB "21222324 2630313233343536$"


DATOS ENDS


; DEFINICION DEL SEGMENTO DE PILA

PILA SEGMENT STACK "STACK"
  DB   40H DUP (0)
PILA ENDS


; DEFINICION DEL SEGMENTO EXTRA

EXTRA SEGMENT
EXTRA ENDS


; DEFINICION DEL SEGMENTO DE CODIGO

CODE SEGMENT
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

  MOV CH, 0 ;CONTADOR DE LINEAS
  MOV AL, 31H
  MOV BL, FIRST_NUM ;AL SER LA PAREJA 10, LA PRIMERA POSICION LA OCUPA EL NUMERO 1
                  ;CUYO ASCII = 49

PRIMERA_LINEA:  ;BUCLE PARA IMPRIMIR LA SEGUNDA LINEA
  MOV AH,2H	; IMPRIMIMOS UNO DE LOS NUMEROS DE LA PARTE SUPERIOR
  MOV DL, 9 ;ESCRIBE UN TABULACION
  INT 21H
  MOV DL, AL ;ESCRIBE AL
  INT 21H
  CMP AL, 36H
  JE NUEVA_LINEA
  INC AL
  JMP PRIMERA_LINEA

NUEVA_LINEA:
  INC CH
  CMP CH, 7
  JE FIN
  MOV CL, 0
  MOV DL, 10 ;ESCRIBE UN SALTO DE LINEA
  INT 21H
  MOV DL, NUM_P ; IMPRIMIMOS UNO DE LOS NUMEROS DE LA PARTE LATERAL
  INT 21H
  INC NUM_P
NUEVO_NUMERO:
  MOV DL, 9 ;ESCRIBE UN TABULACION
  INT 21H
  MOV DL, BL ; IMPRIMIMOS UN CARACTER DE DENTRO DE LA TABLA
  INT 21H
  INC BL
  CMP BL, LAST_LET
  JNE CONT_NUM
  MOV BL, INI_NUM

CONT_NUM:
  CMP CL, 5
  JE NUEVA_LINEA
  INC CL
  CMP BL, LAST_NUM
  JNE NUEVO_NUMERO
  MOV BL, FIRST_LET
  JMP NUEVO_NUMERO

FIN:  ; FIN DEL PROGRAMA
    mov ax, SEG PRUEBA
    mov ds, ax
    mov dx, OFFSET PRUEBA
    MOV AH, 10h
    int 57h

    mov ax, SEG PRUEBA2
    mov ds, ax
    mov dx, OFFSET PRUEBA2
    MOV AH, 11h
    int 57h

    MOV AX, 4C00H
    INT 21H

START ENDP
; FIN DEL SEGMENTO DE CODIGO
CODE ENDS
; FIN DEL PROGRAMA INDICANDO DONDE COMIENZA LA EJECUCION
END START
