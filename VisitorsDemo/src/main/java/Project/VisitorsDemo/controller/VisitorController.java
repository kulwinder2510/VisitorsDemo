package Project.VisitorsDemo.controller;

import Project.VisitorsDemo.model.Visitors;
import Project.VisitorsDemo.repository.VisitorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RestController

public class VisitorController {

    @Autowired
    private VisitorRepo visitorRepo;

    @GetMapping
    public ResponseEntity<List<Visitors>> getAllVisitors() {
        try {
            List<Visitors> visitorsList = new ArrayList<>();
            visitorRepo.findAll().forEach(visitorsList::add);

            if (visitorsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVisitorsById/{id}")
    public ResponseEntity<Visitors> getVisitorsById(@PathVariable int id) {
        Optional<Visitors> visitorsData = visitorRepo.findById(id);

        if (visitorsData.isPresent()) {
            return new ResponseEntity<>(visitorsData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addVisitors")
    public ResponseEntity<Visitors> addVisitors(@RequestBody Visitors visitors) {
        Visitors visitorObj = visitorRepo.save(visitors);
        return new ResponseEntity<>(visitorObj, HttpStatus.OK);
    }
       @PostMapping("/updateVisitorsById/{id}")
       public ResponseEntity<Visitors> updateVisitorById(@PathVariable int id){
       Optional<Visitors> repeatedVisitorData=visitorRepo.findById(id);
       if(repeatedVisitorData.isPresent()){
           Visitors updatedVisitorsData=repeatedVisitorData.get();
           Visitors newVisitorData = null;
           updatedVisitorsData.setFirstName(newVisitorData.getFirstName());
           updatedVisitorsData.setLastName(newVisitorData.getLastName());
       Visitors visitorObj=visitorRepo.save(updatedVisitorsData);
       return new ResponseEntity<>(visitorObj,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteVisitorsById/{id}")
    public ResponseEntity<HttpStatus> deleteVisitorsById(@PathVariable int id) {
        visitorRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
