package com.sursindmitry.reportservice;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.spring.api.DBRider;
import com.sursindmitry.commonmodels.util.JsonParserUtil;
import com.sursindmitry.reportservice.initializers.PostgresInitializer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ContextConfiguration(initializers = PostgresInitializer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.Random.class)
@ActiveProfiles("test")
@AutoConfigureWebTestClient(timeout = "50000")
@DBRider
@DBUnit(caseSensitiveTableNames = true, schema = "public")
public class BaseIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

    @SpyBean
    protected JsonParserUtil jsonParserUtil;
}
