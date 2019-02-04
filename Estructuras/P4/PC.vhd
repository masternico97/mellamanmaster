library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

-- PC
entity PC is
    Port (
		Clk : in  STD_LOGIC;
		NRst : in  STD_LOGIC;
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
	);
end PC;

architecture Practica of PC is

	-- Señal auxiliar que almacena el valor
	signal qTemp : std_logic_vector (31 downto 0);

	begin

	Q <= qTemp;

	process (Clk, NRst)
	begin
		if NRst = '0' then
			qTemp <= (OTHERS => '0');
		elsif rising_edge(Clk) then
			qTemp <= qTemp + 4;
		end if;

	end process;

end Practica;