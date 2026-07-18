-------------------------------------------------------------------------------
--
-- Title       : exe1
-- Design      : exe1
-- Author      : Hanie
-- Company     : Vatani
--
-------------------------------------------------------------------------------
--
-- File        : D:\CAD-HW03-HnaieVatani\exe1\exe1\src\exe1.vhd
-- Generated   : Thu Dec 14 15:21:22 2023
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


entity exe1 is
	port(
	qin: in std_logic;
	Y1, Y2, Y3, Y4, Y5 : out std_logic); 
end exe1;


architecture exe1 of exe1 is
begin
	process(qin) 						    
	begin 
		Y1 <= qin;
		Y2 <= qin after 1ns;
		Y3 <= inertial qin after 1ns;
		Y4 <= transport qin after 1ns;
		Y5 <= reject 500ps inertial qin after 1ns;
	end process;

end exe1;
