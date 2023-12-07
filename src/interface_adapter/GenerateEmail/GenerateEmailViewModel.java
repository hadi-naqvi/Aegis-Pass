package interface_adapter.GenerateEmail;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GenerateEmailViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private GenerateEmailState state = new GenerateEmailState();

    public GenerateEmailViewModel() { super("generate email"); }

    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter method for the Generate Email view model's state
     * @return The Scan Item view model's state
     */
    public GenerateEmailState getState() {
        return this.state;
    }

    /**
     * Setter method for the Generate Email View model's state
     * @param scanItemState The state to be set
     */
    public void setState(GenerateEmailState scanItemState) {
        this.state = scanItemState;
    }
}
