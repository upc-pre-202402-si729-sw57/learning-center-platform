package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

/**
 * Course aggregate root.
 * @summary
 * This class represents a course in the platform.
 * A course is a collection of tutorials that are organized in a learning path.
 * @since 1.0.0
 */
@Getter
@Entity
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private String title;
    private String description;

    @Embedded
    private LearningPath learningPath;

    /**
     * Constructor.
     * @param title Course title.
     * @param description Course description.
     */
    public Course(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    /**
     * Default constructor.
     */
    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.learningPath = new LearningPath();
    }

    /**
     * Update course information.
     * @param title Course title.
     * @param description Course description.
     * @return Course instance.
     */
    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    /**
     * Add tutorial to learning path.
     * @param tutorialId Tutorial identifier.
     */
    public void addTutorialToLearningPath(Long tutorialId) {
        this.learningPath.addItem(this, tutorialId);
    }

    /**
     * Add tutorial to learning path. The tutorial has a next tutorial.
     * @param tutorialId Tutorial identifier.
     * @param nextTutorialId Next tutorial identifier.
     */
    public void addTutorialToLearningPath(Long tutorialId, Long nextTutorialId) {
        this.learningPath.addItem(this, tutorialId, nextTutorialId);
    }
}
