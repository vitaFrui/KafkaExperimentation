package com.example.kafkaexperimentation.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Event creation request object.
 */
@Getter
@Setter
public class EvenCreationRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -1925111592243652207L;

    private Integer numEvents;
}
