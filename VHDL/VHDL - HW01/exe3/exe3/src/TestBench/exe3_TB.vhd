library ieee;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity exe3_tb is
end exe3_tb;

architecture TB_ARCHITECTURE of exe3_tb is
	-- Component declaration of the tested unit
	component exe3
	port(
		W : in STD_LOGIC;
		RESTEN : in STD_LOGIC;
		CLOCK : in STD_LOGIC;
		Z : out STD_LOGIC;
		Q0 : out STD_LOGIC;
		Q1 : out STD_LOGIC );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal W : STD_LOGIC;
	signal RESTEN : STD_LOGIC;
	signal CLOCK : STD_LOGIC;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal Z : STD_LOGIC;
	signal Q0 : STD_LOGIC;
	signal Q1 : STD_LOGIC;

	-- Add your code here ...

begin

	-- Unit Under Test port map
	UUT : exe3
		port map (
			W => W,
			RESTEN => RESTEN,
			CLOCK => CLOCK,
			Z => Z,
			Q0 => Q0,
			Q1 => Q1
		);

	RESTEN <= '0', '1' after 15ns, '0' after 30ns, '1' after 45ns;
	CLOCK <= '0', '1' after 20ns, '0' after 40ns, '1' after 60ns;
	W <= '0', '1' after 10ns, '0' after 20ns, '1' after 30ns, '0' after 40ns, '1' after 50ns, '0' after 60ns, '1' after 70ns;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_exe3 of exe3_tb is
	for TB_ARCHITECTURE
		for UUT : exe3
			use entity work.exe3(exe3);
		end for;
	end for;
end TESTBENCH_FOR_exe3;

