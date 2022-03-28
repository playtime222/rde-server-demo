//package nl.rijksoverheid.rdw.rde.serverdemo.web;//package nl.rijksoverheid.rdw.rde.serverdemo.web;
//
////import nl.rijksoverheid.rdw.rde.serverdemo.commands.*;
//import nl.rijksoverheid.rdw.rde.serverdemo.components.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/registration")
//public class UserRegistrationController {
////    @Autowired
////    RegisterUserCommand command; //TODO inject at function
////    @Autowired
////    UrlProvider urlProvider; //TODO inject at function
//    @ModelAttribute("user")
//    public UserRegistrationDto userRegistrationDto() {
//        return new UserRegistrationDto();
//    }
//
//    @GetMapping
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new UserRegistrationDto());
//        return "registration";
//    }
//
//    @PostMapping()
//    public ResponseEntity<?> registerUser(@ModelAttribute("user") UserRegistrationDto user, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors())
//            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
//
//        try {
//            //var result = command.Execute(Mapper.Map(userRegistration)); //TODO result indicates reason for failure or let ex fly
//            //TODO redirect to home
//            return new ResponseEntity("Success", HttpStatus.CREATED);
//        } catch (Exception ex) //TODO probably is catching exception as the rest should be caught in the command
//        {
//            return new ResponseEntity<>("Nope", HttpStatus.BAD_REQUEST);
//        }
//    }
//}