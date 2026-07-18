-------------------------------------------------------------------------------
--
-- Title       : exe4
-- Design      : exe4
-- Author      : Hanie
-- Company     : Vatani
--
-------------------------------------------------------------------------------
--
-- File        : D:\CAD-HW03-HnaieVatani\exe4\exe4\src\exe4.vhd
-- Generated   : Thu Dec 14 23:35:46 2023
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
use IEEE.NUMERIC_STD.ALL;


entity exe4 is
	port (
	Reset, Clk, Start: in std_logic;
	X: in BIT;
	Z, done: out std_logic;
	R: out std_logic_vector(3 downto 0) := (others => '0')
	);
	
end exe4;



architecture exe4 of exe4 is
	signal startPrv: std_logic := '0'; -- stores starts previous state
	signal bitCount: integer range 0 to 15 := 0; -- for counting bits  
	signal zeroCount: integer range 0 to 15 := 0; --for counting 0s, there are maximum 15 zero bits in 15 bits
	signal ZH: std_logic; -- Z signal helper
	signal counting: boolean := false; -- shows that we count or not
	signal doneH: std_logic; -- done signal helper
	signal R2: std_logic_vector(3 downto 0) := (others => '0'); --R

begin
	process (Reset, Clk, Start)
	begin	
		--default state
		ZH <= ZH;
		bitCount <= bitCount;
		zeroCount <= zeroCount;
		doneH <= doneH;
		counting <= counting;
	
		if (Reset = '1') then --reset
			bitCount <= 0;
			zeroCount <= 0;
			ZH <= '0';
			doneH <= '1';
			counting <= false;
		elsif (Clk'event and Clk = '1') then  --checks X every 0.5ns for changes
			
			if (start = '1') then --start point
				startPrv <= '1';
			end if;
			
			if (Start = '0' and startPrv = '1') then --after start is 1 and then 0 done gets 0
				doneH <= '0';
				startPrv <= '0';
				bitCount <= 0;
				zeroCount <= 0;
				ZH <= '0';
				counting <= true;
			end if;
			
			if (doneH = '0') then --15 bits is not over
				if (counting = true) then --we didn't reached 1
					if (X = '0') then
						zeroCount <= zeroCount + 1;
					else --reaching first 1
						ZH <= '1';
						counting <= false;
					end if;
				else  --after reaching first 1
					ZH <= '0';
				end if;
				bitCount <= bitCount + 1; --bit counts goes up either way
			end if;
		end if;
		
		if (bitCount = 15) then --end of the 15 bits
			bitCount <= 0;
			zeroCount <= 0;
			ZH <= '0';
			doneH <= '1';
			counting <= false;
		end if;
	end process;
	R2 <= std_logic_vector(to_unsigned(zeroCount, 4));
	done <= doneH;
	Z <= ZH;
	R <= R2;
end exe4;
