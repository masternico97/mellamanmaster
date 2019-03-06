;_______________________________________________________________
; DEFINICION DEL SEGMENTO DE DATOS
DATOS SEGMENT
	CLR_PANT 	DB 	1BH,"[2","J$"
	PREG_MODE 	DB 	1BH,"[15;1fElige el modo de empleo del programa $"
	OPC_1		DB	1BH,"[16;1f1)Calcular el determinante con valores por defecto $"
	OPC_2		DB	1BH,"[17;1f2)Calcular el determinante con valores introducidos por teclado $  "
	ESCOGIDO	DB 	3 DUP (0)
	PRUEBA1 	DB 	1BH,"[20;1fPRUEBA 1 $"
	PRUEBA2 	DB 	1BH,"[20;1fPRUEBA 2 ? $"	

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
    ;INICIALIZA LOS REGISTROS DE SEGMENTO CON SUS VALORES 
    MOV AX, DATOS 
    MOV DS, AX 

    MOV AX, PILA 
    MOV SS, AX 

    ; CARGA EL PUNTERO DE PILA CON EL VALOR MAS ALTO 
    MOV SP, 64 

    ; FIN DE LAS INICIALIZACIONES 
	
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
	
FIN: 
    ; FIN DEL PROGRAMA 
    MOV AX, 4C00H 
    INT 21H 

OPCION1:
	MOV AH,9
	MOV DX, OFFSET PRUEBA1		;MUESTRA "PRUEBA1...
	INT 21H
	JMP FIN
	
OPCION2:
	MOV AH,9
	MOV DX, OFFSET PRUEBA2		;MUESTRA "PRUEBA2...
	INT 21H	
	JMP FIN
	
START ENDP
;FIN DEL SEGMENTO DE CODIGO
CODE ENDS
;FIN DE PROGRAMA INDICANDO DONDE COMIENZA LA EJECUCION
END START

