package nl.rijksoverheid.rdw.rde.serverdemo.api;

import nl.rijksoverheid.rdw.rde.serverdemo.commands.*;
import nl.rijksoverheid.rdw.rde.serverdemo.components.Mapper;
import nl.rijksoverheid.rdw.rde.serverdemo.components.UrlProvider;
import nl.rijksoverheid.rdw.rde.serverlib.remoteapi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
public class ApiDocumentController {
    @Autowired
    GenerateKeyFromDocumentCommand generateKeyFromDocumentCommand;
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
            return new ResponseEntity<>(new DocumentAddResult(EnrollDocumentResult.Other), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/api/document/key", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> exchangeKey(@RequestBody DocumentKeyDto requestBody) {

        //if (bindingResult.hasErrors())
        //  return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try
        {
            var args = new GenerateKeyFromDocumentCommandArgs();
            args.setDocument(requestBody.getDocument());
            var result = generateKeyFromDocumentCommand.execute(args);
            var responseBody = new DocumentKeyResponse();
            responseBody.setOuterSecretKeyBase64(result.getSecretKey());
            return new ResponseEntity<>(result, HttpStatus.OK);
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


