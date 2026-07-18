#include <mega16.h>
#include <delay.h>
#include <lcd.h>
#include <stdlib.h>
#include <stdio.h>

#asm
    .equ __lcd_port=0X18; //PORTB
#endasm

int T;
char str1[];
int temp() {
    ADCSRA = 0b11000000; //REGISTER
    while (ADCSRA.4 == 0);
    ADCSRA.4 = 1;
    return ADCW;
}


void main(void)
{
DDRA = 0X00; //INPUT
PORTA = 0X00;
DDRC = 0XFF; //OUTPUT
PORTC = 0X00;

ADMUX = 0b11000000; //REGISTER
ADCSRA.6 = 1; //START

lcd_init(25);

while (1)
    {
        T = temp() / 4; 
        if (T < 20) {
            sprintf(str1, "Temp is Low : %02u", T);
            PORTC.0 = 1; //BLUE LED  
            delay_ms(25);
            PORTC.0 = 0;
        } 
        else if (T > 20 && T < 30) {
            sprintf(str1, "Temp is Moderate : %02u", T);
            PORTC.1 = 1; //GREEN LED   
            delay_ms(25); 
            PORTC.1 = 0;
        }
        else if (T > 30) {
            sprintf(str1, "Temp is High : %02u", T);
            PORTC.2 = 1; //RED LED 
            delay_ms(25);  
            PORTC.2 = 0;
        }
        lcd_puts(str1);
        delay_ms(100);
        lcd_clear();
        PORTC = 0b00000000; //OFF   
    }
}
