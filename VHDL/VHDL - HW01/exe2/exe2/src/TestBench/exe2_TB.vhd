library ieee;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

	-- Add your library and packages declaration here ...

entity exe2_tb is
end exe2_tb;

architecture TB_ARCHITECTURE of exe2_tb is
	-- Component declaration of the tested unit
	component exe2
	port(
		Reset : in STD_LOGIC;
		clk : in STD_LOGIC;
		Command : in STD_LOGIC_VECTOR(2 downto 0);																						   																																													  
		DataIn : in STD_LOGIC_VECTOR(63 downto 0);
		DataOut : out STD_LOGIC_VECTOR(63 downto 0) );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal Reset : STD_LOGIC;
	signal clk : STD_LOGIC;
	signal Command : STD_LOGIC_VECTOR(2 downto 0);
	signal DataIn : STD_LOGIC_VECTOR(63 downto 0);
	-- Observed signals - signals mapped to the output ports of tested entity
	signal DataOut : STD_LOGIC_VECTOR(63 downto 0);



begin

	-- Unit Under Test port map
	UUT : exe2
		port map (
			Reset => Reset,
			clk => clk,
			Command => Command,
			DataIn => DataIn,
			DataOut => DataOut
		);
	
	DataIn <= "1101100101010100010110011101100101101101100110011001010110110101";
	Reset <= '1', '0' after 8ns;
	clk <= '0', '1' after 5ns, '0' after 10ns, '1' after 15ns, '0' after 20ns, '1' after 25ns, '0' after 30ns, '1' after 35ns,
		'0' after 40ns, '1' after 45ns, '0' after 50ns, '1' after 55ns, '0' after 60ns, '1' after 65ns, '0' after 70ns, '1' after 75ns;
	Command <= "000", "001" after 10 ns, "010" after 20ns, "011" after 30ns, "100" after 40ns, "101" after 50ns, "110" after 60ns, "111" after 70ns;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_exe2 of exe2_tb is
	for TB_ARCHITECTURE
		for UUT : exe2
			use entity work.exe2(exe2);
		end for;
	end for;
end TESTBENCH_FOR_exe2;

