library ieee;
use ieee.NUMERIC_STD.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity exe3_tb is
end exe3_tb;

architecture TB_ARCHITECTURE of exe3_tb is
	-- Component declaration of the tested unit
	component exe3
	port(
		CLK : in STD_LOGIC;
		SW : in STD_LOGIC;
		debounced_sw : out STD_LOGIC );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal CLK : STD_LOGIC;
	signal SW : STD_LOGIC;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal debounced_sw : STD_LOGIC;

	-- Add your code here ...

begin

	-- Unit Under Test port map
	UUT : exe3
		port map (
			CLK => CLK,
			SW => SW,
			debounced_sw => debounced_sw
		);					 

	CLK <= '0', '1' after 1ns, '0' after 2ns, '1' after 3ns, '0' after 4ns, '1' after 5ns, '0' after 6ns, '1' after 7ns,
	'0' after 8ns, '1' after 9ns, '0' after 10ns, '1' after 11ns , '0' after 12ns, '1' after 13ns, '0' after 14ns, '1' after 15ns,
	'0' after 16ns, '1' after 17ns, '0' after 18ns, '1' after 19ns, '0' after 20ns, '1' after 21ns, '0' after 22ns, '1' after 23ns,
	'0' after 24ns, '1' after 25ns, '0' after 26ns, '1' after 27ns, '0' after 28ns, '1' after 29ns, '0' after 30ns, '1' after 31ns,
	'0' after 32ns, '1' after 33ns, '0' after 34ns, '1' after 35ns, '0' after 36ns, '1' after 37ns, '0' after 38ns, '1' after 39ns,
	'0' after 40ns; 
	SW <= '1', '0' after 11ns, '1' after 12ns, '0' after 15ns, '1' after 19ns, '0' after 22ns, '1' after 35ns;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_exe3 of exe3_tb is
	for TB_ARCHITECTURE
		for UUT : exe3
			use entity work.exe3(behavioral);
		end for;
	end for;
end TESTBENCH_FOR_exe3;

