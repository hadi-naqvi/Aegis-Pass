package interface_adapter.DeleteAccount;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteAccountViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private DeleteAccountState state = new DeleteAccountState();

    /**
     * Constructor method for Delete Account use case
     */
    public DeleteAccountViewModel() {
        super("delete account");
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
     * Getter method for the Delete Account view model's state
     * @return the Delete Account view model's state
     */
    public DeleteAccountState getState() {
        return this.state;
    }

    /**
     * Setter method for the Delete Account view model's state
     * @param state the Delete Account view model's state
     */
    public void setState(DeleteAccountState state) {
        this.state = state;
    }
}
