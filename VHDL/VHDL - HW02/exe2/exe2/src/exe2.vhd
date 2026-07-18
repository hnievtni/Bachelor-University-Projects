-------------------------------------------------------------------------------
--
-- Title       : exe2
-- Design      : exe2
-- Author      : Hanie
-- Company     : Vatani
--
-------------------------------------------------------------------------------
--
-- File        : D:\CAD-HW04-HanieVatani\exe2\exe2\src\exe2.vhd
-- Generated   : Thu Dec 28 13:20:19 2023
-- From        : interface description file
-- By          : Itf2Vhdl ver. 1.22
--
-------------------------------------------------------------------------------
--
-- Description : 
--
-------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity exe2 is
	Port (
	Reset, CLK : std_logic;
	sensorA : in std_logic;
    sensorB : in std_logic;
	carIn : out integer range 0 to 15; --car in count
    carOut : out INTEGER range 0 to 15 --car out count
	);
end exe2;


architecture exe2 of exe2 is
	type STATE_TYPE is (S0, S1, S2, S3); --S0 is 00, S1 is 10, S2 is 11, S3 is 01

	signal ab: std_logic_vector	(1 downto 0); 
	signal CURRENT_STATE, NEXT_STATE: STATE_TYPE;
	signal carInCount, carOutCount: integer range 0 to 15 := 0;
begin 
	CMB: process (sensorA, sensorB)
	begin 
		ab <= sensorA & sensorB;
		carInCount <= carInCount;
		carOutCount <= carOutCount;
		case CURRENT_STATE is
			when S0 =>
				if (ab = "10") then --car in
					NEXT_STATE <= S1;
				elsif (ab = "01") then --car out
					NEXT_STATE <= S3;
				end if;
			when S1 => 
				if (ab = "11") then	--car in
					NEXT_STATE <= S2;
				elsif (ab = "00") then --car out
					carOutCount <= carOutCount + 1;
					NEXT_STATE <= S0;
				end if;
			when S2 =>
				if (ab = "01") then --car in
					NEXT_STATE <= S3;
				elsif (ab = "10") then --car out
					NEXT_STATE <= S1;
				end if;
			when S3 =>
				if (ab = "00") then	--car in 
					carInCount <= carInCount + 1;
					NEXT_STATE <= S0;
				elsif (ab = "11") then --car out
					NEXT_STATE <= S2;
				end if;
		end case;
	end process CMB;
	
	process(Reset, CLK)
    begin		
		if (Reset = '1') then
			CURRENT_STATE <= S0;	
		elsif (CLK'event and CLK = '1') then
			CURRENT_STATE <= NEXT_STATE;
		end if;	
    end process; 
	
	carIn <= carInCount;
	carOut <= carOutCount;

end exe2;
