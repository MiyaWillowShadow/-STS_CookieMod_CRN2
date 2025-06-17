package CrnMod.father;

import CrnMod.powers.P_Spot;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

import static CrnMod.Helper.P;
import static CrnMod.Helper.addBaseCostWithOutXB;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public abstract class NaiCard extends CustomCard  {
    public static final String str_exhaust =CardCrawlGame.languagePack.getUIString("NaiCard:EXHAUST").TEXT[0];
    public static final String str_exhausts=CardCrawlGame.languagePack.getUIString("NaiCard:EXHAUSTS").TEXT[0];
    public static final String str_ethereal=CardCrawlGame.languagePack.getUIString("NaiCard:ETHEREAL").TEXT[0];
    public static final String str_innate=CardCrawlGame.languagePack.getUIString("NaiCard:INNATE").TEXT[0];
    public static final String str_retain=CardCrawlGame.languagePack.getUIString("NaiCard:RETAIN").TEXT[0];
    public ArrayList<String> strings;   //不能是static 因为子类继承父类static时,最后内存只会有1份
    public int dmgUp,blockUp,magUp, costUp;
    public boolean isEtherealChange=false; //升级时是否改变
    public boolean isInnateChange=false;
    public boolean exhaustChange=false;
    public boolean retainChange=false;
    public int stack=-1; //耐久
    public int stsPosToDescription=-1;

    public NaiCard(String id, ArrayList<String> sts, String img, CardType type, CardColor color, CardRarity rarity, CardTarget target, int dmg,
                   int dmgUp, int block, int blockUp, int mag, int magUp, int cost, int costUp) {
        super(id, sts.get(0), img, cost, sts.get(1), type, color, rarity, target);
        this.strings=sts;
        this.baseDamage = this.damage = dmg;
        this.dmgUp = dmgUp;
        this.baseBlock = this.block = block;
        this.blockUp = blockUp;
        this.baseMagicNumber = this.magicNumber = mag;
        this.magUp = magUp;
        this.costUp = costUp;
    }
    public NaiCard(String id, ArrayList<String> sts, String img, CardType type, CardColor color, CardRarity rarity, CardTarget target, int cost) {
        this(id, sts, img, type,color, rarity, target,0,0,0,0,0,0,cost,0);
    }

    public static void addAction(boolean toBot, AbstractGameAction action) {
        if(toBot){
            AbstractDungeon.actionManager.addToBottom(action);
        }else {
            AbstractDungeon.actionManager.addToTop(action);
        }
    }

    public static void addAction(AbstractGameAction action) {
            addAction(true,action);
    }

    public static void addFunction(boolean toBot, final Lambda func) {
        AbstractGameAction action=new AbstractGameAction() {
            public void update() {
                func.run();
                this.isDone = true;
            }
        };
        if(toBot){
            AbstractDungeon.actionManager.addToBottom(action);
        }else {
            AbstractDungeon.actionManager.addToTop(action);
        }
    }

    public static void addFunction(final Lambda func) {
        addFunction(true,func);
    }

    public static void cardsCheck(boolean drawPile, boolean hand, boolean discardPile, Function<AbstractCard,AbstractCard> func) {
        if(drawPile)for(AbstractCard c: player.drawPile.group){func.apply(c);}
        if(hand)for(AbstractCard c: player.hand.group){func.apply(c);}
        if(discardPile)for(AbstractCard c: player.discardPile.group){func.apply(c);}
    }

    public static int countMonster(){
        int count=0;
        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m.isDeadOrEscaped()){
                count++;
            }
        }
        return count;
    }

    //没有则返回0
    public static int powerAmount(AbstractCreature u, String id){
        for(AbstractPower pw: u.powers){
            if(Objects.equals(pw.ID, id)){
                return pw.amount;
            }
        }
        return 0;
    }

    public static void powerTrigger(AbstractCreature u, String id){
        for(AbstractPower pw: u.powers){
            if(Objects.equals(pw.ID, id)){
                pw.onSpecificTrigger();
            }
        }
    }

    public static void gainPower(AbstractPower pw){
        gainPower(true,pw);
    }

    public static void gainPower(boolean toBot,AbstractPower pw){
        addAction(toBot,new ApplyPowerAction(player, player, pw, pw.amount));
    }

    public static void givePower(AbstractMonster m, AbstractPower pw){
        givePower(true,m,pw);
    }

    public static void givePower(boolean toBot,AbstractMonster m, AbstractPower pw){
        addAction(toBot,new ApplyPowerAction(m, player, pw, pw.amount));
    }

    public static void losePower(String id,int amount){
        losePower(true,id,amount);
    }

    public static void losePower(boolean toBot,String id,int amount){
        addAction(toBot,new ReducePowerAction(player, player, id,amount));
    }

    public static void reducePower(AbstractMonster m,String id,int amount){
        reducePower(true,m,id,amount);
    }

    public static void reducePower(boolean toBot,AbstractMonster m,String id,int amount){
        addAction(toBot,new ReducePowerAction(m, player, id,amount));
    }

    public static void removePower(AbstractCreature target,String id){
        removePower(true,target,id);
    }

    public static void removePower(boolean toBot,AbstractCreature target,String id){
        addAction(toBot,new RemoveSpecificPowerAction(target, player, id));
    }

    public static float dmgFixEnemy1(float d,AbstractMonster m,AbstractCard c){
        d += powerAmount(m, P_Spot.PID);
        AbstractPower pw;
        for(Iterator<AbstractPower> var9 = m.powers.iterator(); var9.hasNext(); d = pw.atDamageReceive(d, c.damageTypeForTurn, c)) {
            pw = var9.next();
        }
        return d;
    }

    public static float dmgFixEnemy2(float d,AbstractMonster m,AbstractCard c){
        AbstractPower pw;
        for(Iterator<AbstractPower> var9 = m.powers.iterator(); var9.hasNext(); d = pw.atDamageFinalReceive(d, c.damageTypeForTurn, c)) {
            pw = var9.next();
        }
        return d;
    }

    public static float dmgFixSelf1(float d,AbstractCard c){
        for (AbstractRelic r : P.relics) {
            d = r.atDamageModify(d, c);
        }
        AbstractPower pw;
        for(Iterator<AbstractPower> var9 = P.powers.iterator(); var9.hasNext(); d = pw.atDamageGive(d, c.damageTypeForTurn, c)) {
            pw = var9.next();
        }
        d = P.stance.atDamageGive(d, c.damageTypeForTurn, c);

        return d;
    }

    public static float dmgFixSelf2(float d,AbstractCard c){
        AbstractPower pw;
        for(Iterator<AbstractPower> var9 = P.powers.iterator(); var9.hasNext(); d = pw.atDamageFinalGive(d, c.damageTypeForTurn, c)) {
            pw = var9.next();
        }
        return d;
    }

    public static void calculateCardDamage2(AbstractMonster m, AbstractCard c) {
        //c.applyPowersToBlock();
        NaiCard naiCard = null;
        if(c instanceof NaiCard){
            naiCard= (NaiCard) c;
        }
        float d = (float) c.baseDamage;
        if(naiCard!=null)d= naiCard.dmgFixSelf0(d);
        d=dmgFixSelf1(d,c);
        if(!(m ==null)) {
            if(naiCard!=null)d= naiCard.dmgFixEnemy0(d,m);
            d=dmgFixEnemy1(d,m,c);
            d=dmgFixSelf2(d,c);
            d=dmgFixEnemy2(d,m,c);
        }
        d = d < 0.0F ? 0:d;
        c.damage = MathUtils.floor(d);
        c.isDamageModified = (c.baseDamage != c.damage);
    }

    public static void newCardInBattle(AbstractCard card){
        if (card.type != CardType.CURSE && card.type != CardType.STATUS && player.hasPower("MasterRealityPower")) {
            card.upgrade();
        }
        if (P.hasPower("Corruption") && card.type == CardType.SKILL) {
            card.setCostForTurn(-9);
        }
    }

    public abstract void upgrade2();

    public void upgrade() {
        if (!this.upgraded) {
            if(dmgUp !=0) {this.upgradeDamage(dmgUp);}
            if(blockUp!=0) {this.upgradeBlock(blockUp);}
            if(magUp!=0) {this.upgradeMagicNumber(magUp);}
            if(costUp !=0)    {addBaseCostWithOutXB(this,costUp);}
            upgrade2();
            upgradeName();
            isEthereal= (isEtherealChange != isEthereal);
            isInnate= (isInnateChange != isInnate);
            exhaust = (exhaustChange != exhaust);
            selfRetain= (retainChange != selfRetain);
            desFix();
            initializeDescription();
        }
    }

    public abstract void use2(AbstractPlayer p, AbstractMonster m) ;

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m==null || m.isDeadOrEscaped()){
            m =  AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        }
        use2(p,m);
    }

    public void desFix(int stsPos){
        String stringStack="";
        if(stack==1 ){ //必须用== 否则默认值-1也会导致卡牌变为消耗
            exhaust =true;
        }
        else if(stack>1){
            exhaust =false;
            stringStack=" "+ str_exhausts+" "+stack;
        }
        if(exhaust){
            stringStack=" "+ str_exhaust +" ";
        }
//        String s=useUpgradeDes?strings.get(2):strings.get(1);
        String s=strings.get(stsPos);
        if(isEthereal){
            s=s+" NL "+str_ethereal+" ";
            if(exhaust ||stack>1){
                s=s+","+stringStack;
            }
        }else if(exhaust ||stack>1){
            s=s+" NL "+stringStack;
        }
        if(isInnate){
            if(selfRetain){
                s=" "+str_innate+" , "+str_retain+" NL "+s;
            }else{
                s=" "+str_innate+" NL "+s;
            }
        }else{
            if(selfRetain){
                s=" "+str_retain+ " NL "+s;
            }
        }
        rawDescription = desFix2(s);
        initializeDescription();
    }

    public abstract String desFix2(String s);

    public void desFix(){
        if(stsPosToDescription>0){
            desFix(stsPosToDescription);
        }
        else if(upgraded && strings.size()>2 && !Objects.equals(strings.get(2), "")){
            desFix(2);
        }else{
            desFix(1);
        }
    }

    public AbstractCard makeStatEquivalentCopy() {
        NaiCard card = (NaiCard) this.makeCopy();

        for(int i = 0; i < this.timesUpgraded; ++i) {
            card.upgrade();
        }

        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.freeToPlayOnce = this.freeToPlayOnce;

        card.tags=this.tags;
        card.stack=this.stack;
        desFix();
        return card;
    }

    public void stackUse(){
        stack--;
        desFix();
    }

    public void attackRandom(int times,AbstractGameAction.AttackEffect e){
        for(int i=0;i<times;i++) {
            addAction(new AttackDamageRandomEnemyAction(this, e));
        }
    }

    public void attack(AbstractMonster m , int times,AbstractGameAction.AttackEffect e){
        for(int i=0;i<times;i++){
            addAction(new DamageAction(m, new DamageInfo(player, this.damage, this.damageTypeForTurn), e));
        }
    }

    public void attack(AbstractMonster m , AbstractGameAction.AttackEffect e){
        attack(m,1,e);
    }

    public void attackAoe(AbstractGameAction.AttackEffect e){
        this.addToBot(new DamageAllEnemiesAction(P, this.multiDamage, this.damageTypeForTurn, e));
//        applyPowersToBlock();
//        ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
//        float[] dmgs = new float[monsters.size()];
//        this.multiDamage = new int[dmgs.length];
//        float d = (float) baseDamage;
//        d=dmgFixSelf0(d);
//        d=dmgFixSelf1(d,this);
//
//        int i;
//        for(i = 0; i < dmgs.length; ++i) {
//            if (!monsters.get(i).isDying && !monsters.get(i).isEscaping) {
//                dmgs[i]= dmgFixEnemy0(d,monsters.get(i));
//                dmgs[i]= dmgFixEnemy1(dmgs[i],monsters.get(i),this);
//            }
//        }
//        for(i = 0; i < dmgs.length; ++i) {
//            if (!monsters.get(i).isDying && !monsters.get(i).isEscaping) {
//                dmgs[i]=dmgFixSelf2(dmgs[i],this);
//                dmgs[i]= dmgFixEnemy2(dmgs[i],monsters.get(i),this);
//                multiDamage[i] = MathUtils.floor(dmgs[i]);
//            }
//        }
//        damage = this.multiDamage[0];
//        addAction(new DamageAllEnemiesAction(player, multiDamage, this.damageTypeForTurn, e));
    }

    public void attackAoe(int times, AbstractGameAction.AttackEffect e){
        for(int i=0;i<times;i++){
            attackAoe(e);
        }
    }

    public void draw(int amount){
        addAction(new DrawCardAction(player,amount));
    }

    public void gainEn(int amount){
        addAction(new GainEnergyAction(amount));
    }

    public void gainBlock(int amount){
        addAction(new GainBlockAction(player, player, amount));
    }

    public float dmgFixEnemy0(float d, AbstractMonster m){
        return d;
    }

    public float dmgFixSelf0(float d){
        return d;
    }

    public void calculateCardDamagePreFix(AbstractMonster m,int newBaseDamage){
        if(newBaseDamage!=baseDamage){
            int baseDamageSave=baseDamage;
            baseDamage=newBaseDamage;
            super.calculateCardDamage(m);
            isDamageModified=true;
            baseDamage=baseDamageSave;
        }else{
            super.calculateCardDamage(m);
        }
    }

    public void applyPowersPreFix(int newBaseDamage){
        if(newBaseDamage!=baseDamage){
            int baseDamageSave=baseDamage;
            baseDamage=newBaseDamage;
            super.applyPowers();
            isDamageModified=true;
            baseDamage=baseDamageSave;
        }else{
            super.applyPowers();
        }
    }

    public interface Lambda extends Runnable {
    }
}
