package com.rocketseat.planner.dto.participant;

import java.util.UUID;

public record ParticipantData(UUID id, String nome, String email, Boolean isConfirmed) {
}
