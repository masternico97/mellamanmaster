----------------------------------------------------------------------
-- Fichero: Deco3a8.vhd
-- Descripción: Decodificado 3 a84
-- Fecha última modificación: 2018-02-11

-- Autores: Nicolás Serrano y Alvaro Sánchez
-- Asignatura: E.C. 1º grado
-- Grupo de Prácticas:
-- Grupo de Teoría:
-- Práctica: 1
-- Ejercicio: 2
----------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;


entity Deco2a4 is
    port ( 
		D : in  std_logic_vector (2 downto 0);
		Q : out  std_logic_vector (7 downto 0)
	);
end Deco2a4;

architecture Practica of Deco2a4 is

begin

	process (D)
	begin
		case D is
			when "000" => Q <= "00000001";
			when "001" => Q <= "00000010";
			when "010" => Q <= "00000100";
			when "011" => Q <= "00001000";
			when "100" => Q <= "00010000";
			when "101" => Q <= "00100000";
			when "110" => Q <= "01000000";
			when others => Q <= "10000000";

		end case;
	end process;

end Practica;
