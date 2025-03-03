package kiosk;

import static io.Input.input;

import io.OutPut;

public class KioskInput {

    public String inputOption() {
        OutPut.displayOption();
        return input();
    }
}