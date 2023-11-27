package interface_adapter.Dashboard;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class DashboardViewModel extends ViewModel {
    private DashboardState state = new DashboardState();
    public DashboardViewModel() {
        super("display dash");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public DashboardState getState() {
        return this.state;
    }

    public void setState(DashboardState dashboardState) {
        this.state = dashboardState;
    }
}
