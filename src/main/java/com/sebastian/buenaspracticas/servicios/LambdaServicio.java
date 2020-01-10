package com.sebastian.buenaspracticas.servicios;
/**
 * servicios para ser cargados desde la carga del lambda.
 *
 * @author Sebastián Ávila A.
 */
public interface LambdaServicio {
  /** realiza la carga del servicio para evitar volver a realizarlo. */
  public void cargar();
}
