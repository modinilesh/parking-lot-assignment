package com.airtribe.parkinglot.dto;

import com.airtribe.parkinglot.enums.TicketStatus;

public record UnparkResponse(Long ticketId, double amount, TicketStatus status, String paymentStatus) {
}
