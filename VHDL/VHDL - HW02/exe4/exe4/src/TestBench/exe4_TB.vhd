library ieee;
use ieee.NUMERIC_STD.all;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity exe4_tb is
	-- Generic declarations of the tested unit
		generic(
		Data_Width : INTEGER := 8;
		Addr_Width : INTEGER := 8 );
end exe4_tb;

architecture TB_ARCHITECTURE of exe4_tb is
	-- Component declaration of the tested unit
	component exe4
		generic(
		Data_Width : INTEGER := 8;
		Addr_Width : INTEGER := 8 );
	port(
		CLK : in STD_LOGIC;
		WE1 : in STD_LOGIC;
		RE1 : in STD_LOGIC;
		WE2 : in STD_LOGIC;
		RE2 : in STD_LOGIC;
		Addr1 : in STD_LOGIC_VECTOR(Addr_Width-1 downto 0);
		Addr2 : in STD_LOGIC_VECTOR(Addr_Width-1 downto 0);
		Data1 : inout STD_LOGIC_VECTOR(Data_Width-1 downto 0);
		Data2 : inout STD_LOGIC_VECTOR(Data_Width-1 downto 0) );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal CLK : STD_LOGIC;
	signal WE1 : STD_LOGIC;
	signal RE1 : STD_LOGIC;
	signal WE2 : STD_LOGIC;
	signal RE2 : STD_LOGIC;
	signal Addr1 : STD_LOGIC_VECTOR(Addr_Width-1 downto 0);
	signal Addr2 : STD_LOGIC_VECTOR(Addr_Width-1 downto 0);
	signal Data1 : STD_LOGIC_VECTOR(Data_Width-1 downto 0);
	signal Data2 : STD_LOGIC_VECTOR(Data_Width-1 downto 0);
	-- Observed signals - signals mapped to the output ports of tested entity

	-- Add your code here ...

begin

	-- Unit Under Test port map
	UUT : exe4
		generic map (
			Data_Width => Data_Width,
			Addr_Width => Addr_Width
		)

		port map (
			CLK => CLK,
			WE1 => WE1,
			RE1 => RE1,
			WE2 => WE2,
			RE2 => RE2,
			Addr1 => Addr1,
			Addr2 => Addr2,
			Data1 => Data1,
			Data2 => Data2
		);

	CLK <= '0', '1' after 5ns, '0' after 10ns, '1' after 15ns, '0' after 20ns, '1' after 25ns, '0' after 30ns, '1' after 35ns,
	'0' after 40ns, '1' after 45ns, '0' after 50ns,  '1' after 55ns, '0' after 60ns, '1' after 65ns, '0' after 70ns; 
	WE1 <= '0', '1' after 10ns, '0' after 20ns, '1' after 30ns, '0' after 40ns, '1' after 50ns, '0' after 60ns, '1' after 70ns;
	RE1 <= '0', '1' after 15ns, '0' after 30ns, '1' after 45ns, '0' after 60ns;
	WE2 <= '0', '1' after 10ns, '0' after 20ns, '1' after 30ns, '0' after 40ns, '1' after 50ns, '0' after 60ns, '1' after 70ns;
	RE2 <= '0', '1' after 15ns, '0' after 30ns, '1' after 45ns, '0' after 60ns;
	Addr1 <= "00000000";
	Addr2 <= "00000001";
	Data1 <= "01010101", "11001100" after 32ns;
	Data2 <= "00111100", "01011101" after 32ns;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_exe4 of exe4_tb is
	for TB_ARCHITECTURE
		for UUT : exe4
			use entity work.exe4(exe4);
		end for;
	end for;
end TESTBENCH_FOR_exe4;

