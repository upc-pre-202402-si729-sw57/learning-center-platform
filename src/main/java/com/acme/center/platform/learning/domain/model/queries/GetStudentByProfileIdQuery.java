package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;

public record GetStudentByProfileIdQuery(ProfileId profileId) {
    public GetStudentByProfileIdQuery {
        if (profileId == null || profileId.profileId() == null || profileId.profileId() <= 0) {
            throw new IllegalArgumentException("ProfileId cannot be null or less than or equal to 0");
        }
    }
}
