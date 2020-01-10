package com.sebastian.buenaspracticas.servicios;

import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * cliente para acceder a los datos en dynamo.
 *
 * @author Sebastián Ávila A.
 */
public class BuscadorDynamo {
  private static final Logger LOGGER = LogManager.getLogger(BuscadorDynamo.class);
  public static final String NOMBRE_TABLA = "usuarios";
  private DynamoDB cliente;

  /**
   * instancia la clase con el cliente de acceso a dynamo.
   * 
   * @param ddb acceso a dynamo
   */
  public BuscadorDynamo(final DynamoDB ddb) {
    cliente = ddb;
  }

  /**
   * busca algún dato en dynamo.
   * 
   * @param buscado dato que tiene que ser buscado
   * @throws IOException error en la lectura de las datos de dynamo 
   */
  public Optional<JsonNode> buscar(final String buscado) throws IOException {
    Optional<JsonNode> encontrado = Optional.empty();
    LOGGER.info("buscando datos...");
    final var tpu = cliente.getTable(NOMBRE_TABLA);
    final var items = tpu.query(
        new QuerySpec().withProjectionExpression("usuario, modificado")
            .withKeyConditionExpression("usuario = :usuario")
            .withValueMap(new ValueMap().withString(":usuario", buscado)));
    final var it = items.iterator();
    while (it.hasNext()) {
      encontrado = Optional.ofNullable(JsonMapper.mapper().readTree(it.next().toJSON()));
    }
    return encontrado;
  }

}
