library ieee;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity exe2_tb is
end exe2_tb;

architecture TB_ARCHITECTURE of exe2_tb is
	-- Component declaration of the tested unit
	component exe2
	port(
		Reset : in STD_LOGIC;
		CLK : in STD_LOGIC;
		sensorA : in STD_LOGIC;
		sensorB : in STD_LOGIC;
		carIn : out INTEGER range 0 to 15;
		carOut : out INTEGER range 0 to 15 );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal Reset : STD_LOGIC;
	signal CLK : STD_LOGIC;
	signal sensorA : STD_LOGIC;
	signal sensorB : STD_LOGIC;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal carIn : INTEGER range 0 to 15;
	signal carOut : INTEGER range 0 to 15;

	-- Add your code here ...

begin

	-- Unit Under Test port map
	UUT : exe2
		port map (
			Reset => Reset,
			CLK => CLK,
			sensorA => sensorA,
			sensorB => sensorB,
			carIn => carIn,
			carOut => carOut
		);

	Reset <= '1', '0' after 4ns;
	CLK <= '0', '1' after 2.5ns, '0' after 5ns, '1' after 7.5ns, '0' after 10ns, '1' after 12.5ns, '0' after 15ns, '1' after 17.5ns,
	'0' after 20ns, '1' after 22.5ns, '0' after 25ns, '1' after 27.5ns, '0' after 30ns, '1' after 32.5ns, '0' after 35ns, '1' after 37.5ns,
	'0' after 40ns, '1' after 42.5ns, '0' after 45ns, '1' after 47.5ns, '0' after 50ns, '1' after 52.5ns, '0' after 55ns, '1' after 57.5ns,
	'0' after 60ns, '1' after 62.5ns, '0' after 65ns, '1' after 67.5ns, '0' after 70ns, '1' after 72.5ns, '0' after 75ns, '1' after 77.5ns,
	'0' after 80ns, '1' after 82.5ns, '0' after 85ns, '1' after 87.5ns, '0' after 90ns, '1' after 92.5ns, '0' after 95ns, '1' after 97.5ns,
	'0' after 100ns, '1' after 102.5ns, '0' after 105ns, '1' after 107.5ns, '0' after 110ns, '1' after 112.5ns,
	'0' after 115ns, '1' after 117.5ns, '0' after 120ns, '1' after 122.5ns, '0' after 125ns, '1' after 127.5ns,	 
	'0' after 130ns, '1' after 132.5ns, '0' after 135ns, '1' after 137.5ns, '0' after 140ns, '1' after 142.5ns,
	'0' after 145ns, '1' after 147.5ns, '0' after 150ns, '1' after 152.5ns, '0' after 155ns, '1' after 157.5ns,
	'0' after 160ns, '1' after 162.5ns, '0' after 165ns, '1' after 167.5ns, '0' after 170ns, '1' after 172.5ns,
	'0' after 175ns, '1' after 177.5ns, '0' after 180ns, '1' after 182.5ns, '0' after 185ns, '1' after 187.5ns,
	'0' after 190ns, '1' after 192.5ns, '0' after 195ns, '1' after 197.5ns, '0' after 200ns, '1' after 202.5ns,
	'0' after 205ns, '1' after 207.5ns, '0' after 210ns, '1' after 212.5ns, '0' after 215ns, '1' after 217.5ns,
	'0' after 220ns, '1' after 222.5ns, '0' after 225ns, '1' after 227.5ns, '0' after 230ns;	  
	
	sensorA <= '0' after 10ns, '1' after 20ns, '1' after 30ns, '0' after 40ns, '0' after 50ns, '0' after 60ns,
	'1' after 70ns, '1' after 80ns, '0' after 90ns, '1' after 100ns, '1' after 110ns, '1' after 120ns, '1' after 130ns,
	'0' after 140ns , '0' after 150ns, '0' after 160ns, '1' after 170ns, '0' after 180ns, '1' after 190ns, '1' after 200ns,
	'0' after 210ns, '1' after 220ns;
	
	sensorB <= '0' after 10ns, '0' after 20ns, '1' after 30ns, '1' after 40ns, '0' after 50ns, '1' after 60ns,
	'1' after 70ns, '0' after 80ns, '0' after 90ns, '0' after 100ns, '1' after 110ns, '0' after 120ns, '1' after 130ns,
	'1' after 140ns, '0' after 150ns, '1' after 160ns, '1' after 170ns, '1' after 180ns, '1' after 190ns, '0' after 200ns,
	'0' after 210ns, '0' after 220ns;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_exe2 of exe2_tb is
	for TB_ARCHITECTURE
		for UUT : exe2
			use entity work.exe2(exe2);
		end for;
	end for;
end TESTBENCH_FOR_exe2;

