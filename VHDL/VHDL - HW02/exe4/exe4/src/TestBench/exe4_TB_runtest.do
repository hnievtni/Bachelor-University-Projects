SetActiveLib -work
comp -include "$dsn\src\exe4.vhd" 
comp -include "$dsn\src\TestBench\exe4_TB.vhd" 
asim +access +r TESTBENCH_FOR_exe4 
wave 
wave -noreg CLK
wave -noreg WE1
wave -noreg RE1
wave -noreg WE2
wave -noreg RE2
wave -noreg Addr1
wave -noreg Addr2
wave -noreg Data1
wave -noreg Data2
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\exe4_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_exe4 
