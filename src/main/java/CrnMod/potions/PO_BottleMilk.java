package CrnMod.potions;

import CrnMod.Helper;
import CrnMod.powers.sup.SUP_Beer;
import CrnMod.powers.sup.SUP_Milk;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForRelic;

public class PO_BottleMilk extends AbstractPotion {
    public static final String POTION_ID = Helper.avoidDup(PO_BottleMilk.class.getSimpleName());
    public static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(potionStrings.NAME, (potionStrings.DESCRIPTIONS[0])));

    public PO_BottleMilk() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.RARE, PotionSize.BOTTLE, PotionColor.WHITE);
        this.labOutlineColor = Settings.PURPLE_RELIC_COLOR;
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = String.format(STS.get(1), potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
//        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.ARTIFACT.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.ARTIFACT.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(P, AbstractDungeon.player, new SUP_Milk(1), 1));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }


    @Override
    public AbstractPotion makeCopy() {
        return new PO_BottleMilk();
    }
}
