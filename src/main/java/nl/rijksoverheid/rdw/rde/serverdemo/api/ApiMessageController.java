package nl.rijksoverheid.rdw.rde.serverdemo.api;

import nl.rijksoverheid.rdw.rde.serverdemo.commands.GetSingleMessageContentCommand;
import nl.rijksoverheid.rdw.rde.serverdemo.commands.ListMessagesCommand;
import nl.rijksoverheid.rdw.rde.serverdemo.components.Mapper;
import nl.rijksoverheid.rdw.rde.serverdemo.components.UrlProvider;
import nl.rijksoverheid.rdw.rde.serverlib.remoteapi.MessageGetResult;
import nl.rijksoverheid.rdw.rde.serverlib.remoteapi.MessageInfoDto;
import nl.rijksoverheid.rdw.rde.serverlib.remoteapi.MessageListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiMessageController {

    @Autowired
    GetSingleMessageContentCommand getSingleMessageContentCommand;
    @Autowired
    ListMessagesCommand listMessagesCommand;
    @Autowired
    UrlProvider urlProvider;

    @RequestMapping(value= "/api/message/list", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageListResult> list()
    {
        try
        {
            var data = listMessagesCommand.Execute(); //TODO result indicates reason for failure or let ex fly
            var items = data.stream().map(x -> Mapper.Map(x, urlProvider)).toArray(MessageInfoDto[]::new);
            var result = new MessageListResult();
            result.setItems(items);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception ex)
        {
            //TODO log
            var result = new MessageListResult();
            result.setError(true);
            result.setItems(new MessageInfoDto[0]);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    //TODO hunting mitigation?
    @RequestMapping(value="/api/message/{id}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageGetResult> getSingle(@PathVariable long id)
    {
        var result = new MessageGetResult();
        result.setId(id);

        try
        {
            var data = getSingleMessageContentCommand.Execute(id);

            if (data == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            result.setContent(data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
//        catch(AuthorizationFailed ex)
//        {
//            //
//            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
//        }
        catch(Exception ex) //TODO probably is catching exception as the rest should be caught in the command
            {
            //TODO log
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
