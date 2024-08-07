package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model;

import java.math.BigDecimal;
/**
 * Represents a bill entity(using java records).
 */
public record Bill(
        int id,
        int order_id,
        BigDecimal amount
) { }
