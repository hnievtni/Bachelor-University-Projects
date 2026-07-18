SetActiveLib -work
comp -include "$dsn\src\exe4.vhd" 
comp -include "$dsn\src\TestBench\exe4_TB.vhd" 
asim +access +r TESTBENCH_FOR_exe4 
wave 
wave -noreg Reset
wave -noreg Clk
wave -noreg Start
wave -noreg X
wave -noreg Z
wave -noreg done
wave -noreg R
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\exe4_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_exe4 
