package com.sursindmitry.reportservice;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.spring.api.DBRider;
import com.sursindmitry.commonmodels.util.JsonParserUtil;
import com.sursindmitry.reportservice.initializers.KafkaInitializer;
import com.sursindmitry.reportservice.initializers.PostgresInitializer;
import com.sursindmitry.reportservice.service.TestProducerService;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ContextConfiguration(initializers = {PostgresInitializer.class, KafkaInitializer.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient(timeout = "50000")
@DBRider
@DBUnit(caseSensitiveTableNames = true, schema = "public")
public abstract class BaseIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

    @SpyBean
    protected JsonParserUtil jsonParserUtil;

    @Autowired
    protected TestProducerService testProducerService;
}
