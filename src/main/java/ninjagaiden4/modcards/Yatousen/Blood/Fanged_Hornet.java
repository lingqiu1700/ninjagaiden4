package ninjagaiden4.modcards.Yatousen.Blood;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Fanged_Hornet extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Fanged_Hornet");

    public Fanged_Hornet() {
        super(
                ID,
                "Fanged_Hornet",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                18,
                1,
                0.09F,
                -13,
                6
        );
        this.tags.add(WeaponTags.WEAPON_YATOUSEN);
        this.tags.add(WeaponTags.BLOOD_F);
        this.tags.add(WeaponTags.X);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.coreAction(p,m,this));
        if (Math.random()<0.7) {
            addToBot(new StunMonsterAction(m,p));
        }
    }
}
