package es.uam.eps.ads.p4;

/**
 * Apartado 5 de la practica que comprueba que todo funciona correctamente
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Apartado5{
    private static final int NUM_VECINOS = 100;
    private static final int NOTA_MIN = 3;
    private static final int TAM_RECOMENDACION = 5;

    public static void main(String[] args) {
        ModeloDatos modeloRecomendador = new ModeloDatosRecomendacion(args[0]);
        ModeloDatos modeloMetrica = new ModeloDatosRecomendacion(args[1]);
        Recomendador recomendadorVecinos = new RecomendadorVecinos(modeloRecomendador, NUM_VECINOS);
        Recomendador recomendadorAleatorio = new RecomendadorAleatorio(modeloRecomendador);
        Recomendador recomendadorPopularidad = new RecomendadorPopularidad(modeloRecomendador);
        Recomendacion recomendacion;
        Metrica metricaPrecision = new MetricaPrecision(modeloMetrica, NOTA_MIN);
        Metrica metricaRecall = new MetricaRecall(modeloMetrica, NOTA_MIN);
        Metrica metricaPrecisionModificada = new MetricaPrecision(modeloMetrica, NOTA_MIN+1);
        Metrica metricaRecallModificada = new MetricaRecall(modeloMetrica, NOTA_MIN+1);
        double evaluaPrecision = 0;
        double evaluaRecall = 0;

        if(args.length != 2){
        	System.out.println("Faltan los ficheros de entrada");
        	return;
        }

        /* Metricas de Recomendador de vecinos con datos dados de la practica */
        for(long current_user: modeloMetrica.getUsuariosUnicos()){
            try{
                recomendacion = recomendadorVecinos.recomienda(current_user, TAM_RECOMENDACION);
                evaluaPrecision += metricaPrecision.evalua(recomendacion, TAM_RECOMENDACION);
                evaluaRecall += metricaRecall.evalua(recomendacion, TAM_RECOMENDACION);
            } catch(RecomendacionInvalida e) {
                System.out.println(e);
            } catch(UsuarioNoRelevante e) {
            	System.out.println(e);
            }
        }
        System.out.println("\nRecomendador de Vecinos original");
        System.out.println("Metrica precision: "+evaluaPrecision/modeloMetrica.getUsuariosUnicos().size());
        System.out.println("Metrica recall: "+evaluaRecall/modeloMetrica.getUsuariosUnicos().size());

        evaluaPrecision = 0;
        evaluaRecall = 0;

        /* Metricas de Recomendador de vecinos con datos modificados de la practica */
        for(long current_user: modeloMetrica.getUsuariosUnicos()){
            try{
                recomendacion = recomendadorVecinos.recomienda(current_user, TAM_RECOMENDACION);
                evaluaPrecision += metricaPrecisionModificada.evalua(recomendacion, TAM_RECOMENDACION);
                evaluaRecall += metricaRecallModificada.evalua(recomendacion, TAM_RECOMENDACION);
            } catch(RecomendacionInvalida e) {
                System.out.println(e);
            } catch(UsuarioNoRelevante e) {
            	System.out.println(e);
            }
        }
        System.out.println("\nRecomendador de Vecinos modificada");
        System.out.println("Metrica precision: "+evaluaPrecision/modeloMetrica.getUsuariosUnicos().size());
        System.out.println("Metrica recall: "+evaluaRecall/modeloMetrica.getUsuariosUnicos().size());

        evaluaPrecision = 0;
        evaluaRecall = 0;

        /* Metricas de Recomendador de Popularidad con datos dados de la practica */
        for(long current_user: modeloMetrica.getUsuariosUnicos()){
            try{
                recomendacion = recomendadorPopularidad.recomienda(current_user, TAM_RECOMENDACION);
                evaluaPrecision += metricaPrecision.evalua(recomendacion, TAM_RECOMENDACION);
                evaluaRecall += metricaRecall.evalua(recomendacion, TAM_RECOMENDACION);
            } catch(RecomendacionInvalida e) {
                System.out.println(e);
            } catch(UsuarioNoRelevante e) {
            	System.out.println(e);
            }
        }
        System.out.println("\nRecomendador de Popularidad original");
        System.out.println("Metrica precision: "+evaluaPrecision/modeloMetrica.getUsuariosUnicos().size());
        System.out.println("Metrica recall: "+evaluaRecall/modeloMetrica.getUsuariosUnicos().size());

        evaluaPrecision = 0;
        evaluaRecall = 0;

        /* Metricas de Recomendador de Popularidad con datos modificados de la practica */
        for(long current_user: modeloMetrica.getUsuariosUnicos()){
            try{
                recomendacion = recomendadorPopularidad.recomienda(current_user, TAM_RECOMENDACION);
                evaluaPrecision += metricaPrecisionModificadaModificada.evalua(recomendacion, TAM_RECOMENDACION);
                evaluaRecall += metricaRecallModificada.evalua(recomendacion, TAM_RECOMENDACION);
            } catch(RecomendacionInvalida e) {
                System.out.println(e);
            } catch(UsuarioNoRelevante e) {
            	System.out.println(e);
            }
        }
        System.out.println("\nRecomendador de Popularidad moficado");
        System.out.println("Metrica precision: "+evaluaPrecision/modeloMetrica.getUsuariosUnicos().size());
        System.out.println("Metrica recall: "+evaluaRecall/modeloMetrica.getUsuariosUnicos().size());

        evaluaPrecision = 0;
        evaluaRecall = 0;

        /* Metricas de Recomendador Aleatorio con datos dados de la practica */
        for(long current_user: modeloMetrica.getUsuariosUnicos()){
            try{
                recomendacion = recomendadorAleatorio.recomienda(current_user, TAM_RECOMENDACION);
                evaluaPrecision += metricaPrecision.evalua(recomendacion, TAM_RECOMENDACION);
                evaluaRecall += metricaRecall.evalua(recomendacion, TAM_RECOMENDACION);
            } catch(RecomendacionInvalida e) {
                System.out.println(e);
            } catch(UsuarioNoRelevante e) {
            	System.out.println(e);
            }
        }
        System.out.println("\nRecomendador Aleatorio original");
        System.out.println("Metrica precision: "+evaluaPrecision/modeloMetrica.getUsuariosUnicos().size());
        System.out.println("Metrica recall: "+evaluaRecall/modeloMetrica.getUsuariosUnicos().size());

        evaluaPrecision = 0;
        evaluaRecall = 0;

        /* Metricas de Recomendador Aleatorio con datos modificados de la practica */
        for(long current_user: modeloMetrica.getUsuariosUnicos()){
            try{
                recomendacion = recomendadorAleatorio.recomienda(current_user, TAM_RECOMENDACION);
                evaluaPrecision += metricaPrecisionModificada.evalua(recomendacion, TAM_RECOMENDACION);
                evaluaRecall += metricaRecallModificada.evalua(recomendacion, TAM_RECOMENDACION);
            } catch(RecomendacionInvalida e) {
                System.out.println(e);
            } catch(UsuarioNoRelevante e) {
            	System.out.println(e);
            }
        }
        System.out.println("\nRecomendador Aleatorio modificado");
        System.out.println("Metrica precision: "+evaluaPrecision/modeloMetrica.getUsuariosUnicos().size());
        System.out.println("Metrica recall: "+evaluaRecall/modeloMetrica.getUsuariosUnicos().size());
    }
}
