package interface_adapter.GeneratePassword;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GeneratePasswordViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private GeneratePasswordState state = new GeneratePasswordState();

    /**
     * Constructor method for the Generate Password view's view model
     */
    public GeneratePasswordViewModel() {
        super("generate password");
    }

    /**
     * Method which triggers/executes when the state changes and the presenter fires a property change
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
     * Getter method for the GeneratePassword view model's state
     * @return The generate password view model's state
     */
    public GeneratePasswordState getState() {
        return this.state;
    }

    /**
     * Setter method for the Generate Password view model's state
     * @param generatePasswordState The state to be set
     */
    public void setState(GeneratePasswordState generatePasswordState) {
        this.state = generatePasswordState;
    }
}
