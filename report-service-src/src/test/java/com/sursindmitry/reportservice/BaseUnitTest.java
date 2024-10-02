package com.sursindmitry.reportservice;

import com.sursindmitry.commonmodels.util.JsonParserUtil;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {

    @Spy
    protected JsonParserUtil jsonParserUtil;
}
