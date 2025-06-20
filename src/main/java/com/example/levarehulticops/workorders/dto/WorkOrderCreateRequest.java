package com.example.levarehulticops.workorders.dto;

import com.example.levarehulticops.workorders.entity.Client;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * DTO for creating a new WorkOrder.
 * Status is set to CREATED on the server side,
 * requestor is derived from the authenticated user.
 */
public record WorkOrderCreateRequest(

        @NotBlank(message = "Work order number must not be blank")
        String workOrderNumber,

        @NotNull(message = "Client ID must not be null")
        Client client,

        @NotBlank(message = "Well must not be blank")
        String well,

        /** Optional list of item IDs */
        List<Long> stockItemIds,

        /** Optional list of new item info request IDs */
        List<String> newItemsIds,

        @NotNull(message = "Delivery date must not be null")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @FutureOrPresent
        LocalDate deliveryDate,

        /** Optional comments */
        String comments

) {
    /**
     * Ensure that deliveryDate is today or in the future.
     */
    @AssertTrue(message = "Delivery date must be today or in the future")
    public boolean isDeliveryDateValid() {
        if (deliveryDate == null) {
            return true;  // @NotNull will catch missing date
        }
        LocalDate today = LocalDate.now(ZoneId.of("Africa/Cairo"));
        return !deliveryDate.isBefore(today);
    }
}
