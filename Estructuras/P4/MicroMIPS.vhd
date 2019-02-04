----------------------------------------------------------------------
-- Fichero: MicroMIPS.vhd
-- Descripción: Microprocesador MIPS

-- Autores: Álvaro Sánchez y Nicolás Serrano
-- Asignatura: EC 1º grado
-- Grupo de Prácticas: 2111
-- Grupo de Teoría: 211
-- Práctica: 4
-- Ejercicio: 3
----------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;


entity MicroMIPS is
	port (
		Clk : in std_logic; -- Reloj
		NRst : in std_logic; -- Reset activo a nivel bajo
		MemProgAddr : out std_logic_vector(31 downto 0); -- Dirección para la memoria de programa
		MemProgData : in std_logic_vector(31 downto 0); -- Código de operación
		MemDataAddr : out std_logic_vector(31 downto 0); -- Dirección para la memoria de datos
		MemDataDataRead : in std_logic_vector(31 downto 0); -- Dato a leer en la memoria de datos
		MemDataDataWrite : out std_logic_vector(31 downto 0); -- Dato a guardar en la memoria de datos
		MemDataWE : out std_logic
	);
end MicroMIPS;

architecture Ej3 of MicroMIPS is
 
  -- ALU
	component ALUMIPS is
	port (		
	  Op1 : in std_logic_vector (31 downto 0);
		Op2 : in std_logic_vector (31 downto 0);
		ALUControl : in std_logic_vector (2 downto 0);
		Res : out std_logic_vector (31 downto 0);
		Z : out std_logic
  );
	end component;
	
	--Multiplicadores
	component Mul4_26bits is
    Port (
		A : in  STD_LOGIC_VECTOR (25 downto 0);
		Z : out STD_LOGIC_VECTOR (27 downto 0)
	  );
  end component;
  
  component Mul4_32bits is
    Port (
		A : in  STD_LOGIC_VECTOR (31 downto 0);
		Z : out STD_LOGIC_VECTOR (31 downto 0)
	);
  end component;
  
--Multiplexores
component Mux2a1_5bits is
    Port (
		A, B : in  STD_LOGIC_VECTOR (4 downto 0);
		Control: in  STD_LOGIC;
		Z : out STD_LOGIC_VECTOR (4 downto 0)
	);
end component;

component Mux2a1_32bits is
    Port (
		A, B : in  STD_LOGIC_VECTOR (31 downto 0);
		Control: in  STD_LOGIC;
		Z : out STD_LOGIC_VECTOR (31 downto 0)
	); 
end component;

--Extensor de Signo 
component ExtSig is
    Port (
		D : in  STD_LOGIC_VECTOR (15 downto 0);
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
	);
end component;

--Extensor de Cero
component ExtCero is
    Port (
		D : in  STD_LOGIC_VECTOR (15 downto 0);
		Q : out  STD_LOGIC_VECTOR (31 downto 0)
	);
end component;

--PC
component DFlipFlop is
	port (
		Clk : in std_logic; -- Reloj
		NRst : in std_logic; -- Reset asíncrono a nivel bajo
		PC : in std_logic_vector(31 downto 0); -- Dirección PC+4
		Z : out std_logic_vector(31 downto 0)  -- Dato PC
	); 
end component;

--Registro MIPS
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

--Unidad de Control
component UnidadControl is
	port(
		OPCode : in  std_logic_vector (5 downto 0); -- OPCode de la instrucción
		Funct : in std_logic_vector(5 downto 0); -- Funct de la instrucción
		-- Señales para el PC
		Jump : out  std_logic;
    --		RegToPC : out std_logic;
		Branch : out  std_logic;
    --		PCToReg : out std_logic;
		-- Señales para la memoria
		MemToReg : out  std_logic;
		MemWrite : out  std_logic;
		
		-- Señales para la ALU
		ALUSrc : out  std_logic;
		ALUControl : out  std_logic_vector (2 downto 0);
		ExtCero : out std_logic;
		
		-- Señales para el GPR
		RegWrite : out  std_logic;
		RegDest : out  std_logic
        );
	end component;
  
  
  --Señales auxiliares
  
  --Entrada A3
  signal MuxA3 : std_logic_vector(4 downto 0);	
  
  --Señales de 1 bit de la Unidad de Control
  signal Jump, Branch, MemToReg, MemWrite, ALUSrc, Ext_Cero, RegWrite, RegDest: std_logic;
  
  --Señales de 3 bits de la Unidad de Control
  signal ALUControl: std_logic_vector(2 downto 0);
  
  --ExtCero y ExtSigno
  signal Ext1, Ext0: std_logic_vector(31 downto 0); 
  
  --Rd2 y Salida de Mux ExtCero
  signal ALUSrcA, ALUSrcB: std_logic_vector(31 downto 0);
  
  signal Op1, Op2, Wd3: std_logic_vector(31 downto 0);
  
  --Jump Target Adress y Branch Target Adress 
  signal JTA: std_logic_vector(31 downto 0);
  signal BTA: std_logic_vector(31 downto 0);
  
  --Salidas multiplicadores 
  signal MultJ: std_logic_vector(27 downto 0);
  signal MultB: std_logic_vector(31 downto 0);
 
  --Salidas de la ALU
  signal MemDataAddress: std_logic_vector(31 downto 0);
  signal Z : std_logic;
  
  --PCSrc
  signal PCSrc: std_logic;
  
  --Salidas de los multiplexores anteriores a PC
  signal Branched, Jumped: std_logic_vector(31 downto 0);
  
  --PC actual
  signal MemProgAddress: std_logic_vector(31 downto 0);
  --PC + 4
  signal PC: std_logic_vector(31 downto 0);

