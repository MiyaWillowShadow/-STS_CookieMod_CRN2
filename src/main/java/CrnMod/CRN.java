package CrnMod;

import CrnMod.cards.*;
import CrnMod.cards.attack.Shot_Critical;
import CrnMod.cards.attack.Vary_Merge;
import CrnMod.cards.skill.SK_EnergyBySup;
import CrnMod.father.NaiCard;
import CrnMod.powers.PCP.PCP_DeathMathch;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import CrnMod.powers.specific.SK_Block_DropCombAim_P;
import CrnMod.powers.specific.SK_Retain_P;
import CrnMod.relics.crn.R_Basic;
import CrnMod.relics.crn.R_Basic2;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static CrnMod.CRN.PlayerColorEnum.CRN_CLASS;
import static CrnMod.Helper.P;
import static CrnMod.Thmod.effectSound;
import static CrnMod.Thmod.talkSound;
import static CrnMod.father.CrnCard.*;
import static CrnMod.father.NaiCard.addFunction;


public class CRN extends CustomPlayer  {
    //卡牌轨迹颜色
    public static final Color SILVER = CardHelper.getColor(0, 255, 255);
    //能量图标的每个图层 LAYER_SPEED是同层转速
    private static final String[] ORB_TEXTURES = new String[]{
            "img/UI/EPanel/layer5.png",
            "img/UI/EPanel/layer4.png",
            "img/UI/EPanel/layer3.png",
            "img/UI/EPanel/layer2.png",
            "img/UI/EPanel/layer1.png",
            "img/UI/EPanel/layer0.png",
            "img/UI/EPanel/layer5d.png",
            "img/UI/EPanel/layer4d.png",
            "img/UI/EPanel/layer3d.png",
            "img/UI/EPanel/layer2d.png",
            "img/UI/EPanel/layer1d.png"};
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    private static final String characterName= "CRN";

