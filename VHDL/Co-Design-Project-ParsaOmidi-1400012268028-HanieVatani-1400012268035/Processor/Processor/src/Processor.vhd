library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;
use IEEE.STD_LOGIC_UNSIGNED.ALL; 

entity Processor is						 
	port(CLK, RESET : in STD_LOGIC);
end Processor;

architecture Processor of Processor is	   

-- Memory
type Memory_Type is array (63 downto 0) of std_logic_vector(5 downto 0);
signal Memory : Memory_Type := 
(
   -- For Simulation of Part 1 you need to comment Part 2 and vice versa 
	
   -- PART 1
    0 => "000000", -- Load R0,
    1 => "000111", -- 7
    2 => "000100", -- Load R1,
    3 => "000100", -- 4
    4 => "010001", -- Add, R0, R1
    others => "111111" -- Halt	 
   	 
   -- PART 2
	--0 => "000000", 
    --1 => "000110",	
	--2 => "000100",	
	--3 => "001000",	
	--4 => "001000",	
	--5 => "000001",	
	--6 => "001100",	
	--7 => "000000",	
	--8 => "011100", 
	--9 => "100110",
	--10 => "110100",
	--11 => "001000",
	--others => "111111"
);

-- FSM  
type State_Type is (S0, S1, Halt, S2, S3, S4, S5, S6, S7);
signal Currentstate, Nextstate : State_Type; 

-- Registers
signal R0, R1, R2, R3, IR, PC : std_logic_vector (5 downto 0); 
signal R0_Next, R1_Next, R2_Next, R3_Next, IR_Next, PC_Next : std_logic_vector (5 downto 0);

-- Control Unit
signal Memory_Data, Data_BUS, ALU_Res, IN1, IN2 : std_logic_vector(5 downto 0);
signal Mux1_Sel, Mux2_Sel : std_logic_vector(1 downto 0);	
signal ZR0, ZR1, ZR2, ZR3, BUS_Sel, LDPC, LDIR, INC, RST, CMD, LD0, LD1, LD2, LD3 : std_logic; 
 
-- Helpers
signal Z : std_logic_vector(3 downto 0);	 
signal index : integer;	

