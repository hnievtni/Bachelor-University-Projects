*************CMOS Inverter TSPICE netlist************ 
.include '\\Mac\Home\Desktop\HanieVatani-1400012268035\exe1\mosistsmc180.lib'
*netlist--------------------------------------- 
.param SUPPLY = 1.8
.param Ni = 4
.param Pi = 8
.option scale = 90n



*input line------------------------------------
VDD Vdd 0 'SUPPLY'
*----------------Square wave, amp. Rise time, Fall time, On time, Period
VA0 A0 Gnd PULSE ('SUPPLY' 0 0ps 100ps 100ps 10ns 20ns)
VA1 A1 Gnd PULSE ('SUPPLY' 0 0ps 100ps 100ps 20ns 40ns)
VB0 B0 Gnd PULSE ('SUPPLY' 0 0ps 100ps 100ps 40ns 80ns)
VB1 B1 Gnd PULSE ('SUPPLY' 0 0ps 100ps 100ps 80ns 160ns)



*Drain, Gate, Source, Body---------------------
*NOR
.subckt NOR A B OUT Gnd Vdd
MN0 OUT A Gnd Gnd NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *A 
MP0 X1 A Vdd Vdd PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *A
MN1 OUT B Gnd Gnd NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *B
MP1 OUT B X1 X1 PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *B
.ends


*NAND
.subckt NAND A B OUT Gnd Vdd
MN0 OUT A X2 X2 NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *A 
MP0 OUT A Vdd Vdd PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *A
MN1 X2 B Gnd Gnd NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *B
MP1 OUT B Vdd Vdd PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *B
.ends


*XNOR
.subckt XNOR A B N_A N_B OUT Gnd Vdd
MN0 OUT A X4 X4 NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *A 
MP0 OUT A X5 X5 PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *A
MN1 X3 B Gnd Gnd NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *B
MP1 X5 B Vdd Vdd PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *B
MN2 OUT N_A X3 X3 NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *A' 
MP2 X5 N_A Vdd Vdd PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *A'
MN3 X4 N_B Gnd Gnd NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' *B'
MP3 OUT N_B X5 X5 PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10' *B'
.ends


*INVERTER
.subckt INV IN OUT Gnd Vdd
MN OUT IN Gnd Gnd NMOS W='Ni' L=2 AS='Ni*5' PS='2*Ni+10' AD='Ni*5' PD='2*Ni+10' 
MP OUT IN Vdd Vdd PMOS W='Pi' L=2 AS='Pi*5' PS='2*Pi+10' AD='Pi*5' PD='2*Pi+10'
.ends


*gate functions--------------------------------
X1 A0 NOT_A0 Gnd Vdd INV *NOT A0
X2 A1 NOT_A1 Gnd Vdd INV *NOT A1
X3 B0 NOT_B0 Gnd Vdd INV *NOT B0
X4 B1 NOT_B1 Gnd Vdd INV *NOT B1

X5 A0 B0 NOT_A0 NOT_B0 Y1 Gnd Vdd XNOR *A0 XNOR B0
X6 A1 B1 NOT_A1 NOT_B1 Y2 Gnd Vdd XNOR *A1 XNOR B1

X7 A0 NOT_B0 NOT_Y3 Gnd Vdd NAND *A0 NAND NOT(B0)
X8 NOT_Y3 Y3 Gnd Vdd INV *A0 AND NOT(B0)
X9 A1 NOT_B1 Y4 Gnd Vdd NAND *A1 NAND NOT(B1)
X10 Y3 Y2 Y5 Gnd Vdd NAND *(A0 AND NOT(B0)) NAND (A1 XNOR B1)

X11 NOT_A0 B0 NOT_Y6 Gnd Vdd NAND *NOT(A0) NAND B0
X12 NOT_Y6 Y6 Gnd Vdd INV *NOT(A0) AND B0
X13 Y6 Y2 Y7 Gnd Vdd NAND *NOT(A0) NAND (A1 XNOR B1)
X14 NOT_A1 B1 Y8 Gnd Vdd NAND *NOT(A1) NAND B1

X15 Y4 Y5 GT Gnd Vdd NAND *(A1 NAND NOT(B1)) NAND ((A0 NOT(B0)) NAND (A1 XNOR B1)) --Grater that
X16 Y2 Y1 NOT_EQ Gnd Vdd NAND *(A1 XNOR B1) NAND (A0 XNOR B0)
X17 NOT_EQ EQ Gnd Vdd INV *(A1 XNOR B1) NAND (A0 XNOR B0) --Equal
X18 Y7 Y8 LT Gnd Vdd NAND *(NOT(A0) NAND (A1 XNOR B1)) NAND (NOT(A1) NAND B1) --Less than

X19 Y1 S0 Gnd Vdd INV *A0 XOR B0
X20 Y2 Y9 Gnd Vdd INV *A1 XOR B1
X21 A0 B0 NOT_Y10 Gnd Vdd NAND *A0 NAND B0
X22 A1 B1 NOT_Y11 Gnd Vdd NAND *A1 NAND B1
X23 NOT_Y10 Y10 Gnd Vdd INV *A0 AND B0
X24 NOT_Y11 Y11 Gnd Vdd INV *A1 AND B1
X25 Y10 Y9 NOT_Y10 Y2 NOT_S1 Gnd Vdd XNOR *(A0 AND B0) XNOR (A1 XOR B1)
X26 NOT_S1 S1 Gnd Vdd INV *(A0 AND B0) XOR (A1 XOR B1) --Sum
X27 Y10 Y9 NOT_Y12 Gnd Vdd NAND *(A0 AND B0) NAND (A1 XOR B1)
X28 NOT_Y12 Y12 Gnd Vdd INV *(A0 AND B0) AND (A1 XOR B1)
X29 Y12 Y11 NOT_CO Gnd Vdd NOR *((A0 AND B0) AND (A1 XOR B1)) NOR (A1 AND B1)
X30 NOT_CO CO Gnd Vdd INV **((A0 AND B0) AND (A1 XOR B1)) OR (A1 AND B1) --Carry out


