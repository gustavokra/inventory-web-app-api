package com.kraemer.domain.entities.vo;

import java.time.LocalDateTime;

public class CreatedAtVO {
    
    final LocalDateTime createdAt;

    public CreatedAtVO(final LocalDateTime createdAt) {
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public LocalDateTime getValue() {
        return createdAt;
    }
}
