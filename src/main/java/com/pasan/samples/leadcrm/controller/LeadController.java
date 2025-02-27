package com.pasan.samples.leadcrm.controller;

import com.pasan.samples.leadcrm.controller.model.*;
import com.pasan.samples.leadcrm.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/lead")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<LeadsResponse>> getLeads(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer agent) {
        return leadService.getLeads(startDate, endDate, status, agent);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<LeadResponse>> getLead(@PathVariable Integer id) {
        return leadService.getLead(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> createLead(@Valid @RequestBody CreateLeadRequest request) {
        return leadService.createLead(request);
    }

    @PostMapping(value = "/{id}/assign", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> assignLeadToAgent(@PathVariable Integer id, @Valid @RequestBody CreateAgentAssignmentRequest request) {
        return leadService.assignLeadToAgent(id, request);
    }

    @PostMapping(value = "/{id}/reserve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> reserveProperty(@PathVariable Integer id, @Valid @RequestBody CreateReservationRequest request) {
        return leadService.reserveProperty(id, request);
    }

    @DeleteMapping(value = "/{id}/cancel-reservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> cancelReservation(@PathVariable Integer id, @Valid @RequestBody ReservationCancelRequest request) {
        return leadService.cancelReservation(id, request);
    }

    @PostMapping(value = "/{id}/approve-finance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> financialApprovalForReservation(@PathVariable Integer id, @Valid @RequestBody CreateReservationFinancialApprovalRequest request) {
        return leadService.financialApprovalForReservation(id, request);
    }

    @PostMapping(value = "/{id}/approve-legal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> legalApprovalForReservation(@PathVariable Integer id, @Valid @RequestBody CreateReservationLegalRequest request) {
        return leadService.legalApprovalForReservation(id, request);
    }

    @PostMapping(value = "/{id}/sale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> saleReservation(@PathVariable Integer id, @Valid @RequestBody CreateSaleRequest request) {
        return leadService.saleReservation(id, request);
    }
}
