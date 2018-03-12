-- Fichero: MicroSuma.vhd
-- Descripción: Micro MIPS muy simplificado, sólo suma con dato inmediato
-- Fecha última modificación: 

-- Autores:  
-- Asignatura: E.C. 1º grado
-- Grupo de Prácticas:
-- Grupo de Teoría:
-- Práctica: 2
-- Ejercicio: 3
----------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_LOGIC_arith.ALL;
use IEEE.std_logic_unsigned.ALL;

entity MicroSuma is
	port (
		Clk : in std_logic; -- Reloj
		NRst : in std_logic; -- Reset activo a nivel bajo
		MemProgAddr : out std_logic_vector(31 downto 0); -- Dirección para la memoria de programa
		MemProgData : in std_logic_vector(31 downto 0) -- Código de operación
	);
	end MicroSuma;

architecture Practica of MicroSuma is

	-- Declaración de RegsMIPS
	component RegsMIPS is
	port (
		Clk : in std_logic; -- Reloj
		NRst : in std_logic; -- Reset asíncrono a nivel bajo
		A1 : in std_logic_vector(4 downto 0); -- Dirección para el puerto Rd1
		Rd1 : out std_logic_vector(31 downto 0); -- Dato del puerto Rd1
		A2 : in std_logic_vector(4 downto 0); -- Dirección para el puerto Rd2
		Rd2 : out std_logic_vector(31 downto 0); -- Dato del puerto Rd2
		A3 : in std_logic_vector(4 downto 0); -- Dirección para el puerto Wd3
		Wd3 : in std_logic_vector(31 downto 0); -- Dato de entrada Wd3
		We3 : in std_logic -- Habilitación del banco de registros
		); 
	end component;

	-- Declaración de ALUMIPS
	
	component ALUMIPS is
	Port (
		Op1 : in std_logic_vector (31 downto 0);
		Op2 : in std_logic_vector (31 downto 0);
		ALUControl : in std_logic_vector (2 downto 0);
		Res : out std_logic_vector (31 downto 0);
		Z : out std_logic
		);
	end component;

	-- Declaración de Extensor de Signo
	component ExtSig is
    	Port (
		D : in  STD_LOGIC_VECTOR (15 downto 0);
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
		);
	end component;

	-- Declaración de PC
	component PC is
    	Port (
		Clk : in  STD_LOGIC;
		NRst : in  STD_LOGIC;
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
	);
	end component;

	-- Declaración MemProgSuma
	component MemProgSuma is
	port (
		MemProgAddr : in std_logic_vector(31 downto 0); -- Dirección para la memoria de programa
		MemProgData : out std_logic_vector(31 downto 0) -- Código de operación
	);
	end component;
	
	-- Declaración de señales auxiliares
	SIGNAL Op1, Op2, C : std_logic_vector(31 downto 0);

begin

	-- Instancia de RegMIPS
	Reg: RegsMIPS port map (	Clk => Clk, 
				NRst => NRst, 
				A1 => MemProgData(25 downto 21), 
				Rd1 => Op1,	
				A2 => (others => '0'), 
				Rd2 => open,
				A3 => MemProgData(20 downto 16),
				Wd3 => C,
				We3 => '1');
	
	-- Instancia de ALUMIPS
	Alu: ALUMIPS port map (	Op1 => Op1,
				Op2 => Op2,
				ALUControl => "010",
				Res => C,
				Z => open);
	
	-- Instancia de  Extensión de signo
	Ext: ExtSig port map (	D => MemProgData(15 downto 0),
				Q => Op2);
	-- Instancia de PC
	PCounter: PC port map (	Clk => Clk,
				NRst => NRst,
				Q => MemProgAddr);
	
	


end Practica;

