package com.rocketseat.planner.link.record;

import java.util.UUID;

public record LinkPayload(UUID id, String title, String url) {
}
