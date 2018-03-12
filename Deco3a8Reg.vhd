library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

entity Deco3a8Reg is     
	port (  
		D : in  std_logic_vector (2 downto 0);  
		CE : in  std_logic;  
		Clk : in  std_logic;  
		Reset : in  std_logic;  
		Q : out  std_logic_vector (7 downto 0)     
	); 
end Deco3a8Reg; 

architecture Projecto OF Deco3a8Reg is

component Deco3a8 port (
		D : in  std_logic_vector (2 downto 0);
		Q : out  std_logic_vector (7 downto 0)
	);
end component;

component Registro port (
		D : in  std_logic_vector(7 downto 0);
		Reset : in  std_logic;
		Clk : in  std_logic;
		Ce : in  std_logic;
		Q : out  std_logic_vector(7 downto 0)
	);
end component;

signal s : std_logic_vector(7 downto 0);

begin
	Deco: Deco3a8 port map (D=>D, Q=>s);
	Reg: Registro port map (D=>s, Reset=>Reset, Ce=>CE, Clk=>Clk, Q=>Q);
end Projecto;
	