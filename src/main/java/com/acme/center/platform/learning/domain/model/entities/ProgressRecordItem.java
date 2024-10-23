package com.acme.center.platform.learning.domain.model.entities;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressStatus;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Represents a single item in a progress record.
 */
@Getter
@Entity
public class ProgressRecordItem extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @Getter
    private Long tutorialId;

    private ProgressStatus status;

    private Date startedAt;

    private Date completedAt;

    /**
     * Default Constructor, required by JPA
     */
    public ProgressRecordItem() {
        // Required by JPA
    }

    /**
     * Creates a new instance of ProgressRecordItem
     *
     * @param enrollment The enrollment to which this progress record item belongs
     * @param tutorialId The tutorial id
     */
    public ProgressRecordItem(Enrollment enrollment, Long tutorialId) {
        this.enrollment = enrollment;
        this.tutorialId = tutorialId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    /**
     * Updates the status of the progress record item to started and sets the startedAt date to now
     */
    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    /**
     * Updates the status of the progress record item to completed and sets the completedAt date to now
     */
    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    /**
     * Checks if the progress record item is completed
     * @return true if the progress record item is completed, false otherwise
     */
    public boolean isCompleted() {
        return ProgressStatus.COMPLETED.equals(status);
    }

    /**
     * Checks if the progress record item is in progress
     * @return true if the progress record item is in progress, false otherwise
     */
    public boolean isInProgress() {
        return ProgressStatus.STARTED.equals(status);
    }

    /**
     * Checks if the progress record item has not been started
     * @return true if the progress record item has not been started, false otherwise
     */
    public boolean isNotStarted() {
        return ProgressStatus.NOT_STARTED.equals(status);
    }

    /**
     * Calculates the number of days elapsed since the progress record item was started
     * @return the number of days elapsed since the progress record item was started,
     * the number of days elapsed since the progress record item was completed,
     * or 0 if the progress record item has not been started,
     */
    public long calculateDaysElapsed() {
        if (isNotStarted()) return 0;
        var defaultTimeZone = ZoneId.systemDefault();
        var fromDate = this.startedAt.toInstant();
        var toDate = isCompleted() ? this.completedAt.toInstant() : LocalDate.now().atStartOfDay(defaultTimeZone).toInstant();
        return Duration.between(fromDate, toDate).toDays();
    }

}
