-------------------------------------------------------------------------------
--
-- Title       : exe2
-- Design      : exe2
-- Author      : Hanie
-- Company     : Vatani
--
-------------------------------------------------------------------------------
--
-- File        : D:\CAD-HW03-HnaieVatani\exe2\exe2\src\exe2.vhd
-- Generated   : Thu Dec 14 17:44:25 2023
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;



entity exe2 is
	port (
	Reset, clk:	in std_logic;
	Command: in std_logic_vector(2 downto 0);
	DataIn: in std_logic_vector(63 downto 0);
	DataOut: out std_logic_vector(63 downto 0)
	);
end exe2;


architecture exe2 of exe2 is
	signal reg: std_logic_vector(63 downto 0);	
begin
	process (clk, Reset, Command, DataIn)
	begin
		if (Reset = '1') then
			reg <= (others => '0');
		elsif (clk'event and clk = '1') then 
			case Command is
				when "001" =>
					reg <= DataIn;
				when "010" =>
					reg <= reg(62 downto 0) & '0';
				when "011" =>
					reg <= '0' & reg(63 downto 1);
				when "101" =>
					reg <= reg(62 downto 0) & reg(63);
				when "110" =>
					reg <= reg(0) & reg(63 downto 1);
				when others =>
					reg <= reg;
			end case;
		end if;	
	end process;
	DataOut <= reg;
end exe2;
