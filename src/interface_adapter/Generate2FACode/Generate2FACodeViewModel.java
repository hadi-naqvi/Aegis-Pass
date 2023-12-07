package interface_adapter.Generate2FACode;

import interface_adapter.CreateAccount.CreateAccountState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Generate2FACodeViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Generate2FACodeState state = new Generate2FACodeState();

    /**
     * Constructor method for the Generate2FACode view's view model
     */
    public Generate2FACodeViewModel() {
        super("generate 2fa code");
    }

    /**
     * method which triggers/executes when the state changes
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
     * Getter method for the Generate2FACode view model's state
     * @return The Generate2FACode view model's state
     */
    public Generate2FACodeState getState() {
        return this.state;
    }

    /**
     * Setter method for the Generate2FACode view model's state
     * @param generate2FACodeState The state to be set
     */
    public void setState(Generate2FACodeState generate2FACodeState) {
        this.state = generate2FACodeState;
    }
}
