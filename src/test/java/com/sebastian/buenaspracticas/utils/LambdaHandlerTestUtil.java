package com.sebastian.buenaspracticas.utils;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.DYNAMODB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testcontainers.containers.localstack.LocalStackContainer;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.sebastian.buenaspracticas.persistencia.CargadorDatosDynamo;

/**
 * metodos utiles para los test.
 * 
 * @author Sebastián Ávila A.
 *
 */
public class LambdaHandlerTestUtil {
  private static final Logger LOGGER = LogManager.getLogger(LambdaHandlerTestUtil.class);
  private static final LocalStackContainer LS = new LocalStackContainer().withServices(DYNAMODB);
  private static AmazonDynamoDB dyn;

  public static void cargarStack() {
    System.setProperty("test", "test");
    LS.start();
    dyn = AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(LS.getEndpointConfiguration(DYNAMODB))
        .withCredentials(LS.getDefaultCredentialsProvider()).build();
    LOGGER.info("cargar datos de dynamo");
    new CargadorDatosDynamo().cargar(dynamodb());
    LOGGER.info("dynamo cargado");
  }
  
  public static void detenerStack() {
    LS.stop();
  }
  
  public static DynamoDB dynamodb() {
    if (dyn == null) {
      throw new IllegalStateException("aún no se a iniciado el stack");
    }
    return new DynamoDB(dyn);
  }

}
