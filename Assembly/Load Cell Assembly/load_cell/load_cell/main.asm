.INCLUDE "M32DEF.INC"

.ORG 0
RJMP MAIN
.ORG $100

MAIN:

	LDI R16, $FF
	OUT DDRB, R16
	OUT DDRC, R16
	OUT SFIOR, R17 //Free mode
	CBI DDRA, 4 //PORTA.4 = IN
	SBI PORTA, 4

	LDI R16, $87 //Enable ADC, set prescaler to 128
	OUT ADCSRA, R16
	LDI R16, $60 //Set reference voltage to AVCC, with ADLAR = 0
	OUT ADMUX, R16

	LDI R31, $01
	LDI R30, $00

	CLR R17
	CLR R18


LOOP:
	SBI ADCSRA, 6 //ADSC = 1

CONVERSION_WAIT:
	SBIS ADCSRA, 4 //Skip if ADIF = 1
	RJMP CONVERSION_WAIT

	SBI ADCSRA, 4 //ADIF Cleared
	IN R18, ADCH //Load the higher 8 bits of the result
	IN R17, ADCL //Load the lower 2 bits of the result
	ST Z+, R18
	ST Z+, R17
	OUT PORTB, R18 //higher 8 bits
	OUT PORTC, R17 //lower 2 bits
	RJMP LOOP
