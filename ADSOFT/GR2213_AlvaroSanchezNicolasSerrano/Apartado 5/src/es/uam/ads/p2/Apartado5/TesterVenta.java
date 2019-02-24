package es.uam.ads.p2.Apartado5;

public class TesterVenta {
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

      System.out.println("Inventario con los tres productos iniciales:");
      venta.mostrarInventario();

      /*Dado que nuestras sillas depende del azar, haremos mandarArreglarS
      previamente a todas para asegurarnos de que están en perfecto estado*/
      Silla silla1 = new Silla(4, "Sterling", "Zt18", "Roja", 5);
      Silla silla2 = new Silla(5, "Magnus", "Ch30", "Verde", 7);
      Silla silla3 = new Silla(6, "Magnus", "Ch32", "Azul", 6);

      venta.anadirProducto(silla1);
      venta.anadirProducto(silla2);
      venta.anadirProducto(silla3);

      venta.mandarArreglarS(silla1);
      venta.mandarArreglarS(silla2);
      venta.mandarArreglarS(silla3);

      venta.autorizarPago(silla);
      venta.autorizarPago(silla);
      venta.autorizarPago(silla);

      System.out.println("\nInventario con las tres sillas nuevas:");
      venta.mostrarInventario();

      venta.venderArticulo(silla1);
      venta.venderArticulo(silla2);
      venta.venderArticulo(silla3);

      System.out.println("\nInventario tras haber vendido las tres ultimas sillas:");
      venta.mostrarInventario();
   }
}
