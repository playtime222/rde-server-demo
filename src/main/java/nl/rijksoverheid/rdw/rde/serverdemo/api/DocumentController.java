package nl.rijksoverheid.rdw.rde.serverdemo.api;

import nl.rijksoverheid.rdw.rde.serverdemo.commands.*;
import nl.rijksoverheid.rdw.rde.serverdemo.components.Mapper;
import nl.rijksoverheid.rdw.rde.serverdemo.components.UrlProvider;
import nl.rijksoverheid.rdw.rde.serverlib.DocumentAddResult;
import nl.rijksoverheid.rdw.rde.serverlib.EnrollDocumentDto;
import nl.rijksoverheid.rdw.rde.serverlib.EnrollDocumentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DocumentController {

    @Autowired
    EnrollDocumentCommand command;
    @Autowired
    UrlProvider urlProvider;

    //AKA enroll
    @RequestMapping(value = "/api/document", method= RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerDocument(@RequestBody EnrollDocumentDto enrollDocument, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try
        {
            var args = Mapper.Map(enrollDocument);
            var result = command.Execute(args); //TODO result indicates reason for failure or let ex fly
            return new ResponseEntity<>(new DocumentAddResult(result), Map(result));
        }
        catch(Exception ex) //TODO probably is catching Exception as the rest should be caught in the command
        {
            //TODO log
            ex.printStackTrace();
            return new ResponseEntity<>(new DocumentAddResult(EnrollDocumentResult.Other),  HttpStatus.BAD_REQUEST);
        }
    }

    private HttpStatus Map(EnrollDocumentResult value)
    {
        switch (value)
        {
            case Success:
                return HttpStatus.CREATED;
            case Duplicate:
                return HttpStatus.CONFLICT;
            default:
                return HttpStatus.BAD_REQUEST;
        }
    }
}


