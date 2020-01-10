package com.sebastian.buenaspracticas;

import java.io.InputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.sebastian.buenaspracticas.utils.LambdaHandlerTestUtil;

/**
 * test para la clase {@link LambdaHandler}.
 *
 * @author Sebastián Ávila A.
 */
public class LambdaHandlerTest {

  @BeforeAll
  public static void inicio() {
    LambdaHandlerTestUtil.cargarStack();
  }

  @AfterAll
  public static void fin() {
    LambdaHandlerTestUtil.detenerStack();
  }

  @Test
  @DisplayName("permite utilizar el metodo de la lambda con acceso a dynamo en test")
  void permiteEjecutarLambdaConAccesoADynamoEnTest() {
    Assertions.assertDoesNotThrow(() -> {
      final InputStream is = LambdaHandler.class.getClassLoader().getResourceAsStream("empty.json");
      new LambdaHandler(LambdaHandlerTestUtil.dynamodb()).handleRequest(is, System.out, null);
    });

  }
}
