library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

-- Testbench for Processor2 (Part 3: Multiplication Instruction)

entity processor2_tb is
end processor2_tb;

architecture TB_ARCHITECTURE of processor2_tb is

    component Processor2
        port(
            clk   : in std_logic;
            reset : in std_logic
        );
    end component;

    signal clk   : std_logic := '0';
    signal reset : std_logic := '0';


begin
	
	-- Instantiate processor
    UUT : Processor2
        port map (
            clk   => clk,
            reset => reset
        );

    -- Clock generation (20 ns period)
    clk_process : process
    begin
        loop
            clk <= '0';
            wait for 10 ns;
            clk <= '1';
            wait for 10 ns;
        end loop;
    end process;

    -- Reset generation
    reset_process : process
    begin
        reset <= '1';
        wait for 40 ns;   
        reset <= '0';
        wait;         
    end process;

end TB_ARCHITECTURE;