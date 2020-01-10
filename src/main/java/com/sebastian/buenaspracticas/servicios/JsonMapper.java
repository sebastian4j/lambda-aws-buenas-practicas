package com.sebastian.buenaspracticas.servicios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * envuelve una instancia del {@link ObjectMapper} para instanciarla al cargar el lambda.
 * 
 * @author Sebastián Ávila A.
 *
 */
public class JsonMapper implements LambdaServicio {
  private static final Logger LOGGER = LogManager.getLogger(JsonMapper.class);
  private static final ObjectMapper OM;

  static {
    OM = new ObjectMapper();
    OM.enable(SerializationFeature.INDENT_OUTPUT);
  }

  /**
   * mapper con opciones predefinidas.
   * 
   * @return mapper para json.
   */
  public static ObjectMapper mapper() {
    return OM;
  }

  @Override
  public void cargar() {
    LOGGER.info("json mapper cargado");
  }
}
