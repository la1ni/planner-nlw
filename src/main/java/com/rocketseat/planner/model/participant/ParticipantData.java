package com.rocketseat.planner.model.participant;

import java.util.UUID;

public record ParticipantData(UUID id, String nome, String email, Boolean isConfirmed) {
}
