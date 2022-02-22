package sabri.elmahdi.formula1;

import android.app.Application;

import sabri.elmahdi.formula1.service.ApiResponse;

public class DriverDetailsApplication extends Application {
    private ApiResponse driver;

    public ApiResponse getDriver() {
        return driver;
    }

    public void setDriver(ApiResponse driver) {
        this.driver = driver;
    }

    public void onCreate() {
        super.onCreate();
        this.driver=null;
    }
}
