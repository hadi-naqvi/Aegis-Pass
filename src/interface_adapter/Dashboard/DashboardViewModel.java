package interface_adapter.Dashboard;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DashboardViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private DashboardState state = new DashboardState();

    /**
     * Constructor method for the Dashboard view's view model
     */
    public DashboardViewModel() {
        super("display dash");
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
     * Getter method for the Dashboard view model's state
     * @return The Dashboard view model's state
     */
    public DashboardState getState() {
        return this.state;
    }

    /**
     * Setter method for the Dashboard view model's state
     * @param dashboardState The state to be set
     */
    public void setState(DashboardState dashboardState) {
        this.state = dashboardState;
    }
}
