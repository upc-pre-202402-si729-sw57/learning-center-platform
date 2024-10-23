package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the progress record of an enrollment.
 */
@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;

    /**
     * Default Constructor. Initializes the progress record with an empty list of progress record items.
     */
    public ProgressRecord() {
        this.progressRecordItems = new ArrayList<>();
    }

    /**
     * Initializes the progress record with the first tutorial in the learning path.
     *
     * @param enrollment The enrollment to which this progress record belongs
     * @param learningPath The learning path
     */
    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath) {
        if(learningPath.isEmpty()) return;
        Long tutorialId = learningPath.getFirstTutorialIdInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorialId);
        this.progressRecordItems.add(progressRecordItem);
    }

    /**
     * Checks if there is an item in progress in the progress record.
     */
    private boolean hasAnItemInProgress() {
        return this.progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    /**
     * Gets the progress record item with the given tutorial id.
     *
     * @param tutorialId The tutorial id
     * @return The progress record item with the given tutorial id, or null if not found
     */
    public ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId) {
        return this.progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    /**
     * Starts the tutorial with the given tutorial id.
     *
     * @param tutorialId The tutorial id
     */
    public void startTutorial(Long tutorialId) {
        if(hasAnItemInProgress()) throw new IllegalStateException("There is already a tutorial in progress");
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if(Objects.isNull(progressRecordItem))
            throw new IllegalArgumentException("Tutorial not found in progress record");
        if(progressRecordItem.isNotStarted()) progressRecordItem.start();
        else throw new IllegalStateException("Tutorial already started");
    }

    /**
     * Completes the tutorial with the given tutorial id.
     *
     * @param tutorialId The tutorial id
     * @param learningPath The learning path
     */
    public void completeTutorial(Long tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if(Objects.isNull(progressRecordItem)) throw new IllegalArgumentException("Tutorial not found in progress record");
        if(progressRecordItem.isInProgress()) progressRecordItem.complete();
        if(learningPath.isLastTutorialInLearningPath(tutorialId)) return;
        Long nextTutorialId = learningPath.getNextTutorialIdInLearningPath(tutorialId);
        if(Objects.isNull(nextTutorialId)) return;
        ProgressRecordItem nextProgressRecordItem = getProgressRecordItemWithTutorialId(nextTutorialId);
        if(Objects.isNull(nextProgressRecordItem)) {
            nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
            this.progressRecordItems.add(nextProgressRecordItem);
        }
    }

    /**
     * Calculates the number of days elapsed for the given enrollment.
     *
     * @param enrollment The enrollment
     * @return The number of days elapsed for the given enrollment
     */
    public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed)
                .sum();
    }


}
