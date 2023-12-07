package interface_adapter.CheckPassQuality;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CheckPassQualityViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private CheckPassQualityState state = new CheckPassQualityState();

    /**
     * Constructor method for the Check password quality use case's view model
     */
    public CheckPassQualityViewModel() {
        super("check pass quality");
    }

    /**
     * Setter method for the state
     * @param state The state
     */
    public void setState(CheckPassQualityState state) {
        this.state = state;
    }

    /**
     * Getter method for the state
     * @return The state
     */
    public CheckPassQualityState getState() {
        return this.state;
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
