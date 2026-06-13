.INCLUDE "M32DEF.INC"
.ORG 0
RJMP MAIN
.ORG $100


MAIN:
	SBI DDRA, 0     //PORTA(0): OUTPUT
	LDI R16, 204	//204 is equal to %80 
	LDI R17, $41	//TCCR0 code in hex
	LDI R18, $02	
	SER R20
	CLR R21
	OUT OCR0, R16
	OUT TCCR0, R17	   //MODE: PWM Phase Correct
WAIT: 
	IN R19, TIFR
	SBRS R19, 1		//checks if OCF0 = 1 (TIFR(1)), skip if it's equal to 1
	RJMP WAIT
	EOR R21, R20	//for toggling
	OUT PORTA, R21
	OUT TIFR, R18	//clear TIFR
	RJMP WAIT

