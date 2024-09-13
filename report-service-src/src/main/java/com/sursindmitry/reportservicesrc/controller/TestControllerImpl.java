package com.sursindmitry.reportservicesrc.controller;

import com.sursindmitry.reportserviceapi.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Реализация {@link com.sursindmitry.reportserviceapi.controller.TestController}.
 */
@RestController
@Slf4j
public class TestControllerImpl implements TestController {

    @Override
    public String helloController() {
        log.debug("Вызов метода helloController");
        return "Я работаю";
    }
}
