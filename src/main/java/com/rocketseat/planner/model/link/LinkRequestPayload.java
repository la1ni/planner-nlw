package com.rocketseat.planner.model.link;

import java.util.UUID;

public record LinkRequestPayload(UUID id,String title, String url) {
}
