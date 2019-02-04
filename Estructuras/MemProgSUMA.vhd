----------------------------------------------------------------------
-- Fichero: MemProgSuma.vhd
-- Descripci�n: Memoria de programa para el MIPS. Contiene sumas entre un registro y un dato inmediato
-- Fecha �ltima modificaci�n: 2017-02-02

-- Autores: Alberto S�nchez (2012, 2017), �ngel de Castro (2010) 
-- Asignatura: E.C. 1� grado
-- Grupo de Pr�cticas:
-- Grupo de Teor�a:
-- Pr�ctica: 2
-- Ejercicio: 2
----------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

entity MemProgSuma is
	port (
		MemProgAddr : in std_logic_vector(31 downto 0); -- Direcci�n para la memoria de programa
		MemProgData : out std_logic_vector(31 downto 0) -- C�digo de operaci�n
	);
end MemProgSuma;

architecture Simple of MemProgSuma is

begin

	LecturaMemProg: process(MemProgAddr)
	begin
		-- La memoria devuelve un valor para cada direcci�n.
		-- Estos valores son los c�digos de programa de cada instrucci�n,
		-- estando situado cada uno en su direcci�n.
		case MemProgAddr is
			when X"00000000" => MemProgData <= X"2001000a";
			when X"00000004" => MemProgData <= X"20220005";
			when X"00000008" => MemProgData <= X"20430019";
			when X"0000000C" => MemProgData <= X"20000005";
			when X"00000010" => MemProgData <= X"2064FFFB";
			when others => MemProgData <= X"00000000"; -- Resto de memoria vac�a
		end case;
	end process LecturaMemProg;

end Simple;
