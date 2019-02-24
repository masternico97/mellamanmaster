package es.uam.ads.p2.Apartado5;

public class TesterTienda02 {
   public static void main(String[] args) {
      Venta venta = new Venta();
      Cuna cuna = new Cuna(1, 3);
      Silla silla = new Silla(2, "Magnus", "Ch32", "Roja", 6);
      Carrito carrito = new Carrito(3, "Pium", "Lambo", "Negro", 8);

      venta.anadirProducto(cuna);
      venta.anadirProducto(silla);
      venta.anadirProducto(carrito);

      venta.mandarArreglarC(carrito);
      venta.mandarArreglarS(silla);

      venta.mostrarInventario();
   }
}