    public CRN(String name) {
        super(name, CRN_CLASS,ORB_TEXTURES, "img/UI/energyBlueVFX.png", LAYER_SPEED, null, null);
        //对话气泡的左下角坐标点）
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;

        this.initializeClass(
                "img/char/CRN.png", // 人物图片
                "img/char/shoulder2.png", // 火堆立绘行动前
                "img/char/shoulder1.png", // 火堆立绘行动后
                "img/char/fallen.png", // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );
        //------------------------------动画加载------------------------------
        this.loadAnimation("img/char/CRN.atlas", "img/char/CRN.json", 2.9F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(1.0F);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Basic_Slain.CID);
        retVal.add(Basic_Slain.CID);
        retVal.add(Basic_Shot.CID);
        retVal.add(Basic_Shot.CID);
        retVal.add(Basic_Vary.CID);
        retVal.add(Basic_Vary.CID);
        retVal.add(Basic_Defend.CID);
        retVal.add(Basic_Defend.CID);
        retVal.add(Basic_Defend.CID);
        retVal.add(Basic_Defend.CID);
        retVal.add(Basic_CA.CID);
        retVal.add(Basic_Merge.CID);
        return retVal;
//        return new ArrayList<>(Arrays.asList(
//                ATK_Slain_Strike.ID,ATK_Slain_Strike.ID,
//                ATK_Shot_Strike.ID,ATK_Shot_Strike.ID,
//                SK_Defend.ID,SK_Defend.ID,SK_Defend.ID,SK_Defend.ID,
//                ATK_TAC_Basic.ID, SK_TAC_Basic.ID
//        ));
    }

    public ArrayList<String> getStartingRelics() {
        UnlockTracker.markRelicAsSeen(R_Basic.RID);
        UnlockTracker.markRelicAsSeen(R_Basic2.RID);
        return new ArrayList<>(Arrays.asList(R_Basic.RID, R_Basic2.RID));
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterName,
                "朽木市的雇佣兵， NL 接受任务前来调查高塔。",
                69, // HP
                69, // MHP
                0, // 球位
                114, // 钱
                5, // 抽牌数
                this, 
                getStartingRelics(), 
                getStartingDeck(), 
                false // 别动
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "CRN"; //游戏左上角的英文名)
    }
    public String getLocalizedCharacterName() {
        return characterName; //游戏左上角的名字(适配玩家的语言)
    }
    public AbstractCard.CardColor getCardColor() {
        return PlayerColorEnum.CRN_COLOR; //职业色
    }
    public AbstractCard getStartCardForEvent() {
        return new SK_EnergyBySup(); // 翻牌事件出现的你的职业牌（当然是来张厉害的）
    }
    public Color getCardTrailColor() {
        return SILVER;          // 卡牌轨迹颜色
    }
    public Color getCardRenderColor() {
        return SILVER;         // 卡牌选择界面选择该牌的颜色
    }
    public int getAscensionMaxHPLoss() {
        return 4;             // 高进阶带来的生命值损失
    }
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;    // 卡牌的能量字体，不用改
    }

    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("CRN", MathUtils.random(-0.2F, 0.2F)); //选择特效 音效
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false); //选择特效 轻微震动
    }
    public String getCustomModeCharacterButtonSoundKey() {
        return "GUN2";
    } // 自定义模式选择你的人物时播放的音效


    public String getSpireHeartText() {
        return "你抬起手中的枪";// 第三章打心脏木桩台词（例如战士是“你握紧了你的长刀……”之类的）
    }
    // 第三章打心脏木桩造成伤害时的特效
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }
    public Color getSlashAttackColor() {
        return SILVER;// 打心脏的颜色，不是很明显
    }
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>(); // 碎心图片 sfx是音效
        panels.add(new CutscenePanel("img/char/Yukari1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("img/char/Yukari2.png"));
        panels.add(new CutscenePanel("img/char/Yukari3.png"));
        return panels;
    }
    public AbstractPlayer newInstance() {
        return new CRN(this.name);// 创建人物实例，别动
    }

    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];//吸血鬼人称,0为他,1为她,也可以自己填
    }

    //受伤动作
    public void damage(DamageInfo info) {
        super.damage(info);
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);
            e.setTimeScale(1.0F);
        }
    }

    public void useCardFixForYano(AbstractCard c, AbstractMonster m, int energyOnUse) {
        c.applyPowers(); //well play
        if (c.type == AbstractCard.CardType.ATTACK) {
            this.useFastAttackAnimation();
            c.calculateCardDamage(m);
        }
        if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse) {
            c.energyOnUse = EnergyPanel.totalCount;
        }
        if (c.cost == -1 && c.isInAutoplay) {
            c.freeToPlayOnce = true;
        }
        c.use(this, m);
        AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, m));
        if (!c.dontTriggerOnUseCard) {
            this.hand.triggerOnOtherCardPlayed(c);
        }

        this.hand.removeCard(c);
        this.cardInUse = c;
        c.target_x = (float)(Settings.WIDTH / 2);
        c.target_y = (float)(Settings.HEIGHT / 2);
        if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (!this.hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL)) {
            this.energy.use(c.costForTurn);
        }

        if (!this.hand.canUseAnyCard() && !this.endTurnQueued) {
            AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
        }
    }

    public void useCard(AbstractCard c, AbstractMonster m, int energyOnUse) {
        useCardFixForYano(c,m,energyOnUse);
        boolean loseComb = false;
        boolean loseAim = false;
        boolean retainCombAim=hasPower(SK_Retain_P.PID);
        int comb=0;
        int aim=0;
        for(AbstractPower pw:P.powers){
            if(Objects.equals(pw.ID, P_Comb.PID)){
                comb=pw.amount;
            }else if(Objects.equals(pw.ID, P_Aim.PID)){
                aim=pw.amount;
            }
        }
        boolean pure=!(c.hasTag(CustomTags.BOTH) || (isVary(c) && comb==aim && comb>0));

        if (isSlain(c)) {
            NaiCard.gainPower(new P_Comb(1));
            if (aim>0 && pure) {
                loseAim = true;
            }
        }

        if (isShot(c) && !retainCombAim) {
            boolean critical=false;
            for(AbstractPower pw: P.powers){
                if(pw instanceof P_Aim){
                    P_Aim pwAim= (P_Aim) pw;
                    critical=pwAim.amount>=4 || (pwAim.amount == 3 && Objects.equals(c.cardID, Shot_Critical.CID));
                    break;
                }
            }
            if(critical){
                effectSound("CriticalShot",1.10F);
            }else{
                effectSound("GUN",1.25F,3);
            }
            NaiCard.gainPower(new P_Aim(1));
            if (comb>0 && pure) {
                loseComb = true;
            }
        }

        if ((loseComb || loseAim) & !retainCombAim & !Objects.equals(this.id, Vary_Merge.CID)) {
            int combLose=0,aimLose=0;
            if (loseComb) {
//                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this, this, PW_Comb.PID));
                combLose= Math.min(comb, 2);
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this, this, P_Comb.PID,combLose));
                PCP_DeathMathch.run(true);
            }
            if (loseAim) {
                aimLose= Math.min(aim, 2);
//                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this, this, PW_Aim.PID));
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this, this, P_Aim.PID,aimLose));
                PCP_DeathMathch.run(false);
            }
            SK_Block_DropCombAim_P.run(combLose+aimLose);
        }
//        if(Objects.equals(c.cardID, Block_Merge.CID)||Objects.equals(c.cardID, Vary_Merge.CID)){
//            Block_Merge.merge(c.magicNumber);
//        }
    }

    //------------------------------进阶代码区------------------------------

    public static class PlayerColorEnum {
        @SpireEnum
        public static PlayerClass CRN_CLASS; //patch新职业 @SpireEnum不能删否则patch失败

        @SpireEnum
        public static AbstractCard.CardColor CRN_COLOR; //职业卡牌tag 可以搞点下横线啥的防止和别人撞车

        @SpireEnum
        public static AbstractCard.CardColor CRN_DERIVATIONS;
    }

    public static class PlayerLibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType CRN_COLOR; //职业卡牌tag 和上面那个值必须一样

        @SpireEnum
        public static CardLibrary.LibraryType CRN_DERIVATIONS;
    }

    public static class CustomTags {
        //不能一个@管多行 否则除了第一行以外的tag都会被认为是同一个tag
        @SpireEnum public static AbstractCard.CardTags SHOT;
        @SpireEnum public static AbstractCard.CardTags SLAIN;
        @SpireEnum public static AbstractCard.CardTags VARY;
        @SpireEnum public static AbstractCard.CardTags RESET;
        @SpireEnum public static AbstractCard.CardTags BOTH;
        @SpireEnum public static AbstractCard.CardTags CRNCARD;
    }
}
