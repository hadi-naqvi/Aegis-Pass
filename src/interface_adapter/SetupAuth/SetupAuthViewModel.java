package interface_adapter.SetupAuth;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SetupAuthViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SetupAuthState state = new SetupAuthState();

    /**
     * Constructor method for the SetupAuth view model
     */
    public SetupAuthViewModel() {
        super("setup auth");
    }

    /**
     * Setter method for the SetupAuth's view model's state
     * @param state The state to be set
     */
    public void setState(SetupAuthState state) {
        this.state = state;
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
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter method for the SetupAuth view model's state
     * @return The SetupAuth view model's state
     */
    public SetupAuthState getState() {
        return this.state;
    }
}