begin	  
	
	-- Initialize helper signals
	Z(0) <= ZR0;
	Z(1) <= ZR1;
	Z(2) <= ZR2;
	Z(3) <= ZR3;
	index <= to_integer(unsigned(IR(3 downto 2)));
	
	Registers: process(CLK, RESET)  
	begin 	
		if RESET='1' then  
			-- Initialize all registers and state
			CurrentState <= s0;
			IR <= "000000";
			PC <= "000000";	
			R0 <= "000000";	 
			R1 <= "000000";
			R2 <= "000000";
			R3 <= "000000";
		elsif (rising_edge(CLK)) then
			-- Update state and registers
			Currentstate <= Nextstate;
			IR <= IR_Next;
			PC <= PC_Next;
		    R0 <= R0_Next;	 
			R1 <= R1_Next;
			R2 <= R2_Next;
			R3 <= R3_Next;
		end if;
	end process; 
	
	-- Mux0: Selects between memory data and ALU result
	with BUS_Sel select
	    Data_BUS <= Memory_Data when '0',
	                ALU_Res when '1',
	                (others => '0') when others;
	
	-- Mux1: Selects one of R0-R3 as ALU input1
	with Mux1_Sel select
	    IN1 <= R0 when "00",
	           R1 when "01",
	           R2 when "10",
	           R3 when "11",
	           (others => '0') when others;
	
	-- Mux2: Selects one of R0-R3 as ALU input2
	with Mux2_Sel select
	    IN2 <= R0 when "00",
	           R1 when "01",
	           R2 when "10",
	           R3 when "11",
	           (others => '0') when others;
		 
	-- Fetch memory data
	Memory_Data <= Memory(to_integer(unsigned(PC)));
	
	-- Set zero flags 
	ZR0 <= '1' when R0 = "000000" else '0';
	ZR1 <= '1' when R1 = "000000" else '0';
	ZR2 <= '1' when R2 = "000000" else '0';
	ZR3 <= '1' when R3 = "000000" else '0';
		
	-- Compute next PC, IR, and registers
	PC_Next <= Data_BUS when LDPC = '1' else PC + 1 when INC = '1' else "000000" when RST = '1' else PC;
	IR_Next <= Data_BUS when LDIR = '1' else IR;
	R0_Next <= Data_BUS when LD0 = '1' else R0;
	R1_Next <= Data_BUS when LD1 = '1' else R1;
	R2_Next <= Data_BUS when LD2 = '1' else R2;
	R3_Next <= Data_BUS when LD3 = '1' else R3; 
		
		
	ALU: process(IN1, IN2, CMD)
	begin
	    case CMD is
	        when '0' =>
	            ALU_Res <= IN1 + IN2;  -- Addition
	        when '1' =>
	            ALU_Res <= IN1 - IN2;  -- Subtraction
	        when others =>
	            ALU_Res <= (others => '0');
	    end case;
	end process;
 
	FSM: process(IR, Z, Currentstate)
	begin						 
		
	-- Initialize signals
	CMD <= '0';
	INC <= '0';
	RST <= '0';
	LD0 <= '0';
	LD1 <= '0';
	LD2 <= '0';
	LD3 <= '0';
	LDPC <= '0';
	LDIR <= '0';
	Mux1_Sel <= "00";
	Mux2_Sel <= "00";
	BUS_Sel <= '0';

	-- state transitions
	case Currentstate is	
		
		-- Reset state
		when S0 =>
			RST <= '1';
			Nextstate <= S1;
			
		-- Fetch instruction
		when S1 =>
			LDIR <= '1';
			INC <= '1';
			BUS_Sel <= '0';
			Nextstate <= Halt;
			
		-- Decode instruction
        when Halt =>
            if IR = "111111" then
                Nextstate <= S2;
            else
                case IR(5 downto 4) is
				    when "00" => Nextstate <= S3;
				    when "01" => Nextstate <= S4;
				    when "10" => Nextstate <= S5;
				    when "11" =>
				        if Z(index) = '0' then
				            Nextstate <= S6;
				        else
				            Nextstate <= S7;
				        end if;
				    when others => Nextstate <= S1;
				end case;
            end if;
			
		-- Halted
		when S2 =>
			Nextstate <= S2;
		   
		-- Execute load
		when S3 =>
			Nextstate <= S1;
			INC <= '1';
			BUS_Sel <= '0';	
			
			case IR(3 downto 2) is
				when "00" => LD0 <= '1';
				when "01" => LD1 <= '1';
				when "10" => LD2 <= '1';
				when others => LD3 <= '1';
			end case;
			
		-- Execute add
		when S4 =>
			Nextstate <= S1;
			CMD <= '0';
			Mux1_Sel <= IR(3 downto 2);
			Mux2_Sel <= IR(1 downto 0);
			BUS_Sel <= '1';

			case IR(3 downto 2) is
				when "00" => LD0 <= '1';
				when "01" => LD1 <= '1';
				when "10" => LD2 <= '1';
				when others => LD3 <= '1';
			end case;
			
		-- Execute subtract
		when S5 =>
			Nextstate <= S1;
			CMD <= '1';	 
			Mux1_Sel <= IR(3 downto 2);
			Mux2_Sel <= IR(1 downto 0);
			BUS_Sel <= '1';

			case IR(3 downto 2) is
				when "00" => LD0 <= '1';
				when "01" => LD1 <= '1';
				when "10" => LD2 <= '1';
				when others => LD3 <= '1';
			end case;
			
		-- Branch taken
		when S6 =>
			Nextstate <= S1;
			LDPC <= '1';
			BUS_Sel <= '0';
		   
		-- Branch not taken
		when S7 =>	
			INC <= '1';
			Nextstate <= S1;

	end case;
	end process;
	
end Processor;
