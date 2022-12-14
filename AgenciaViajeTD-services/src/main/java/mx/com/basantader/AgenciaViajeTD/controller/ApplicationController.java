package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import mx.com.basantader.AgenciaViajeTD.dto.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import mx.com.basantader.AgenciaViajeTD.service.ApplicationService;
import mx.com.basantader.AgenciaViajeTD.model.ApplicationItem;

import mx.com.basantader.AgenciaViajeTD.commons.Messages;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
/**
 * Created by 
 */
@RestController
@RequestMapping("/API")
@Api(value = "Applciation AgenciaViajeTD")
public class ApplicationController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json"
	,value={"/getAllAlerts"})
    @ResponseBody
    @ApiOperation(value = "view the list of ALL current active created stored appllication items", response = ApplicationEntry.class)
    public List<ApplicationEntry> getAllAlerts() {
        log.debug("Trying to retrieve all alerts");
        return applicationService.getApplicationItems();

    }
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json"
	,value={"/getAll"})
    @ResponseBody
    @ApiOperation(value = "view the list of ALL current active created stored appllication items. this methos throws not found exception"
	, response = ApplicationEntry.class)
    public List<ApplicationEntry> getAll() {
        log.debug("Trying to retrieve all alerts");
        throw new ResourceNotFoundException(Messages.getMessage(1));
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json"
	,value={"/createAlert"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create an application entry into the application manager")
    public void createAlert(@Valid @RequestBody ApplicationEntry request) {
        log.debug("Trying to create an alert: {}", request.toString());
        applicationService.createApplicationItem(request);
    }

    @RequestMapping(method={RequestMethod.GET},value={"/version"})
    public String getVersion() {
        return "1.0";
    }
}
