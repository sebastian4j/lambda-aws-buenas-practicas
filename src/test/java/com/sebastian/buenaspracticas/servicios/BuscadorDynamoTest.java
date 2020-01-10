package com.sebastian.buenaspracticas.servicios;

import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.sebastian.buenaspracticas.utils.LambdaHandlerTestUtil;

/**
 * test para la clase {@link BuscadorDynamo}.
 *
 * @author Sebastián Ávila A.
 */
public class BuscadorDynamoTest {

  @BeforeAll
  public static void inicio() {
    LambdaHandlerTestUtil.cargarStack();
  }

  @AfterAll
  public static void fin() {
    LambdaHandlerTestUtil.detenerStack();
  }

  @Test
  @DisplayName("obtengo el valor desde dynamo con localstack")
  public void obtengoValorEsperado() throws IOException {
    final var res = new BuscadorDynamo(LambdaHandlerTestUtil.dynamodb()).buscar("savila").get();
    Assertions.assertThat("{\"modificado\":1578685347727,\"usuario\":\"savila\"}")
        .isEqualTo(res.toString());
  }



}
