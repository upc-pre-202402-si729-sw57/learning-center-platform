package com.acme.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TutorialId(Long tutorialId) {
    public TutorialId() { this(0L); }

    public TutorialId {
        if (tutorialId < 0)
            throw new IllegalArgumentException("Tutorial id cannot be negative");
    }
}
