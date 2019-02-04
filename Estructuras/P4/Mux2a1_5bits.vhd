library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

-- Multiplexor 2 a 1 de 5 bits
entity Mux2a1_5bits is
    Port (
		A, B : in  STD_LOGIC_VECTOR (4 downto 0);
		Control: in  STD_LOGIC;
		Z : out STD_LOGIC_VECTOR (4 downto 0)
	);
end Mux2a1_5bits;

architecture Practica of Mux2a1_5bits is

	begin 
		with Control select
			Z <= A when '0',
                 B when others;			

end Practica; 