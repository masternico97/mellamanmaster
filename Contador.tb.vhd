library IEEE;

use IEEE.std_logic_1164.all;

use IEEE.std_logic_arith.all;

use IEEE.std_logic_unsigned.all;


entity ContadorTb is

end ContadorTb;


architecture Testbench of ContadorTb is

  

  component Contador

  Port (

		Clk : in  STD_LOGIC;

		Reset : in  STD_LOGIC;

		Ce : in  STD_LOGIC;

		Up : in  STD_LOGIC;

    Q : out  STD_LOGIC_VECTOR (7 downto 0)

  );

  end component;

    

  --Entradas  

  signal clk : std_logic := '0';

  signal reset : std_logic := '1';

  signal ce : std_logic := '0';

  signal up : std_logic := '1';

  

  --Salidas

  signal q : std_logic_vector(7 downto 0); 

  

  --Constantes

  constant CLKPERIOD : time := 10 ns;

  constant ESPERA : time := 1 ns;

  constant NINPUT : integer := 8;  

  

begin


  -- Instanciación del componente Contador

  uut: Contador port map(

    Clk => clk,

    Reset => reset,

    Ce => ce,

    Up => up,

    Q => q

  );

  

  --Creación del reloj

  CLKPROCESS: process

  begin

    clk <= '0';

    wait for CLKPERIOD/2;

    clk <= '1';

    wait for CLKPERIOD/2;

  end process;

  

  --Proceso de estímulos

  stim_proc: process

  begin

    --Inicialización

    ce <= '0';

    up <= '1';

    reset <= '1';

    

    wait for ESPERA;

    assert q = x"00"

      report "Error de reset"

			severity failure; 

  

    --Cambiamos el valor del reset

    reset <= '0';

    

    wait for CLKPERIOD;

    

    Assert q = x"00" 

			report "Error de Ce"

			severity failure;

    

    --Cambiamos el valor del Ce

    ce <= '1';

    wait for ESPERA;

    for i in 1 to 255 loop

			wait until clk = '1';

			wait for ESPERA;

			assert conv_integer(q)=i

				report "Fallo con valor i = "  & integer'image(i)

				severity failure;

		end loop;

    

    wait until clk = '1';

			wait for ESPERA;

			assert conv_integer(q)=0

				report "Fallo con valor 0"

				severity failure;

    

    

    --Hacemos lo mismo con up igual a 0

    up <= '0';
    wait for ESPERA;

    for i in 255 downto 0 loop

			wait until clk = '1';

			wait for ESPERA;

			assert conv_integer(q)=i

				report "Fallo con valor i = "  & integer'image(i)

				severity failure;

		end loop;

    

    --Ponemos reset a 1

    

    reset <= '1';

    

    wait for ESPERA;

    assert q = x"00"

      report "Error de reset"

			severity failure; 

    

    

    report "Simulación correcta";

    wait;

end process;


end Testbench;  