begin
  
  
  -- Instancia de RegMIPS
	Reg: RegsMIPS port map (	
	      Clk  => Clk, 
				NRst => NRst, 
				A1   => MemProgData(25 downto 21), 
				Rd1  => Op1,	
				A2   => MemProgData(20 downto 16), 
				Rd2  => ALUSrcA,
				A3   => MuxA3,
				Wd3  => Wd3,
				We3  => RegWrite
	);
				
  MuxDir: Mux2a1_5bits port map(
        A       => MemProgData(20 downto 16), 
        B       => MemProgData(15 downto 11),
		    Control => RegDest,
		    Z       => MuxA3
	);
	
	ExtensorCero: ExtCero port map(
	      D => MemProgData(15 downto 0),
		    Q => Ext1
	);
	
  ExtensorSigno: ExtSig port map(
	      D => MemProgData(15 downto 0),
		    Q => Ext0
	);       
	
	MuxExt : Mux2a1_32bits port map(
		    A       => Ext0,
		    B       => Ext1,
		    Control => Ext_Cero,
		    Z       => ALUSrcB
	);
	
	MuxAlu : Mux2a1_32bits port map(
		    A       => ALUSrcA,
		    B       => ALUSrcB,
		    Control => ALUSrc,
		    Z       => Op2
	);
	
	ALU : ALUMIPS port map(
	      Op1        => Op1,
		    Op2        => Op2,
		    ALUControl => ALUControl,
		    Res        => MemDataAddress,
		    Z          => Z
  );
	
	--Conectamos la memoria de datos
  MemDataAddr      <= MemDataAddress;
	MemDataDataWrite <= ALUSrcA;
	MemDataWE        <= MemWrite;
	
	MuxReg : Mux2a1_32bits port map(
        A       => MemDataAddress, 
        B       => MemDataDataRead,
		    Control => MemToReg,
		    Z       => Wd3
	);
	
	Mul32 : Mul4_32bits port map(
  		    A => Ext0,
		    Z => MultB
	);
	
	ALUBranch : ALUMIPS port map(
	      Op1        => MultB,
		    Op2        => PC,
		    ALUControl => "010",
		    Res        => BTA,
		    Z          => open 
  );
	
	Mul26 : Mul4_26bits port map(
  		    A => MemProgData(25 downto 0),
		    Z => MultJ
	);
	
	JTA <= PC(31 downto 28) & MultJ;
	
	PCSrc <= Z and Branch;
	
	MuxBranch : Mux2a1_32bits port map(
		    A       => PC,
		    B       => BTA,
		    Control => PCSrc,
		    Z       => Branched
	);
	
	MuxJump : Mux2a1_32bits port map(
		    A       => Branched,
		    B       => JTA,
		    Control => Jump,
		    Z       => Jumped
	);
	
  ProgramCounter : DFlipFlop port map(
		    Clk  => Clk,
		    NRst => NRst,
		    PC   => Jumped,
		    Z    => MemProgAddress
	); 

  MemProgAddr <= MemProgAddress;
  
  ALUPC : ALUMIPS port map(
	      Op1        => MemProgAddress,
		    Op2        => conv_std_logic_vector(4, 32),
		    ALUControl => "010",
		    Res        => PC,
		    Z          => open 
	);
	
	UControl : UnidadControl port map(
		    OPCode     => MemProgData(31 downto 26),
		    Funct      => MemProgData(5 downto 0),
		    Jump       => Jump,
		    Branch     => Branch,
		    MemToReg   => MemToReg,
		    MemWrite   => MemWrite,
		    ALUSrc     => AluSrc,
		    ALUControl => ALUControl,
		    ExtCero    => Ext_Cero,
		    RegWrite   => RegWrite,
		    RegDest    => RegDest
	);
	
end Ej3;
	
