package com.rocketseat.planner.model.activity;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityData(UUID id, LocalDateTime occurs_at, String title) {
}
