SetActiveLib -work
comp -include "$dsn\src\exe2.vhd" 
comp -include "$dsn\src\TestBench\exe2_TB.vhd" 
asim +access +r TESTBENCH_FOR_exe2 
wave 
wave -noreg Reset
wave -noreg CLK
wave -noreg sensorA
wave -noreg sensorB
wave -noreg carIn
wave -noreg carOut
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\exe2_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_exe2 
