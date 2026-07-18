-------------------------------------------------------------------------------
--
-- Title       : exe3
-- Design      : exe3
-- Author      : Hanie
-- Company     : Vatani
--
-------------------------------------------------------------------------------
--
-- File        : D:\CAD-HW03-HnaieVatani\exe3\exe3\src\exe3.vhd
-- Generated   : Wed Dec 13 22:24:50 2023
-- From        : interface description file
-- By          : Itf2Vhdl ver. 1.22
--
-------------------------------------------------------------------------------
--
-- Description : 
--
-------------------------------------------------------------------------------

--{{ Section below this comment is automatically maintained
--   and may be overwritten
--{entity {exe3} architecture {exe3}}
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity exe3 is	
	port(
	W, RESTEN, CLOCK: in std_logic;
	Z, Q0, Q1 : out std_logic); 
	
end exe3;

--}} End of automatically maintained section

architecture exe3 of exe3 is  
	 signal H0,H1 : std_logic;  --H0 is Q0_next, H1 is Q1_next
begin
	process(W, RESTEN, CLOCK) 						    
	begin 
		if (RESTEN = '0') then -- reset the output to 0 if reset is 0
			Q0 <= '0'; 
			Q1 <= '0'; 
		elsif (CLOCK'event and CLOCK = '1') then
			Q0 <= H0;
			Q1 <= H1;
		end if;
		H0 <= W and (not(H0) or not(H1));
		H1 <= W xnor (H0 or H1);
		Z <= H0 and not(H1);
	end process;
end exe3;
