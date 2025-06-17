package CrnMod;

import CrnMod.potions.PO_Beer;
import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.KeywordStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static CrnMod.Thmod.keywordPrefix;

public class NJson {
//    public static ArrayList<String> keyWord1 =new ArrayList<>(Arrays.asList("NL","格挡","消耗","虚无","固有","升级","变化","不能被打出",
//            "力量","敏捷","无实体","人工制品","荆棘","再生","活力","斩杀","中毒","易伤","虚弱","灼伤","诅咒","晕眩","状态牌","伤口"));
//    public static ArrayList<String> keyWord2 =new ArrayList<>(Arrays.asList("斩击","连击","射击","瞄准","变化","复合","呼叫","支援","求援",
//            "突破","便乘","弱点","事件","药水","残影","暴击","游击","diyusi","siyudi","mdcn","秘鲁","击晕"));
    public static ArrayList<String> keyWord1 =new ArrayList<>(Collections.singletonList("NL"));
    public static ArrayList<String> keyWord2 =new ArrayList<String>();
    public static boolean initKeyWordFinished =false;
    public NJson(){}

    public static void tryInitKeyWord(){
        if(!initKeyWordFinished){
            initKeyWordFinished =true;
            String lang= "ZHS"; // default language
            if (Settings.language == Settings.GameLanguage.ENG) {
                lang = "ENG";
            } else if (Settings.language == Settings.GameLanguage.JPN) {
                lang = "JPN";
            }
            UIStrings uiString = CardCrawlGame.languagePack.getUIString("NaiCard:OriginalKeyWords");
            keyWord1.addAll(Arrays.asList(uiString.TEXT));
            //
            Gson gson2 = new Gson();
            String json2 = Gdx.files.internal("localization/" + lang + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
            Keyword[] keywords = gson2.fromJson(json2, Keyword[].class);
            if (keywords != null) {
                for (Keyword keyword : keywords) {
                    keyWord2.add(keyword.NAMES[0]);
                }
            }
        }
    }

    public static String descriptionMakeForCard(String string){
        tryInitKeyWord();
        if(string!=null && !string.isEmpty()){
            String s=string;
            s=s.replaceAll("DD"," !D! ");
            s=s.replaceAll("BB"," !B! ");
            s=s.replaceAll("MM"," !M! ");
            s=s.replaceAll("EE"," [E] ");
            for(String st: keyWord1){
                s=s.replaceAll(st," "+st+" ");
            }
            for(String st: keyWord2){
                s=s.replaceAll(st," "+keywordPrefix+":"+st+" ");
            }
            return s;
        }else{
            return "";
        }
    }
    public static String descriptionMakeForRelic(String string){
        tryInitKeyWord();
        String s=string;
        for(String st: keyWord1){
            if(Objects.equals(st, "st")){
                s=s.replaceAll(st," "+st+" ");
            }else{
                s=s.replaceAll(st," #y"+st+" ");
            }
        }
        for(String st: keyWord2){
            s=s.replaceAll(st," #y"+keywordPrefix+":"+st+" ");
        }
        return s;
    }
}


//        Strike_Shot.STS = new ArrayList<>(Arrays.asList("射击", desMake("造成DD点伤害。")));
//        Strike_Slain.STS = new ArrayList<>(Arrays.asList("斩击", desMake("造成DD点伤害。")));
//        Strike_Vary.STS = new ArrayList<>(Arrays.asList("打击", desMake("造成DD点伤害。")));
//        Defend.STS = new ArrayList<>(Arrays.asList("防御", desMake("获得BB点格挡。")));
//
//        None_CombAim.STS = new ArrayList<>(Arrays.asList("试探", desMake("造成DD点伤害。NL若目标意图攻击，获得MM层瞄准，否则获得MM层连击。")));
//        Block_Merge.STS = new ArrayList<>(Arrays.asList("合作", desMake("获得BB点格挡。NL合并连击和瞄准"), desMake("获得BB点格挡。NL可合并连击和瞄准")));
//            SKO_Merge_Comb.STS = new ArrayList<>(Arrays.asList("连击", desMake("合并为连击。")));
//            SKO_Merge_Aim.STS = new ArrayList<>(Arrays.asList("瞄准", desMake("合并为瞄准。")));
//            SKO_Merge_Nothing.STS = new ArrayList<>(Arrays.asList("无", desMake("不合并。")));
//        SK_BasicCall.STS = new ArrayList<>(Arrays.asList("联络", desMake("消耗最多MM层无线电，获得等量层呼叫。有剩余则便乘。"),
//                desMake("消耗最多MM层无线电，获得等量+1层呼叫。有剩余则便乘。")));
//
//        Shot_ToBlock.STS = new ArrayList<>(Arrays.asList("击退", desMake("造成DD点伤害。NL若目标意图攻击，获得突破点格挡。")));
//        Shot_Aoe.STS = new ArrayList<>(Arrays.asList("轰炸", desMake("造成DD的群体伤害。NL求援2:增加呼叫数的伤害。")));
//        Shot_ByBlock.STS = new ArrayList<>(Arrays.asList("贯穿", desMake("造成DD点伤害。NL目标的格挡会增加此伤害。")));
//        Shot_Along.STS = new ArrayList<>(Arrays.asList("独狼", desMake("造成DD点伤害MM次。NL若伤害不小于呼叫数，多攻击1次。")));
//        Shot_Spot.STS = new ArrayList<>(Arrays.asList("Moku", desMake("造成DD点伤害。NL给予MM层弱点。")));
//        Shot_Copy.STS = new ArrayList<>(Arrays.asList("扫射", desMake("造成DD点伤害。NL未暴击时消耗1张牌，若其耗能为0，便乘"),
//                                                             desMake("造成DD点伤害。NL未暴击时可消耗自身和1张牌，若其耗能为0，便乘")));
//        Shot_Critical.STS = new ArrayList<>(Arrays.asList("狙击", desMake("造成DD点伤害。NL3层瞄准即可暴击。")));
//        Shot_Cost.STS = new ArrayList<>(Arrays.asList("Homo Gun", desMake("造成DD点伤害。NL暴击时耗能为0。")));
//        Shot_Poison.STS = new ArrayList<>(Arrays.asList("毒弹", desMake("造成DD点伤害。NL给予等额的中毒。")));
//
//        Slain_BySup.STS = new ArrayList<>(Arrays.asList("乱斩", desMake("随机造成DD点伤害。NL每有1种支援重复1次。")));
//        Slain_Aoe.STS = new ArrayList<>(Arrays.asList("横扫", desMake("造成DD点群体伤害。NL每层连击增加MM点伤害。")));
//        Slain_ByDebuff.STS = new ArrayList<>(Arrays.asList("银梦之一太刀", desMake("造成DD点伤害MM次。NL目标每有1个负面效果多攻击1次。")));
//        Slain_Copy.STS = new ArrayList<>(Arrays.asList("本当日本刀", desMake("造成DD点伤害。NL若有连击，下回合便乘并升级。NL能被多次升级。")));
//        Slains.STS = new ArrayList<>(Arrays.asList("连斩", desMake("造成DD点伤害MM次。NL获得1层连击"), desMake("造成DD点伤害MM次。NL获得2层连击")));
//        Slain_Call.STS = new ArrayList<>(Arrays.asList("宣传", desMake("造成DD点伤害。NL获得MM层呼叫。")));
//        Slain_GiveDebuff.STS = new ArrayList<>(Arrays.asList("邪剑夜", desMake("造成DD点伤害。NL每层连击给予1层中毒，虚弱，易伤，弱点。")));
//        Slain_Energy.STS = new ArrayList<>(Arrays.asList("妖力", desMake("造成DD点伤害。NL若目标持有负面效果，获得EE EE。")));
//        Slain_OnlyBlock.STS = new ArrayList<>(Arrays.asList("叛逆", desMake("获得BB点格挡。NL获得MM层残影。NL造成DD点伤害。NL上述效果受2倍连击影响。")));
//
//        Vary_Debuff.STS = new ArrayList<>(Arrays.asList("附魔", desMake("造成DD点伤害。NL若有连击，给予MM层虚弱。若有瞄准，给予MM层易伤。")));
//        Vary_Draw.STS = new ArrayList<>(Arrays.asList("飞刀", desMake("造成DD点伤害。NL每有1连击或瞄准抽1张牌，最多抽MM张。")));
//        Vary_Exhaust.STS = new ArrayList<>(Arrays.asList("湮灭", desMake("造成DD点伤害。NL消耗最多MM张牌，获得其耗能数的连击和瞄准。")));
//        Vary_Merge.STS = new ArrayList<>(Arrays.asList("合击", desMake("造成DD点伤害。NL可合并连击和瞄准")));
//        Vary_Parry.STS = new ArrayList<>(Arrays.asList("反击", desMake("造成DD点伤害。NL本回合每次受到攻击，给予对方MM层弱点。")));
//        Vary_ByBuff.STS = new ArrayList<>(Arrays.asList("圣剑月", desMake("造成DD点伤害。NL目标每层负面效果增加此伤害M点。")));
//        Vary_Free.STS = new ArrayList<>(Arrays.asList("Hide", desMake("造成DD点伤害。NL求援1：获得EE并且抽1张牌。")));
//        Vary_Cost.STS = new ArrayList<>(Arrays.asList("伏兵", desMake("造成DD点伤害。NL获得支援时耗能减1。")));
//        Vary_X.STS = new ArrayList<>(Arrays.asList("火力支援", desMake("造成DD点伤害X次 NL求援X：伤害增加X。"), desMake("造成DD点伤害X+1次 NL求援X：伤害增加X。")));
//
//        Vary_Repeat.STS = new ArrayList<>(Arrays.asList("死相", desMake("造成DD点伤害。NL击杀时对血量最少的敌人重复1次。")));
//        Both_SZ.STS = new ArrayList<>(Arrays.asList("玩弄致死", desMake("造成DD点伤害。NL若伤害不足MM，便乘。")));
//        Both_HNS.STS = new ArrayList<>(Arrays.asList("拷问", desMake("造成DD点群体伤害MM次。NL斩杀获得1层无线电，精英3层，首领6层。")));
//        Both_Stone.STS = new ArrayList<>(Arrays.asList("石头", desMake("造成DD点伤害。NL有至少4层连击或瞄准时击晕目标并消耗此牌。")));
//
//        SK_Block_ByCombAim.STS = new ArrayList<>(Arrays.asList("闪身", desMake("获得BB点格挡。NL每有1层连击或瞄准多1格挡。")));
//        SK_Block_BySup.STS = new ArrayList<>(Arrays.asList("战壕", desMake("获得BB点格挡。NL每有1种支援多MM格挡。")));
//        SK_Block_Call.STS = new ArrayList<>(Arrays.asList("挂机", desMake("获得BB点格挡。NL获得MM层呼叫。")));
//        SK_Block_Cost.STS = new ArrayList<>(Arrays.asList("孤立无援", desMake("获得BB点格挡。若呼叫少于手牌数，耗能为0但丢失连击和瞄准。")));
//        SK_Block_DropCombAim.STS = new ArrayList<>(Arrays.asList("游走", desMake("获得1张 *遗忘 。NL本回合每丢失1层连击或瞄准获得MM格挡。")));
//        ST_SK_Block_DropCombAim.STS = new ArrayList<>(Arrays.asList("遗忘", desMake("丢失连击和瞄准。")));
//        SK_Block_ToAoe.STS = new ArrayList<>(Arrays.asList("无量空处", desMake("本回合获得格挡时造成等额的群体斩击伤害。NL获得BB点格挡MM次。")));
//        SK_Block_Retain.STS = new ArrayList<>(Arrays.asList("援护", desMake(" NL求援MM：获得BB点格挡。")));
//
//        SK_BodySensor.STS = new ArrayList<>(Arrays.asList("戊辰战争", desMake("所有角色获得MM层金属化。"), desMake("所有角色获得MM层金属化。抽1张牌。")));
//        SK_Call.STS = new ArrayList<>(Arrays.asList("补给", desMake("获得MM层呼叫。")));
//        SK_HazardSign.STS = new ArrayList<>(Arrays.asList("危险标识", desMake("发现1种 *危险标识 。")));
//            SKO_Hazard_AOE.STS = new ArrayList<>(Arrays.asList("落石注意", desMake("造成MM点群体伤害。")));
//            SKO_Hazard_Debuff.STS = new ArrayList<>(Arrays.asList("感染注意", desMake("将所有敌人的负面层数翻倍。")));
//            SKO_Hazard_Exhaust.STS = new ArrayList<>(Arrays.asList("火灾注意", desMake("消耗所有手牌。")));
//            SKO_Hazard_SIYUDI.STS = new ArrayList<>(Arrays.asList("激光注意", desMake("将非4级的支援转化为siyudi支援。")));
//        SK_Daughter.STS = new ArrayList<>(Arrays.asList("立教三人娘", desMake("获得MM层呼叫。NL可升级3次。"),desMake("获得MM层呼叫。NL可升级2次。"),
//                    desMake("获得MM层呼叫。NL抽1张牌。NL可升级1次。"),desMake("获得MM层呼叫。NL抽1张牌。NL获得EE。")));
//        SK_PreAim.STS = new ArrayList<>(Arrays.asList("预瞄", desMake("本回合结束时，NL获得MM层瞄准。")));
//        SK_Draw_Cost.STS = new ArrayList<>(Arrays.asList("背水之阵", desMake("抽MM张牌。若呼叫不高于能量，耗能为0但丢失连击和瞄准。")));
//        SK_Energy.STS = new ArrayList<>(Arrays.asList("协议", desMake("求援MM：获得EE EE "),desMake("求援MM：获得EE EE EE ")));
//        SK_EnergyBySup.STS = new ArrayList<>(Arrays.asList("伙伴", desMake("每有1种支援，抽1张牌并获得EE，最多计算MM种。")));
//        SK_Event.STS = new ArrayList<>(Arrays.asList("萤火虫哒", desMake("事件：若有无线电，获得MM层。否则获得MM点最大生命。")));
//        SK_ExTurn.STS = new ArrayList<>(Arrays.asList("腾空", desMake("获得1层残影并进入额外回合，但不能出牌。")));
//        SK_FreeDoubleAttack.STS = new ArrayList<>(Arrays.asList("姐妹", desMake("下1张攻击牌耗能为0并打出2次。")));
//        SK_NewCards.STS = new ArrayList<>(Arrays.asList("外援", desMake("获得打出前0费的 *顺劈斩、 *灾祸 或 *惩恶 。"), desMake("获得打出前0费的 *顺劈斩+ ，*灾祸+ 或 *惩恶+ 。")));
//        SK_Potion.STS = new ArrayList<>(Arrays.asList("九番茶", desMake("消耗1张牌获得药水。NL 无法获得则直接使用。")));
//        SK_ExhaustToDraw.STS = new ArrayList<>(Arrays.asList("削除", desMake("消耗抽牌堆的1张牌，获得EE，并根据其 crnmod:种类 抽牌。")));
//        SK_Spot.STS = new ArrayList<>(Arrays.asList("侦察", desMake("给予MM层弱点和易伤。")));
//        SK_Plan.STS = new ArrayList<>(Arrays.asList("定番", desMake("将抽牌堆最多MM张牌置顶。"),desMake("将抽牌堆最多MM张牌置顶。NL抽1张牌。")));
//        SK_Search.STS = new ArrayList<>(Arrays.asList("探索", desMake("选择抽2张斩击牌NL或射击牌。")));
//            SKO_Friend_Slain.STS = new ArrayList<>(Arrays.asList("斩击", desMake("抽2张斩击牌。")));
//            SKO_Friend_Shot.STS = new ArrayList<>(Arrays.asList("射击", desMake("抽2张射击牌。")));
//        SK_Retain.STS = new ArrayList<>(Arrays.asList("耐心", desMake("获得BB点格挡。NL 保留 手牌，本回合结束前连击和瞄准无效但不会丢失。")));
//        SK_Block_Weak.STS = new ArrayList<>(Arrays.asList("下段", desMake("获得BB点格挡，给予MM层虚弱。NL若已有虚弱则改为获得EE EE。")));
//        SK_CallTemp.STS = new ArrayList<>(Arrays.asList("讨债", desMake("获得MM层呼叫，下回合结束时失去3层。")));
//        SK_Upgrade.STS = new ArrayList<>(Arrays.asList("整备", desMake("升级MM张手牌。"),desMake("抽1张牌。NL升级MM张手牌。")));
//        SK_StrDown.STS = new ArrayList<>(Arrays.asList("宿醉", desMake("使目标失去Y点力量，Y为连击和瞄准的较大值。")));
//
//        PC_DIYUSI.STS = new ArrayList<>(Arrays.asList("DIYUSI支援", desMake("获得1层diyusi支援。")));
//        PC_Sin.STS = new ArrayList<>(Arrays.asList("罪姬 正义之柱", desMake("每当获得支援或有1张牌被消耗时，获得1层连击和瞄准")));
//        PC_NEL.STS = new ArrayList<>(Arrays.asList("凝视", desMake("4级支援NL获得支援时，抽1张牌。")));
//        PC_Joker.STS = new ArrayList<>(Arrays.asList("神秘援助",desMake("4级支援NL回合结束时每剩余1点能量，下回合多抽MM张牌。"),desMake("4级支援NL获得EE。NL回合结束时每剩余1点能量，下回合多抽MM张牌。")));
//        PC_JGN.STS = new ArrayList<>(Arrays.asList("次元大介", desMake("4级支援NL暴击伤害增加100%。"), desMake("4级支援NL暴击伤害增加100%。NL获得MM层瞄准。")));
//        PC_KNN.STS = new ArrayList<>(Arrays.asList("不死鸟", desMake("免疫下次 *致死伤害 ，触发此效果或战斗胜利时回复MM%生命。")));
//        PC_MDCN.STS = new ArrayList<>(Arrays.asList("MDCN支援", desMake("获得1层mdcn支援。"),desMake("触发mdcn支援。NL获得1层mdcn支援。")));
//        PC_OFFside.STS = new ArrayList<>(Arrays.asList("OFFside形态", desMake("每回合开始时，NL获得MM层呼叫。")));
//        PC_Rest.STS = new ArrayList<>(Arrays.asList("休息", desMake("获得1层秘鲁支援。")));
//        PC_Train.STS = new ArrayList<>(Arrays.asList("训练", desMake("强化指定的1级支援，并获得该支援MM层。")));
//        PC_SIYUDI.STS = new ArrayList<>(Arrays.asList("SIYUDI支援", desMake("获得1层siyudi支援。"),desMake("获得1层siyudi支援。NL触发所有siyudi支援。")));
//        PC_DeathMatch.STS = new ArrayList<>(Arrays.asList("死斗", desMake("丢失连击时获得1层瞄准。NL丢失瞄准时获得1层连击。")));
//
//        ST_LoseCall.STS = new ArrayList<>(Arrays.asList("串频", desMake(" 不能被打出 NL回合结束时失去2层呼叫。"),desMake(" 获得2层呼叫。")));
//
//        PCO_DIYUSI.STS = new ArrayList<>(Arrays.asList("强化DIYUSI", desMake("diyusi支援将受2倍连击影响。")));
//        PCO_SIYUDI.STS = new ArrayList<>(Arrays.asList("强化SIYUDI", desMake("siyudi支援将模仿本回合的暴击。")));
//        PCO_MDCN.STS = new ArrayList<>(Arrays.asList("强化MDCN", desMake("攻击伤害、斩击伤害和射击伤害将触发敌人的中毒。")));
//
//        O_DIYUSI.STS = new ArrayList<>(Arrays.asList("DIYUSI支援", desMake("回合结束时，获得4点格挡。NL受敏捷、脆弱和连击影响。")));
//        O_SIYUDI.STS = new ArrayList<>(Arrays.asList("SIYUDI支援", desMake("回合结束时，对生命最少敌人造成6点射击伤害。受力量、虚弱和易伤影响，暴击时不会丢失瞄准。")));
//        O_MDCN.STS = new ArrayList<>(Arrays.asList("MDCN支援", desMake("游击NL给予所有敌人5层中毒。")));
//        O_SIK.STS = new ArrayList<>(Arrays.asList("SIK支援", desMake("攻击伤害和射击伤害增加2点。"))); //
//        O_ICG.STS = new ArrayList<>(Arrays.asList("ICG支援", desMake("从卡牌或diyusi支援获得的格挡增加2点。")));
//        O_NYN.STS = new ArrayList<>(Arrays.asList("NYN支援", desMake("回合开始时，抽1张牌。")));
//        O_RMA.STS = new ArrayList<>(Arrays.asList("RMA支援", desMake("回合开始时，获得EE。")));
//        O_Flour.STS = new ArrayList<>(Arrays.asList("小麦粉支援", desMake("回合结束时，获得并 保留 弃牌堆的1张牌。")));
//        O_Milk.STS = new ArrayList<>(Arrays.asList("牛乳支援", desMake("游击NL除负面效果。")));
//        O_HNS.STS = new ArrayList<>(Arrays.asList("HNS支援", desMake("对敌人造成的伤害增加50%。")));
//        O_KNN.STS = new ArrayList<>(Arrays.asList("KNN支援", desMake("生命的失去量减半。回合结束时，获得3点临时生命。")));
//        O_SZ.STS = new ArrayList<>(Arrays.asList("SZ支援", desMake("每打出6张攻击牌，获得EE。")));

//        SUP_Beer.STS = new ArrayList<>(Arrays.asList("秘鲁支援", "登场时，回复 #b%d %% 最大生命"));
//        SUP_NEL.STS = new ArrayList<>(Arrays.asList("凝视", "4级支援。 NL 获得支援时，抽 #b%d 张牌"));
//        SUP_JGN.STS = new ArrayList<>(Arrays.asList("次元大介", "4级支援。 NL 每1重暴击增加 #b%d %% 伤害"));
//        SUP_Joker.STS = new ArrayList<>(Arrays.asList("Joker", "4级支援。 NL 回合结束时，每剩余1点能量，下回合多抽 #b%d 张牌。"));
//        PCP_KNN.STS = new ArrayList<>(Arrays.asList("不死鸟", "免疫下 #b%d 次致死伤害，触发此效果或战斗胜利时回复 #b%d %% 生命"));
//        PCP_OFFside.STS = new ArrayList<>(Arrays.asList("OFFside形态", "每回合开始获得MM层呼叫。"));
//        PCP_Sin.STS = new ArrayList<>(Arrays.asList("罪姬 正义之柱", "每当获得支援或有1张牌被消耗时，获得 #b%d 层连击和瞄准。"));
//        PCP_DeathMathch.STS = new ArrayList<>(Arrays.asList("死斗", "丢失连击时获得 #b%d 层瞄准。 NL 丢失瞄准时获得 #b%d 层连击。"));
//        PCP_MDCN_PowerUp.STS = new ArrayList<>(Arrays.asList("强化MDCN", "攻击伤害、斩击伤害和射击伤害将触发敌人的中毒。"));
//
//        SUP_DIYUSI.STS = new ArrayList<>(Arrays.asList("DIYUSI支援", "回合结束时，获得 #b%d 点格挡 #b%d 次。"));
//        SUP_SIYUDI.STS = new ArrayList<>(Arrays.asList("SIYUDI支援", "回合结束时，对生命最少敌人造成 #b%d 点伤害 #b%d 次。该伤害暴击时不会丢失瞄准。"));
//        SUP_MDCN.STS = new ArrayList<>(Arrays.asList("MDCN支援", "#y游击 NL 给予所有敌人 #b%d 层 #y中毒 。"));
//        SUP_SIK.STS = new ArrayList<>(Arrays.asList("SIK支援", "攻击伤害和射击伤害增加 #b%d 点。"));
//        SUP_ICG.STS = new ArrayList<>(Arrays.asList("ICG支援", "从卡牌或diyusi支援获得的格挡增加 #b%d 点。"));
//        SUP_NYN.STS = new ArrayList<>(Arrays.asList("NYN支援", "回合开始时，抽 #b%d 张牌。"));
//        SUP_RMA.STS = new ArrayList<>(Arrays.asList("RMA支援", "回合开始时，获得 #b%d 点能量。"));
//        SUP_Flour.STS = new ArrayList<>(Arrays.asList("小麦粉支援", "回合结束时，获得并保留弃牌堆的 #b%d 张牌。"));
//        SUP_Milk.STS = new ArrayList<>(Arrays.asList("牛乳支援", "#y游击 NL 移除负面效果。"));
//        SUP_HNS.STS = new ArrayList<>(Arrays.asList("HNS支援", "对敌人造成的伤害增加 #b%d %% 。"));
//        SUP_KNN.STS = new ArrayList<>(Arrays.asList("KNN支援", "生命的失去量减半。回合结束时，获得 #b%d 点临时生命。"));
//        SUP_SZ.STS = new ArrayList<>(Arrays.asList("SZ支援", "每打出 #b%d 张攻击牌，获得EE。已打出 #b%d 张。"));
//        SUP_PCLY.STS = new ArrayList<>(Arrays.asList("PCLY支援", "#y游击 NL 获得2点能量，抽1张牌。"));
//
//        P_Aim.STS = new ArrayList<>(Arrays.asList("瞄准", "每4层瞄准使下1张射击攻击牌增加1倍伤害"));
//        P_Spot.STS = new ArrayList<>(Arrays.asList("弱点", "受到的攻击伤害、斩击伤害或射击伤害增加 #b%d 。回合结束时减少1层"));
//        P_Comb.STS = new ArrayList<>(Arrays.asList("连击", "斩击伤害增加 #b%d %%"));
//        P_Call.STS = new ArrayList<>(Arrays.asList("呼叫", "每3层 #y呼叫 发现1个 #y支援 。"));
//
//        SK_PreAim_PW.STS = new ArrayList<>(Arrays.asList("预瞄", "本回合结束时获得 #b%d 层瞄准。"));
//        Slain_Copy_P.STS = new ArrayList<>(Arrays.asList("本当日本刀", "下回合获得1张 #y本当日本刀 + #b%d 。"));
//        SK_Block_DropCombAim_P.STS = new ArrayList<>(Arrays.asList("游走", "丢失 #y连击 或 #y瞄准 时，每层获得 #b%d 点格挡。"));
//        SK_Block_ToAoe_P.STS = new ArrayList<>(Arrays.asList("无量空处", "获得格挡时造成等额的群体 #y斩击 伤害"));
//        SK_ExTurn_P.STS = new ArrayList<>(Arrays.asList("禁区", "不能出牌","在禁区不能出牌"));
//        Vary_Parry_P.STS = new ArrayList<>(Arrays.asList("招架", "受到攻击时给予对方 #b%d 层 #y弱点。"));
//        SK_Retain_P.STS = new ArrayList<>(Arrays.asList("耐心", "#b%d 回合内，保留手牌， #y连击 和 #y瞄准 无效但不会丢失。"));
//        SK_TempCall_P.STS = new ArrayList<>(Arrays.asList("快速呼叫", "下回合结束时失去3层 #y呼叫 。","本回合结束时失去3层 #y呼叫 。"));

//        BaseMod.addKeyword("crnmod", "斩击", new String[] {"斩击"}, "打出后获得1层连击，并丢失2层瞄准。");
//        BaseMod.addKeyword("crnmod", "连击", new String[] {"连击"}, "每层增加10%斩击伤害。");
//        BaseMod.addKeyword("crnmod", "射击", new String[] {"射击"}, "打出后获得1层瞄准，并丢失2层连击。");
//        BaseMod.addKeyword("crnmod", "瞄准", new String[] {"瞄准"}, "每有4层瞄准时，下1张射击牌造成1重暴击，增加100%伤害并减少4层瞄准。");
//        BaseMod.addKeyword("crnmod", "变化", new String[] {"变化"}, "连击更多时为斩击，瞄准更多时为射击。");
//        BaseMod.addKeyword("crnmod", "复合", new String[] {"复合"}, "既是斩击也是射击，不会丢失连击或瞄准。");
//        BaseMod.addKeyword("crnmod", "呼叫", new String[] {"呼叫"}, "每3层呼叫可发现1个支援。");
//        BaseMod.addKeyword("crnmod", "支援", new String[] {"支援"}, "一种提供持续或一次性增益的效果。");
//        BaseMod.addKeyword("crnmod", "求援", new String[] {"求援"}, "支付呼叫获得额外的效果。 NL 由混沌精炼或复制药水打出时可免费触发求援。");
//        BaseMod.addKeyword("crnmod", "突破", new String[] {"突破"}, "未被格挡的伤害。");
//        BaseMod.addKeyword("crnmod", "便乘", new String[] {"便乘"}, "将打出牌的1张复制加入手牌。");
//        BaseMod.addKeyword("crnmod", "弱点", new String[] {"弱点"}, "增加攻击牌和SIYUDI对目标的伤害。 NL 回合结束时减少1层。");
//        BaseMod.addKeyword("crnmod", "事件", new String[] {"事件"}, "拾起时触发效果，然后将此牌从牌组中移除。");
//        BaseMod.addKeyword("crnmod", "药水", new String[] {"药水"}, "攻击:火焰药水 NL 技能:格挡药水 NL 能力:液态记忆 NL 状态:罐装啤酒 NL 诅咒:罐装牛乳 NL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       没有:随机药水");
//        BaseMod.addKeyword("crnmod", "残影", new String[] {"残影"}, "回合开始时格挡不会消失。");
//        BaseMod.addKeyword("crnmod", "暴击", new String[] {"暴击"}, "每有4层瞄准时，下1张射击牌造成1重暴击，增加100%伤害并减少4层瞄准。");
//        BaseMod.addKeyword("crnmod", "种类", new String[] {"种类"}, "稀有或诅咒：抽3张牌。 NL 罕见或状态：抽2张牌。 NL 其他：抽1张牌。");
//        BaseMod.addKeyword("crnmod", "游击", new String[] {"游击"}, "此支援仅在登场时触发1次效果，且呼叫数低于对应层数后移除。");
//        BaseMod.addKeyword("crnmod", "击晕", new String[] {"击晕"}, "被击晕的敌人的行动会被延后。");
//
//        BaseMod.addKeyword("crnmod", "diyusi", new String[] {"diyusi"}, "回合结束时，获得4点格挡。");
//        BaseMod.addKeyword("crnmod", "siyudi", new String[] {"siyudi"}, "回合结束时，对血量最低的敌人造成6点伤害。");
//        BaseMod.addKeyword("crnmod", "mdcn", new String[] {"mdcn"}, "登场时，给予5层群体中毒。");
//        BaseMod.addKeyword("crnmod", "秘鲁", new String[] {"秘鲁"}, "登场时，回复10%的最大生命值。");