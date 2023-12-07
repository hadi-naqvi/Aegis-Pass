package interface_adapter.UpdateAccount;

import interface_adapter.CreateAccount.CreateAccountState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UpdateAccountViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private UpdateAccountState state = new UpdateAccountState();

    /**
     * Constructor method for the UpdateAccount view's view model
     */
    public UpdateAccountViewModel() {
        super("update account");
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
     * Getter method for the UpdateAccount view model's state
     * @return The UpdateAccount view model's state
     */
    public UpdateAccountState getState() {
        return this.state;
    }

    /**
     * Setter method for the UpdateAccount view model's state
     * @param updateAccountState The state to be set
     */
    public void setState(UpdateAccountState updateAccountState) {
        this.state = updateAccountState;
    }
}
