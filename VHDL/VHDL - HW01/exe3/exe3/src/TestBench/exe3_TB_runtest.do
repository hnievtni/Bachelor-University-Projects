SetActiveLib -work
comp -include "$dsn\src\exe3.vhd" 
comp -include "$dsn\src\TestBench\exe3_TB.vhd" 
asim +access +r TESTBENCH_FOR_exe3 
wave 
wave -noreg W
wave -noreg RESTEN
wave -noreg CLOCK
wave -noreg Z
wave -noreg Q0
wave -noreg Q1
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\exe3_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_exe3 
