package com.sebastian.buenaspracticas.servicios;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

/**
 * cliente para acceder a los datos de una tabla en dynamo.
 *
 * @author Sebastián Ávila A.
 */
public class ClienteDynamo implements LambdaServicio {
  private static final Logger LOGGER = LogManager.getLogger(ClienteDynamo.class);
  private static AmazonDynamoDB add;
  private static DynamoDB ddb;
  private static ClienteDynamo cd;

  /**
   * obtiene la instancia del cliente que envuelve al cliente ya inciado.
   * 
   * @return cliente que envuelve a dynamo
   * @throws IOException 
   */
  public static synchronized ClienteDynamo instance() throws IOException {
    if (add == null) {
      add = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
      ddb = new DynamoDB(add);
      new BuscadorDynamo(ddb).buscar("---");
    }
    if (cd == null) {
      cd = new ClienteDynamo();
    }
    return cd;
  }

  /**
   * obtiene la instancia para {@link DynamoDB}.
   * 
   * @return acceso a dynamodb
   */
  public DynamoDB ddb() {
    return ddb;
  }

  @Override
  public void cargar() {
    LOGGER.info("iniciar cliente dynamo");
    try {
      instance();
    } catch (IOException e) {
      throw new IllegalStateException("no se puede instanciar servicio", e);
    }
    LOGGER.info("cliente dynamo cargado");
  }
}
