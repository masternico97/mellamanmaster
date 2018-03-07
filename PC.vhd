library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

-- Contador de 8 bits
entity PC is
    Port (
		Clk : in  STD_LOGIC;
		Reset : in  STD_LOGIC;
		Ce : in  STD_LOGIC;
		Up : in  STD_LOGIC;
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
	);
end PC;

architecture Practica of PC is

	-- Señal auxiliar que almacena el valor
	signal qTemp : std_logic_vector (31 downto 0);

	begin

	Q <= qTemp;

	process (Clk, Reset)
	begin
		if Reset = '1' then
			qTemp <= (OTHERS => '0');
		elsif rising_edge(Clk) then
			if Ce = '1' then
				if Up = '1' then
					qTemp <= qTemp + 1;
				else
					qTemp <= qTemp - 1;
				end if;
			end if;
		end if;

	end process;

end Practica;