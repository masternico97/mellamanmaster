----------------------------------------------------------------------
-- Fichero: UnidadControlTb.vhd
-- Descripción: Unidad de Control para el microprocesador MIPS

-- Autores: Alvaro y Nicolas.
-- Asignatura: E.C. 1º grado
-- Grupo de Prácticas: 2111
-- Grupo de Teoría: 211
-- Práctica: 4
-- Ejercicio: 2
----------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;

entity UnidadControl is
	port(
		OPCode : in  std_logic_vector (5 downto 0); -- OPCode de la instrucción
		Funct : in std_logic_vector(5 downto 0); -- Funct de la instrucción
		-- Señales para el PC
		Jump : out  std_logic;
    --		RegToPC : out std_logic;
		Branch : out  std_logic;
    --		PCToReg : out std_logic;
		-- Señales para la memoria
		MemToReg : out  std_logic;
		MemWrite : out  std_logic;
		
		-- Señales para la ALU
		ALUSrc : out  std_logic;
		ALUControl : out  std_logic_vector (2 downto 0);
		ExtCero : out std_logic;
		
		-- Señales para el GPR
		RegWrite : out  std_logic;
		RegDest : out  std_logic
        );
	end UnidadControl;
	
architecture p4e2 of UnidadControl is
  signal ALU: std_logic_vector (2 downto 0);
  begin
	   with OPCode select
	     Jump <= '1' when "000010",
	             '0' when others;
	             
	   with OPCode select
	     Branch <= '1' when "000100",
	               '0' when others;

     with OPCode select
	     MemToReg <= '1' when "100011",
	                 '0' when "001000",
	                 '0' when "001100",
	                 '0' when "001101",
	                 '0' when "001010",
	                 '0' when "000000",      	                 
	                 'U' when others;
	     
     with OPCode select
	     MemWrite <= '1' when "101011",
	                 '0' when others;	     
	   
     with Funct select
	     ALU <= "000" when "100100",
	            "001" when "100101",	
	            "010" when "100000",
	            "101" when "100111",
	            "110" when "100010",    
	            "111" when "101010",
	            "UUU" when others;
	   
	 
	  
	   with OPCode select
	     ALUControl <=  ALU when "000000",
	                    "010" when "001000",
	                    "000" when "001100",
	                    "001" when "001101",
	                    "110" when "000100",
	                    "111" when "001010",
	                    "010" when "100011",
	                    "010" when "101011",
	                    "UUU" when others;
	   
	     	                   	 
     with OPCode select
	     ALUSrc <= '0' when "000000",
	               '0' when "000100",
	               'U' when "000010",
	               '1' when others;	   
	   
     with OPCode select
	     ExtCero <= '0' when "001000",
	                '0' when "101011",
	                '0' when "100011",
	                '0' when "001010",
	                '1' when "001100",
	                '1' when "001101",
	                'U' when others;	 	   
	   
     with OPCode select
	     RegWrite <= '0' when "000010",
	                 '0' when "000100",
	                 '0' when "101011",
	                 '1' when others;	   
	   
     with OPCode select
	     RegDest <= '0' when "100011",
	                '0' when "001000",
	                '0' when "001100",
	                '0' when "001101",
	                '0' when "001010",
	                '1' when "000000",
	                'U' when others;	   
	   

end p4e2;