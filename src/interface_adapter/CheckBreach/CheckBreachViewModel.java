package interface_adapter.CheckBreach;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CheckBreachViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private CheckBreachState state = new CheckBreachState();

    /**
     * Constructor method for the View Model of the Check Breach view
     */
    public CheckBreachViewModel() {
        super("check breach");
    }

    /**
     * Method to fire a property change with hte PropertyChangeSupport attribute
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Method to add a property change listener to the PropertyChangeSupport attribute
     * @param listener the listener to add to the PropertyChangeSupport attribute
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter method for the Check Breach view model's state
     * @return The Check Breach view model's state
     */
    public CheckBreachState getState() {
        return state;
    }

    /**
     * Setter method for the Check Breach view model's state
     * @param state The state to be set
     */
    public void setState(CheckBreachState state) {
        this.state = state;
    }
}
