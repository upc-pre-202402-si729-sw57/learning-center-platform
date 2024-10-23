package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LearningPath is a value object that represents the learning path of a course.
 * It is embedded in the Course aggregate root.
 * It is a list of LearningPathItem objects.
 * @since 1.0
 */
@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

    /**
     * Default constructor
     */
    public LearningPath() {
        this.learningPathItems = new ArrayList<>();
    }

    /**
     * Constructor with a list of LearningPathItem objects
     * @param course The course to which the learning path belongs.
     * @param tutorialId The tutorial id
     * @param nextItem The next item in the learning path
     *
     * @see Course
     * @see LearningPathItem
     */
    public void addItem(Course course,Long tutorialId, LearningPathItem nextItem) {
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, nextItem);
        this.learningPathItems.add(learningPathItem);
    }

    /**
     * Add a LearningPathItem to the learning path
     * @param course The course to which the learning path belongs.
     * @param tutorialId The tutorial id
     *
     * @see Course
     * @see LearningPathItem
     */
    public void addItem(Course course, Long tutorialId) {
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, null);
        LearningPathItem originalLastItem = null;
        if (!this.learningPathItems.isEmpty())
            originalLastItem = this.learningPathItems.getLast();
        learningPathItems.add(learningPathItem);
        if (originalLastItem != null) originalLastItem.updateNextItem(learningPathItem);
    }

    /**
     * Get learning path item which has the tutorial id
     * @param tutorialId The tutorial id
     * @return The LearningPathItem object
     * @see LearningPathItem
     */
    public LearningPathItem getLearningPathItemWithTutorialId(Long tutorialId) {
        return learningPathItems.stream()
                .filter(item -> item.getTutorialId().equals(tutorialId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Add a LearningPathItem to the learning path
     * @param course The course to which the learning path belongs.
     * @param tutorialId The tutorial id
     * @param nextTutorialId The next tutorial id
     *
     * @see Course
     * @see LearningPathItem
     */
    public void addItem(Course course, Long tutorialId, Long nextTutorialId) {
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(nextTutorialId);
        addItem(course, tutorialId, nextItem);
    }

    /**
     * Get the first tutorial id in the learning path
     * @return The first tutorial id
     */
    public Long getFirstTutorialIdInLearningPath() {
        return learningPathItems.getFirst().getTutorialId();
    }

    /**
     * Get the last tutorial id in the learning path
     * @return The last tutorial id
     */
    public Long getLastTutorialIdInLearningPath() {
        return learningPathItems.getLast().getTutorialId();
    }

    /**
     * Get the learning path item with the given id
     * @param itemId The id of the learning path item
     * @return The LearningPathItem object
     * @see LearningPathItem
     */
    public LearningPathItem getLearningPathItemWithId(Long itemId) {
        return learningPathItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get the next tutorial id in the learning path
     * @param currentTutorialId The id of the current tutorial
     * @return The next tutorial id
     */
    public Long getNextTutorialIdInLearningPath(Long currentTutorialId) {
        LearningPathItem currentLearningPathItem = getLearningPathItemWithTutorialId(currentTutorialId);
        return Objects.isNull(currentLearningPathItem) ? null : currentLearningPathItem.getNextItem().getTutorialId();
    }

    /**
     * Verify if the current tutorial is the last tutorial in the learning path
     * @param currentTutorialId The id of the current tutorial
     * @return True if the current tutorial is the last tutorial in the learning path, false otherwise
     */
    public boolean isLastTutorialInLearningPath(Long currentTutorialId) {
        return getLearningPathItemWithTutorialId(currentTutorialId).getNextItem() == null;
    }

    /**
     * Verify if the learning path is empty
     * @return True if the learning path is empty, false otherwise
     */
    public boolean isEmpty() {
        return learningPathItems.isEmpty();
    }

}
