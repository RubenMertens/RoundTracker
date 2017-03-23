package com.test.rest.controllers;

import com.test.rest.resources.InitiativeResource;
import com.test.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ravanys on 21/03/2017.
 */
@RestController
@RequestMapping("api/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public ResponseEntity<String> receiveInitiative(@RequestBody InitiativeResource initiativeResource){
        System.out.println(initiativeResource.getPlayerId() + " : " + initiativeResource.getInitiative());
        gameService.setOrAddInitiative(initiativeResource.getPlayerId(),initiativeResource.getInitiative());
        return new ResponseEntity<String>(HttpStatus.OK);

    }

}
