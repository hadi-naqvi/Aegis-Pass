package interface_adapter.ScanItem;

import interface_adapter.Dashboard.DashboardState;
import interface_adapter.ViewModel;
import view.ScanItemView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ScanItemViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ScanItemState state = new ScanItemState();

    public ScanItemViewModel() { super("scan item"); }


    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter method for the Scan Item view model's state
     * @return The Scan Item view model's state
     */
    public ScanItemState getState() {
        return this.state;
    }

    /**
     * Setter method for the Dashboard view model's state
     * @param scanItemState The state to be set
     */
    public void setState(ScanItemState scanItemState) {
        this.state = scanItemState;
    }
}
