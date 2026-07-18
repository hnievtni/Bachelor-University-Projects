-------------------------------------------------------------------------------
--
-- Title       : exe4
-- Design      : exe4
-- Author      : Hanie
-- Company     : Vatani
--
-------------------------------------------------------------------------------
--
-- File        : D:\CAD-HW04-HanieVatani\exe4\exe4\src\exe4.vhd
-- Generated   : Thu Dec 28 20:30:55 2023
-- From        : interface description file
-- By          : Itf2Vhdl ver. 1.22
--
-------------------------------------------------------------------------------
--
-- Description : 
--
-------------------------------------------------------------------------------
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
use IEEE.numeric_std.all;



entity exe4 is 
	generic(
    Data_Width, Addr_Width : integer := 8
  	);
 	port(
    CLK, WE1, RE1, WE2, RE2: in std_logic;
    Addr1, Addr2: in std_logic_vector (Addr_Width - 1 downto 0);
    Data1, Data2: inout std_logic_vector (Data_Width-1 downto 0)
  	);
end exe4;


architecture exe4 of exe4 is 
	type RAM_TYPE is array(0 to 2**Addr_Width - 1) of std_logic_vector (Data_Width - 1 downto 0); --Data_Width - 1 = 7, Addr_Width - 1 = 7
    signal RAM : RAM_TYPE;
begin
	process(CLK)
    begin
	  if (CLK'event and CLK = '1') then
		  if (WE1 = '1') then
			  RAM(to_integer(unsigned(Addr1))) <= Data1;
		  end if;
		  if (WE2 = '1') then
			  RAM(to_integer(unsigned(Addr2))) <= Data2;
		  end if;
	  end if;
	end process;
	Data1 <= RAM(to_integer(unsigned(Addr1))) when (RE1 = '1') 
		  else (others => 'Z'); 
	Data2 <= RAM(to_integer(unsigned(Addr2))) when (RE2 = '1') 
		  else (others => 'Z');

end exe4;
