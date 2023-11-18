package interface_adapter.Authentication;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class AuthenticationViewModel extends ViewModel {
    /**
     * Constructor method for the Authentication view's view model
     */
    public AuthenticationViewModel() {
        super("authentication");
    }

    @Override
    public void firePropertyChanged() {
        
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
