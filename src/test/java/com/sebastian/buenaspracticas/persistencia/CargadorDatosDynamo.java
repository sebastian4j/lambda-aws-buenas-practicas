package com.sebastian.buenaspracticas.persistencia;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.sebastian.buenaspracticas.servicios.BuscadorDynamo;

/**
 * crea la tabla y carga un dato inicial.
 * 
 * @author Sebastián Ávila A.
 *
 */
public class CargadorDatosDynamo {
  private Table tabla;
  private static final Logger LOGGER = LogManager.getLogger(CargadorDatosDynamo.class);

  /**
   * crea la tabla y le agrega un registro.
   * 
   * @param ddb cliente para acceder a dynamo
   */
  public void cargar(final DynamoDB ddb) {
    tabla = ddb.createTable(BuscadorDynamo.NOMBRE_TABLA,
        Arrays.asList(new KeySchemaElement("usuario", KeyType.HASH),
            new KeySchemaElement("modificado", KeyType.RANGE)),
        Arrays.asList(new AttributeDefinition("usuario", ScalarAttributeType.S),
            new AttributeDefinition("modificado", ScalarAttributeType.N)),
        new ProvisionedThroughput(10L, 10L));
    final var item =
        new Item().withPrimaryKey("usuario", "savila").withLong("modificado", 1578685347727L);
    tabla.putItem(item);
    LOGGER.debug("demo cargado");
  }
}

