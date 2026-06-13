.INCLUDE "m32def.inc" //include ATmega32 header file
.DEF TEMP = R16 //define temporary register R16
.DEF ONES = R17 //define ones register R17
.DEF TENS = R18 //define tens register R18


.ORG $00 //reset vector
RJMP MAIN //jump to MAIN routine





MAIN:
	//initialization
	LDI TEMP, $04
	OUT SPH, TEMP
	OUT SPL, TEMP 
	LDI TEMP, $FF

    OUT DDRA, TEMP	//set PORTA as the output port
	OUT DDRB, TEMP //set PORTB as the output switch port for 7-Segments
	
	SBI PORTB, 0	//ones digit is PORTB bit 0
	SBI PORTB, 1	//tens digit is PORTB bit 1

    
	LDI TEMP, $045    //load prescaler temp for Timer0
    OUT TCCR0, TEMP   //set prescaler for Timer0
	LDI TEMP, 100	  //load the value you want to set TCNT0 to into TEMP
    OUT TCNT0, TEMP   //set the value of TCNT0
	LDI TEMP, (1 << TOIE0)	//timer0 setup
    OUT TIMSK, TEMP   //enable Timer0 overflow and compare match interrupts


    LDI ONES, 0		//initialize ones
    LDI TENS, 0		//initialize tens

	LDI TEMP, 1		//using temp as an flag and set it to 1 to turn the ones 7-segment on
	

    SEI   //enable global interrupts
	

LOOP:
    RJMP LOOP	//program loop







NUMBERS: //hex codes for cathod 7-segments output
    .DB $3F, $06, $5B, $4F, $66, $6D, $7D, $07, $7F, $67
	//    0,   1,   2,   3,   4,   5,   6,   7,   8,   9



TIMER0_OVF_vect:
	CPI TEMP, 1		//using temp as an flag 
	BREQ ONES_SUB	//if in was 1 it mean that ones 7-segment will be on

	CPI TEMP, 0		//using temp as an flag
	BREQ TENS_SUB	//and if the flag is 0 then tens 7-segment will be on


    RETI	//return from interrupt





ONES_SUB:
	CPI ONES, $A	//compare ones with 10 
	BRNE DISPLAY_ONES	//if ones is not equal to 10 jump to display ones subroutine

	//increasing tens digit
	INC TENS	 //if ones is equal to 10 then increament the tens register
	CLR ONES	//clear the ones register	
	LDI TEMP, 1		//using temp as an flag and set it to 1 to turn the ones 7-segment on
	CALL DISPLAY_ONES	//calling display ones subroutine


	RETI	//return from interrupt


TENS_SUB:
	CPI TENS, $06	//compare tens with 6
    BRNE DISPLAY_TENS	//if tens is not equal to 6 it means its not 60 second yet so we jump tp the tens subroutine
	RJMP MAIN

	RETI	//return from interrupt





DISPLAY_ONES:
	LDI ZH, HIGH(NUMBERS * 2)	//initializing the the ZH that is the high value of NUMBERS
    LDI ZL, LOW(NUMBERS * 2)	//initializing the the ZL that is the low value of NUMBERS

	ADD ZL, ONES	//adding ones to Zl 
	LPM TEMP, Z    //loading Z to temp register
	
	OUT PORTA, TEMP    //output to PORTA
	CBI PORTB, 0	//ones 7-segment is on
	SBI PORTB, 1	//tens 7-segment is off

	INC ONES	//increment ones

	LDI TEMP, 0    //if temp is 0 it mean for next clock tens 7-segment will be on


	RETI	//return from interrupt
	
DISPLAY_TENS:
	LDI ZH, HIGH(NUMBERS * 2)	//initializing the the ZH that is the high value of NUMBERS
    LDI ZL, LOW(NUMBERS * 2)	//initializing the the ZL that is the low value of NUMBERS

	ADD ZL, TENS	//adding tens to Zl 
	LPM TEMP, Z	    //loading Z to temp register
	
	OUT PORTA, TEMP	   //output to PORTA
	CBI PORTB, 1	//tens 7-segment is on
	SBI PORTB, 0	//ones 7-segment is off

	
	LDI TEMP, 1    //if temp is 1 it mean for next clock ones 7-segment will be on


	RETI	//return from interrupt






TIMER0_COMP_vect:
    RETI	//return from interrupt