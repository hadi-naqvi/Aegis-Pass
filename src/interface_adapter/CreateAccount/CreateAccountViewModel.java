package interface_adapter.CreateAccount;

import interface_adapter.ViewModel;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateAccountViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private CreateAccountState state = new CreateAccountState();

    /**
     * Constructor method for the CreateAccount view's view model
     */
    public CreateAccountViewModel() {
        super("create account");
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
     * Getter method for the CreateAccount view model's state
     * @return The CreateAccount view model's state
     */
    public CreateAccountState getState() {
        return this.state;
    }

    /**
     * Setter method for the CreateAccount view model's state
     * @param createAccountState The state to be set
     */
    public void setState(CreateAccountState createAccountState) {
        this.state = createAccountState;
    }
}
