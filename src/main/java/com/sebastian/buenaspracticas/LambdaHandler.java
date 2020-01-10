package com.sebastian.buenaspracticas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ServiceLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.sebastian.buenaspracticas.servicios.BuscadorDynamo;
import com.sebastian.buenaspracticas.servicios.ClienteDynamo;
import com.sebastian.buenaspracticas.servicios.LambdaServicio;

/**
 * demo usando buenas practicas y recomendaciones.
 *
 * @author Sebastián Ávila A.
 */
public class LambdaHandler implements RequestStreamHandler {
  private static final Logger LOGGER = LogManager.getLogger(LambdaHandler.class);
  private DynamoDB cliente;

  public LambdaHandler() {}

  public LambdaHandler(final DynamoDB ddb) {
    cliente = ddb;
  }

  static {
    if (System.getProperty("test") == null) { // en test no es necesario cargarlos
      LOGGER.info("cargando servicios");
      ServiceLoader.load(LambdaServicio.class).forEach(LambdaServicio::cargar);
      LOGGER.info("servicios cargados");
    }
  }

  @Override
  public void handleRequest(InputStream is, OutputStream os, Context ctx) throws IOException {
    LOGGER.info("inicio demo");
    LOGGER.info(new BuscadorDynamo(cliente == null ? ClienteDynamo.instance().ddb() : cliente).buscar("savila"));
    LOGGER.info("fin demo");
  }
}
