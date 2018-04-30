library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

-- Multiplica por 4 un dato de 32 bits
entity Mul4_32bits is
    Port (
		A : in  STD_LOGIC_VECTOR (31 downto 0);
		Z : out STD_LOGIC_VECTOR (31 downto 0)
	);
end Mul4_32bits;

architecture Practica of Mul4_32bits is

	begin 
		Z <= A(29 downto 0) & "00";		

end Practica; 