package com.rocketseat.planner.dto.link;

import java.util.UUID;

public record LinkRequestPayload(UUID id,String title, String url) {
}
