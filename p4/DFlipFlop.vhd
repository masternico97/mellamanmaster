----------------------------------------------------------------------
-- Fichero: DFlipFlop.vhd
-- Descripción: Biestable tipo D, flip flop
-- Fecha última modificación:

-- Autores: Nicolás Serrano y Álvaro Sánchez
-- Asignatura: E.C. 1º grado
-- Grupo de Prácticas: 2111
-- Grupo de Teoría: 211
-- Práctica: 2
-- Ejercicio: 1
----------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
use IEEE.std_logic_arith.all;

entity DFlipFlop is
	port (
		Clk : in std_logic; -- Reloj
		NRst : in std_logic; -- Reset asíncrono a nivel bajo
		PC : in std_logic_vector(31 downto 0); -- Dirección PC+4
		Z : out std_logic_vector(31 downto 0)  -- Dato PC
	); 
end DFlipFlop;

architecture Practica of DFlipFlop is
  
  begin  
	process(Clk, NRst)
	  begin
	    if NRst = '0' then
	     Z <= x"00000000";
	    elsif rising_edge(Clk) then
	         Z <= PC;
	    end if;
	end process;
	
end architecture;
	