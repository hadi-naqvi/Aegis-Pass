package interface_adapter.Authentication;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AuthenticationViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Aegis Pass Authentication";
    public static final String PASSWORD_LABEL = "Enter your password:";
    public static final String CONFIRM_BUTTON_LABEL = "Confirm";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private AuthenticationState state = new AuthenticationState();

    /**
     * Constructor method for the Authentication view's view model
     */
    public AuthenticationViewModel() {
        super("authentication");
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
     * Getter method for the Authentication view model's state
     * @return The Authentication view model's state
     */
    public AuthenticationState getState() {
        return this.state;
    }

    /**
     * Setter method for the Authentication's view model's state
     * @param state The state to be set
     */
    public void setState(AuthenticationState state) {
        this.state = state;
    }

}
