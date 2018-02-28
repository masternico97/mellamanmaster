----------------------------------------------------------------------
-- Fichero: RegsMIPS.vhd
-- Descripci�n: Banco completo de registros del microprocesador MIPS
-- Fecha �ltima modificaci�n:

-- Autores: Nicol�s Serrano y �lvaro S�nchez
-- Asignatura: E.C. 1� grado
-- Grupo de Pr�cticas: 2111
-- Grupo de Teor�a: 211
-- Pr�ctica: 2
-- Ejercicio: 1
----------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
use IEEE.std_logic_arith.all;

entity RegsMIPS is
	port (
		Clk : in std_logic; -- Reloj
		NRst : in std_logic; -- Reset as�ncrono a nivel bajo
		A1 : in std_logic_vector(4 downto 0); -- Direcci�n para el puerto Rd1
		Rd1 : out std_logic_vector(31 downto 0); -- Dato del puerto Rd1
		A2 : in std_logic_vector(4 downto 0); -- Direcci�n para el puerto Rd2
		Rd2 : out std_logic_vector(31 downto 0); -- Dato del puerto Rd2
		A3 : in std_logic_vector(4 downto 0); -- Direcci�n para el puerto Wd3
		Wd3 : in std_logic_vector(31 downto 0); -- Dato de entrada Wd3
		We3 : in std_logic -- Habilitaci�n del banco de registros
	); 
end RegsMIPS;

architecture Practica of RegsMIPS is

	-- Tipo para almacenar los registros
	type regs_t is array (0 to 31) of std_logic_vector(31 downto 0);

	-- Esta es la se�al que contiene los registros. El acceso es de la
	-- siguiente manera: regs(i) acceso al registro i, donde i es
	-- un entero. Para convertir del tipo std_logic_vector a entero se
	-- hace de la siguiente manera: conv_integer(slv), donde
	-- slv es un elemento de tipo std_logic_vector

	signal regs : regs_t;

begin  -- PRACTICA

	------------------------------------------------------
	-- Escritura del registro Wd
	------------------------------------------------------
	-- Escribe el contenido de Wd3 en el registro indicado
	-- por A3 cuando hay flanco de subida de reloj y
	-- la se�al de habilitaci�n de escritura We3 est� activa.
	-- Tiene reset as�ncrono. Si est� habilitado el reset, 
	-- todos los registros se inicializan a valor 0
	-- No se escribe en el registro R0.
	
	process(Clk, NRst)
	  begin
	    if NRst = '0' then
	      for i in 0 to 31 loop
	       regs(i) <= (others => '0');
	      end loop;
	     elsif rising_edge(Clk) then
	       if We3 = '1' and A3 /= "00000" then
	         regs(conv_integer(A3)) <= Wd3;
	       end if;
	     end if;
	end process;
	           

	------------------------------------------------------
	-- Lectura del registro Rd1
	------------------------------------------------------
	-- Lee en Rd1 el registro indicado por A1. 
	  
	Rd1 <=regs(conv_integer(A1));
	  
	------------------------------------------------------
	-- Lectura del registro Rd2
	------------------------------------------------------
	-- Lee en Rd2 el registro indicado por A2.

  Rd2 <=regs(conv_integer(A2));

end Practica;
