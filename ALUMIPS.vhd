----------------------------------------------------------------------
-- Fichero: ALUMIPS.vhd
-- Descripción: ALU del microprocesador MIPS
-- Fecha última modificación: 

-- Autores: 
-- Asignatura: E.C. 1º grado
-- Grupo de Prácticas:
-- Grupo de Teoría:
-- Práctica: 2
-- Ejercicio: 2
----------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_signed.ALL;

entity ALUMIPS is
	Port (
		Op1 : in std_logic_vector (31 downto 0);
		Op2 : in std_logic_vector (31 downto 0);
		ALUControl : in std_logic_vector (2 downto 0);
		Res : out std_logic_vector (31 downto 0);
		Z : out std_logic
		);
end ALUMIPS;

architecture Practica of ALUMIPS is
  signal resta, resslt, result : std_logic_vector (31 downto 0);
begin
  
  with ALUControl select
    result <= Op1 and Op2 when "000",
         Op1 or Op2 when "001",
         Op1 + Op2 when "010",
         Op1 nor Op2 when "101",
         resta  when "110",
         resslt when "111",
         --(0 => (Op1 < Op2), others => ?0?) when "111",
         x"BEBACAFE" when others;
         
    resta  <= Op1 - Op2; 
    
    resslt <= (31 downto 1 => '0', 0 => '1') when Op1 < Op2
        else (31 downto 1 => '0', 0 => '0');

    Res <= result;
    
    with conv_integer(result) select
      Z <= '1' when 0,
           '0' when others;  
            

end Practica;
