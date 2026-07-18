library ieee;
use ieee.NUMERIC_STD.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity exe4_tb is
end exe4_tb;

architecture TB_ARCHITECTURE of exe4_tb is
	-- Component declaration of the tested unit
	component exe4
	port(
		Reset : in STD_LOGIC;
		Clk : in STD_LOGIC;
		Start : in STD_LOGIC;
		X : in BIT;
		Z : out STD_LOGIC;
		done : out STD_LOGIC;
		R : out STD_LOGIC_VECTOR(3 downto 0) );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal Reset : STD_LOGIC;
	signal Clk : STD_LOGIC;
	signal Start : STD_LOGIC;
	signal X : BIT;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal Z : STD_LOGIC;
	signal done : STD_LOGIC;
	signal R : STD_LOGIC_VECTOR(3 downto 0);

	-- Add your code here ...

begin

	-- Unit Under Test port map
	UUT : exe4
		port map (
			Reset => Reset,
			Clk => Clk,
			Start => Start,
			X => X,
			Z => Z,
			done => done,
			R => R
		);

	Reset <= '1', '0' after 0.4ns;
	
	Clk <= '0', '1' after 0.5ns, '0' after 1ns, '1' after 1.5ns, '0' after 2ns, '1' after 2.5ns, '0' after 3ns, '1' after 3.5ns,
	'0' after 4ns, '1' after 4.5ns, '0' after 5ns, '1' after 5.5ns, '0' after 6ns, '1' after 6.5ns, '0' after 7ns, '1' after 7.5ns,
	'0' after 8ns, '1' after 8.5ns, '0' after 9ns, '1' after 9.5ns, '0' after 10ns, '1' after 10.5ns, '0' after 11ns, '1' after 11.5ns,
	'0' after 12ns, '1' after 12.5ns, '0' after 13ns, '1' after 13.5ns, '0' after 14ns, '1' after 14.5ns, '0' after 15ns, '1' after 15.5ns,
	'0' after 16ns, '1' after 16.5ns, '0' after 17ns, '1' after 17.5ns, '0' after 18ns, '1' after 18.5ns, '0' after 19ns, '1' after 19.5ns,
	'0' after 20ns, '1' after 20.5ns, '0' after 21ns, '1' after 21.5ns, '0' after 22ns, '1' after 22.5ns, '0' after 23ns, '1' after 23.5ns,
	'0' after 24ns, '1' after 24.5ns, '0' after 25ns, '1' after 25.5ns, '0' after 26ns, '1' after 26.5ns, '0' after 27ns, '1' after 27.5ns,
	'0' after 28ns, '1' after 28.5ns, '0' after 29ns, '1' after 29.5ns, '0' after 30ns, '1' after 30.5ns, '0' after 31ns, '1' after 31.5ns,
	'0' after 32ns, '1' after 32.5ns, '0' after 33ns, '1' after 33.5ns, '0' after 34ns, '1' after 34.5ns, '0' after 35ns;	
	
	Start <= '0', '1' after 0.5ns, '0' after 1.5ns, '1' after 19.5ns, '0' after 20.5ns;
	
	X <=  '0', '0' after 1ns, '0' after 2ns, '0' after 3ns, '0' after 4ns, '0' after 5ns, '0' after 6ns, '0' after 7ns,
	'0' after 8ns, '1' after 9ns, '0' after 10ns, '1' after 11ns, '0' after 12ns, '1' after 13ns, '0' after 14ns, '0' after 15ns,
	'1' after 16ns, '1' after 17ns, '0' after 18ns, '0' after 19ns, '0' after 20ns, '0' after 21ns, '0' after 22ns, '0' after 23ns,
	'0' after 24ns, '1' after 25ns, '0' after 26ns, '1' after 27ns, '1' after 28ns, '1' after 29ns, '0' after 30ns, '1' after 31ns,
	'0' after 32ns, '1' after 33ns, '1' after 34ns, '0' after 35ns;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_exe4 of exe4_tb is
	for TB_ARCHITECTURE
		for UUT : exe4
			use entity work.exe4(exe4);
		end for;
	end for;
end TESTBENCH_FOR_exe4;

