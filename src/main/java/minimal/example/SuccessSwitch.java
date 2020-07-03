package minimal.example;

import org.springframework.stereotype.Component;

@Component
public class SuccessSwitch {

    private boolean success = false;

    public void letSucceed() {
        this.success = true;
    }

    public void letFail() {
        this.success = false;
    }

    public boolean succeeded() {
        return success;
    }
}
