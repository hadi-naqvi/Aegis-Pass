package interface_adapter.LogOut;

import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogOutViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LogOutState state = new LogOutState();

    /**
     * Constructor method for the LogOut view's view model
     */
    public LogOutViewModel() {
        super("log out");
    }

    /**
     * Method which triggers/executes when the state changes
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Method which adds a PropertyChangeListener to the view model
     * @param listener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    /**
     * Getter method for the LogOut view model's state
     * @return The LogOut view model's state
     */
    public LogOutState getState() {
        return this.state;
    }

    /**
     * Setter method for the LogOut's view model's state
     * @param state The state to be set
     */
    public void setState(LogOutState state) {
        this.state = state;
    }
}
