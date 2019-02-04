library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

-- Extensor de Signo
entity ExtSig is
    Port (
		D : in  STD_LOGIC_VECTOR (15 downto 0);
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
	);
end ExtSig;

architecture Practica of ExtSig is

	begin 
		with D(15) select
			Q <= 	x"0000" & D when '0',
				x"FFFF" & D when others;

end Practica; 