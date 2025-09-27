package com.flair.flair.controller;

import com.flair.flair.model.NewAssignmentRequest;
import com.flair.flair.service.impl.AssignmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    private final AssignmentServiceImpl assignmentService;

    public AssignmentController(AssignmentServiceImpl assignmentService) {
        this.assignmentService = assignmentService;
    }

    //TODO make response
    @PostMapping("/new")
    public ResponseEntity<String> createAssignment(@RequestBody NewAssignmentRequest assignmentRequest) {
        this.assignmentService.createAssignment(assignmentRequest);
        return ResponseEntity.ok("success");
    }
}