*Cloads---------------------------------------
CL0 NOT_A0 Gnd 10fF
CL1 NOT_A1 Gnd 10fF
CL2 NOT_B0 Gnd 10fF
CL3 NOT_B1 Gnd 10fF 
	
CL4 Y1 Gnd 10fF 	 		
CL5 Y2 Gnd 10fF 	
CL6 Y3 Gnd 10fF 	
CL7 Y4 Gnd 10fF
CL8 Y5 Gnd 10fF 
CL9 Y6 Gnd 10fF  	
CL10 Y7 Gnd 10fF 
CL11 Y8 Gnd 10fF 
CL12 Y9 Gnd 10fF 
CL13 Y10 Gnd 10fF 
CL14 Y11 Gnd 10fF 
CL15 Y12 Gnd 10fF 

CL16 NOT_Y3 Gnd 10fF
CL17 NOT_Y6 Gnd 10fF
CL18 NOT_Y10 Gnd 10fF
CL19 NOT_Y11 Gnd 10fF
CL20 NOT_Y12 Gnd 10fF
   	   
CL21 NOT_EQ Gnd 10fF 
CL22 NOT_S1 Gnd 10fF
CL23 NOT_Co Gnd 10fF		
CL24 GT Gnd 10fF
CL25 EQ Gnd 10fF
CL26 LT Gnd 10fF 
CL27 S0 Gnd 10fF
CL28 S1 Gnd 10fF
CL29 CO Gnd 10fF




*extra control information--------------------- 
.options post=2 nomod 
.op 
*analysis-------------------------------------- 
.TRAN 10ps 160ns * transient analysis: Step end_time


*measures-------------------------------------- 
.measure charge INTEGRAL I(Vdd) FROM = 0ns TO = 80ns *B0
.measure energy param = '-charge * SUPPlY'
.measure power param = 'energy * 0.00625'

*GT
.measure TRAN tpdr_GT TRIG v(B0) VAL = 'SUPPLY / 2' FALL = 1 
+		TARG v(GT) VAL = 'SUPPLY / 2' RISE = 1 *Rising Propagation Delay
.measure TRAN tpdf_GT TRIG v(B0) VAL = 'SUPPLY / 2' RISE = 2 
+		TARG v(GT) VAL = 'SUPPLY / 2' FALL = 2 *Falling Propagation Delay

.measure TRAN tpd_GT param = '(tpdr_GT + tpdf_GT) / 2' *Avarage Propagation Delay

.measure TRAN riseTime_GT TRIG v(GT) VAL = '0.1 * SUPPLY' RISE = 1 
+ 		TARG v(GT) VAL = '0.9 * SUPPLY' RISE = 1 *Rise Time
.measure TRAN fallTime_GT TRIG v(GT) VAL = '0.9 * SUPPLY' FALL = 2 
+		TARG v(GT) VAL = '0.1 * SUPPLY' FALL = 2 *Fall Time


*EQ
.measure TRAN tpdr_EQ TRIG v(A1) VAL = 'SUPPLY / 2' FALL = 1 
+		TARG v(EQ) VAL = 'SUPPLY / 2' RISE = 1 *Rising Propagation Delay
.measure TRAN tpdf_EQ TRIG v(A1) VAL = 'SUPPLY / 2' RISE = 2 
+	   TARG v(EQ) VAL = 'SUPPLY / 2' FALL = 2 *Falling Propagation Delay

.measure TRAN tpd_EQ param = '(tpdr_EQ + tpdf_EQ) / 2' *Avarage Propagation Delay

.measure TRAN riseTime_EQ TRIG v(EQ) VAL = '0.1*SUPPLY' RISE = 1 
+		TARG v(EQ) VAL = '0.9 * SUPPLY' RISE = 1 *Rise Time
.measure TRAN fallTime_EQ TRIG v(EQ) VAL = '0.9*SUPPLY' FALL = 2 
+		TARG v(EQ) VAL = '0.1 * SUPPLY' FALL = 2 *Fall Time


*LT
.measure TRAN tpdr_LT TRIG v(A0) VAL = 'SUPPLY / 2' FALL = 1 
+		TARG v(LT) VAL = 'SUPPLY / 2' RISE = 1 *Rising Propagation Delay
.measure TRAN tpdf_LT TRIG v(A0) VAL = 'SUPPLY / 2' RISE = 2 
+		TARG v(LT) VAL = 'SUPPLY / 2' FALL = 2 *Falling Propagation Delay

.measure TRAN tpd_LT param = '(tpdr_LT + tpdf_LT) / 2' *Avarage Propagation Delay

.measure TRAN riseTime_LT TRIG v(LT) VAL = '0.1*SUPPLY' RISE = 1 
+		TARG v(LT) VAL = '0.9 * SUPPLY' RISE = 1 *Rise Time
.measure TRAN fallTime_LT TRIG v(LT) VAL = '0.9*SUPPLY' FALL = 2 
+		TARG v(LT) VAL = '0.1 * SUPPLY' FALL = 2 *Fall Time


*outputs--------------------------------------
.print
+v(A0)
+v(A1)
+v(B0)
+v(B1)
+v(GT)
+v(EQ)
+v(LT)
+v(S0)
+v(S1)
+v(CO)


.END 