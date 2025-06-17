package CrnMod.father;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public abstract class NaiPower extends AbstractPower {
    public ArrayList<String> strings;
    public ArrayList<PTags> tags;
    public ArrayList<String> ladyList=new ArrayList<>();

    public NaiPower(AbstractCreature owner, int amount, String ID, ArrayList<String> strings, String img, AbstractPower.PowerType type) {
        this.owner=owner;
        this.amount=amount;
        this.ID=ID;
        this.strings = strings;
        this.img = new Texture(img);
        this.name=strings.get(0);
        this.description=strings.get(1);
        this.type = type;
        this.tags = new ArrayList<>();
        this.updateDescription();
    }

    public NaiPower(AbstractCreature owner, int amount, String ID, ArrayList<String> strings, String img) {
        this(owner,amount,ID,strings,img,PowerType.BUFF); //默认增益效果
    }

    //make sure the leader will be triggered first
    public static void powerOrder(AbstractCreature u,String leader,String follow){
        int i=0,l=-1,f=-1;
        for(AbstractPower pw: u.powers){
            if(Objects.equals(pw.ID, leader)){
                l=i;
            }else if(Objects.equals(pw.ID, follow)){
                f=i;
            }
            i++;
        }
        if(l>=0 && f>=0 && l>f){
            Collections.swap(u.powers, l, f);
        }
    }

    public boolean hasTag(PTags tagToCheck) {
        return tags.contains(tagToCheck);
    }

    public enum PTags {
        SUP,
        OneTimeSUP;

        PTags() {
        }
    }
}