package model.tasks;

import model.contracts.tasks.Feedback;
import model.enums.Status;

import utils.ValidationHelpers;


public class FeedbackImpl extends BaseTask implements Feedback {
    private static final int RATING_MIN = 1;
    private static final int RATING_MAX = 10;
    private static final String RATING_ERROR_MESSAGE = String.format("Rating must be between %s and %s.",
            RATING_MIN, RATING_MAX);
    public static final String CHANGE_RATING = "Rating set from %d to %d";
    public static final String CREATED_FEEDBACK = "Feedback with ID:%d was created.";
    public static final String PROVIDED_INVALID = "Provided invalid %s.";

    private int rating;

    protected FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, Status.NEW);
        this.rating = rating;
        addHistoryLog(CREATED_FEEDBACK.formatted(getID()));
    }

    public static FeedbackImpl createFeedback(int id, String title, String description, int rating) {
        validateTitle(title);
        validateDescription(description);
        ValidationHelpers.validateValue(rating, RATING_MIN, RATING_MAX, RATING_ERROR_MESSAGE);
        FeedbackImpl feedback = new FeedbackImpl(id, title, description, rating);
        System.out.printf(TASK_CREATED.formatted(feedback.getRealName(), feedback.getID()));

        return feedback;
    }


    private void setRating(int rating) {
        ValidationHelpers.validateValue(rating, RATING_MIN, RATING_MAX, RATING_ERROR_MESSAGE);
        if (this.rating != 0) {
            addHistoryLog(CHANGE_RATING.formatted(this.rating, rating));
        }
        this.rating = rating;
    }

    @Override
    public void setStatus(String status) {
       this.status = setEnumValue(Status.getFeedbackStatusList(), this.status, status, "Status", PROVIDED_INVALID);
    }

    @Override
    public int getRating() {
        return this.rating;
    }

    @Override
    public void changeRating(int rating) {
        setRating(rating);
    }

    @Override
    public String toString() {
        return "ID#%d | Type: %s | Status: %s%nTitle: %s%n"
                .formatted(getID(), getRealName(), getStatus(), getTitle());

    }
}
