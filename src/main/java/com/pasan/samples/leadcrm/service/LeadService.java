package com.pasan.samples.leadcrm.service;

import com.pasan.samples.leadcrm.config.exceptions.DatabaseValidationException;
import com.pasan.samples.leadcrm.config.exceptions.LogicViolationException;
import com.pasan.samples.leadcrm.controller.model.*;
import com.pasan.samples.leadcrm.repository.AgentRepository;
import com.pasan.samples.leadcrm.repository.LeadRepository;
import com.pasan.samples.leadcrm.repository.PropertyRepository;
import com.pasan.samples.leadcrm.repository.PropertyReservationRepository;
import com.pasan.samples.leadcrm.repository.model.Agent;
import com.pasan.samples.leadcrm.repository.model.Lead;
import com.pasan.samples.leadcrm.repository.model.Property;
import com.pasan.samples.leadcrm.repository.model.PropertyReservation;
import com.pasan.samples.leadcrm.util.ErrorCode;
import com.pasan.samples.leadcrm.util.LeadStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final AgentRepository agentRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyReservationRepository propertyReservationRepository;

    public LeadService(LeadRepository leadRepository, AgentRepository agentRepository, PropertyRepository propertyRepository,
                       PropertyReservationRepository propertyReservationRepository) {
        this.leadRepository = leadRepository;
        this.agentRepository = agentRepository;
        this.propertyRepository = propertyRepository;
        this.propertyReservationRepository = propertyReservationRepository;
    }

    public ResponseEntity<CommonResponse> createLead(CreateLeadRequest request) {
        Lead lead = new Lead();
        lead.setName(request.name());
        lead.setContact(request.contact());
        lead.setSource(request.source());
        lead.setInquiryDate(request.inquiryDate());
        lead.setStatus(LeadStatus.UNASIGNED);
        lead = leadRepository.save(lead);

        return CommonResponse.createdResponse(lead.getId());
    }

    public ResponseEntity<CommonResponse> assignLeadToAgent(Integer id, CreateAgentAssignmentRequest request) {
        Optional<Lead> optLead = leadRepository.findById(id);
        if (optLead.isEmpty()) {
            throw new DatabaseValidationException(ErrorCode.LEAD_NOT_FOUND);
        }

        Optional<Agent> optAgent = agentRepository.findById(id);
        if (optAgent.isEmpty()) {
            throw new DatabaseValidationException(ErrorCode.AGENT_NOT_FOUND);
        }

        Lead lead = optLead.get();

        if (LeadStatus.UNASIGNED != lead.getStatus()) {
            throw new LogicViolationException(ErrorCode.INELIGIBLE_LEAD_STATUS);
        }

        lead.setAgent(optAgent.get());
        lead.setFollowUpStatus(request.followUpStatus());
        lead.setPreferredPropertyType(request.preferredPropertyType());
        lead.setBudget(request.budget());
        lead.setNotes(request.note());
        lead.setStatus(LeadStatus.ASSIGNED);
        leadRepository.save(lead);

        return CommonResponse.successResponse();
    }

    @Transactional
    public ResponseEntity<CommonResponse> reserveProperty(Integer id, @Valid CreateReservationRequest request) {
        Optional<Lead> optLead = leadRepository.findById(id);
        if (optLead.isEmpty()) {
            throw new DatabaseValidationException(ErrorCode.LEAD_NOT_FOUND);
        }

        Optional<Property> optProperty = propertyRepository.findById(request.propertyId());
        if (optProperty.isEmpty()) {
            throw new DatabaseValidationException(ErrorCode.PROPERTY_NOT_FOUND);
        }

        Lead lead = optLead.get();

        if (LeadStatus.ASSIGNED != lead.getStatus()) {
            throw new LogicViolationException(ErrorCode.INELIGIBLE_LEAD_STATUS);
        }

        lead.setStatus(LeadStatus.RESERVATION);
        leadRepository.save(lead);

        PropertyReservation propertyReservation = new PropertyReservation();
        propertyReservation.setLead(lead);
        propertyReservation.setProperty(optProperty.get());
        propertyReservation.setReservationDate(request.reservationDate());
        propertyReservation.setReservationFee(request.reservationFee());
        propertyReservation.setExpectedClosingDate(request.expectedClosingDate());
        propertyReservation = propertyReservationRepository.save(propertyReservation);

        return CommonResponse.createdResponse(propertyReservation.getId());
    }

    @Transactional
    public ResponseEntity<CommonResponse> cancelReservation(Integer id, @Valid ReservationCancelRequest request) {
        Optional<Lead> optLead = leadRepository.findById(id);
        if (optLead.isEmpty()) {
            throw new DatabaseValidationException(ErrorCode.LEAD_NOT_FOUND);
        }

        Optional<PropertyReservation> optPropertyReservation = propertyReservationRepository.findById(request.reservationId());
        if (optPropertyReservation.isEmpty()) {
            throw new DatabaseValidationException(ErrorCode.RESERVATION_NOT_FOUND);
        }

        Lead lead = optLead.get();
        PropertyReservation propertyReservation = optPropertyReservation.get();

        if (LeadStatus.RESERVATION != lead.getStatus()) {
            throw new LogicViolationException(ErrorCode.INELIGIBLE_LEAD_STATUS);
        }

        lead.setStatus(LeadStatus.ASSIGNED);
        lead.setNotes(request.notes());
        leadRepository.save(lead);

        propertyReservationRepository.delete(propertyReservation);

        return CommonResponse.successResponse();
    }

    public ResponseEntity<CommonResponse> financialApprovalForReservation(Integer id, @Valid CreateReservationFinancialApprovalRequest request) {
        return null;
    }

    public ResponseEntity<CommonResponse> legalApprovalForReservation(Integer id, @Valid CreateReservationLegalRequest request) {
        return null;
    }

    public ResponseEntity<CommonResponse> saleReservation(Integer id, @Valid CreateSaleRequest request) {
        return null;
    }
}
