library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

-- Extensor de Cero
entity ExtCero is
    Port (
		D : in  STD_LOGIC_VECTOR (15 downto 0);
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
	);
end ExtCero;

architecture Practica of ExtCero is

	begin 
		Q <= 	x"0000" & D;

end Practica; 