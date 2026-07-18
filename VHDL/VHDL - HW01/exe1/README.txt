Research the inertial, transport, and reject commands. Finally, write a code that executes the following commands. the results
Show the simulation in the report and analyze the waveform of each output (Y1 to Y5).

Y1 <= qin;
Y2 <= qin after 1ns;
Y3 <= inertial qin after 1ns;
Y4 <= transport qin after 1ns;
Y5 <= reject 500ps inertial qin after 1ns;
In the testbench, consider your input as follows. qin <= '0', '1' after 10 ns, '0' after 13 ns, '1' after 16 ns, '0' after 16.8 ns, '1' after 19 ns, '0' after 19.4 ns, ' 1'
after 25 ns, '0' after 30 ns, '1' after 30.6ns;