-------------------------------------------------------------------------------
--
-- Title       : exe3
-- Design      : exe3
-- Author      : Hanie
-- Company     : Vatani
--
-------------------------------------------------------------------------------
--
-- File        : D:\CAD-HW04-HanieVatani\exe3\exe3\src\exe3.vhd
-- Generated   : Fri Dec 29 22:18:33 2023
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




entity exe3 is
    Port (
        CLK : in std_logic; 
        SW : in std_logic; 
        debounced_sw : out std_logic
    );
end exe3;

architecture Behavioral of exe3 is
    constant debounce_time: integer := 1000000; -- 100MHz * 10ms
    signal counter: integer range 0 to debounce_time := 0;
    signal reg: std_logic := '0'; --register for debounced switch values

begin

    process (CLK, SW, reg)
    begin
        if (CLK'event and CLK = '1') then
			if (counter < debounce_time) then
                counter <= counter + 1;
            elsif (counter = debounce_time) then
				counter <= 0;
				reg <= SW;
			else
				counter <= 0;
			end if;
        end if;
    end process;
    debounced_sw <= reg;

end Behavioral;