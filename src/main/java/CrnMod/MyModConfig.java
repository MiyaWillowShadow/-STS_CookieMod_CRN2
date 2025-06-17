package CrnMod;

import basemod.EasyConfigPanel;

public class MyModConfig  extends EasyConfigPanel {
    public static boolean enableTalkSound = true;
    public MyModConfig() {
        super(Helper.modId, Helper.avoidDup("MyModConfig"));
    }
}
