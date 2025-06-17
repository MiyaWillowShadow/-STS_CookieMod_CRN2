package CrnMod;


import CrnMod.cards.Basic_Slain;
import CrnMod.potions.PO_Beer;
import CrnMod.potions.PO_BottleMilk;
import CrnMod.potions.PO_Ration;
import CrnMod.powers.P_Call;
import CrnMod.powers.sup.SUP_DIYUSI;
import CrnMod.powers.sup.SUP_MDCN;
import CrnMod.powers.sup.SUP_SIYUDI;
import CrnMod.relics.crn.*;
import CrnMod.relics.share.*;
import basemod.*;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

import static CrnMod.CRN.PlayerColorEnum.*;
import static CrnMod.Helper.initialGlobalP;
import static CrnMod.Helper.modId;
import static basemod.BaseMod.addKeyword;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


@SpireInitializer
public class Thmod implements RelicGetSubscriber,EditCharactersSubscriber,  EditRelicsSubscriber, EditCardsSubscriber,
        EditStringsSubscriber,EditKeywordsSubscriber,  AddAudioSubscriber ,OnStartBattleSubscriber,PostInitializeSubscriber,PostDeathSubscriber {
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    public static final String keywordPrefix="crnmod";
    private static boolean TalkSound=true;

    public Thmod() {
        BaseMod.subscribe(this);
        //bg是卡牌背景色
        BaseMod.addColor(CRN_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER,
                "img/512/bg_attack_CRN_s.png",
                "img/512/bg_skill_CRN_s.png",
                "img/512/bg_power_CRN_s.png",
                "img/512/CRNOrb.png",
                "img/1024/bg_attack_CRN.png",
                "img/1024/bg_skill_CRN.png",
                "img/1024/bg_power_CRN.png",
                "img/1024/CRNOrb.png",
                "img/UI/energyOrb.png");
//        临时卡牌 等代码问题解决再恢复?
        BaseMod.addColor(CRN_DERIVATIONS, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER,
                "img/512/bg_attack_CRN_s.png",
                "img/512/bg_skill_CRN_s.png",
                "img/512/bg_power_CRN_s.png",
                "img/512/CRNOrb.png",
                "img/1024/bg_attack_CRN.png",
                "img/1024/bg_skill_CRN.png",
                "img/1024/bg_power_CRN.png",
                "img/1024/CRNOrb.png",
                "img/UI/energyOrb.png");

    }

    public static void initialize() {
        new Thmod();

        try {
            Properties defaults = new Properties();
            defaults.setProperty(Helper.avoidDup("TalkSound"), "true");
            SpireConfig config = new SpireConfig(Helper.modId, "Common", defaults);
            TalkSound = config.getBool(Helper.avoidDup("TalkSound"));
        } catch (Exception var2) {
            Exception e = var2;
            e.printStackTrace();
        }

    }

    public static void talkSound(String s,float volume) {
        if(MyModConfig.enableTalkSound){
            CardCrawlGame.sound.playV(s, volume);
        }
    }

    public static void talkSound(String s,float volume,int randomRang) {
        if(MyModConfig.enableTalkSound){
            String sound=s+cardRandomRng.random(randomRang-1);
            talkSound(sound,volume);
        }
    }

    public static void effectSound(String s, float volume) {
        CardCrawlGame.sound.playV(s, volume);
    }

    public static void effectSound(String s,float volume,int randomRang) {
        String sound=s+cardRandomRng.random(randomRang-1);
        effectSound(sound,volume);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        initialGlobalP();
        P_Call.sups.clear();
        SUP_DIYUSI.powerUping=false;
        SUP_SIYUDI.powerUping=false;
        SUP_MDCN.powerUping=false;
    }

    public void receiveEditCards() {
        AutoAdd autoAdd = new AutoAdd(Helper.modId);// 这里填写你在ModTheSpire.json中写的id
        autoAdd.packageFilter(Basic_Slain.class); // 寻找所有和此类同一个包及内部包的类
        autoAdd.setDefaultSeen(true); // 是否将卡牌标为可见
        autoAdd.cards(); // 开始批量添加卡牌
    }

    public void receiveEditCharacters() {
        BaseMod.addCharacter(new CRN(CardCrawlGame.playerName), //选择界面按钮 选择界面立绘 职业
                "img/charSelect/CRNButton.png",
                "img/charSelect/CRNPortrait.png",
                CRN_CLASS);
    }

    public void receiveEditRelics() {
        AutoAdd autoAdd = new AutoAdd("CRNmod2");// 这里填写你在ModTheSpire.json中写的id
        autoAdd.packageFilter(R_Basic.class); // 寻找所有和此类同一个包及内部包的类
        autoAdd.setDefaultSeen(true);
        autoAdd.any(AbstractRelic.class, (info, relic) -> {
            BaseMod.addRelicToCustomPool(relic,CRN_COLOR );
            if (info.seen) {
                UnlockTracker.markRelicAsSeen(relic.relicId);
            }
        });
        BaseMod.addRelic(new R_Biim(), RelicType.SHARED);
        BaseMod.addRelic(new R_Grape(), RelicType.SHARED);
        BaseMod.addRelic(new R_NicoNico(), RelicType.SHARED);
        BaseMod.addRelic(new R_Shop(), RelicType.SHARED);
        BaseMod.addRelic(new R_ZIP(), RelicType.SHARED);
    }

    public void receiveEditKeywords() {
        String lang= "ZHS"; // default language
        if (Settings.language == Settings.GameLanguage.ENG) {
            lang = "ENG";
        } else if (Settings.language == Settings.GameLanguage.JPN) {
            lang = "JPN";
        }
        Gson gson = new Gson();
        String json = Gdx.files.internal("localization/" + lang + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keywordPrefix, keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);// 这个id要全小写! 要全小写!! 要全小写!!! 要全小写!!!!
            }
        }
    }

    public void receiveEditStrings() {
        String lang= "ZHS"; // default language
        if (Settings.language == Settings.GameLanguage.ENG) {
            lang = "ENG";
        } else if (Settings.language == Settings.GameLanguage.JPN) {
            lang = "JPN";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "localization/" + lang + "/potions.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "localization/" + lang + "/events.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "localization/" + lang + "/UI.json");
    }

    public void receiveAddAudio() {
        BaseMod.addAudio("GUN0", "audio/hermit_gun0.ogg");
        BaseMod.addAudio("GUN1", "audio/hermit_gun1.ogg");
        BaseMod.addAudio("GUN2", "audio/hermit_gun2.ogg");
        BaseMod.addAudio("SPIN", "audio/hermit_spin.ogg");
//        BaseMod.addAudio("RELOAD", "audio/hermit_reload.ogg");
        BaseMod.addAudio("DIYUSI0", "audio/DIYUSI0.ogg");
        BaseMod.addAudio("DIYUSI1", "audio/DIYUSI1.ogg");
        BaseMod.addAudio("DIYUSIBell", "audio/DIYUSIBell.ogg");
        BaseMod.addAudio("SIYUDI", "audio/SIYUDI.ogg");
        BaseMod.addAudio("SIYUDI2", "audio/SIYUDI2.ogg");
        BaseMod.addAudio("SIK0", "audio/SIK0.ogg");
        BaseMod.addAudio("SIK1", "audio/SIK1.ogg");
        BaseMod.addAudio("SIK2", "audio/SIK2.ogg");
        BaseMod.addAudio("ICG0", "audio/ICG0.ogg");
        BaseMod.addAudio("ICG1", "audio/ICG1.ogg");
        BaseMod.addAudio("ICG2", "audio/ICG2.ogg");
        BaseMod.addAudio("NYN0", "audio/NYN0.ogg");
        BaseMod.addAudio("NYN1", "audio/NYN1.ogg");
        BaseMod.addAudio("NYN2", "audio/NYN2.ogg");
        BaseMod.addAudio("RMA", "audio/RMA.ogg");
        BaseMod.addAudio("SZ0", "audio/SZ0.ogg");
        BaseMod.addAudio("SZ1", "audio/SZ1.ogg");
        BaseMod.addAudio("SZ2", "audio/SZ2.ogg");
        BaseMod.addAudio("SZ3", "audio/SZ3.ogg");
        BaseMod.addAudio("HNS1", "audio/HNS1.ogg");
        BaseMod.addAudio("HNS2", "audio/HNS2.ogg");
        BaseMod.addAudio("HNS4", "audio/HNS4.ogg");
        BaseMod.addAudio("HNSSIN", "audio/HNSSIN.ogg");
        BaseMod.addAudio("KNN息してる", "audio/KNN息してる.ogg");
        BaseMod.addAudio("KNN生き返る", "audio/KNN生き返る.ogg");
        BaseMod.addAudio("KNN背水", "audio/KNN背水.ogg");
        BaseMod.addAudio("PCLY0", "audio/PCLY0.ogg");
        BaseMod.addAudio("PCLY1", "audio/PCLY1.ogg");
        BaseMod.addAudio("PCLY2", "audio/PCLY2.ogg");
        BaseMod.addAudio("893", "audio/893.ogg");
        BaseMod.addAudio("RUぷはー", "audio/RUぷはー.ogg");
        BaseMod.addAudio("RU九番茶", "audio/RU九番茶.ogg");
        BaseMod.addAudio("酒飲", "audio/酒飲.ogg");
        BaseMod.addAudio("YMN0", "audio/YMN0.ogg");
        BaseMod.addAudio("YMN1", "audio/YMN1.ogg");
        BaseMod.addAudio("YMN2", "audio/YMN2.ogg");
        BaseMod.addAudio("YMNINM", "audio/YMNINM.ogg");
        BaseMod.addAudio("CRN", "audio/CRN.ogg");
        BaseMod.addAudio("NDKいたたたた", "audio/NDKいたたたた.ogg");
        BaseMod.addAudio("NDK知らなーい", "audio/NDK知らなーい.ogg");
        BaseMod.addAudio("NEL1", "audio/NEL1.ogg");
        BaseMod.addAudio("NEL2", "audio/NEL2.ogg");
        BaseMod.addAudio("SNNM", "audio/SNNM.ogg");
        BaseMod.addAudio("RRM", "audio/RRM.ogg");
        BaseMod.addAudio("MGR", "audio/MGR.ogg");
        BaseMod.addAudio("宣伝", "audio/宣伝.ogg");
        BaseMod.addAudio("削除", "audio/削除.ogg");
        BaseMod.addAudio("YJじゃけん夜行きましょうね", "audio/YJじゃけん夜行きましょうね.ogg");
        BaseMod.addAudio("YJ戊辰戦争", "audio/YJ戊辰戦争.ogg");
        BaseMod.addAudio("YJ BEER", "audio/YJ BEER.ogg");
        BaseMod.addAudio("AKYS0", "audio/AKYS0.ogg");
        BaseMod.addAudio("AKYS1", "audio/AKYS1.ogg");
        BaseMod.addAudio("AKYS2", "audio/AKYS2.ogg");

        BaseMod.addAudio("NYN SHOP1", "audio/NYN SHOP1.ogg");
        BaseMod.addAudio("NYN SHOP2", "audio/NYN SHOP2.ogg");
        BaseMod.addAudio("TIS0", "audio/TIS0.ogg");
        BaseMod.addAudio("TIS1", "audio/TIS1.ogg");
        BaseMod.addAudio("TIS2", "audio/TIS2.ogg");
        BaseMod.addAudio("CriticalShot", "audio/CriticalShot.ogg");
        BaseMod.addAudio("CriticalReady", "audio/CriticalReady.ogg");
        BaseMod.addAudio("334", "audio/334.ogg");
        BaseMod.addAudio("BlackCar", "audio/BlackCar.ogg");
        BaseMod.addAudio("Door", "audio/Door.ogg");
        BaseMod.addAudio("BN", "audio/BN.ogg");
        BaseMod.addAudio("JOKER0", "audio/JOKER0.ogg");
        BaseMod.addAudio("JOKER1", "audio/JOKER1.ogg");
        BaseMod.addAudio("JOKER2", "audio/JOKER2.ogg");
        BaseMod.addAudio("MCT0", "audio/MCT0.ogg");
        BaseMod.addAudio("MCT1", "audio/MCT1.ogg");
        BaseMod.addAudio("MCT2", "audio/MCT2.ogg");
        BaseMod.addAudio("MCTDefend0", "audio/MCTDefend0.ogg");
        BaseMod.addAudio("MCTDefend1", "audio/MCTDefend1.ogg");
    }

    public void receiveRelicGet(AbstractRelic relic) {
        //可以在此处将遗物移出池子
        if (Objects.equals(AbstractDungeon.player.name, "CRN")) { //设置条件避免误伤其他角色 池子要正确才能成功移除
            AbstractDungeon.commonRelicPool.remove(MawBank.ID);//鲸鱼银行
            AbstractDungeon.uncommonRelicPool.remove(StrikeDummy.ID);//打击木偶
            AbstractDungeon.uncommonRelicPool.remove(DarkstonePeriapt.ID);//黑石护符
//            AbstractDungeon.rareRelicPool.remove(DeadBranch.ID);//黑星
//            AbstractDungeon.bossRelicPool.remove(TinyHouse.ID);//小屋子
        }

    }

    public void receivePostInitialize() {
        BaseMod.addPotion(PO_Beer.class, Color.GOLD.cpy(), Color.YELLOW.cpy(), (Color)null, PO_Beer.POTION_ID,CRN_CLASS );
        BaseMod.addPotion(PO_BottleMilk.class, Color.WHITE.cpy(), Color.WHITE.cpy(), (Color)null, PO_BottleMilk.POTION_ID,CRN_CLASS );
        BaseMod.addPotion(PO_Ration.class, Color.GRAY.cpy(), Color.GRAY.cpy(), (Color)null, PO_Ration.POTION_ID,CRN_CLASS );

        Texture badgeTexture = ImageMaster.loadImage("img/UI/badge.png");
        BaseMod.registerModBadge(badgeTexture, modId, "Naiya", "mod description", new MyModConfig());


    }

    @Override
    public void receivePostDeath() {
        effectSound("334",1.5F);
    }

//    private void CreateConfig() throws IOException {
//        SpireConfig spireConfig = new SpireConfig("CrnMod2", "Common");
//        ModPanel settingPanel = new ModPanel();
//        ModLabeledToggleButton talkSound = new ModLabeledToggleButton("TalkSound", 500.0F, 600.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, TalkSound, settingPanel, (modLabel) -> {
//        }, (modToggleButton) -> {
//            spireConfig.setBool(Helper.avoidDup("TalkSound"), TalkSound = modToggleButton.enabled);
//            CardCrawlGame.mainMenuScreen.optionPanel.effects.clear();
//
//            try {
//                spireConfig.save();
//            } catch (IOException var3) {
//                IOException e = var3;
//                e.printStackTrace();
//            }
//
//        });
//        settingPanel.addUIElement(talkSound);
////        Texture badgeTexture = ImageMaster.loadImage(ModHelper.makeImgPath("UI", "configButton"));
////        BaseMod.registerModBadge(badgeTexture, "BATwinsMod", "0v0", "config", settingPanel);
//    }

}
