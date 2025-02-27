package com.pasan.samples.leadcrm.service;

import com.pasan.samples.leadcrm.config.exceptions.DatabaseValidationException;
import com.pasan.samples.leadcrm.config.exceptions.LogicViolationException;
import com.pasan.samples.leadcrm.controller.model.*;
import com.pasan.samples.leadcrm.repository.*;
import com.pasan.samples.leadcrm.repository.model.*;
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
    private final SaleRepository saleRepository;

    public LeadService(LeadRepository leadRepository, AgentRepository agentRepository, PropertyRepository propertyRepository,
                       PropertyReservationRepository propertyReservationRepository,
                       SaleRepository saleRepository) {
        this.leadRepository = leadRepository;
        this.agentRepository = agentRepository;
        this.propertyRepository = propertyRepository;
        this.propertyReservationRepository = propertyReservationRepository;
        this.saleRepository = saleRepository;
    }

    public ResponseEntity<CommonResponse> createLead(CreateLeadRequest request) {
        Lead lead = new Lead();
        lead.setName(request.name());
        lead.setContact(request.contact());
        lead.setSource(request.source());
        lead.setInquiryDate(request.inquiryDate());
        lead.setStatus(LeadStatus.UNASSIGNED);
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

        if (LeadStatus.UNASSIGNED != lead.getStatus()) {
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

        if (Boolean.TRUE.equals(request.financialStatus())) {
            lead.setStatus(LeadStatus.FINANCIAL_APPROVED);
        } else {
            lead.setStatus(LeadStatus.ENDED);
        }
        leadRepository.save(lead);

        propertyReservation.setFinancialStatus(request.financialStatus());
        propertyReservation.setLoanAmount(request.loanAmount());
        propertyReservation.setPaymentPlan(request.paymentPlan());
        propertyReservationRepository.save(propertyReservation);

        return CommonResponse.successResponse();
    }

    public ResponseEntity<CommonResponse> legalApprovalForReservation(Integer id, @Valid CreateReservationLegalRequest request) {
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

        if (LeadStatus.FINANCIAL_APPROVED != lead.getStatus()) {
            throw new LogicViolationException(ErrorCode.INELIGIBLE_LEAD_STATUS);
        }

        if (Boolean.TRUE.equals(request.contractSinged())) {
            lead.setStatus(LeadStatus.LEGAL_FINALIZED);
        } else {
            lead.setStatus(LeadStatus.ENDED);
        }
        leadRepository.save(lead);

        propertyReservation.setContractSigned(request.contractSinged());
        propertyReservation.setLegalNotes(request.legalNotes());
        propertyReservationRepository.save(propertyReservation);

        return CommonResponse.successResponse();
    }

    public ResponseEntity<CommonResponse> saleReservation(Integer id, @Valid CreateSaleRequest request) {
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

        if (LeadStatus.LEGAL_FINALIZED != lead.getStatus()) {
            throw new LogicViolationException(ErrorCode.INELIGIBLE_LEAD_STATUS);
        }

        lead.setStatus(LeadStatus.SALE);
        leadRepository.save(lead);

        Sale sale = new Sale();
        sale.setLead(lead);
        sale.setAgent(lead.getAgent());
        sale.setProperty(propertyReservation.getProperty());
        sale.setSaleDate(request.saleDate());
        sale.setFinalSalePrice(request.finalSalePrice());
        sale.setCommissionDetails(request.commissionDetails());
        saleRepository.save(sale);

        return CommonResponse.successResponse();
    }
}
