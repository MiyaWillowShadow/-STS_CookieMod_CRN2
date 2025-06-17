package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

public class SUP_JGN extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_JGN.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int damageUp=100;

    public SUP_JGN(int amount) {
        super( amount, PID, STS,"img/powers/JGN.png");
        supLv=4;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), damageUp*amount);
    }

}