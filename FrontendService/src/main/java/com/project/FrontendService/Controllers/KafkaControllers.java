package com.project.FrontendService.Controllers;


import com.project.FrontendService.Services.FrontendService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class KafkaControllers {
    @Autowired
    private FrontendService frontendService;

    @PostMapping("/journey")
    public void capture(@RequestBody String userInteraction){
        frontendService.sendUserInteraction(userInteraction);
    }
}
