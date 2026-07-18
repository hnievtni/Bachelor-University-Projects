Design a RAM that has two series of ports for memory control (Dual-Port Memory).
• Generic: Data_Width and Addr_Width (default = 8)
• Inputs: Clk, WE1, RE1, Addr1, WE2, RE2, Addr2
• InOut: Data1, Data2
If WEx signal is active, Datax data is written on the line indicated by Addrx; And if REx is one, the reading operation is performed from the line related to Addrx and is placed on Datax (in other cases, the value 'Z' must be written on Datax). If both REx and WEx inputs are active, both operations are performed. .